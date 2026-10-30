# clonal
The source for Clonal IDL

## Build

This Kotlin/JVM modulith uses a Gradle multi-project build with Groovy build
scripts. Install JDK 17; the checked-in wrapper downloads Gradle automatically.

```sh
./gradlew projects
./gradlew build
./gradlew test
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

## Modules

| Module | Responsibility | Dependencies |
| --- | --- | --- |
| `clonal-model` | Shared IDL model | None |
| `clonal` | Core IDL functionality | `clonal-model` (API) |
| `clonal-openapi` | OpenAPI integration | `clonal` (implementation) |
| `clonal-cli` | Scala command-line interface | `clonal` (implementation) |

Dependencies flow from OpenAPI and the CLI to the core to the model, never in
reverse. The core exposes the shared model to consumers; OpenAPI and the CLI
remain adapters rather than dependencies of the core.

The modules are initial scaffolding, without domain implementations or tests.
Add Kotlin code under each module's `src/main/kotlin` and tests under
`src/test/kotlin`, with test dependencies in that module's `build.gradle`.
The Scala CLI module uses `src/main/scala` and `src/test/scala` instead.
Build it with `./gradlew :clonal-cli:build`; no CLI entry point is implemented yet.
For example, build only the OpenAPI module and its dependencies with:

```sh
./gradlew :clonal-openapi:build
```

To add another module, include it in `settings.gradle`, apply `java-library`
and `org.jetbrains.kotlin.jvm` (or `scala` for Scala modules) in its
`build.gradle`, and declare its project dependencies. The root build supplies
Maven Central and the JDK 17 toolchain for both languages.
