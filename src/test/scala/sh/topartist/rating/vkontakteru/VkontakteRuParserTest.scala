package sh.topartist.rating.vkontakteru

import org.scalatest.FunSuite
import net.liftweb.json._
import sh.topartist.rating.vkontakteru.VkontakteRuParser._

class VkontakteRuParserTest extends FunSuite {
  test("parseTracksCount(), no tracks found") {
    assert(VkontakteRuRating.Unknown === parseRating(parse("""{"response":[0]}""")))
  }

  test("parseTracksCount(), tracks found") {
    assert(VkontakteRuRating(454221) === parseRating(parse("""
    {
      "response": [
        454221,
        {
          "aid": "72583074",
          "owner_id": "3508708",
          "artist": "The Prodigy",
          "title": "Omen",
          "duration": "216",
          "url": "http:\/\/cs4589.vkontakte.ru\/u3508708\/audio\/4c3f5da968c5.mp3",
          "lyrics_id": "3690013"
        }
      ]
    }
    """)))
  }
}