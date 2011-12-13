package sh.topartist.rating.promodjru

import org.scalatest.FunSuite
import org.jsoup.Jsoup
import java.io.File
import PromodjRuParser._


class PromodjRuParserTest extends FunSuite {

  test("parseDjUrl() with 'promodj-search-Loveski.htm' on input") {
    val url = getClass.getResource("/sh/topartist/rating/promodjru/promodj-search-Loveski.htm")
    val doc = Jsoup.parse(new File(url.toURI), "UTF-8")

    assert("http://djloveski.promodj.ru/" === parseDjUrl("loveski", doc).getOrElse(fail("Nothing!")))
  }

  test("parseDjPromoRank(), promoranks occure twice in web page") {
    expect(None) {
      val doc = Jsoup.parse("""
      <html>
        <body>
          <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          </div>
        </body>
      </html>""")

      parseDjPromoRank(doc)
    }
  }

  test("parseDjPromoRank(), promorank occures once in web page") {
    expect(Some(2177)) {
      val doc = Jsoup.parse("""
      <html>
        <body>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;">2177</a>
          </div>
        </body>
      </html>""")

      parseDjPromoRank(doc)
    }
  }

  test("parseDjPromoRank(), promorank occures once in web page but no value defined") {
    expect(None) {
      val doc = Jsoup.parse("""
      <html>
        <body>
          <div>
            <a href="#" id="rankvoter" title="Голосовать"  onClick=" Vote('user',633441,$('promoranker'),''); cb(event); return false;"></a>
          </div>
        </body>
      </html>""")

      parseDjPromoRank(doc)
    }
  }

  test("parseDjPromoRank() with 'djloveski.promodj.ru.htm' on input") {
    val url = getClass.getResource("/sh/topartist/rating/promodjru/djloveski.promodj.ru.htm")
    val doc = Jsoup.parse(new File(url.toURI), "UTF-8")

    assert(2177 === parseDjPromoRank(doc).getOrElse(fail("Nothing!")))
  }
}