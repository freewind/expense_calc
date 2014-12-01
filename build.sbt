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
  "org.apache.commons" % "commons-lang3" % "3.3.2",
  "commons-io" % "commons-io" % "2.4",
  "com.google.guava" % "guava" % "18.0",
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "io.argonaut" %% "argonaut" % "6.1-M4",
  "net.databinder" %% "unfiltered" % "0.8.1",
  "net.databinder" %% "unfiltered-filter-async" % "0.8.1",
  "net.databinder" %% "unfiltered-jetty" % "0.8.1",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.commonjava.googlecode.markdown4j" % "markdown4j" % "2.2-cj-1.0",
  "com.github.spullara.mustache.java" % "compiler" % "0.8.16",
  "org.jsoup" % "jsoup" % "1.7.3",
  "org.specs2" %% "specs2" % "2.4.2" % "test"
)

mainClass in run := Some("TravellingAllowance")
