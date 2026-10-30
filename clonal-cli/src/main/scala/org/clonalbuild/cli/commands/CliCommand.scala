package org.clonalbuild.cli.commands

/** A parsed subcommand, ready to execute. Returns the process exit code. */
trait CliCommand:
  def run(): Int
