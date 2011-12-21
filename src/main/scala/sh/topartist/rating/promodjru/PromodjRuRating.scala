package sh.topartist.rating.promodjru

import sh.topartist.rating.Rating


case class PromodjRuRating(promoRank: Int) extends Rating {
  override def +(that: Rating): PromodjRuRating =
    PromodjRuRating(this.promoRank + that.asInstanceOf[PromodjRuRating].promoRank)

  override def compare(that: Rating) = this.promoRank - that.asInstanceOf[PromodjRuRating].promoRank
}

object PromodjRuRating {
  val Unknown = PromodjRuRating(0)
}

