package sh.topartist.rating.lastfm

import net.liftweb.json._
import sh.topartist.util.JsonUtil._
import sh.topartist.rating.RatingParser


object LastFmRatingParser extends RatingParser {
  override def parseRating(searchArtistResponse: String): LastFmRating = {
    val searchArtistResponseJson = parse(searchArtistResponse)
    if (getFoundArtistsCount(searchArtistResponseJson) <= 0) return LastFmRating.Unknown

    LastFmRating(getFoundArtistsName(searchArtistResponseJson), getListenersCount(searchArtistResponseJson))
  }

  private def getFoundArtistsName(json: JValue): String = {
    val artistNameJValue = json \ "results" \ "artistmatches" \ "artist" \ "name"

    if (artistNameJValue.values == None) return ""
    extractStringValue(artistNameJValue)
  }

  private def getFoundArtistsCount(json: JValue): Int = {
    extractIntValue(json \ "results" \ "opensearch:totalResults")
  }

  private def getListenersCount(json: JValue): Int = {
    val listenersJValue = json \ "results" \ "artistmatches" \ "artist" \ "listeners"

    if (listenersJValue.values == None) return 0
    extractIntValue(listenersJValue)
  }
}