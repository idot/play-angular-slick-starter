name := "play-angular-slick-starter"

version := "0.1-SNAPSHOT"

resolvers += "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "thirdpary" at "http://mammut:8082/nexus/content/repositories/hirdparty"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  //scala
  jdbc,
  cache,
  ws,
  filters,
  "com.typesafe.play" %% "play-slick" % "0.7.0-M1",
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.6",
  "org.specs2" %% "specs2" % "2.3.12" % "test",
  "org.specs2" %% "specs2-matcher-extra" % "2.3.12" % "test",
  "org.specs2" %% "specs2-scalacheck" % "2.3.12" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test",
  "org.postgresql" % "postgresql" % "9.2-1003-jdbc4",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.jasypt" % "jasypt" % "1.9.2",
  "commons-io" % "commons-io" % "2.4",
  "com.andersen-gott" %% "scravatar" % "1.0.2",
  "org.apache.poi" % "poi" % "3.10-FINAL",
  //javascript
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "requirejs-domready" % "2.0.1-1",
  "org.webjars" % "restangular" % "1.4.0-2",
  "org.webjars" % "underscorejs" % "1.6.0-3",
  "org.webjars" % "momentjs" % "2.6.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-1",
  "org.webjars" % "angularjs" % "1.3.0-beta.2",
  "org.webjars" % "angular-ui" % "0.4.0-3",
  "org.webjars" % "angular-ui-bootstrap" % "0.11.0-2",
  "org.webjars" % "angular-ui-router" % "0.2.10-1",
  "org.webjars" % "angular-ui-utils" % "0.1.1",
  "org.webjars" % "d3js" % "3.4.8",
  "org.webjars" % "nvd3" % "1.1.15-beta-2",
  "org.webjars" % "angularjs-nvd3-directives" % "0.0.7-1",
  "org.webjars" % "font-awesome" % "4.1.0",
  "org.webjars" % "ng-table" % "0.3.3",
  "org.webjars" % "angularjs-toaster" % "0.4.7",
//test dependencies
  "org.webjars" % "rjs" % "2.1.11-1-trireme" % "test",
  "org.webjars" % "squirejs" % "0.1.0" % "test"
)     

lazy val root = (project in file(".")).enablePlugins(PlayScala)

pipelineStages := Seq(rjs, digest, gzip)
