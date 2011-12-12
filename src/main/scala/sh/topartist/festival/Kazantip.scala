package sh.topartist.festival

import sh.topartist.util.raisin.Raisin._
import sh.topartist.festival.parser.Kazantip19LineUpParser
import sh.topartist.rating.Rating
import sh.topartist.rating.lastfm.{LastFmRating, LastFmRetriever}
import sh.topartist.util.ArtistUtil
import scala.collection.mutable.ListBuffer
import sh.topartist.Artist
import sh.topartist.rating.promodjru.PromodjRuRetriever


trait FestivalValuer {
  def rateArtists()
}

object Kazantip extends FestivalValuer {
  val lineUpStrWithDoubledArtists = """31,07 - Cosmonaut (SUNSET Point )
5,08 - Loveski (SUNSET Point )
5,08 - Soundwalk Collective (MAIN) PRE- PREMIER
6,08 - Pendulum DJ set + MC WREK (MAIN STAGE) OPENING CEREMONY
6,08 - R-TEM (MAIN STAGE) OPENING CEREMONY
6,08 - Cosmonaut (MAIN STAGE) OPENING CEREMONY
6,08 - NILS (MAIN STAGE) OPENING CEREMONY
6,08 - FEEL (MAIN STAGE) OPENING CEREMONY
20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY
20,08 - Bobina (MAIN STAGE) CLOSING PARTY
20,08 - Ricardo Villalobos (CROISSANT) ARMA / COCOON
21,08 - Ricardo Villalobos (SUNSET) point"""

  val lineUpStr = """31,07 - Cosmonaut (SUNSET Point )
5,08 - Loveski (SUNSET Point )
6,08 - R-TEM (MAIN STAGE) OPENING CEREMONY
6,08 - Cosmonaut (MAIN STAGE) OPENING CEREMONY
6,08 - NILS (MAIN STAGE) OPENING CEREMONY
6,08 - FEEL (MAIN STAGE) OPENING CEREMONY
20,08 - Bobina (MAIN STAGE) CLOSING PARTY"""


  def main(args: Array[String]) {
    rateArtists()
  }

  override def rateArtists() {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(lineUpStr)
    val artists = new ListBuffer[Artist]

    using(new LastFmRetriever, new PromodjRuRetriever) {
      (lRetriever, pRetriever) =>
        lineUp.getArtists.foreach(artist => {
          ArtistUtil.splitArtists(artist.name).foreach(name => {
            artist.totalRating.lastFmRating = artist.totalRating.lastFmRating.get + rateWithLastFm(artist.name, lRetriever)
            artist.totalRating.promodjRuRating = artist.totalRating.promodjRuRating.get + rateWithPromodjRu(artist.name, pRetriever)
          })
          println(artist)
          artists += artist
        })
    }

    println(artists)
  }

  private def rateWithLastFm(name: String, lastFmRetriever: LastFmRetriever): Rating = {
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

  private def rateWithPromodjRu(name: String, retriever: PromodjRuRetriever): Rating = {
    println("Trying to search artist " + name)
    retriever.retrieveRating(name)
  }


  private def artistNameAsDjNik(name: String) = {
    if (name.toLowerCase.startsWith("dj")) name else "dj " + name
  }

  private def artistNameWithoutDjPefix(name: String) = {
    if (name.toLowerCase.startsWith("dj")) name.drop(2) else name
  }
}