package scala.examples

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadSimulationDesign extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def getSpecificGame() = {
    exec(
      http("Get Specific Game")
        .get("videogames/2")
        .check(status.is(200))
    )
  }

  // ** NORMAL SCENARIO **
//  val scn = scenario("Basic Load Simulation")
//    .exec(getAllVideoGames())
//    .pause(5)
//    .exec(getSpecificGame())
//    .pause(5)
//    .exec(getAllVideoGames())

  // ** SCENARIO THAT LOOPS FOREVER **
  val scn = scenario("Fixed Duration Load Simulation")
    .forever() {
      exec(getAllVideoGames())
        .pause(5)
        .exec(getSpecificGame())
        .pause(5)
        .exec(getAllVideoGames())
    }

  // ** Basic Load Simulation **

//  setUp(
//    scn.inject(
//      nothingFor(5 seconds),
//      atOnceUsers(5),
//      rampUsers(10) during (10 seconds)
//    ).protocols(httpConf)
//  )

  // ** Ramp Users Load Simulation **

//  setUp(
//    scn.inject(
//      nothingFor(5 seconds),
//      constantUsersPerSec(10) during (10 seconds),
//      rampUsersPerSec(1) to (5) during (20 seconds)
//    ).protocols(httpConf)
//  )

  // ** Fixed duration Load Simulation **
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(10),
      rampUsers(50) during (30 second)
    ).protocols(httpConf)
  ).maxDuration(1 minute)
    .assertions(
      global.responseTime.max.lt(100),
      global.successfulRequests.percent.gt(95)
    )
}
