import Dependencies._

// TODO - Scala 3 support (needs the dependent libraries to catch up...)
lazy val scala212 = "2.12.15"
lazy val scala213 = "2.13.6"
lazy val supportedScalaVersions = List(scala213, scala212)

ThisBuild / scalaVersion     := scala213
ThisBuild / organization     := "co.uk.danielrendall"
ThisBuild / organizationName := "scalaimagetiler"

githubOwner := "danielrendall"
githubRepository := "ScalaMathLib"
githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")
releaseCrossBuild := true

lazy val root = (project in file("."))
  .settings(
    name := "ScalaImageTiler",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "uk.co.danielrendall" %% "scalamathlib" % "0.1.3",
      "org.apache.xmlgraphics" % "batik-anim" % "1.14",
      "org.apache.xmlgraphics" % "batik-transcoder" % "1.14"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
