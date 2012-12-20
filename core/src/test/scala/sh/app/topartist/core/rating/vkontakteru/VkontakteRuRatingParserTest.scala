package sh.app.topartist.core.rating.vkontakteru

import sh.app.topartist.core.rating.vkontakteru.VkontakteRuRatingParser._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import sh.app.topartist.core.rating.RatingParserException
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class VkontakteRuRatingParserTest extends FlatSpec with ShouldMatchers {
  "VkontakteRuRatingParser" should "throw RatingParserException if no tracks were found" in {
    evaluating {
      parseRating("""{"response":[0]}""")
    } should produce[RatingParserException]
  }

  it should ("throw RatingParserException if json format in respond is invalid") in {
    evaluating {
      parseRating("""
      {
      "response":
        454221,
        {}]
      }""")
    } should produce[RatingParserException]
  }

  it should ("throw RatingParserException if tracks count isn't specified in response") in {
    evaluating {
      parseRating("""
      {
        "response": [
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
      }""")
    } should produce[RatingParserException]
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