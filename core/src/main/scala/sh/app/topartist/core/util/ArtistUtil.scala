package sh.app.topartist.core.util

object ArtistUtil {

  def splitArtists(name: String): List[String] = {
    var names = name.split("""\s+\+\s+""")
    if (names.size <= 1) {
      names = name.split("""\s+\/\s+""")
    }

    List.empty[String] ++ names
  }
}