package sh.topartist.festival

import sh.topartist.festival.parser.Kazantip19LineUpParser
import sh.topartist.rating.lastfm.{LastFmRating, LastFmRetriever}
import sh.topartist.util.ArtistUtil
import sh.topartist.LineUp
import sh.topartist.rating.promodjru.{PromodjRuRating, PromodjRuRetriever}


trait FestivalValuer {
  def rateArtists(lineUpContent: String, lastFmRetriever: LastFmRetriever, promodjRuRetriever: PromodjRuRetriever): LineUp
}

object Kazantip extends FestivalValuer {
  override def rateArtists(lineUpContent: String, lastFmRetriever: LastFmRetriever, promodjRuRetriever: PromodjRuRetriever): LineUp = {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(lineUpContent)

    lineUp.artists.foreach(artist => {
      ArtistUtil.splitArtists(artist.name).foreach(name => {
        artist.totalRating.lastFmRating = artist.totalRating.lastFmRating + rateWithLastFm(name, lastFmRetriever)
        artist.totalRating.promodjRuRating = artist.totalRating.promodjRuRating + rateWithPromodjRu(name, promodjRuRetriever)
      })
    })

    lineUp
  }

  private def rateWithLastFm(name: String, lastFmRetriever: LastFmRetriever): LastFmRating = {
    println("Trying to rate with last.fm artist " + name)

    val djName = if (name.toLowerCase.startsWith("dj")) name else "dj " + name
    println("Trying to search artist " + djName)
    val djLastFmRating = lastFmRetriever.retrieveRating(djName)

    val artistName = if (name.toLowerCase.startsWith("dj")) name.drop(2) else name
    println("Trying to search artist " + artistName)
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


  private def artistNameAsDjNik(name: String) = {
    if (name.toLowerCase.startsWith("dj")) name else "dj " + name
  }

  private def artistNameWithoutDjPefix(name: String) = {
    if (name.toLowerCase.startsWith("dj")) name.drop(2) else name
  }
}