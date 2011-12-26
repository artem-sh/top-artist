package sh.topartist.rating.vkontakteru

import sh.topartist.rating.vkontakteru.VkontakteRuRatingParser._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


class VkontakteRuRatingParserTest extends FlatSpec with ShouldMatchers {
  "VkontakteRuRatingParser" should "return Unknown rating if no tracks were found" in {
    parseRating("""{"response":[0]}""") should equal(VkontakteRuRating.Unknown)
  }

  it should ("return certain rating if tracks were found") in {
    parseRating("""
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
    """) should equal(VkontakteRuRating(454221))
  }
}