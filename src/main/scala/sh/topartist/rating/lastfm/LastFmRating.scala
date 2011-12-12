package sh.topartist.rating.lastfm

import sh.topartist.rating.Rating


case class LastFmRating(foundName: String, listeners: Int) extends Rating {
  override def +(that: Rating): Rating =
    new LastFmRating("summ rating!", this.listeners + that.asInstanceOf[LastFmRating].listeners)

  override def compare(that: Rating): Int = this.listeners - that.asInstanceOf[LastFmRating].listeners
}

object LastFmRating {
  val Unknown = LastFmRating("", 0)
}