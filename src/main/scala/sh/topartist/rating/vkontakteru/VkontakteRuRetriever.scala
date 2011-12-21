package sh.topartist.rating.vkontakteru

import sh.topartist.util.raisin.Disposable
import dispatch._
import net.liftweb.json._
import sh.topartist.rating.RatingRetriever


class VkontakteRuRetriever extends RatingRetriever with Disposable {
  val httpHandler = new Http

  override def retrieveRating(artistName: String): VkontakteRuRating = {
    val request = url("https://api.vkontakte.ru/method/audio.search?count=1&access_token=39815a1a3992f5ec3992f5ecba39bb6f21339923993f5e4018a97ef4241aabe&q=" + Request.encode_%(artistName))
    VkontakteRuParser.parseRating(parse(httpHandler(request as_str)))
  }

  override def dispose() {
    httpHandler.shutdown()
  }
}