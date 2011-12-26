package sh.topartist.rating.lastfm

import dispatch._
import sh.topartist.rating.{RatingParserException, RatingRetriever}


class LastFmRetriever(http: Http) extends RatingRetriever {
  private val httpClient = http
  val commonRequestParameters = Map("api_key" -> "d1e661cb4cae16c690443c3bc6cf8465", "format" -> "json")
  val baseRequest = :/("ws.audioscrobbler.com") / "2.0/"

  override def retrieveRating(artistName: String): LastFmRating = {
    try {
      LastFmRatingParser.parseRating(doRequestSearchArtist(artistName))
    } catch {
      case e: RatingParserException => {
        println(e)
        LastFmRating.Unknown
      }
    }
  }

  private def doRequestSearchArtist(artistName: String): String = {
    val request = baseRequest <<? (commonRequestParameters + ("method" -> "artist.search") + ("artist" -> artistName) + ("limit" -> "1"))
    httpClient(request as_str)
  }
}