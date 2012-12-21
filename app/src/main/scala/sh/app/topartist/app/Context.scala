package sh.app.topartist.app

import dispatch.Http
import sh.app.topartist.core.rating.lastfm.LastFmRatingRetriever
import sh.app.topartist.core.rating.promodjru.PromodjRuRatingRetriever
import sh.app.topartist.core.rating.vkontakteru.VkontakteRuRatingRetriever
import sh.app.topartist.core.rating.TotalRatingRetriever
import sh.app.topartist.core.festival.Kazantip


object Context {
   lazy val httpClient = new Http
   lazy val lastFmRatingRetriever = new LastFmRatingRetriever(this)
   lazy val promodjRuRatingRetriever = new PromodjRuRatingRetriever(this)
   lazy val vkontakteRuRatingRetriever = new VkontakteRuRatingRetriever(this)
   lazy val totalRatingRetriever = new TotalRatingRetriever(this)
   lazy val kazantip = new Kazantip(this)
 }