package scala.simulations.reactive

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GetStocksYahooReactiveLoadScenario extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/stocks/reactive")
    .header("Accept", "application/json")

  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    repeat(10) {
      feed(csvFeeder)
        .exec(http("Get Yahoo stock: ${ticker}")
          .get("/yahoo/${ticker}")
          .check(status.is(200)))
    }
  }

  val scn = scenario("Fixed Duration Load Simulation")
    .forever() {
      exec(getSpecificStockTicker())
    }

  // ** Fixed duration Load Simulation **
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1)
    ).protocols(httpConf)
  ).maxDuration(5 minute)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )


}
