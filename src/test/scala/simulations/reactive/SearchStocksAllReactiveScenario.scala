package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class SearchStocksAllReactiveScenario extends Simulation {

  // 1 Http Conf
  val httpConf = http.baseUrl("http://localhost:8080/stocks/reactive")
    .header("Accept", "application/json")

  // 2 Scenarios Definition
  def getAllStocks() = {
    exec(
      http("Get all stocks")
        .get("/search")
        .check(status.is(200))
    ).exec { session => println(session); session }
  }

  val scn = scenario("Fixed Duration Load Simulation")
    .forever() {
      exec(getAllStocks())
    }

  // 3 Load Scenario - Fixed duration Load Simulation **
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1)
    ).protocols(httpConf)
  ).maxDuration(1 minute)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )

}
