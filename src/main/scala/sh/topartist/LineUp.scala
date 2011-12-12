package sh.topartist

class LineUp {
  private val artists = scala.collection.mutable.Map[String, Artist]()

  def getArtist(name: String): Artist = {
    artists(name)
  }

  def getArtists: Iterable[Artist] = {
    artists.values
  }

  def addPerformance(name: String, when: String, where: String) {
    val performance = new Performance(when, where)

    artists.get(name) match {
      case Some(artist) => artist.addPerformance(performance)
      case None => {
        val artist = new Artist(name)
        artist.addPerformance(performance);
        artists += name -> artist
      }
    }
  }

  override def toString = artists.toString()
}