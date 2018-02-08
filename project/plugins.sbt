addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.3")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

// This project is its own plugin :)
unmanagedSourceDirectories in Compile += baseDirectory.value.getParentFile / "src" / "main" / "scala"
