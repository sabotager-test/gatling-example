package org.gatling.helpers

import scala.util.Random

object Randoms {

  private val side: List[String] = List("HOME", "DRAW", "AWAY")

  def shuffleList(list: List[String] = side): String = Random.shuffle(list).head
}
