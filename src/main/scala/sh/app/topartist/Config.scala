package sh.app.topartist

import dispatch.Http
import sh.app.topartist.festival.Kazantip
import sh.app.topartist.rating.lastfm.LastFmRetriever
import sh.app.topartist.rating.promodjru.PromodjRuRetriever
import sh.app.topartist.rating.vkontakteru.VkontakteRuRatingRetriever


object Config {
  lazy val httpClient = new Http
  lazy val lastFmRetriever = new LastFmRetriever(this)
  lazy val promodjRuRetriever = new PromodjRuRetriever(this)
  lazy val vkontakteRuRatingRetriever = new VkontakteRuRatingRetriever(this)
  lazy val kazantip = new Kazantip(this)
}
