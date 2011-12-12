package sh.topartist

import org.scalatest.FunSuite
import sh.topartist.rating.lastfm.LastFmRating
import sh.topartist.rating.promodjru.PromodjRuRating


class ArtistTest extends FunSuite {
  val artistName = "Кино"
  val artist = new Artist(artistName)

  test("name, getter") {
    assert(artistName === artist.name)
  }

  test("addPerformance()") {
    val performance1 = new Performance("2.4", "kazantip MAIN")
    val performance2 = new Performance("2.3", "MAIN")
    artist.addPerformance(performance1)
    artist.addPerformance(performance2)
    assert(Set(performance1, performance2) === artist.performances)
  }

  test("compare()") {
    val artist1 = new Artist("a1")
    artist1.totalRating.lastFmRating = LastFmRating("a1_", 10)
    val artist2 = new Artist("a2")
    artist2.totalRating.lastFmRating = LastFmRating("a2_", 20)
    assert(artist1 < artist2)
  }

  test("compareByPromodjRuRating()") {
    val artist1 = new Artist("a1")
    artist1.totalRating.promodjRuRating = PromodjRuRating(10)
    val artist2 = new Artist("a2")
    artist2.totalRating.promodjRuRating = PromodjRuRating(20)
    assert(artist1.compareByPromodjRuRating(artist2) < 0)
  }
}