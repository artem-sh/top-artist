package sh.app.topartist.util

import net.liftweb.json._


object JsonUtil {
  implicit val formats = Serialization.formats(NoTypeHints)

  def extractStringValue(json: JValue): String = {
    json.extract[String]
  }

  def extractIntValue(json: JValue): Int = {
    extractStringValue(json).toInt
  }
}