package scala.simulations.nonreactive

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersStatusInvestStatistics extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def userCount: Int = getProperty("USERS", "3000").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "30").toInt
  def testDuration: Int = getProperty("DURATION", "120").toInt
  def userConstantCount: Int = getProperty("USERS", "1").toInt
  def constantRamp: Int = getProperty("CONSTANT_RAMP_DURATION", "10").toInt


  before {
    println(s"Running test with ${userCount} fixed users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Total test duration: ${testDuration} seconds")
  }

  val httpConf = http.baseUrl("http://localhost:8081/statistics")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    feed(csvFeeder)
      .exec(http("Get Status Invest stock: ${ticker}")
        .get("/statusInvest/${ticker}")
        .check(status.is(200)))
      .pause(1)
  }

  val scn = scenario("Get Status Invest statistics stock")
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
}

