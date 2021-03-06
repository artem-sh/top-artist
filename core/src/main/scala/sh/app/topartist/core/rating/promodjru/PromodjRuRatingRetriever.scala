package sh.app.topartist.core.rating.promodjru

import org.jsoup.Jsoup
import dispatch._
import sh.app.topartist.core.rating.{SessionData, RatingParserException, RatingRetriever}
import sh.app.topartist.core.rating.promodjru.PromodjRuRatingParser._


class PromodjRuRatingRetriever(cfg: {val httpClient: Http}) extends RatingRetriever {
  override def retrieveRating(artistName: String, sessionData: SessionData): PromodjRuRating = {
    try {
      findDjPageUrl(artistName) match {
        case Some(djPageUrl) => parseRating(cfg.httpClient(url(djPageUrl) as_str))
        case None => PromodjRuRating.Unknown
      }
    } catch {
      case e: RatingParserException => PromodjRuRating.Unknown
    }
  }

  private def findDjPageUrl(artistName: String): Option[String] = {
    val request = url("http://promodj.ru/search/?mode=user&sortby=rating&searchfor=" + Request.encode_%(artistName))
    parseDjUrl(artistName, Jsoup.parse(cfg.httpClient(request as_str)))
  }
}