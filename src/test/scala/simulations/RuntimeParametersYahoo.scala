package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersYahoo extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }


  def userCount: Int = getProperty("USERS", "3000").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "30").toInt
  def testDuration: Int = getProperty("DURATION", "1").toInt

  before {
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Total test duration: ${testDuration} seconds")
  }

  val httpConf = http.baseUrl("http://localhost:8081/stocks")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    repeat(10) {
      feed(csvFeeder)
        .exec(http("Get Yahoo stock: ${ticker}")
          .get("/search/${ticker}")
          .check(status.is(200)))
        .pause(1)
    }
  }

  val scn = scenario("Get Yahoo stock")
    .exec(getSpecificStockTicker())

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount) during (rampDuration second)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration minutes)

}
