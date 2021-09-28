package org.gatling.helpers

import com.typesafe.scalalogging.LazyLogging

import java.text.SimpleDateFormat
import java.util.Calendar

object Dates extends LazyLogging {

  def currentDate(): String = {

    val format = new SimpleDateFormat("yyyy-MM-dd")
    val date = format.format(Calendar.getInstance().getTime)

    logger.info(s"-> current date: $date")

    date
  }
}
