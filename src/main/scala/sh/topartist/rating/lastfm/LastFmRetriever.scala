package sh.topartist.rating.lastfm

import dispatch._
import net.liftweb.json._
import sh.topartist.rating.{RatingRetriever, Rating}
import sh.topartist.util.raisin.Disposable


class LastFmRetriever extends RatingRetriever with Disposable {
  val commonRequestParameters = Map("api_key" -> "d1e661cb4cae16c690443c3bc6cf8465", "format" -> "json")
  val baseRequest = :/("ws.audioscrobbler.com") / "2.0/"
  val httpHandler = new Http

  override def retrieveRating(artistName: String): LastFmRating = {
    val json = doRequestSearchArtist(artistName)
    LastFmRatingParser.parseRating(json)
  }

  override def dispose() {
    httpHandler.shutdown()
  }

  private def doRequestSearchArtist(artistName: String): JValue = {
    val request = baseRequest <<? (commonRequestParameters + ("method" -> "artist.search") + ("artist" -> artistName) + ("limit" -> "1"))
    parse(httpHandler(request as_str))
  }


  //    //    println(compact(render((parse(jsonToTest) \ "results" \ "artistmatches" \ "artist" \ "listeners")(0))))
  //    val http = new Http
  //    //        val searchArtistResponseJson = http(:/("ws.audioscrobbler.com") / "2.0/" <<? Map("method" -> "auth.gettoken", "api_key" -> "d1e661cb4cae16c690443c3bc6cf8465", "format" -> "searchArtistResponseJson") >~ {_.getLines.mkString})
  //    val jsonText = http(:/("ws.audioscrobbler.com") / "2.0/" <<? Map("method" -> "artist.search", "artist" -> "Mindless Self Indulgence", "limit" -> "1", "api_key" -> "d1e661cb4cae16c690443c3bc6cf8465", "format" -> "searchArtistResponseJson") >~ {
  //      _.getLines.mkString
  //    })
  //    println(pretty(render(parse(jsonText))))


  //    val searchArtistResponseJson = http(url("http://ws.audioscrobbler.com/2.0/?method=auth.gettoken&api_key=d1e661cb4cae16c690443c3bc6cf8465&format=searchArtistResponseJson") >~ {_.getLines.mkString})

  //    implicit val formats = Serialization.formats(NoTypeHints)
  //    val user = Serialization.read[User](searchArtistResponseJson)
  //
  //    println(user.description)
}