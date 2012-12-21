package sh.app.topartist.core.festival

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import sh.app.topartist.core.rating.promodjru.{PromodjRuRating, PromodjRuRatingRetriever}
import sh.app.topartist.core.rating.lastfm.{LastFmRating, LastFmRatingRetriever}
import sh.app.topartist.core.rating.vkontakteru.{VkontakteRuRating, VkontakteRuRatingRetriever}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import sh.app.topartist.core.rating.SessionData


@RunWith(classOf[JUnitRunner])
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

    object Config {
      val lastFmRatingRetriever = createLastFmRetrieverMock
      val promodjRuRatingRetriever = createPromodjRuRetrieverMock
      val vkontakteRuRatingRetriever = createVkontakteRuRetrieverMock
    }
    val lineUp = new Kazantip(Config).rateArtists(lineUpStr)
    val sortedArtists = lineUp.artists.sorted

    assert(3 === sortedArtists.size)
    assert("Groove Armada / ANDY CATO" === sortedArtists(0).name)
    assert("Cosmonaut" === sortedArtists(1).name)
    assert("Pendulum + MC WREK" === sortedArtists(2).name)
  }

  private def createLastFmRetrieverMock: LastFmRatingRetriever = {
    val retriever = mock[LastFmRatingRetriever]
    when(retriever.retrieveRating("Cosmonaut", SessionData(""))).thenReturn(LastFmRating("Cosmonaut", 100))
    when(retriever.retrieveRating("dj Cosmonaut", SessionData(""))).thenReturn(LastFmRating("dj Cosmonaut", 0))
    when(retriever.retrieveRating("Pendulum", SessionData(""))).thenReturn(LastFmRating("Pendulum", 200))
    when(retriever.retrieveRating("dj Pendulum", SessionData(""))).thenReturn(LastFmRating("dj Pendulum", 0))
    when(retriever.retrieveRating("MC WREK", SessionData(""))).thenReturn(LastFmRating("MC WREK", 10))
    when(retriever.retrieveRating("dj MC WREK", SessionData(""))).thenReturn(LastFmRating("dj MC WREK", 0))
    when(retriever.retrieveRating("Groove Armada", SessionData(""))).thenReturn(LastFmRating("Groove Armada", 50))
    when(retriever.retrieveRating("dj Groove Armada", SessionData(""))).thenReturn(LastFmRating("dj Groove Armada", 0))
    when(retriever.retrieveRating("ANDY CATO", SessionData(""))).thenReturn(LastFmRating("ANDY CATO", 30))
    when(retriever.retrieveRating("dj ANDY CATO", SessionData(""))).thenReturn(LastFmRating("dj ANDY CATO", 0))
    retriever
  }

  private def createPromodjRuRetrieverMock: PromodjRuRatingRetriever = {
    val retriever = mock[PromodjRuRatingRetriever]
    when(retriever.retrieveRating("Cosmonaut", SessionData(""))).thenReturn(PromodjRuRating(1000))
    when(retriever.retrieveRating("Pendulum", SessionData(""))).thenReturn(PromodjRuRating(2000))
    when(retriever.retrieveRating("MC WREK", SessionData(""))).thenReturn(PromodjRuRating(100))
    when(retriever.retrieveRating("Groove Armada", SessionData(""))).thenReturn(PromodjRuRating(500))
    when(retriever.retrieveRating("ANDY CATO", SessionData(""))).thenReturn(PromodjRuRating(300))
    retriever
  }

  private def createVkontakteRuRetrieverMock: VkontakteRuRatingRetriever = {
    val retriever = mock[VkontakteRuRatingRetriever]
    when(retriever.retrieveRating("Cosmonaut", SessionData(""))).thenReturn(VkontakteRuRating(10000))
    when(retriever.retrieveRating("Pendulum", SessionData(""))).thenReturn(VkontakteRuRating(20000))
    when(retriever.retrieveRating("MC WREK", SessionData(""))).thenReturn(VkontakteRuRating(1000))
    when(retriever.retrieveRating("Groove Armada", SessionData(""))).thenReturn(VkontakteRuRating(5000))
    when(retriever.retrieveRating("ANDY CATO", SessionData(""))).thenReturn(VkontakteRuRating(3000))
    retriever
  }
}