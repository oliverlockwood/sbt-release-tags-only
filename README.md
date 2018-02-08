# sbt-release-tags-only
A small extension to sbt-release, to support version tracking purely through Git tags, without the need for commits
to a `version.sbt` file.  Natively supports independently versioned multi-module projects.

### Using it
1.  Add the plugin:
    ```sbt
    addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
    ```
2.  Define your release process, including the relevant tasks:
    ```sbt
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      releaseStepCommand(ExtraReleaseCommands.initialVcsChecksCommand),
      setVersionFromTags(releaseTagPrefix.value),
      runClean,
      tagRelease,
      publishArtifacts
      pushTagsOnly
    )
    ```

#### Notes
-  `setVersionFromTags()` replaces `inquireVersions`, `setReleaseVersion`
-  `commitReleaseVersion`, `commitNextVersion`, and `setNextVersion` should no longer be used
-  `pushTagsOnly` replaces `pushChanges`

Look at [the code](src/main/scala/TagsOnly.scala) to go deeper!
