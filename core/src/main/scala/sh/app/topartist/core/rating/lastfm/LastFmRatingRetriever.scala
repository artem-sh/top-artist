package sh.app.topartist.core.rating.lastfm

import dispatch._
import sh.app.topartist.core.rating.{RatingParserException, RatingRetriever}


class LastFmRatingRetriever(cfg: {val httpClient: Http}) extends RatingRetriever {
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
    cfg.httpClient(request as_str)
  }
}