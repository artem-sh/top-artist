package sh.app.topartist

import dispatch.Http
import sh.app.topartist.festival.Kazantip
import sh.app.topartist.rating.lastfm.LastFmRatingRetriever
import sh.app.topartist.rating.promodjru.PromodjRuRatingRetriever
import sh.app.topartist.rating.vkontakteru.VkontakteRuRatingRetriever
import sh.app.topartist.rating.TotalRatingRetriever


object Config {
  lazy val httpClient = new Http
  lazy val lastFmRatingRetriever = new LastFmRatingRetriever(this)
  lazy val promodjRuRatingRetriever = new PromodjRuRatingRetriever(this)
  lazy val vkontakteRuRatingRetriever = new VkontakteRuRatingRetriever(this)
  lazy val totalRatingRetriever = new TotalRatingRetriever(this)
  lazy val kazantip = new Kazantip(this)
}
