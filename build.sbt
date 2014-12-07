organization := "expense_calc"

name := "default"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.0"

sbtVersion := "0.13.5"

resolvers ++= Seq(
  "ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.5",
  "org.specs2" %% "specs2" % "2.4.2" % "test"
)

mainClass in run := Some("TravellingAllowance")
