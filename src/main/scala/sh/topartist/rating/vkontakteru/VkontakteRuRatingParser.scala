package sh.topartist.rating.vkontakteru

import net.liftweb.json._
import sh.topartist.util.JsonUtil._
import sh.topartist.rating.RatingParser


object VkontakteRuRatingParser extends RatingParser {
  override def parseRating(audioSearchResponse: String): VkontakteRuRating = {
    val jValue = (parse(audioSearchResponse) \ "response")(0)
    VkontakteRuRating(extractIntValue(jValue))
  }
}