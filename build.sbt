name := "Beeline Homework"
version := "0.1"

scalaVersion := "2.12.12"
val sparkVersion = "3.1.2"
val scalatestVersion = "3.2.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion % "test",
  "org.scalatest" %% "scalatest-flatspec" % scalatestVersion % "test",
  "com.github.mrpowers" %% "spark-fast-tests" % "0.21.3" % "test",
  "com.github.mrpowers" %% "spark-daria" % "1.2.3" % "test",

)
testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v", "-s")