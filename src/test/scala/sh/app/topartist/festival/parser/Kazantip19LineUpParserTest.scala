package sh.app.topartist.festival.parser

import org.scalatest.FunSuite
import sh.app.topartist.Performance


class Kazantip19LineUpParserTest extends FunSuite {

  test("parseLineUp(), incorrect line-up format, no 'when'") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp("""  - Cosmonaut (SUNSET Point )""")
    assert(0 === lineUp.artists.size)
  }

  test("parseLineUp(), incorrect line-up format, no 'who'") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp("""31,07 - (SUNSET Point )""")
    assert(0 === lineUp.artists.size)
  }

  test("parseLineUp(), incorrect line-up format, no 'where'") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(""" 1.2 - Cosmonaut ()""")
    assert(0 === lineUp.artists.size)
  }

  test("parseLineUp() with different djs") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp("""
    31,07 - Cosmonaut (SUNSET Point )
    5,08 - Loveski (SUNSET Point )
    5,08 - Soundwalk Collective (MAIN) PRE- PREMIER
    6,08 - Pendulum DJ set + MC WREK (MAIN STAGE) OPENING CEREMONY
    7,08 - GVOZD (CLOUDS) STEPPIN SESSION / RESPECT FAMILY
    15,08 - Anrilov / B-Voice (SUNSET point) AMSTERDANCE""")

    assert(6 === lineUp.artists.size)
    assert(Set(new Performance("31,07", "SUNSET Point")) === lineUp.getArtist("Cosmonaut").performances)
    assert(Set(new Performance("5,08", "SUNSET Point")) === lineUp.getArtist("Loveski").performances)
    assert(Set(new Performance("5,08", "MAIN")) === lineUp.getArtist("Soundwalk Collective").performances)
    assert(Set(new Performance("6,08", "MAIN STAGE")) === lineUp.getArtist("Pendulum DJ set + MC WREK").performances)
    assert(Set(new Performance("7,08", "CLOUDS")) === lineUp.getArtist("GVOZD").performances)
    assert(Set(new Performance("15,08", "SUNSET point")) === lineUp.getArtist("Anrilov / B-Voice").performances)
  }

  test("parseLineUp() with several djs perfrorming not once") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp("""
    31,07 - Cosmonaut (SUNSET Point )
    5,08 - Cosmonaut (MAIN)""")

    assert(1 === lineUp.artists.size)
    assert(Set(new Performance("31,07", "SUNSET Point"), new Performance("5,08", "MAIN")) === lineUp.getArtist("Cosmonaut").performances)
  }

  ignore("parseLineUp() with 'kazantip-19-lineup_full.txt'") {
    val lineUp = new Kazantip19LineUpParser().parseLineUp(getClass.getResource("./test/resources/kazantip-19-lineup_full.txt"))
    assert(199 === lineUp.artists.size)
    println(lineUp)
  }
}