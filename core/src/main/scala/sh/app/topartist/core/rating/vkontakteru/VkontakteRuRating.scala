package sh.app.topartist.core.rating.vkontakteru

import sh.app.topartist.core.rating.Rating


case class VkontakteRuRating(tracksCount: Int) extends Rating {
  override def +(that: Rating): VkontakteRuRating =
    new VkontakteRuRating(this.tracksCount + that.asInstanceOf[VkontakteRuRating].tracksCount)

  override def compare(that: Rating) = this.tracksCount - that.asInstanceOf[VkontakteRuRating].tracksCount
}

object VkontakteRuRating {
  val Unknown = VkontakteRuRating(0)
}