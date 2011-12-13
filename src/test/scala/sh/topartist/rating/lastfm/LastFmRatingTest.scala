package sh.topartist.rating.lastfm

import org.scalatest.FunSuite


class LastFmRatingTest extends FunSuite {
  ignore("+") {
    assert(LastFmRating("UNKNOWN YET!", 3) === LastFmRating("name1", 1) + LastFmRating("name2", 2))
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