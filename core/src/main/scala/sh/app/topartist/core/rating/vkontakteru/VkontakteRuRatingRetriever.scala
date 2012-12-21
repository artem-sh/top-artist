package sh.app.topartist.core.rating.vkontakteru

import dispatch._
import sh.app.topartist.core.rating.{SessionData, RatingParserException, RatingRetriever}


class VkontakteRuRatingRetriever(cfg: {val httpClient: Http}) extends RatingRetriever {
  override def retrieveRating(artistName: String, sessionData: SessionData): VkontakteRuRating = {
    val request = url("https://api.vk.com/method/audio.search") <<? List(
      ("count", "1"),
      ("access_token", sessionData.vkontakteRuAccessToken.get),
      ("q", Request.encode_%(artistName)))
    try {
      VkontakteRuRatingParser.parseRating(cfg.httpClient(request as_str))
    } catch {
      case e: RatingParserException => VkontakteRuRating.Unknown
    }
  }
}