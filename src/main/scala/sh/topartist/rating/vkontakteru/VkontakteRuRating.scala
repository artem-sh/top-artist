package sh.topartist.rating.vkontakteru

import sh.topartist.rating.Rating


case class VkontakteRuRating(foundTracksCount: Int) extends Rating {
  override def +(that: Rating): VkontakteRuRating =
    new VkontakteRuRating(this.foundTracksCount + that.asInstanceOf[VkontakteRuRating].foundTracksCount)

  override def compare(that: Rating) = this.foundTracksCount - that.asInstanceOf[VkontakteRuRating].foundTracksCount
}

object VkontakteRuRating {
  val Unknown = VkontakteRuRating(0)
}