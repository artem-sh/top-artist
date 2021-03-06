package sh.app.topartist.core.rating.lastfm

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class LastFmRatingTest extends FunSuite {
  test("+") {
    assert(3 === (LastFmRating("name1", 1) + LastFmRating("name2", 2)).listeners)
  }

  test(">") {
    assert(LastFmRating("UNKNOWN YET!", 3) > LastFmRating("name1", 1))
  }

  test(">=") {
    assert(LastFmRating("UNKNOWN YET!", 8) >= LastFmRating("name1", 8))
  }

  test("<") {
    assert(LastFmRating("UNKNOWN YET!", 1) < LastFmRating("name1", 15))
  }

  test("<=") {
    assert(LastFmRating("UNKNOWN YET!", 8) <= LastFmRating("name1", 8))
  }
}