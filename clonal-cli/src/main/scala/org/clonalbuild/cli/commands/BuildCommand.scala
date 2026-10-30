package org.clonalbuild.cli.commands

import com.monovore.decline.Opts
import cats.syntax.apply.catsSyntaxTuple2Semigroupal

final case class BuildCommand(tasks: List[String], offline: Boolean) extends CliCommand:
  def run(): Int =
    val targets = if tasks.isEmpty then "default tasks" else tasks.mkString(", ")
    val mode = if offline then " (offline)" else ""
    println(s"Building $targets$mode")
    0

object BuildCommand:
  val opts: Opts[BuildCommand] =
    Opts.subcommand("build", "Run build tasks.") {
      val tasks = Opts.arguments[String]("task").orEmpty
      val offline = Opts.flag("offline", "Do not access the network.", short = "o").orFalse
      (tasks, offline).mapN(BuildCommand.apply)
    }
