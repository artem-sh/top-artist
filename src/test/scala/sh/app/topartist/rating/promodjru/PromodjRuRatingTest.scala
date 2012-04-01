package sh.app.topartist.rating.promodjru

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class PromodjRuRatingTest extends FunSuite {
  test("+") {
    assert(PromodjRuRating(3) === PromodjRuRating(1) + PromodjRuRating(2))
  }

  test("<") {
    assert(PromodjRuRating(10) < PromodjRuRating(11))
  }

  test("<=") {
    assert(PromodjRuRating(10) <= PromodjRuRating(10))
  }

  test(">") {
    assert(PromodjRuRating(12) > PromodjRuRating(11))
  }

  test(">=") {
    assert(PromodjRuRating(10) >= PromodjRuRating(10))
  }
}