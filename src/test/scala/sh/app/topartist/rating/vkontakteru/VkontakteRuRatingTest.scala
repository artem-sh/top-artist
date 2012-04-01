package sh.app.topartist.rating.vkontakteru

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class VkontakteRuRatingTest extends FunSuite {
  test("+") {
    assert(VkontakteRuRating(3) === VkontakteRuRating(1) + VkontakteRuRating(2))
  }

  test("<") {
    assert(VkontakteRuRating(10) < VkontakteRuRating(11))
  }

  test("<=") {
    assert(VkontakteRuRating(10) <= VkontakteRuRating(10))
  }

  test(">") {
    assert(VkontakteRuRating(12) > VkontakteRuRating(11))
  }

  test(">=") {
    assert(VkontakteRuRating(10) >= VkontakteRuRating(10))
  }
}