package sh.app.topartist.rating.promodjru

import org.jsoup.Jsoup
import PromodjRuRatingParser._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import sh.app.topartist.rating.RatingParserException
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import sh.app.topartist.util.ResourceUtil.resourceNextToMe


@RunWith(classOf[JUnitRunner])
class PromodjRuRatingParserTest extends FlatSpec with ShouldMatchers {
  "PromodjRuRatingParser" should "find dj Loveski url with 'promodj-search-Loveski.htm' on input" in {
    val doc = Jsoup.parse(resourceNextToMe("promodj-search-Loveski.htm").mkString)

    parseDjUrl("loveski", doc).getOrElse(fail("Nothing!")) should equal("http://djloveski.promodj.ru/")
  }

  it should ("throw RatingParserException if promorank occure once in web page but no value defined") in {
    evaluating {
      parseRating("""
      <html>
        <body>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;"></a>
          </div>
        </body>
      </html>""")
    } should produce[RatingParserException]
  }

  it should ("throw RatingParserException if promoranks occure twice in web page") in {
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
    } should produce[RatingParserException]
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
    parseRating(resourceNextToMe("djloveski.promodj.ru.htm").mkString) should equal(PromodjRuRating(2177))
  }
}