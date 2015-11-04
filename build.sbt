name := "spray-test-directive"

version := "1.0"

scalaVersion := "2.11.7"

val sprayVersion = "1.3.3"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-testkit" % sprayVersion % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
