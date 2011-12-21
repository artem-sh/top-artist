package sh.topartist.rating

import lastfm.LastFmRating
import org.scalatest.FunSuite
import promodjru.PromodjRuRating


class TotalRatingTest extends FunSuite {
  val totalRating = new TotalRating

  test("lastFmRating getter/setter") {
    val lfr1 = LastFmRating("Vakulenko", 123)
    totalRating.lastFmRating = lfr1
    assert(lfr1 === totalRating.lastFmRating)

    val lfr2 = LastFmRating("Vakulenko", 321)
    totalRating.lastFmRating = lfr2
    assert(lfr2 === totalRating.lastFmRating)
  }

  test("promodjRuRating getter/setter") {
    val pdr1 = PromodjRuRating(123)
    totalRating.promodjRuRating = pdr1
    assert(pdr1 === totalRating.promodjRuRating)

    val pdr2 = PromodjRuRating(12)
    totalRating.promodjRuRating = pdr2
    assert(pdr2 === totalRating.promodjRuRating)
  }
}