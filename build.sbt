// general setting
organization  := "your.organization"
name          := "sbt-qualus-plugin"
version       := "0.0.1-SNAPSHOT"
sbtPlugin     := true
scalacOptions ++= Seq("-feature", "-deprecation")

val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "sbt-quarkus-plugin",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "io.quarkus" % "quarkus-analytics-common" % "3.17.0",
      "io.quarkus" % "quarkus-project-core-extension-codestarts" % "3.17.0",
      "io.quarkus" % "quarkus-core-deployment" % "3.17.0"
    )
  )
