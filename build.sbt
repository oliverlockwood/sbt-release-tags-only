lazy val `sbt-release-tags-only` = project in file(".")

organization := "com.oliverlockwood"
name := "sbt-release-tags-only"

homepage := Some(url("https://github.com/sbt/sbt-release"))
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

crossSbtVersions := Vector("0.13.17", "1.1.0")
sbtPlugin := true
publishMavenStyle := false

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

// Bintray
bintrayOrganization := Some("oliverlockwood")
bintrayRepository := "sbt"
bintrayReleaseOnPublish := false
bintrayVcsUrl := Some("https://github.com/oliverlockwood/sbt-release-tags-only")
licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0"))

// Release
import sbtrelease.ExtraReleaseCommands
import ReleaseTransformations._
import TagsOnly._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  releaseStepCommand(ExtraReleaseCommands.initialVcsChecksCommand),
  setVersionFromTags(releaseTagPrefix.value),
  runClean,
  tagRelease,
  releaseStepCommandAndRemaining("^ publish"),
  releaseStepTask(bintrayRelease in `sbt-release-tags-only`),
  pushTagsOnly
)
