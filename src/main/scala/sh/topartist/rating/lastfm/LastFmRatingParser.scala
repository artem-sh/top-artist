package sh.topartist.rating.lastfm

import net.liftweb.json._
import sh.topartist.util.JsonUtil.extractStringValue

object LastFmRatingParser {
  def parseRating(searchArtistResponseJson: JValue): LastFmRating = {
    if (getFoundArtistsCount(searchArtistResponseJson) <= 0) return LastFmRating.Unknown

    LastFmRating(getFoundArtistsName(searchArtistResponseJson), getListenersCount(searchArtistResponseJson))
  }

  private def getFoundArtistsName(json: JValue): String = {
    val artistNameJValue = json \ "results" \ "artistmatches" \ "artist" \ "name"

    if (artistNameJValue.values == None) return ""
    extractStringValue(artistNameJValue)
  }

  private def getFoundArtistsCount(json: JValue): Int = {
    (json \ "results" \ "opensearch:totalResults").asInstanceOf[JString].values.toInt
  }

  private def getListenersCount(json: JValue): Int = {
    val listenersJValue = json \ "results" \ "artistmatches" \ "artist" \ "listeners"

    if (listenersJValue.values == None) return 0
    extractStringValue(listenersJValue).toInt
  }
}