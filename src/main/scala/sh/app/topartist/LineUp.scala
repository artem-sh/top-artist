package sh.topartist

class LineUp {
  private val _artists = scala.collection.mutable.Map[String, Artist]()

  def getArtist(name: String): Artist = {
    _artists(name)
  }

  def artists: Seq[Artist] = {
    _artists.values.toSeq
  }

  def addPerformance(name: String, when: String, where: String) {
    val performance = new Performance(when, where)

    _artists.get(name) match {
      case Some(artist) => artist.addPerformance(performance)
      case None => {
        val artist = new Artist(name)
        artist.addPerformance(performance);
        _artists += name -> artist
      }
    }
  }

  override def toString = _artists.toString()
}