package sh.app.topartist

import org.scalatest.FunSuite


class LineUpTest extends FunSuite {
  val lineUp = new LineUp()
  val artistName = "DJ Kikin"
  val artist = new Artist(artistName)
  artist.addPerformance(new Performance("21.3", "Кто знает"))
  lineUp.addPerformance(artistName, "21.3", "Кто знает")

  test("getArtist()") {
    assert(artist === lineUp.getArtist(artistName))
  }

  test("artists()") {
    val artists = lineUp.artists
    assert(1 === artists.size)
    assert(artist === artists.iterator.next())
  }

  test("addPerformance() for an existing artist") {
    lineUp.addPerformance(artistName, "22.23", "sdlflskdf")

    assert(2 === lineUp.getArtist(artistName).performances.size)
  }
}