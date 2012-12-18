package sh.app.topartist

import java.io.FileInputStream


class Config {
  private val properties = new java.util.Properties()
  properties.load(new FileInputStream("top-artist.properties"))

  def property(key: String) = properties.getProperty(key)
}