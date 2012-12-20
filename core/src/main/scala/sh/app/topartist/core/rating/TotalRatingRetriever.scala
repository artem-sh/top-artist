package sh.app.topartist.core.rating

import sh.app.topartist.core.rating.promodjru.PromodjRuRatingRetriever
import sh.app.topartist.core.rating.lastfm.LastFmRatingRetriever
import sh.app.topartist.core.rating.vkontakteru.VkontakteRuRatingRetriever

class TotalRatingRetriever(cfg: {
                              val lastFmRatingRetriever: LastFmRatingRetriever
                              val promodjRuRatingRetriever: PromodjRuRatingRetriever
                              val vkontakteRuRatingRetriever: VkontakteRuRatingRetriever})
  extends RatingRetriever {


  override def retrieveRating(artistName: String):TotalRating = {
    val rating = new TotalRating
    rating.lastFmRating = cfg.lastFmRatingRetriever.retrieveRating(artistName)
    rating.promodjRuRating = cfg.promodjRuRatingRetriever.retrieveRating(artistName)
    rating.vkontakteRuRating = cfg.vkontakteRuRatingRetriever.retrieveRating(artistName)
    rating
  }
}
