package sh.topartist.rating.promodjru

import org.jsoup.Jsoup
import dispatch._
import sh.topartist.rating.RatingRetriever


class PromodjRuRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http

  override def retrieveRating(artistName: String): PromodjRuRating = {
    findDjPageUrl(artistName) match {
      case Some(djPageUrl) => {
        val request = url(djPageUrl)
        PromodjRuParser.parseDjPromoRank(Jsoup.parse(httpClient(request as_str))) match {
          case Some(r) => PromodjRuRating(r)
          case None => PromodjRuRating.Unknown
        }
      }
      case None => PromodjRuRating.Unknown
    }
  }

  private def findDjPageUrl(artistName: String): Option[String] = {
    val request = url("http://promodj.ru/search/?mode=user&sortby=rating&searchfor=" + Request.encode_%(artistName))
    PromodjRuParser.parseDjUrl(artistName, Jsoup.parse(httpClient(request as_str)))
  }
}

