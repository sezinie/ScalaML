organization := "malunggay"

name := "propertybasedtesting"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
   "org.apache.commons" % "commons-math3" % "3.3",
   "org.jfree" % "jfreechart" % "1.0.17",
   "com.typesafe.akka" %% "akka-actor" % "2.3.3",
   "org.apache.spark" %% "spark-core" % "1.3.1",
   "org.apache.spark" %% "spark-mllib" % "1.3.1",
//   "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
   "org.scalacheck" %% "scalacheck" % "1.12.4",
   "org.scalatest" %% "scalatest" % "2.2.4"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

scalacOptions ++= Seq("-unchecked", "-optimize", "-Yinline-warnings")

scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

//testOptions in Test += Tests.Argument("-oDS")
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
