package sh.app.topartist

import rating.lastfm.LastFmRetriever
import rating.promodjru.PromodjRuRetriever
import sh.app.topartist.festival.Kazantip
import sh.app.topartist.rating.vkontakteru.VkontakteRuRatingRetriever
import dispatch.Http


object App extends scala.App {
  val lineUpStr = """31,07 - Cosmonaut (SUNSET Point )
      6,08 - Pendulum + MC WREK (MAIN STAGE) OPENING CEREMONY
      20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY"""

  val http = new Http
  try {
    val lineUp = Kazantip.rateArtists(lineUpStr, new LastFmRetriever(http), new PromodjRuRetriever(http), new VkontakteRuRatingRetriever(http))
    lineUp.artists.sortWith(_ > _).foreach(println)
  } finally {
    http.shutdown()
  }
}