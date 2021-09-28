package org.gatling.helpers

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Requests {

  def openMainPage(): ChainBuilder = {
    exec(
      http("Open main page")
        .get("/")
        .check(status.is(200))
    )
  }

  def goToFootball(): ChainBuilder = {
    exec(
      http("Select football page")
        .get("/#/soccer/daily")
        .check(status.is(200))
    )
  }

  def extractCompetitions(date: String = Dates.currentDate(), sport: String = "SOCCER"): ChainBuilder = {
    exec(
      http("Get and extract competitions on actual page")
        .get(ConfigReader.getAPIBase + "/getCompetitionsForDay")
        .queryParam("sport", sport)
        .queryParam("date", date)
        .queryParam("count", 6)
        .queryParam("utcOffset", 1)
        .queryParam("locale", "en-gb")
        .queryParam("channelId", 6)
        .queryParam("siteId", 32768)
        .check(status.is(200))
        .check(jsonPath("$..event[*].id").findAll.saveAs("events"))
        .check(jsonPath("$..event[*].name").findAll.saveAs("participants"))
    )
  }

  def buildRandomBet(): ChainBuilder = {
    exec(session => {
      // map id to players
      val mapping: Map[String, String] = (session("events").as[Vector[String]] zip session("participants").as[Vector[String]]).toMap

      // get random event
      val key = Randoms.shuffleList(mapping.keys.toList)

      // what bet
      val bet = Matchers.matchBet(Randoms.shuffleList())

      // which player
      val player = Matchers.matchPlayer(bet, mapping.getOrElse(key, "None"))

      session.set("selectedEvent", key).set("selectedBet", bet).set("player", player)
    })
  }

  def selectRandomBet(): ChainBuilder = {
    exec(
      http("Place random bet on available selections")
        .get(ConfigReader.getAPIBase + "/getUpSellSuggestion")
        .queryParam("eventId", "${selectedEvent}")
        .queryParam("selectionName", "${player}")
        .queryParam("selectionType", "${selectedBet}")
        .queryParam("marketType", "SOCCER:FT:AXB")
        .queryParam("locale", "en-gb")
        .queryParam("channelId", 6)
        .queryParam("siteId", 32768)
        .check(status.is(200))
    )
  }
}
