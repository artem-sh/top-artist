package sh.app.topartist.festival

import sh.app.topartist.festival.parser.Kazantip19LineUpParser
import sh.app.topartist.rating.lastfm.{LastFmRating, LastFmRetriever}
import sh.app.topartist.util.ArtistUtil
import sh.app.topartist.LineUp
import sh.app.topartist.rating.promodjru.{PromodjRuRating, PromodjRuRetriever}
import sh.app.topartist.rating.vkontakteru.{VkontakteRuRating, VkontakteRuRetriever}


trait FestivalValuer {
  def rateArtists(lineUpContent: String,
                  lastFmRetriever: LastFmRetriever,
                  promodjRuRetriever: PromodjRuRetriever,
                  vkontakteRuRetriever: VkontakteRuRetriever): LineUp
}


object Kazantip extends FestivalValuer {
  override def rateArtists(lineUpContent: String,
                           lastFmRetriever: LastFmRetriever,
                           promodjRuRetriever: PromodjRuRetriever,
                           vkontakteRuRetriever: VkontakteRuRetriever): LineUp = {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(lineUpContent)

    lineUp.artists.foreach(artist => {
      ArtistUtil.splitArtists(artist.name).foreach(name => {
        artist.totalRating.lastFmRating = artist.totalRating.lastFmRating + rateWithLastFm(name, lastFmRetriever)
        artist.totalRating.promodjRuRating = artist.totalRating.promodjRuRating + rateWithPromodjRu(name, promodjRuRetriever)
        artist.totalRating.vkontakteRuRating = artist.totalRating.vkontakteRuRating + rateWithVkontakteRu(name, vkontakteRuRetriever)
      })
    })

    lineUp
  }

  private def rateWithLastFm(name: String, lastFmRetriever: LastFmRetriever): LastFmRating = {
    println("Trying to rate with last.fm artist " + name)

    val djName = if (name.toLowerCase.startsWith("dj")) name else "dj " + name
    println("Trying to search artist (as dj)" + djName)
    val djLastFmRating = lastFmRetriever.retrieveRating(djName)

    val artistName = if (name.toLowerCase.startsWith("dj")) name.drop(2) else name
    println("Trying to search artist (simply)" + artistName)
    val lastFmRating = lastFmRetriever.retrieveRating(name)

    def normalizeRating(rating: LastFmRating, name: String): LastFmRating = {
      if (name.equalsIgnoreCase(rating.foundName)) rating else LastFmRating.Unknown
    }

    if (normalizeRating(djLastFmRating, djName) > normalizeRating(lastFmRating, artistName)) djLastFmRating else lastFmRating
  }

  private def rateWithPromodjRu(name: String, retriever: PromodjRuRetriever): PromodjRuRating = {
    println("Trying to rate with promodj.ru artist " + name)
    retriever.retrieveRating(name)
  }

  private def rateWithVkontakteRu(name: String, retriever: VkontakteRuRetriever): VkontakteRuRating = {
    println("Trying to rate with vkontakte.ru artist " + name)
    retriever.retrieveRating(name)
  }
}