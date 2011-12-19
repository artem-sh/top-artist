package sh.topartist

import rating.lastfm.LastFmRetriever
import rating.promodjru.PromodjRuRetriever
import sh.topartist.festival.Kazantip
import sh.topartist.util.raisin.Raisin._


object App extends scala.App {
  val lineUpStr = """31,07 - Cosmonaut (SUNSET Point )
      6,08 - Pendulum + MC WREK (MAIN STAGE) OPENING CEREMONY
      20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY"""

  using(new LastFmRetriever, new PromodjRuRetriever) {
    (lastFmRetriever, promodjRuRetriever) =>
      val lineUp = Kazantip.rateArtists(lineUpStr, lastFmRetriever, promodjRuRetriever)
      lineUp.artists.sortWith(_ > _).foreach(println)
  }
}