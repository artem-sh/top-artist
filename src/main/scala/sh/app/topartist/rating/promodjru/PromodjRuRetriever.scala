package sh.topartist.rating.promodjru

import org.jsoup.Jsoup
import dispatch._
import sh.topartist.rating.{RatingParserException, RatingRetriever}
import sh.topartist.rating.promodjru.PromodjRuRatingParser._


class PromodjRuRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http

  override def retrieveRating(artistName: String): PromodjRuRating = {
    try {
      findDjPageUrl(artistName) match {
        case Some(djPageUrl) => parseRating(httpClient(url(djPageUrl) as_str))
        case None => PromodjRuRating.Unknown
      }
    } catch {
      case e: RatingParserException => PromodjRuRating.Unknown
    }
  }

  private def findDjPageUrl(artistName: String): Option[String] = {
    val request = url("http://promodj.ru/search/?mode=user&sortby=rating&searchfor=" + Request.encode_%(artistName))
    parseDjUrl(artistName, Jsoup.parse(httpClient(request as_str)))
  }
}