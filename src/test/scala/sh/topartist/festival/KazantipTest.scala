package sh.topartist.festival

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import sh.topartist.rating.lastfm.{LastFmRating, LastFmRetriever}
import sh.topartist.rating.promodjru.{PromodjRuRating, PromodjRuRetriever}


class KazantipTest extends FunSuite with MockitoSugar {
  val lineUpStrWithDoubledArtists = """31,07 - Cosmonaut (SUNSET Point )
  5,08 - Loveski (SUNSET Point )
  5,08 - Soundwalk Collective (MAIN) PRE- PREMIER
  6,08 - Pendulum DJ set + MC WREK (MAIN STAGE) OPENING CEREMONY
  6,08 - R-TEM (MAIN STAGE) OPENING CEREMONY
  6,08 - Cosmonaut (MAIN STAGE) OPENING CEREMONY
  6,08 - NILS (MAIN STAGE) OPENING CEREMONY
  6,08 - FEEL (MAIN STAGE) OPENING CEREMONY
  20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY
  20,08 - Bobina (MAIN STAGE) CLOSING PARTY
  20,08 - Ricardo Villalobos (CROISSANT) ARMA / COCOON
  21,08 - Ricardo Villalobos (SUNSET) point"""

  test("rateArtists()") {
    val lineUpStr = """31,07 - Cosmonaut (SUNSET Point )
    6,08 - Pendulum + MC WREK (MAIN STAGE) OPENING CEREMONY
    20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY"""

    val lastFmRetrieverMock = mock[LastFmRetriever]
    when(lastFmRetrieverMock.retrieveRating("Cosmonaut")).thenReturn(new LastFmRating("Cosmonaut", 100))
    when(lastFmRetrieverMock.retrieveRating("dj Cosmonaut")).thenReturn(new LastFmRating("dj Cosmonaut", 0))
    when(lastFmRetrieverMock.retrieveRating("Pendulum")).thenReturn(new LastFmRating("Pendulum", 200))
    when(lastFmRetrieverMock.retrieveRating("dj Pendulum")).thenReturn(new LastFmRating("dj Pendulum", 0))
    when(lastFmRetrieverMock.retrieveRating("MC WREK")).thenReturn(new LastFmRating("MC WREK", 10))
    when(lastFmRetrieverMock.retrieveRating("dj MC WREK")).thenReturn(new LastFmRating("dj MC WREK", 0))
    when(lastFmRetrieverMock.retrieveRating("Groove Armada")).thenReturn(new LastFmRating("Groove Armada", 50))
    when(lastFmRetrieverMock.retrieveRating("dj Groove Armada")).thenReturn(new LastFmRating("dj Groove Armada", 0))
    when(lastFmRetrieverMock.retrieveRating("ANDY CATO")).thenReturn(new LastFmRating("ANDY CATO", 30))
    when(lastFmRetrieverMock.retrieveRating("dj ANDY CATO")).thenReturn(new LastFmRating("dj ANDY CATO", 0))

    val promodjRuRetrieverMock = mock[PromodjRuRetriever]
    when(promodjRuRetrieverMock.retrieveRating("Cosmonaut")).thenReturn(new PromodjRuRating(1000))
    when(promodjRuRetrieverMock.retrieveRating("Pendulum")).thenReturn(new PromodjRuRating(2000))
    when(promodjRuRetrieverMock.retrieveRating("MC WREK")).thenReturn(new PromodjRuRating(100))
    when(promodjRuRetrieverMock.retrieveRating("Groove Armada")).thenReturn(new PromodjRuRating(500))
    when(promodjRuRetrieverMock.retrieveRating("ANDY CATO")).thenReturn(new PromodjRuRating(300))

    val lineUp = Kazantip.rateArtists(lineUpStr, lastFmRetrieverMock, promodjRuRetrieverMock)
    val sortedArtists = lineUp.artists.sorted
    assert(3 === sortedArtists.size)
    assert("Groove Armada / ANDY CATO" === sortedArtists(0).name)
    assert("Cosmonaut" === sortedArtists(1).name)
    assert("Pendulum + MC WREK" === sortedArtists(2).name)
  }
}