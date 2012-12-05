package sh.app.topartist.mvc.model

import sh.app.topartist.rating.TotalRating

import javax.enterprise.context.RequestScoped
import javax.inject.Named
import reflect.BeanProperty
import sh.app.topartist.rating.lastfm.LastFmRating

class TotalRatingWrapper(artist: String, totalRating: TotalRating) {
  def getArtist = artist
  def getLastFmRating = totalRating.lastFmRating.listeners
  def getPromodjRuRating = totalRating.promodjRuRating.promoRank
  def getVkontakteRuRating = totalRating.vkontakteRuRating.tracksCount
}