package sh.topartist.rating

import lastfm.LastFmRating
import org.scalatest.FunSuite
import promodjru.PromodjRuRating


class TotalRatingTest extends FunSuite {
  val totalRating = new TotalRating

  test("setLastFmRating()") {
    totalRating.lastFmRating = new LastFmRating("Vakulenko", 123)

    intercept[IllegalArgumentException] {
      totalRating.lastFmRating = new LastFmRating("Vakulenko", 123)
    }
  }

  test("setPromodjRuRating()") {
    totalRating.promodjRuRating = new PromodjRuRating(123)

    intercept[IllegalArgumentException] {
      totalRating.promodjRuRating = new PromodjRuRating(123)
    }
  }
}