# TP 6 - Code review

In this practical course, you will implement two features for Blinky, open
pull requests (PRs), and review other students' work. The objective is not
only to make the game work: your changes and tests should make it possible
for someone else to understand and evaluate your decisions.

## Good practices for developing a feature

- **Use one branch and one PR per feature.** Start each branch from the same
  up-to-date `main` branch of your fork. Give branches descriptive names, such
  as `feature/blinky-patrol` and `feature/blinky-speed`. Keep each PR focused
  on its own exercise; do not include unrelated changes. If you need to fix a bug to implement a feature, do so in a separate PR first.
- **Add tests for the feature.** Test the specified behavior,
  boundaries, and important interactions with existing code.
- **Make small, meaningful commits.** Review your own diff before pushing,
  remove accidental changes, and update directly relevant documentation.
  Run the relevant tests and the full test suite before requesting a review.
- **Make the PR easy to review.** When you create a new pull request, it will automatically use the [PULL_REQUEST_TEMPLATE.md](../.github/PULL_REQUEST_TEMPLATE.md) from the repository. Fill this template to explain your design choices and how you tested them. Respond constructively to feedback and push follow-up commits to the same branch.

Create each PR against the `main` branch of **your own fork**. Students will be randomly assigned as reviewers of your PR. Keep the PRs open until the review is complete.

## Exercise 1 - Blinky's corner patrol

Blinky currently follows a shortest path towards the nearest player.
Implement the following behavior.

### Requirements

1. A newly created Blinky starts in chase mode. Its decisions follow a
   repeating cycle of **8 chase decisions**, then **4 patrol decisions**.
   Every call to `nextAiMove()` counts as one decision, even if it returns
   an empty result or uses the chase fallback below. The cycle then repeats.
2. During chase decisions, preserve the current behavior: find the nearest
   player and return the first direction of a shortest accessible path.
   Preserve the existing empty result when there is no player, no path, or
   the path is empty.
3. During patrol decisions, Blinky heads towards a fixed patrol target. Choose
   it from the squares reachable by Blinky from its initial square, including
   that square: select the smallest row index (`y`), breaking ties with the
   largest column index (`x`). Follow a shortest accessible path to that
   target. Any valid shortest path is acceptable when several exist.
   If Blinky is already at the target, use chase behavior for that decision.
   If no path to the target exists, return an empty result.
4. Pausing and resuming the level must not reset the cycle; a new
   Blinky starts a new cycle.

Consider walls and the board's wrap-around links when determining reachable
squares and paths. A square does not expose its board coordinates, so consider
how to make the target available to Blinky without unnecessary coupling.
Remember that an empty `nextAiMove()` result is not necessarily a stationary
ghost: `Ghost.nextMove()` falls back to a random accessible move.

Write tests for the mode boundaries and cycle repetition, target selection,
movement towards the target, and the fallback. Include wall and wrap-around
cases, and check that chase behavior is preserved. Include a test using normal
level construction, not only an isolated ghost. `ClydeTest`,
`NavigationTest`, and the test-side `GhostMapParser` may be useful; the latter
currently has an explicit marker for Clyde only.

Open a PR containing your implementation, tests, and any relevant
documentation changes.

## Exercise 2 - Blinky speeds up

Start a **separate branch from the same `main` baseline**, not from your
exercise 1 branch. Read the speed TODO in `Blinky`, `Ghost.getInterval()`,
`Level.remainingPellets()`, and the NPC scheduling code in `Level`.

### Requirements

1. When more than **20 pellets** remain on Blinky's level, its movement
   interval is a base of **250 ms** plus a random integer variation from
   **0 to 49 ms**, inclusive.
2. When **20 or fewer pellets** remain, including zero, its interval is a
   base of **150 ms** plus the same variation. A shorter interval means
   Blinky moves faster.
3. Use the current pellet count, not the initial count. After a pellet is
   eaten, the next interval calculation must reflect the updated count.
   Pellets still present underneath a ghost count as remaining pellets.
4. Apply this behavior to Blinky only. Preserve other ghosts' behavior,
   collision and scoring behavior, and the existing scheduling and
   pause/resume behavior. A delay already scheduled need not be changed:
   the updated interval applies when the next interval is calculated.

The level knows the pellet count, but Blinky currently does not. Choose and
explain how the necessary information is made available, including how your
design works when a level is built through the normal factories and map parser.
Consider that ghost movement is scheduled asynchronously and that pellet
consumption can happen during play.

Write tests for the threshold (21, 20, and 19 pellets), a count changing
after a pellet is eaten, zero pellets, and the unchanged behavior of another
ghost. Include a test that uses normal level construction and a real pellet
consumption; avoid timing-dependent tests that wait for a scheduled move.

## Peer review

Students will be randomly assigned to PRs for peer review. For each assigned PR:

1. Read the PR description, then inspect the
   production code, tests, and documentation together.
2. Check correctness, boundary cases, preservation of existing behavior,
   test quality, and whether the design is understandable and maintainable.
   Run relevant tests if you can.
3. Leave specific, constructive comments on the PR. Explain the potential
   impact of an issue and suggest a direction for improvement or ask a
   focused question. Distinguish required corrections from suggestions.
4. Submit a review summarizing your assessment. The author should respond to
   the comments, make appropriate changes, and explain any disagreements.
   Revisit significant changes before completing the review.
