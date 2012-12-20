package sh.app.topartist.core.rating

import sh.app.topartist.core.rating.lastfm.LastFmRating
import sh.app.topartist.core.rating.promodjru.PromodjRuRating
import sh.app.topartist.core.rating.vkontakteru.VkontakteRuRating
import sun.reflect.generics.reflectiveObjects.NotImplementedException


trait Rating extends Ordered[Rating] {
  def +(that: Rating): Rating
}


class TotalRating extends Rating {
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

  override def compare(that: Rating) = throw new NotImplementedException

  override def +(that: Rating) = throw new NotImplementedException
}