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


  def userConstantCount: Int = getProperty("USERS", "50").toInt

  def constantRamp: Int = getProperty("CONSTANT_RAMP_DURATION", "60").toInt

  def testDuration: Int = getProperty("DURATION", "5").toInt

  before {
    println(s"Running test with ${userConstantCount} fixed users")
    println(s"Ramping users over ${constantRamp} seconds")
    println(s"Total test duration: ${testDuration} seconds")
  }

  val httpConf = http.baseUrl("http://localhost:8080/stocks/reactive")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    feed(csvFeeder)
      .exec(http("Get Yahoo stock: ${ticker}")
        .get("/yahoo/${ticker}")
        .check(status.is(200)))
      .pause(1 minute)
  }

  val scn = scenario("Get Yahoo stock")
    .exec(getSpecificStockTicker())

  setUp(
    scn.inject(
      nothingFor(30 seconds),
      constantUsersPerSec(userConstantCount) during (constantRamp seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration minutes)
}

