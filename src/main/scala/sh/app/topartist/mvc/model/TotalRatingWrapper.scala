package sh.app.topartist.mvc.model

import sh.app.topartist.rating.TotalRating


class TotalRatingWrapper(artist: String, lastFmListeners: Int, promodjRuPromoRank: Int, vkontakteRuTracksCount: Int) {
  def getArtist = artist
  def getLastFmRating = lastFmListeners
  def getPromodjRuRating = promodjRuPromoRank
  def getVkontakteRuRating = vkontakteRuTracksCount

  def this(artist: String, rating: TotalRating) =
    this(artist, rating.lastFmRating.listeners, rating.promodjRuRating.promoRank, rating.vkontakteRuRating.tracksCount)
}