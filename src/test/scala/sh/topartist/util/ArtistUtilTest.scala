package sh.topartist.util

import org.scalatest.FunSuite
import sh.topartist.util.ArtistUtil._


class ArtistUtilTest extends FunSuite {

  test("splitArtists(), one artist only") {
    expect(List("Ivanov")) {
      splitArtists("Ivanov")
    }
  }

  test("splitArtists(), '+' divider") {
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set + MC WREK")
    }
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set           +   MC WREK")
    }
  }

  test("splitArtists(), '/' divider") {
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set / MC WREK")
    }
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set           /   MC WREK")
    }
  }
}