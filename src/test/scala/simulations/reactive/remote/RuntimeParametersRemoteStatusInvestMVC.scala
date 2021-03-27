package scala.simulations.reactive.remote

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersRemoteStatusInvestMVC extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def userCount: Int = getProperty("USERS", "500").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt
  def testDuration: Int = getProperty("DURATION3", "60").toInt

  before {
    println(s"Running test with ${userCount} fixed users")
  }

  val httpConf = http.baseUrl("http://localhost:8080/statusInvest")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    feed(csvFeeder)
      .exec(http("Get Status Invest stock: ${ticker}")
        .get("/stocks/generate/${ticker}")
        .check(status.is(200)))
      .pause(1 second)
  }

  val scn = scenario("Generate stock")
    .forever() {
      exec(getSpecificStockTicker())
    }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount) during (rampDuration seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration seconds)

}

