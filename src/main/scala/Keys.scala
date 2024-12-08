import sbt._

object Keys {
  val message = settingKey[String]("表示メッセージ") // ジェネリックにはKeyの型を、引数にはKeyの説明を指定
//  val hello3times = taskKey[Unit]("messageを3回表示する")
//  val echo3times = inputKey[Unit]("タスク起動時に渡された文字列を3回表示する")

  val quarkusDev = taskKey[Unit]("")
//  quarkusDev
//  quarkusBuild
//  addExtension
//  removeExtension
//  listExtensions
//  quarkusRun
//  quarkusBuildAppModel
//  quarkusGenerateAppModel
//  quarkusGenerateDevAppModel
//  quarkusGenerateTestAppModel
//  quarkusImageExtensionChecks
}