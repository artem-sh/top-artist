package sh.topartist.util

import net.liftweb.json._


object JsonUtil {
  def extractStringValue(json: JValue): String = {
    implicit val formats = Serialization.formats(NoTypeHints)
    json.extract[String]
  }
}