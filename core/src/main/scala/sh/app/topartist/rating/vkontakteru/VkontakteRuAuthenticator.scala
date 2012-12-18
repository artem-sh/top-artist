package app.rating.vkontakteru

import dispatch.{Http, url}
import sh.app.topartist.rating.RatingParserException
import sh.app.topartist.rating.vkontakteru.{VkontakteRuRating, VkontakteRuRatingParser}

class VkontakteRuAuthenticator(cfg: {val httpClient: Http}) {

  def authenticate(code: String) {
    val request =
      url("https://oauth.vk.com/access_token") <<? List(("client_id", "b"))
    try {
      VkontakteRuRatingParser.parseRating(cfg.httpClient(request as_str))
    } catch {
      case e: RatingParserException => VkontakteRuRating.Unknown
    }

  }
}