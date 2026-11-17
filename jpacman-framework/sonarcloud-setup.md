# SonarQube Cloud setup for INFOM124

Follow this guide to configure SonarQube Cloud analysis for your INFOM124
project fork using GitHub Actions.

## Before you start

Make sure you have completed the steps in
[`jpacman-framework/reconnect-detached-fork-guide.md`](./jpacman-framework/reconnect-detached-fork-guide.md).
Your fork must be connected to the course repository before you continue.

## 1. Create your SonarQube Cloud project

1. Go to [sonarcloud.io](https://sonarcloud.io/) and sign in with GitHub.
2. Create a new project in the
   `INFOM124-Verification-et-Validation` organization for your forked
   repository. Select your repository when prompted.
3. Follow the setup wizard and choose these options:
   - **New code definition:** Previous version
   - **Analysis method:** With GitHub Actions
4. Follow SonarQube Cloud's instructions to create a GitHub Actions secret.
Keep the token secret; never add it to a file or commit
   it to your repository.
5. Under **Create or update a build file**, choose **Maven**.
6. When asked about automatic configuration, click to disable it.
7. Add the following property inside the `<properties>` element in
   `jpacman-framework/pom.xml`:

   ```xml
   <sonar.organization>infom124-verification-et-validation</sonar.organization>
   ```

8. Choose **Linux** as the operating system.
9. Create the workflow directories and an empty workflow file at
   `.github/workflows/build.yml` in the root of your repository. GitHub
   requires the directory to be named `.github` (including the leading dot).
   Do not copy the workflow content proposed by SonarQube Cloud into this
   file.

## 2. Add the course workflow

Copy the contents of [`sonar-template/build.yml`](./sonar-template/build.yml)
into `.github/workflows/build.yml`. In the copied file, replace
`<PROJECT_KEY>` with the project key shown in the GitHub Actions setup
instructions provided by SonarQube Cloud. Replace the entire placeholder,
including the angle brackets.

Make sure the Maven organization
property is in `jpacman-framework/pom.xml` and that the workflow file is at
`.github/workflows/build.yml`.

## 3. Run the analysis

1. Commit and push your changes to the `main` branch of your fork.
2. On GitHub, open the **Actions** tab and check that the workflow runs.
3. After the GitHub Actions run has finished, return to [SonarQube
   Cloud](https://sonarcloud.io/) and open your project to view its analysis
   report.
