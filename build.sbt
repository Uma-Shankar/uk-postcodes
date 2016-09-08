name := "uk_postalcode_service"

version := "1.0-SNAPSHOT"

sbtPlugin := true

lazy val javaDemo = project.in( file(".") ).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  cache,
  javaWs,
  filters,
  javaJdbc,
  "mysql" % "mysql-connector-java" % "5.1.18"
)

