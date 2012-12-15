package sh.app.topartist

import scala.collection.Set
import sh.app.topartist.rating.TotalRating


class Artist(artistName: String) extends Ordered[Artist] {
  private val _name = artistName
  private val _performances = scala.collection.mutable.Set[Performance]()
  private val _totalRating = new TotalRating

  def name = _name

  def addPerformance(performance: Performance) {
    _performances += performance
  }

  def performances: Set[Performance] = Set.empty ++ _performances

  def totalRating = _totalRating

  override def equals(other: Any): Boolean =
    other match {
      case that: Artist =>
        (that.isInstanceOf[Artist]) &&
          _name == that._name &&
          _performances == that._performances
      case _ => false
    }

  override def hashCode: Int = 41 + _name.hashCode() + _performances.hashCode() + _totalRating.hashCode()

  override def compare(that: Artist) = this.totalRating.lastFmRating.compareTo(that.totalRating.lastFmRating)

  def compareByPromodjRuRating(that: Artist) = this.totalRating.promodjRuRating.compareTo(that.totalRating.promodjRuRating)

  override def toString = "name: " + _name + ", perfomances: " + _performances + ", totalRating: " + _totalRating
}