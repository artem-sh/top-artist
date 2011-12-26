package sh.topartist.rating.vkontakteru

import net.liftweb.json._
import sh.topartist.util.JsonUtil._


object VkontakteRuRatingParser {
  def parseRating(audioSearchJsonResponse: JValue): VkontakteRuRating = {
    val jValue = (audioSearchJsonResponse \ "response")(0)
    VkontakteRuRating(extractIntValue(jValue))
  }
}