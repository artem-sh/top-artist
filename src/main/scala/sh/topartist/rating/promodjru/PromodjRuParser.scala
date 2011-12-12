package sh.topartist.rating.promodjru

import org.jsoup.nodes.Document
import scala.collection.JavaConversions._
object PromodjRuParser {
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

  def parseDjPromoRank(doc: Document): Option[Int] = {
    val elements = doc.select("a#rankvoter")
    if (elements.isEmpty || (elements.size() > 1)) return None

    try {
      Some(elements.first().text().toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
}