package scala.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParametersGetStocksReactive extends Simulation {

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }


  def userConstantCount: Int = getProperty("USERS", "400").toInt

  def constantRamp: Int = getProperty("CONSTANT_RAMP_DURATION", "30").toInt

  def testDuration: Int = getProperty("DURATION", "2").toInt

  before {
    println(s"Running test with ${userConstantCount} fixed users")
    println(s"Ramping users over ${constantRamp} seconds")
    println(s"Total test duration: ${testDuration} seconds")
  }

  val httpConf = http.baseUrl("http://localhost:8080/reactive")
    .header("Accept", "application/json")
  val csvFeeder = csv("data/yahooCsvFile.csv").circular

  def getSpecificStockTicker() = {
    feed(csvFeeder)
      .exec(http("Get Yahoo stock: ${ticker}")
        .get("/quotes/${ticker}")
        .check(status.is(200)))
      .pause(1 second, 2 second)
  }

  val scn = scenario("Get Yahoo stock")
    .exec(getSpecificStockTicker())

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      constantUsersPerSec(userConstantCount) during (constantRamp seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration minutes)

}
