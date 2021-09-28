package org.gatling.helpers

object Matchers {

  def matchBet(bet: String): String = {
    bet match {
      case "HOME" => "A"
      case "DRAW" => "Draw"
      case "AWAY" => "B"
    }
  }

  def matchPlayer(bet: String, players: String): String = {

    bet match {
      case "A" => players.split("v")(0).trim
      case "Draw" => "Draw"
      case "B" => players.split("v")(1).trim
    }
  }
}
