package app.rating.vkontakteru

import dispatch.{Http, url}
import sh.app.topartist.rating.RatingParserException
import sh.app.topartist.rating.vkontakteru.VkontakteRuRating
import sh.app.topartist.Config

class VkontakteRuAuthenticator(context: {
  val httpClient: Http
  val config: Config
}) {

  def authenticate(code: String) {
    val request =
      url("https://oauth.vk.com/access_token") <<? List(
        ("client_id", context.config.property("vk.app.id")),
        ("client_secret", context.config.property("vk.app.secret")),
        ("redirect_uri", context.config.property("vk.app.secret")),
        ("code", code))
    try {
      val str = context.httpClient(request as_str)
      println(str)
    } catch {
      case e: RatingParserException => VkontakteRuRating.Unknown
    }
  }
}