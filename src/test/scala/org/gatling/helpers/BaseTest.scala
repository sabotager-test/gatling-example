package org.gatling.helpers

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class BaseTest extends Simulation {

  val httpConf: HttpProtocolBuilder = http.baseUrl(ConfigReader.getBaseHttp)
    .header("Accept", "application/json")
}
