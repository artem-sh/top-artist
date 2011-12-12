package sh.topartist.rating

trait Rating extends Ordered[Rating] {
  def +(that: Rating): Rating
}


class TotalRating {
  private var _lastFmRating: Option[Rating] = None
  private var _promodjRuRating: Option[Rating] = None

  def lastFmRating = _lastFmRating

  def lastFmRating_=(rating: Rating) {
    require(lastFmRating.isEmpty);
    _lastFmRating = Some(rating)
  }

  def promodjRuRating = _promodjRuRating

  def promodjRuRating_=(rating: Rating) {
    require(_promodjRuRating.isEmpty)
    _promodjRuRating = Some(rating)
  }

  override def toString = "lastFmRating: " + _lastFmRating + ", promodjRuRating: " + _promodjRuRating
}