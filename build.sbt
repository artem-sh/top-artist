name := "Top artist"

version := "0.0.0.1"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "1.6.1" % "test",
	"org.mockito" % "mockito-all" % "1.9.0-rc1" % "test",
	"net.databinder" %% "dispatch-core" % "0.8.5" withSources,
	"net.databinder" %% "dispatch-http" % "0.8.5" withSources,
	"net.liftweb" %% "lift-json" % "2.4-M4" withSources,
    "org.jsoup" % "jsoup" % "1.6.1" withSources
)

retrieveManaged := true