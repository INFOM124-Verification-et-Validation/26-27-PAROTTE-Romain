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

This time, you have to design the test suite. Read `start()`, `stop()` and `move()`, and apply **specification-based** and **structural-based testing** to identify the test cases to implement.
Write one JUnit test per case you identified, using Mockito's `when(...)` to stub the mocked `level` and `verify(...)` to check that `Game` interacts with `level` as expected (or does *not* interact with it, using `verify(level, never())...`).

> Hint: Mockito docs, and more on `verify(...)` and `when(...)` methods
> - https://site.mockito.org/
> - https://www.baeldung.com/mockito-verify
> - https://www.baeldung.com/mockito-behavior

## Exercise 4

The class `nl.tudelft.jpacman.level.MapParser` translates a map description into a `Level` object. It collaborates with `BoardFactory` and `LevelFactory` to create the board, squares, pellets, ghosts, and level. Write a test suite for `checkMapFormat(List<String> text)`, `makeGhostSquare(List<Ghost> ghosts, Ghost ghost)` and `parseMap(char[][] map)`. Use assertions to check results and state, and Mockito's `mock(...)`, `when(...)` and `verify(...)` methods to check important interactions with collaborators.

Begin with `checkMapFormat(List<String> text)` that does not need any mocks. Then create the tests for `makeGhostSquare(List<Ghost> ghosts, Ghost ghost)` and finish by `parseMap(char[][] map), which is more complex than the others.