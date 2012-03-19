package sh.app.topartist.rating.vkontakteru

import dispatch._
import sh.app.topartist.rating.{RatingParserException, RatingParser, RatingRetriever}


class VkontakteRuRatingRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http

  override def retrieveRating(artistName: String): VkontakteRuRating = {
    val request =
      url("https://api.vkontakte.ru/method/audio.search?count=1&access_token=39815a1a3992f5ec3992f5ecba39bb6f21339923993f5e4018a97ef4241aabe&q="
        + Request.encode_%(artistName))
    try {
      VkontakteRuRatingParser.parseRating(httpClient(request as_str))
    } catch {
      case e: RatingParserException => VkontakteRuRating.Unknown
    }
  }
}