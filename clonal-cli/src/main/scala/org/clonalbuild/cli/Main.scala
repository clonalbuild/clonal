package org.clonalbuild.cli

import com.monovore.decline.Command
import org.clonalbuild.cli.commands.{BuildCommand, CliCommand, InitCommand}

val command: Command[CliCommand] =
  Command(name = "clonal", header = "The clonal CLI.") {
    InitCommand.opts orElse BuildCommand.opts
  }

@main def run(args: String*): Unit =
  val exitCode = command.parse(args.toIndexedSeq, sys.env) match
    case Right(cliCommand) => cliCommand.run()
    case Left(help) if help.errors.isEmpty =>
      println(help)
      0
    case Left(help) =>
      System.err.println(help)
      1

  sys.exit(exitCode)