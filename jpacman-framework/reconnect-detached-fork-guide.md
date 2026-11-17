# How to reconnect your detached fork to the public INFOM124 upstream repository

SonarCloud's pricing plans have recently changed and do not allow private repositories to be analyzed on SonarCloud free of charge anymore. So we have to make the upstream repository of the course (INFOM124-STUDENTS-26-27) and all your forks public. Unfortunately, when a private GitHub repository becomes public (in our case, INFOM124-STUDENTS-26-27), existing private forks are permanently detached from the upstream network. As a result, forks cannot be synchronized with the upstream changes and Pull Requests can no longer be easily targeted to the upstream repository.

For the rest of this course, we will need to fix that by following this guide to safely recreate your fork connection while preserving all your commit history, branches, and any uncommitted local changes.

---

## Important note before starting

**Do NOT delete your local working folder!** Keep your existing local repository folder intact throughout this entire process so your uncommitted work stays safe.

---

## Step 1: Protect your uncommitted local work

Open your terminal and navigate into your **existing local project folder** where your code and any uncommitted edits live.

1. **Create a safety backup of any uncommitted changes**:
   ```bash
   git stash push -u -m "Safety backup before refork"
   ```
   `git stash` is used here for safety. It allows you to protect your uncommitted changes against an accidental command, to save untracked files and to keep the working tree clean. It is not absolutely necessary, but I recommend you to do it to make sure nothing will be lost.

2. **Check and note your current remote URL:**
   ```bash
   git remote -v
   ```
   *(Copy the `origin` URL—you will use it as `<OLD_FORK_URL>` in the next step.)*

---

## Step 2: Back up your committed history

Move outside of your current working directory so you can create a clean mirror backup of all branches, commits, and tags.

1. **Move one directory up from your project folder.**

2. **Create a full mirror backup** (replace `<OLD_FORK_URL>` with the URL from Step 1):
   ```bash
   git clone --mirror <OLD_FORK_URL> temp-fork-backup.git
   ```

---

## Step 3: Recreate the fork on GitHub

Complete these actions in your web browser on GitHub:

1. **Rename your detached fork:**
   * Go to the Settings of your old fork repository on GitHub.
   * Rename the repository so the original repository name is available (for example: `OLD-26-27-<NAME>-<first-name>`).

2. **Create a fresh fork from the public upstream:**
   * Navigate to the now-public upstream repository on GitHub.
   * Click the **Fork** button in the top-right corner.
   * Create the new fork **under the organization "INFOM124-Verification-et-Validation" name** using your original repository name (so `26-27-<NAME>-<first-name>`).
   * Copy the URL of this brand-new fork (this is your `<NEW_FORK_URL>`).

---

## Step 4: Restore your history to the new fork

Return to your terminal and push your backed-up branches, commits, and tags into your newly created fork.

1. **Enter the mirror backup folder** (`temp-fork-backup` if you used the same name as in this file).

2. **Push all history to the new fork** (replace `<NEW_FORK_URL>` with your new fork's URL):
   ```bash
   git push --mirror <NEW_FORK_URL>
   ```

3. **Remove the temporary backup folder** (`temp-fork-backup` in this example).

---

## Step 5: Reconnect your local working folder

Return to your original local project folder to update its connection and restore your uncommitted changes.

1. **Navigate back into your original project folder** (so `26-27-<NAME>-<first-name>`).

2. **Update your `origin` remote URL** (required if your new fork URL differs from the old one):
   ```bash
   git remote set-url origin <NEW_FORK_URL>
   ```

3. **Restore your uncommitted local edits:**
   ```bash
   git stash pop
   ```
   *(Note: If you had no uncommitted changes in Step 1, this will simply tell you "No stash entries found.")*

4. **Verify your repository status:**
   ```bash
   git status
   ```

---

## Step 6: Verify the connection on GitHub

Refresh your new fork's page on GitHub to confirm everything is linked properly:

* **Fork Badge:** Under your repository name at the top left, you should see *"forked from `INFOM124-Verification-et-Validation/INFOM124-STUDENTS-26-27`"*.
* **Sync Fork Button:** Above your file list, the **Sync fork** dropdown button will now be visible and ready to pull future updates from the upstream repository.
* **Clean Up:** Once you confirm all branches and commits are present in the new fork, you can safely delete your renamed `OLD-26-27-<NAME>-<first-name>` (if you used the same name as in this document) repository on GitHub.
