package sh.app.topartist.core

import dispatch.Http
import festival.Kazantip
import rating.TotalRatingRetriever
import rating.vkontakteru.VkontakteRuRatingRetriever
import sh.app.topartist.core.rating.lastfm.LastFmRatingRetriever
import sh.app.topartist.core.rating.promodjru.PromodjRuRatingRetriever


object Context {
  lazy val config = new Config
  lazy val httpClient = new Http
  lazy val lastFmRatingRetriever = new LastFmRatingRetriever(this)
  lazy val promodjRuRatingRetriever = new PromodjRuRatingRetriever(this)
  lazy val vkontakteRuRatingRetriever = new VkontakteRuRatingRetriever(this)
  lazy val totalRatingRetriever = new TotalRatingRetriever(this)
  lazy val kazantip = new Kazantip(this)
}