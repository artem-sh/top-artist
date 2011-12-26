package sh.topartist.rating.lastfm

import org.scalatest.FunSuite


class LastFmRatingParserTest extends FunSuite {
  test("parseRating(), artist found, listeners count presented in json respond") {
    assert(LastFmRating("Mindless Self Indulgence", 442299) === LastFmRatingParser.parseRating("""
{
  "results":{
    "opensearch:Query":{
      "#text":"",
      "role":"request",
      "searchTerms":"Mindless Self Indulgence",
      "startPage":"1"
    },
    "opensearch:totalResults":"42",
    "opensearch:startIndex":"0",
    "opensearch:itemsPerPage":"1",
    "artistmatches":{
      "artist":{
        "name":"Mindless Self Indulgence",
        "listeners":"442299",
        "mbid":"44f42386-a733-4b51-8298-fe5c807d03aa",
        "url":"http://www.last.fm/music/Mindless+Self+Indulgence",
        "streamable":"1",
        "image":[{
          "#text":"http://userserve-ak.last.fm/serve/34/10283443.jpg",
          "size":"small"
        },{
          "#text":"http://userserve-ak.last.fm/serve/64/10283443.jpg",
          "size":"medium"
        },{
          "#text":"http://userserve-ak.last.fm/serve/126/10283443.jpg",
          "size":"large"
        },{
          "#text":"http://userserve-ak.last.fm/serve/252/10283443.jpg",
          "size":"extralarge"
        },{
          "#text":"http://userserve-ak.last.fm/serve/_/10283443/Mindless+Self+Indulgence+MSI.jpg",
          "size":"mega"
        }]
      }
    },
    "@attr":{
      "for":"Mindless Self Indulgence"
    }
  }
}"""))
  }

  test("parseRating(), artist found, listeners count is not presented in json respond") {
    expect(LastFmRating("Soundwalk Collective", 0)) {
      LastFmRatingParser.parseRating("""
    {"results":{"opensearch:Query":{"#text":"","role":"request","searchTerms":"Soundwalk Collective","startPage":"1"},
    "opensearch:totalResults":"1","opensearch:startIndex":"0","opensearch:itemsPerPage":"1",
    "artistmatches":{"artist":{"name":"Soundwalk Collective","mbid":"","url":"http:\/\/www.last.fm\/music\/Soundwalk+Collective",
    "streamable":"0","image":[{"#text":"","size":"small"},{"#text":"","size":"medium"},{"#text":"","size":"large"},{"#text":"","size":"extralarge"},
    {"#text":"","size":"mega"}]}},"@attr":{"for":"Soundwalk Collective"}}}""")
    }
  }

  test("parseRating(), no artist found") {
    expect(LastFmRating("", 0)) {
      LastFmRatingParser.parseRating("""
    {"results":{"opensearch:Query":{"#text":"","role":"request","searchTerms":"DJ NILS qwwwwwwwwwwwwwwwwwwwwww","startPage":"1"},
    "opensearch:totalResults":"0","opensearch:startIndex":"0","opensearch:itemsPerPage":"1","artistmatches":"\n","@attr":{"for":"DJ NILS___"}}}""")
    }
  }
}