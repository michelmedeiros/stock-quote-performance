package scala.simulations.reactive.remote

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersRemoteStatusInvestStocksWebClient extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def userCount: Int = getProperty("USERS", "10").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "30").toInt
  def testDuration: Int = getProperty("DURATION", "120").toInt
  def userConstantCount: Int = getProperty("USERS", "1").toInt
  def constantRamp: Int = getProperty("CONSTANT_RAMP_DURATION", "30").toInt


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
      rampUsers(userCount) during (rampDuration seconds),
      constantUsersPerSec(userConstantCount) during (constantRamp seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration seconds)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )
}

