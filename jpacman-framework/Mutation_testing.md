# Mutation testing with PIT

Mutation testing evaluates how well tests detect changes in the production code.
PIT makes small, temporary changes called **mutations** (for example, changing
`>` to `>=`) and runs relevant tests against each mutant.

PIT reports the following outcomes for each mutant:
- **Killed**: a test failed when run against the mutated code. It means the test suite is effective at detecting this change.
- **Survived**: all tests passed when run against the mutated code. It means the test suite did not detect the change and should be improved.
- **No coverage**: no test executed the mutated code.
- **Timed out**: the mutant caused a test to exceed PIT's time limit; it may
  have introduced an infinite loop, but a slow test can also time out.

PIT first runs tests against the unmodified code to calculate coverage. These
tests must pass before mutation testing can begin.

## Run from IntelliJ IDEA

Create a reusable Maven run configuration:

1. Select **Run > Edit Configurations**.
2. Click **+** and select **Maven**.
3. Name the configuration, for example `PIT mutation testing`.
4. Set the working directory to the `jpacman-framework` directory.
5. In **Run** or **Command line**, enter:
   `clean test org.pitest:pitest-maven:mutationCoverage`
6. Under **Runner**, select JDK 21 as the JRE. If the configuration does not
   provide a JRE setting, set it under **Settings > Build, Execution,
   Deployment > Build Tools > Maven > Runner**.
7. Click **Apply** and **OK**. Select the configuration in the toolbar and
   click the green **Run** button.

## Run from a terminal

Check the JDK used by the Maven wrapper:

```bash
./mvnw -version
```

On Linux or macOS, if necessary, set `JAVA_HOME` to your installed JDK 21
directory for the current terminal session, then check the version again:

```bash
export JAVA_HOME=/path/to/jdk-21
export PATH="$JAVA_HOME/bin:$PATH"
./mvnw -version
```

Run the clean build, tests, and PIT:

```bash
./mvnw clean test org.pitest:pitest-maven:mutationCoverage
```

On Windows, use the Maven wrapper commands below instead, and configure
`JAVA_HOME` to point to JDK 21:

```bat
mvnw.cmd -version
mvnw.cmd clean test org.pitest:pitest-maven:mutationCoverage
```

If Maven is installed, `mvn` can be used instead of the wrapper. Check
`mvn -version` to confirm which JDK it uses.

## Read the report

After a successful run, open `target/pit-reports/index.html` in a browser.
Navigate to a class and its source lines to see the mutations and their
outcomes. Focus on surviving and uncovered mutants to identify potential gaps
in the tests, then rerun PIT after improving them.
