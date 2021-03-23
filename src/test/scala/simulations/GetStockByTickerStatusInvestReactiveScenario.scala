package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class GetStockByTickerStatusInvestReactiveScenario extends Simulation {

  // 1 Http Conf
  val httpConf = http.baseUrl("http://localhost:8080/stocks")
    .header("Accept", "application/json")

  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    repeat(200) {
      feed(csvFeeder)
        .exec(http("Get Status Invest stock: ${ticker}")
          .get("/statusInvest/${ticker}")
          .check(status.is(200)))
    }
  }

  val scn = scenario("Fixed Duration Load Simulation")
    .forever() {
      exec(getSpecificStockTicker())
    }

  // 3 Load Scenario
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