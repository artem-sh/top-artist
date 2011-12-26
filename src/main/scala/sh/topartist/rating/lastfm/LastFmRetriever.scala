package sh.topartist.rating.lastfm

import dispatch._
import net.liftweb.json._
import sh.topartist.rating.RatingRetriever


class LastFmRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http
  val commonRequestParameters = Map("api_key" -> "d1e661cb4cae16c690443c3bc6cf8465", "format" -> "json")
  val baseRequest = :/("ws.audioscrobbler.com") / "2.0/"

  override def retrieveRating(artistName: String): LastFmRating = {
    val json = doRequestSearchArtist(artistName)
    LastFmRatingParser.parseRating(json)
  }

  private def doRequestSearchArtist(artistName: String): JValue = {
    val request = baseRequest <<? (commonRequestParameters + ("method" -> "artist.search") + ("artist" -> artistName) + ("limit" -> "1"))
    parse(httpClient(request as_str))
  }
}