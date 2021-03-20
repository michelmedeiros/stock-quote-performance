package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GetStocksReactiveScenario extends Simulation {

  // 1 Http Conf
  val httpConf = http.baseUrl("http://localhost:8080/stocks/reactive")
    .header("Accept", "application/json")

  // 2 Scenarios Definition
  def getAllStocks() = {
    exec(
      http("Get all stocks")
        .get("/search")
        .check(status.is(200))
        .check(jsonPath("$[0].ticket").saveAs("ticket"))
    ).exec { session => println(session); session }
  }

  def getSpecificStock() = {
    exec(
      http("Get Specific Stock")
        .get("/search/${ticket}")
        .check(status.is(200))
    )
  }

  val scn = scenario("Fixed Duration Load Simulation")
    .forever() {
      exec(getAllStocks())
        .pause(5)
        .exec(getSpecificStock())
    }

  // 3 Load Scenario - Fixed duration Load Simulation **
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1),
      rampUsers(10) during (30 second)
    ).protocols(httpConf)
  ).maxDuration(1 minute)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )

}
