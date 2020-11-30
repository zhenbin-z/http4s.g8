// give the user a nice default project!
ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.2"

val Http4sVersion = "0.21.5"
val Specs2Version = "4.10.0"
val ScalatestVersion = "3.1.0"
val PureconfigVersion = "0.12.2"
val MacwireVersion = "2.3.3"
val Slf4jdkVersion = "1.7.30"
val CirceVersion = "0.13.0"
val CatsVersion = "2.1.1"
val DoobieVersion = "0.9.0"
val FlyWayVersion = "6.3.1"
val RefinedVersion = "0.9.18"

lazy val root = (project in file("."))
  .settings(
    name := "http4s.g8",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.specs2" %% "specs2-core" % Specs2Version % "test",
      "org.scalatest" %% "scalatest" % ScalatestVersion % Test,
      "com.github.pureconfig" %% "pureconfig" % PureconfigVersion,
      "com.softwaremill.macwire" %% "macros" % MacwireVersion % "provided",
      "org.slf4j" % "slf4j-jdk14" % Slf4jdkVersion,
      "io.circe" %% "circe-core" % CirceVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "io.circe" %% "circe-generic-extras" % CirceVersion,
      "io.circe" %% "circe-parser" % CirceVersion,
      "io.circe" %% "circe-refined" % CirceVersion,
      "org.typelevel" %% "cats-core" % CatsVersion,
      "org.typelevel" %% "cats-effect" % CatsVersion,
      "org.tpolecat" %% "doobie-core" % DoobieVersion,
      "org.tpolecat" %% "doobie-hikari" % DoobieVersion,
      "org.tpolecat" %% "doobie-refined" % DoobieVersion,
      "org.flywaydb" % "flyway-core" % FlyWayVersion,
      "eu.timepit" %% "refined" % RefinedVersion,
      "eu.timepit" %% "refined-cats" % RefinedVersion,
      "eu.timepit" %% "refined-pureconfig" % RefinedVersion,
      "eu.timepit" %% "refined-scalacheck" % RefinedVersion
    ),
    parallelExecution in Test := false,
    /* coverage settings */
    coverageEnabled.in(Test, test) := true,
    coverageEnabled in (Compile, compile) := false,
    coverageMinimum := 90,
    coverageFailOnMinimum := false,
    coverageHighlighting := true,
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-explaintypes", // Explain type errors in more detail.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros", // Allow macro definition (besides implementation and application)
      "-language:higherKinds", // Allow higher-kinded types
      "-language:implicitConversions", // Allow definition of implicit functions called views
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
      "-Xfatal-warnings", // Fail the compilation if there are any warnings.
      "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
      "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
      "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
      "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
      "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
      "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
      "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
      "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
      "-Xlint:option-implicit", // Option.apply used implicit view.
      "-Xlint:package-object-classes", // Class or object defined in package object.
      "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
      "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
      "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
      "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
      "-Ywarn-numeric-widen", // Warn when numerics are widened.
      "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
      "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
      "-Ywarn-unused:locals", // Warn if a local definition is unused.
      "-Ywarn-unused:params", // Warn if a value parameter is unused.
      "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
      "-Ywarn-unused:privates", // Warn if a private member is unused.
      "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
      "-Ybackend-parallelism",
      "8", // Enable paralellisation — change to desired number!
      "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
      "-Ycache-macro-class-loader:last-modified", // and macro definitions. This can lead to performance improvements.
      "-Ymacro-annotations", // Enables macro annotation mainly for circe
      "-Yrangepos" // required by SemanticDB compiler plugin,
    )
  )
  .enablePlugins(FlywayPlugin)
