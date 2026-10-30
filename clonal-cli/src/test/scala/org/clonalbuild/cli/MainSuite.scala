package org.clonalbuild.cli

import org.clonalbuild.cli.commands.{BuildCommand, InitCommand}

import java.nio.file.Paths

class MainSuite extends munit.FunSuite:

  private def parse(args: String*) = Main.command.parse(args)

  test("init defaults to the current directory") {
    assertEquals(parse("init"), Right(InitCommand(Paths.get("."))))
  }

  test("build collects tasks and the offline flag") {
    assertEquals(parse("build", "--offline", "compile", "test"), Right(BuildCommand(List("compile", "test"), offline = true)))
  }

  test("unknown subcommand is an error") {
    assert(parse("deploy").isLeft)
  }
