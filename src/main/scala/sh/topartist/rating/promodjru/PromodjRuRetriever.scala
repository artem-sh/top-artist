package sh.topartist.rating.promodjru

import org.jsoup.Jsoup
import dispatch._
import sh.topartist.rating.RatingRetriever


class PromodjRuRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http

  override def retrieveRating(artistName: String): PromodjRuRating = {
    findDjPageUrl(artistName) match {
      case Some(djPageUrl) => {
        PromodjRuRatingParser.parseRating(httpClient(url(djPageUrl) as_str))
      }
      case None => PromodjRuRating.Unknown
    }
  }

  private def findDjPageUrl(artistName: String): Option[String] = {
    val request = url("http://promodj.ru/search/?mode=user&sortby=rating&searchfor=" + Request.encode_%(artistName))
    PromodjRuRatingParser.parseDjUrl(artistName, Jsoup.parse(httpClient(request as_str)))
  }
}

