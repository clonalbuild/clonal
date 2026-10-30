package org.clonalbuild.cli.commands

import scala.io.AnsiColor._
import pprint.pprintln
import com.monovore.decline.Opts
import java.nio.file.{Files, Path, Paths}

final case class InitCommand(directory: Path) extends CliCommand:
  def run(): Int = {
    if Files.exists(directory) then
      pprintln("⚠️ Directory already exists!")
      1
    else
  }
  0

object InitCommand:
  private val Path = "path"

  val opts: Opts[InitCommand] =
    Opts.subcommand("init", "Create a new clonal project.") {
      Opts
        .argument[Path]("directory")
        .withDefault(Paths.get("."))
        .map(InitCommand(_))
    }
