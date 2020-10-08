ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.serdeliverance.streamschops"
ThisBuild / organizationName := "example"

lazy val akkaVersion = "2.6.8"

lazy val root = (project in file("."))
  .settings(
    name := "akka-streams-chops",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "org.scalatest" %% "scalatest" % "3.2.2"
    ),
    mainClass in (Compile, run) := Option("com.serdeliverance.streamschops.AkkaStreamsChops")
  )