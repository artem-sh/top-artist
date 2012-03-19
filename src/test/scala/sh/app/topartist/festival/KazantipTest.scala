package sh.app.topartist.festival

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import sh.app.topartist.rating.promodjru.{PromodjRuRating, PromodjRuRetriever}
import sh.app.topartist.rating.lastfm.{LastFmRating, LastFmRetriever}
import sh.app.topartist.rating.vkontakteru.{VkontakteRuRating, VkontakteRuRatingRetriever}


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
    val lineUp = Kazantip.rateArtists(lineUpStr, createLastFmRetrieverMock, createPromodjRuRetrieverMock, createVkontakteRuRetrieverMock)
    val sortedArtists = lineUp.artists.sorted
    assert(3 === sortedArtists.size)
    assert("Groove Armada / ANDY CATO" === sortedArtists(0).name)
    assert("Cosmonaut" === sortedArtists(1).name)
    assert("Pendulum + MC WREK" === sortedArtists(2).name)
  }

  private def createLastFmRetrieverMock: LastFmRetriever = {
    val retriever = mock[LastFmRetriever]
    when(retriever.retrieveRating("Cosmonaut")).thenReturn(LastFmRating("Cosmonaut", 100))
    when(retriever.retrieveRating("dj Cosmonaut")).thenReturn(LastFmRating("dj Cosmonaut", 0))
    when(retriever.retrieveRating("Pendulum")).thenReturn(LastFmRating("Pendulum", 200))
    when(retriever.retrieveRating("dj Pendulum")).thenReturn(LastFmRating("dj Pendulum", 0))
    when(retriever.retrieveRating("MC WREK")).thenReturn(LastFmRating("MC WREK", 10))
    when(retriever.retrieveRating("dj MC WREK")).thenReturn(LastFmRating("dj MC WREK", 0))
    when(retriever.retrieveRating("Groove Armada")).thenReturn(LastFmRating("Groove Armada", 50))
    when(retriever.retrieveRating("dj Groove Armada")).thenReturn(LastFmRating("dj Groove Armada", 0))
    when(retriever.retrieveRating("ANDY CATO")).thenReturn(LastFmRating("ANDY CATO", 30))
    when(retriever.retrieveRating("dj ANDY CATO")).thenReturn(LastFmRating("dj ANDY CATO", 0))
    retriever
  }

  private def createPromodjRuRetrieverMock: PromodjRuRetriever = {
    val retriever = mock[PromodjRuRetriever]
    when(retriever.retrieveRating("Cosmonaut")).thenReturn(PromodjRuRating(1000))
    when(retriever.retrieveRating("Pendulum")).thenReturn(PromodjRuRating(2000))
    when(retriever.retrieveRating("MC WREK")).thenReturn(PromodjRuRating(100))
    when(retriever.retrieveRating("Groove Armada")).thenReturn(PromodjRuRating(500))
    when(retriever.retrieveRating("ANDY CATO")).thenReturn(PromodjRuRating(300))
    retriever
  }

  private def createVkontakteRuRetrieverMock: VkontakteRuRatingRetriever = {
    val retriever = mock[VkontakteRuRatingRetriever]
    when(retriever.retrieveRating("Cosmonaut")).thenReturn(VkontakteRuRating(10000))
    when(retriever.retrieveRating("Pendulum")).thenReturn(VkontakteRuRating(20000))
    when(retriever.retrieveRating("MC WREK")).thenReturn(VkontakteRuRating(1000))
    when(retriever.retrieveRating("Groove Armada")).thenReturn(VkontakteRuRating(5000))
    when(retriever.retrieveRating("ANDY CATO")).thenReturn(VkontakteRuRating(3000))
    retriever
  }
}