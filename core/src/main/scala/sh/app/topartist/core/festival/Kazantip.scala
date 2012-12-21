package sh.app.topartist.core.festival

import sh.app.topartist.core.festival.parser.Kazantip19LineUpParser
import sh.app.topartist.core.rating.lastfm.{LastFmRating, LastFmRatingRetriever}
import sh.app.topartist.core.util.ArtistUtil
import sh.app.topartist.core.rating.promodjru.{PromodjRuRating, PromodjRuRatingRetriever}
import sh.app.topartist.core.rating.vkontakteru.{VkontakteRuRating, VkontakteRuRatingRetriever}
import sh.app.topartist.core.LineUp
import sh.app.topartist.core.rating.SessionData


trait FestivalValuer {
  def rateArtists(lineUpContent: String): LineUp
}


class Kazantip(cfg: {
  val lastFmRatingRetriever: LastFmRatingRetriever
  val promodjRuRatingRetriever: PromodjRuRatingRetriever
  val vkontakteRuRatingRetriever: VkontakteRuRatingRetriever}) extends FestivalValuer {

  override def rateArtists(lineUpContent: String): LineUp = {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(lineUpContent)
    lineUp.artists.foreach(artist => {
      ArtistUtil.splitArtists(artist.name).foreach(name => {
        artist.totalRating.lastFmRating = artist.totalRating.lastFmRating + rateWithLastFm(name, cfg.lastFmRatingRetriever)
        artist.totalRating.promodjRuRating = artist.totalRating.promodjRuRating + rateWithPromodjRu(name, cfg.promodjRuRatingRetriever)
        artist.totalRating.vkontakteRuRating = artist.totalRating.vkontakteRuRating + rateWithVkontakteRu(name, cfg.vkontakteRuRatingRetriever)
      })
    })

    lineUp
  }

  private def rateWithLastFm(name: String, lastFmRetriever: LastFmRatingRetriever): LastFmRating = {
    println("Trying to rate with last.fm artist " + name)

    val djName = if (name.toLowerCase.startsWith("dj")) name else "dj " + name
    println("Trying to search artist (as dj)" + djName)
    val djLastFmRating = lastFmRetriever.retrieveRating(djName, SessionData(None))

    val artistName = if (name.toLowerCase.startsWith("dj")) name.drop(2) else name
    println("Trying to search artist (simply)" + artistName)
    val lastFmRating = lastFmRetriever.retrieveRating(name, SessionData(None))

    def normalizeRating(rating: LastFmRating, name: String): LastFmRating = {
      if (name.equalsIgnoreCase(rating.foundName)) rating else LastFmRating.Unknown
    }

    if (normalizeRating(djLastFmRating, djName) > normalizeRating(lastFmRating, artistName)) djLastFmRating else lastFmRating
  }

  private def rateWithPromodjRu(name: String, retriever: PromodjRuRatingRetriever): PromodjRuRating = {
    println("Trying to rate with promodj.ru artist " + name)
    retriever.retrieveRating(name, SessionData(None))
  }

  private def rateWithVkontakteRu(name: String, retriever: VkontakteRuRatingRetriever): VkontakteRuRating = {
    println("Trying to rate with vkontakte.ru artist " + name)
    retriever.retrieveRating(name, SessionData(None))
  }
}