package scala.simulations.reactive.remote

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersRemoteStatusInvestStatisticsMVC extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def userCount: Int = getProperty("USERS", "10").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "1").toInt
  def testDuration: Int = getProperty("DURATION3", "60").toInt

  before {
    println(s"Running test with ${userCount} fixed users")
  }

  val httpConf = http.baseUrl("http://localhost:8080/statusInvest")
    .header("Accept", "application/json")

  def getAllStatistics() = {
    exec(
      http("Get all statistics")
        .get("/statistics/generate")
        .check(status.is(200))
    )
  }

  val scn = scenario("Generate stock")
    .forever() {
      exec(getAllStatistics())
    }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount) during (rampDuration seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration seconds)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )
}

