package sh.topartist.rating.promodjru

import org.jsoup.nodes.Document
import scala.collection.JavaConversions._
import sh.topartist.rating.RatingParser
import org.jsoup.Jsoup


object PromodjRuRatingParser extends RatingParser {
  override def parseRating(httpResponse: String): PromodjRuRating = {
    val elements = Jsoup.parse(httpResponse).select("a#rankvoter")
    require(elements.size() == 1)

    PromodjRuRating(elements.first().text().toInt)
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