package plugin

import io.quarkus.analytics.AnalyticsService
import io.quarkus.analytics.config.FileLocationsImpl
import io.quarkus.bootstrap.BootstrapConstants
import io.quarkus.bootstrap
import io.quarkus.bootstrap.app.ConfiguredClassLoading
import io.quarkus.bootstrap.app.QuarkusBootstrap
import io.quarkus.bootstrap.devmode.DependenciesFilter
import io.quarkus.bootstrap.model.ApplicationModel
import io.quarkus.bootstrap.model.PathsCollection
import io.quarkus.bootstrap.workspace.ArtifactSources
import io.quarkus.bootstrap.workspace.SourceDir
import io.quarkus.deployment.dev.DevModeCommandLine
import io.quarkus.deployment.dev.DevModeCommandLineBuilder
import io.quarkus.deployment.dev.DevModeContext
import io.quarkus.deployment.dev.DevModeMain
import io.quarkus.deployment.dev.ExtensionDevModeJvmOptionFilter

import java.nio.file.Files
//import io.quarkus.gradle.dependency.ApplicationDeploymentClasspathBuilder
//import io.quarkus.gradle.dsl.CompilerOption
//import io.quarkus.gradle.dsl.CompilerOptions
//import io.quarkus.gradle.extension.QuarkusPluginExtension
//import io.quarkus.gradle.tooling.ToolingUtils
import io.quarkus.maven.dependency.ArtifactKey
import io.quarkus.maven.dependency.ResolvedDependency
import io.quarkus.paths.PathList
import io.quarkus.runtime.LaunchMode
import io.quarkus.devtools.messagewriter.DefaultMessageWriter
import io.quarkus.devtools.messagewriter.MessageWriter

import scala.util.Using

import java.util.Scanner
import java.io.FilterInputStream


object QuarkusDevPlugin {
  val IO_QUARKUS_DEVMODE_ARGS = "io.quarkus.devmode-args"


  def startDev(): Unit = {
    val messageWriter: MessageWriter = DefaultMessageWriter()
    val analyticsService: AnalyticsService = new AnalyticsService(FileLocationsImpl.INSTANCE,
//          new GradleMessageWriter(getLogger())
      messageWriter
    )

    analyticsService.buildAnalyticsUserInput(prompt => {
      print(prompt)
      Using(new Scanner(new FilterInputStream(System.in) {
        override def close(): Unit = {
          // Don't close System.in!
          ()
        }
      }))(_.nextLine()) match {
        case scala.util.Success(value) => value
        case scala.util.Failure(e) => print("Failed to collect user input for analytics", e); ""
      }
    })

    val runner = newLauncher(analyticsService)
    val maybeOutputFile = System.getProperty(IO_QUARKUS_DEVMODE_ARGS)
    Option(maybeOutputFile) match {
      case Some(outputFile) => {
        Using(new Files.newBufferedWriter(Paths.get(outputFile))) {
          is => foreach(i : runner.getArguments()) {
            is.write(i)
            is.newLine()
          }
          )
        }
      }
      case None => {]}
    }

  }


  def newLauncher(analyticsService: AnalyticsService ): DevModeCommandLine = ???

  // これを実装
  //https: //github.com/quarkusio/quarkus/blob/fee0049cf53cdb8666fa0ec2ac79885eda1233d9/devtools/gradle/gradle-application-plugin/src/main/java/io/quarkus/gradle/tasks/QuarkusDev.java#L347


}
