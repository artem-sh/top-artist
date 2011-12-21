package sh.topartist.rating

import lastfm.LastFmRating
import promodjru.PromodjRuRating


trait Rating extends Ordered[Rating] {
  def +(that: Rating): Rating
}


class TotalRating {
  private var _lastFmRating: LastFmRating = LastFmRating.Unknown
  private var _promodjRuRating: PromodjRuRating = PromodjRuRating.Unknown

  def lastFmRating = _lastFmRating

  def lastFmRating_=(rating: LastFmRating) {
    _lastFmRating = rating
  }

  def promodjRuRating = _promodjRuRating

  def promodjRuRating_=(rating: PromodjRuRating) {
    _promodjRuRating = rating
  }

  override def toString = "lastFmRating: " + _lastFmRating + ", promodjRuRating: " + _promodjRuRating
}