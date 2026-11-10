# TP 3 - Structural testing and mocking

*Don't forget to commit frequently on branches and to make pull requests when you want to merge your changes to your main branch !*

## Exercise 1

Analyze the requirements found in `docs/scenarios.md` and derive a decision table for the JPacman collisions from it. In this decision table you should encode the outcomes of collisions between two pairs of entities. You are free to filter out collisions that do not occur, such as two `Pellet` colliding. Make your decision table based on the table below. Note that this table is incomplete and may have too many or too few columns.

|                 |                    |                        |                         |             |              |                          |
|-----------------|--------------------|------------------------|-------------------------|-------------|--------------|--------------------------|
| **Collider**    | Ghost              | Ghost                  | Player                  | Player      | Player       | Ghost                    |
| **Collidee**    | Pellet             | Ghost                  | Pellet                  | Ghost       | Wall         | Wall                     |
| **Consequence** | Ghost faze <br/>through | Bump in each<br/>other | Pellet is <br/>consumed | Player dies | Player stuck | Ghost bump <br/>into the wall |


Based on the decision table above, derive a JUnit test suite for the `level.PlayerCollisions` class using the **specification-based** and **structural-based testing** frameworks. You should be as rigorous as possible here. Think not only of collisions that result in something, but also on collisions where "nothing happens".

## Exercise 2

The `PlayerCollisions` class is far from ideal, as it does not scale well to more realistic collision maps. An alternative is the `DefaultPlayerInteractionMap`, which makes use of the (more complicated) `CollisionInteractionMap` class. Restructure your test suite from exercise 1 so that you can execute the same test suite on both `PlayerCollisions` and `DefaultPlayerInteractionMap` objects.

## Exercise 3

Open the `game.Game` class (and its concrete subclass `game.SinglePlayerGame`). This class does not depend on any concrete `Level` implementation, only on the `Level` API, which makes it a good candidate for testing with a **mock**.

> Reminder: A "mock object" is an object that imitates a production object in limited ways (from Wikipedia). It is used to help test component interactions without using actual dependencies.

Open the `game.GameTest` class: a `Game` is already built for you on top of a mocked `Level` (using `GameFactory#createSinglePlayerGame`), so you can focus entirely on the testing itself.

This time, you have to design the test suite. Read `start()`, `stop()`, `move()`, `levelWon()` and `levelLost()`, and apply **specification-based** and **structural-based testing** to identify the test cases to implement.
Write one JUnit test per case you identified, using Mockito's `when(...)` to stub the mocked `level` and `verify(...)` to check that `Game` interacts with `level` as expected (or does *not* interact with it, using `verify(level, never())...`).

> Tip: some of these methods interact with each other (e.g. `start()` sets the state that `move()` and `stop()` depend on). Make sure your tests set up the game's state explicitly rather than relying on the order in which you happen to run them.

## Exercise 4

Write a test suite for the `nl.tudelft.jpacman.level.MapParser` class and those methods:
- `checkMapFormat(List<String> text)`
- `addSquare(Square[][] grid, List<Ghost> ghosts, List<Square> startPositions, int x, int y, char c)`
- `parseMap(List<String> text)`
- `makeGhostSquare(List<Ghost> ghosts, Ghost ghost)`

Some methods will need mocks of the factories used in the `MapParser` class. Using Mockito to mock them and use the `verify` method to verify that a map leads to the proper interactions with those factories. Do not forget to use the assertions to check what you want to check in your test.