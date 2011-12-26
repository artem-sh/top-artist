package sh.topartist.rating.vkontakteru

import net.liftweb.json._
import sh.topartist.util.JsonUtil._
import sh.topartist.rating.{RatingParserException, RatingParser}


object VkontakteRuRatingParser extends RatingParser {
  override def parseRating(audioSearchResponse: String): VkontakteRuRating = {
    try {
      val tracksCountJson = (parse(audioSearchResponse) \ "response")(0)
      val tracksCount = extractIntValue(tracksCountJson)
      require(tracksCount > 0)
      
      VkontakteRuRating(tracksCount)
    } catch {
      case e: Exception => throw new RatingParserException(e)
    }
  }
}