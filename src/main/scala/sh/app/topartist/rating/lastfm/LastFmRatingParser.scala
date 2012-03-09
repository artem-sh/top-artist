package sh.app.topartist.rating.lastfm

import net.liftweb.json._
import sh.app.topartist.util.JsonUtil._
import sh.app.topartist.rating.{RatingParserException, RatingParser}


object LastFmRatingParser extends RatingParser {
  override def parseRating(searchArtistResponse: String): LastFmRating = {
    try {
      val searchArtistResponseJson = parse(searchArtistResponse)
      require(getFoundArtistsCount(searchArtistResponseJson) > 0)

      LastFmRating(getFoundArtistsName(searchArtistResponseJson), getListenersCount(searchArtistResponseJson))
    } catch {
      case e: Exception => throw new RatingParserException(e)
    }
  }

  private def getFoundArtistsName(json: JValue): String = {
    val artistNameJValue = json \ "results" \ "artistmatches" \ "artist" \ "name"
    val name = extractStringValue(artistNameJValue)
    require(!name.isEmpty)
    name
  }

  private def getFoundArtistsCount(json: JValue): Int = {
    extractIntValue(json \ "results" \ "opensearch:totalResults")
  }

  private def getListenersCount(json: JValue): Int = {
    extractIntValue(json \ "results" \ "artistmatches" \ "artist" \ "listeners")
  }
}