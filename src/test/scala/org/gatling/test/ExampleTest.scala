package org.gatling.test

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import org.gatling.helpers.{BaseTest, ConfigReader, Requests}

import scala.concurrent.duration._
import scala.language.postfixOps

class ExampleTest extends BaseTest {

  val scn: ScenarioBuilder = scenario("Add random bet to bet slip")
    .exec(
      Requests.openMainPage(),
      Requests.goToFootball(),
      Requests.extractCompetitions(),
      Requests.buildRandomBet(),
      Requests.selectRandomBet()
    )

  setUp(
    scn.inject(
      rampUsers(ConfigReader.getUsers) during (ConfigReader.getRampUp second)
    )
  ).protocols(httpConf)
}
