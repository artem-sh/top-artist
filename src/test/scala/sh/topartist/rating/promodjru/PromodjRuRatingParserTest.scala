package sh.topartist.rating.promodjru

import org.jsoup.Jsoup
import java.io.File
import PromodjRuRatingParser._
import scala.io.Source
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec


class PromodjRuRatingParserTest extends FlatSpec with ShouldMatchers {
  "PromodjRuRatingParser" should "find dj Loveski url with 'promodj-search-Loveski.htm' on input" in {
    val url = getClass.getResource("/sh/topartist/rating/promodjru/promodj-search-Loveski.htm")
    val doc = Jsoup.parse(new File(url.toURI), "UTF-8")

    parseDjUrl("loveski", doc).getOrElse(fail("Nothing!")) should equal("http://djloveski.promodj.ru/")
  }

  it should ("throw IllegalArgumentException if promorank occure once in web page but no value defined") in {
    evaluating {
      parseRating("""
      <html>
        <body>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;"></a>
          </div>
        </body>
      </html>""")
    } should produce[IllegalArgumentException]
  }

  it should ("throw IllegalArgumentException if promoranks occure twice in web page") in {
    evaluating {
      parseRating("""
      <html>
        <body>
          <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          </div>
        </body>
      </html>""")
    } should produce[IllegalArgumentException]
  }

  it should ("return rating cause promorank occures once in a fake web page") in {
    parseRating("""
      <html>
        <body>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          </div>
        </body>
      </html>""") should equal(PromodjRuRating(2177))
  }

  it should ("return rating cause promorank occures once in a real web page 'djloveski.promodj.ru.htm'") in {
    val file = Source.fromURL(getClass.getResource("/sh/topartist/rating/promodjru/djloveski.promodj.ru.htm"), "UTF-8")
    try {
      parseRating(file.mkString) should equal(PromodjRuRating(2177))
    } finally {
      file.close()
    }
  }
}