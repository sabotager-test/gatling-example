package org.gatling.helpers

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

object ConfigReader extends LazyLogging {

  private val config: Config = ConfigFactory.load()
  private val env: String = config.getString("settings.env")

  logger.info(s"-> using: $env")

  def getEnv: String = env
  def getBaseHttp: String = config.getString(s"settings.envs.$env.baseHttp")
  def getAPIBase: String = config.getString(s"settings.envs.$env.apiBase")
  def getUsers: Int = config.getInt(s"settings.envs.$env.users")
  def getRampUp: Int = config.getInt(s"settings.envs.$env.rampUp")
}
