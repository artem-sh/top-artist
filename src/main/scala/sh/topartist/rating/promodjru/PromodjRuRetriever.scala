package sh.topartist.rating.promodjru

import org.jsoup.Jsoup
import sh.topartist.util.raisin.Disposable
import dispatch._
import sh.topartist.rating.{Rating, RatingRetriever}


class PromodjRuRetriever extends RatingRetriever with Disposable {
  val httpHandler = new Http

  def findDjPageUrl(artistName: String): Option[String] = {
    val request = url("http://promodj.ru/search/?mode=user&sortby=rating&searchfor=" + artistName)
    PromodjRuParser.parseDjUrl(artistName, Jsoup.parse(httpHandler(request as_str)))
  }

  override  def retrieveRating(artistName: String): Rating = {
    findDjPageUrl(artistName) match {
      case Some(djPageUrl) => {
        val request = url(djPageUrl)
        PromodjRuParser.parseDjPromoRank(Jsoup.parse(httpHandler(request as_str))) match {
          case Some(r) => PromodjRuRating(r)
          case None => PromodjRuRating.Unknown
        }
      }
      case None => PromodjRuRating.Unknown
    }
  }

  override def dispose() {
    httpHandler.shutdown()
  }
}
