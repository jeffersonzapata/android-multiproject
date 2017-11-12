android.useSupportVectors

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil,
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "0.9.0",
    "io.monix" %% "monix" % "2.3.0",
    "io.monix" %% "monix-cats" % "2.3.0"
  )
)

lazy val androidSettings = Seq(
  //platformTarget := "android-27",
  version := "0.1-SNAPSHOT",
  versionCode := Some(1),
  instrumentTestRunner := "android.support.test.runner.AndroidJUnitRunner",
  libraryDependencies ++= Seq (
    "com.android.support" % "appcompat-v7" % "24.0.0",
    "com.android.support.test" % "runner" % "0.5" % "androidTest",
    "com.android.support.test.espresso" % "espresso-core" % "2.2.2" % "androidTest"
  ),
  proguardCache += "cats",
  proguardOptions in Android ++= Seq(
    "-dontwarn sun.misc.Unsafe"
  )
)

lazy val core = project
  .settings(commonSettings, exportJars := true)

lazy val multiproject = (project in file("."))
  .enablePlugins(AndroidApp)
  .settings(androidSettings, commonSettings)
  .dependsOn(core)
	.aggregate(core)
