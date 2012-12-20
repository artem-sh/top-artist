package sh.app.topartist.core

import org.scalatest.FunSuite
import sh.app.topartist.core.rating.lastfm.LastFmRating
import sh.app.topartist.core.rating.promodjru.PromodjRuRating
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class ArtistTest extends FunSuite {
  test("name, getter") {
    val artistName = "Кино"
    val artist = new Artist(artistName)
    assert(artistName === artist.name)
  }

  test("addPerformance()") {
    val artist = new Artist("Ivan")
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

  test("naturally sorted (by lastFmRating)") {
    val a5 = createArtist(5)
    val a1 = createArtist(1)
    val a10 = createArtist(10)

    assert(List(a1, a5, a10) === List(a5, a1, a10).sorted)
    assert(List(a1, a5, a10) === List(a1, a10, a5).sorted)
    assert(List(a1, a5, a10) === List(a10, a5, a1).sorted)
    assert(List(a1, a1, a10) === List(a10, a1, a1).sorted)
  }

  test("desceding sort (by lastFmRating)") {
    val a5 = createArtist(5)
    val a1 = createArtist(1)
    val a10 = createArtist(10)

    assert(List(a10, a5, a1) === List(a5, a1, a10).sortWith(_ > _))
    assert(List(a10, a5, a1) === List(a1, a10, a5).sortWith(_ > _))
    assert(List(a10, a5, a1) === List(a10, a5, a1).sortWith(_ > _))
    assert(List(a10, a1, a1) === List(a10, a1, a1).sortWith(_ > _))
  }

  test("compareByPromodjRuRating()") {
    val artist1 = new Artist("a1")
    artist1.totalRating.promodjRuRating = PromodjRuRating(10)
    val artist2 = new Artist("a2")
    artist2.totalRating.promodjRuRating = PromodjRuRating(20)
    assert(artist1.compareByPromodjRuRating(artist2) < 0)
  }

  private def createArtist(lastFmListenersCount: Int): Artist = {
    val artist = new Artist("a" + lastFmListenersCount)
    artist.totalRating.lastFmRating = LastFmRating("a", lastFmListenersCount)
    artist
  }
}