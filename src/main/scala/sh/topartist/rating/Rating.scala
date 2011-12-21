package sh.topartist.rating

import lastfm.LastFmRating
import promodjru.PromodjRuRating
import sh.topartist.rating.vkontakteru.VkontakteRuRating


trait Rating extends Ordered[Rating] {
  def +(that: Rating): Rating
}


class TotalRating {
  private var _lastFmRating: LastFmRating = LastFmRating.Unknown
  private var _promodjRuRating: PromodjRuRating = PromodjRuRating.Unknown
  private var _vkontakteRuRating: VkontakteRuRating = VkontakteRuRating.Unknown

  def lastFmRating = _lastFmRating

  def lastFmRating_=(rating: LastFmRating) {_lastFmRating = rating}

  def promodjRuRating = _promodjRuRating

  def promodjRuRating_=(rating: PromodjRuRating) {_promodjRuRating = rating}

  def vkontakteRuRating = _vkontakteRuRating

  def vkontakteRuRating_=(rating: VkontakteRuRating) {_vkontakteRuRating = rating}

  override def toString =
    "lastFmRating: " + lastFmRating + ", promodjRuRating: " + promodjRuRating + ", vkontakteRuRating: " + vkontakteRuRating
}