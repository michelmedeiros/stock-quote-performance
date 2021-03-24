package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersYahooReactive extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def userCount: Int = getProperty("USERS", "50").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt
  def testDuration: Int = getProperty("DURATION", "1").toInt

  before {
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Total test duration: ${testDuration} minutes")
  }

  val httpConf = http.baseUrl("http://localhost:8080/reactive")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    feed(csvFeeder)
      .exec(http("Get Yahoo stock: ${ticker}")
        .get("/quotes/${ticker}")
        .check(status.is(200)))
      .pause(1)
  }

  val scn = scenario("Get all video games")
    .forever() {
      exec(getSpecificStockTicker())
    }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount) during (rampDuration second)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration minutes)

}
