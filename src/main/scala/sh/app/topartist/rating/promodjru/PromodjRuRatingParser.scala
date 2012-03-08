package sh.topartist.rating.promodjru

import org.jsoup.nodes.Document
import scala.collection.JavaConversions._
import org.jsoup.Jsoup
import sh.topartist.rating.{RatingParserException, RatingParser}


object PromodjRuRatingParser extends RatingParser {
  override def parseRating(httpResponse: String): PromodjRuRating = {
    try {
    val elements = Jsoup.parse(httpResponse).select("a#rankvoter")
    require(elements.size() == 1)

    PromodjRuRating(elements.first().text().toInt)
    } catch {
      case e: Exception => throw new RatingParserException(e)
    }
  }

  def parseDjUrl(artistName: String, doc: Document): Option[String] = {
    val elements = doc.select("a.avatar")

    def findDjUrl(): Option[String] = {
      elements.foreach(el => {
        val href = el.attr("href")
        if (href.toLowerCase.contains(artistName.toLowerCase)) return Some(href)
      })
      None
    }

    findDjUrl()
  }
}