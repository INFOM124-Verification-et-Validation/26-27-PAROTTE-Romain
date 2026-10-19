# TP 2 - Specification-based testing

During the first practical course, we discovered Jpacman and implemented some empty tests. During this second course, we will not just implement empty tests, but we will use the specification-based testing framework to design our tests, in the case of the movements of the ghosts. For those who do not remember what the specification-based testing framework is, please go check [these slides](https://webcampus.unamur.be/pluginfile.php/497490/mod_resource/content/6/INFOM124%20-%20SVV%20-%20Topic1b.pdf) from the course. Don't forget that devising test cases is not only testing the "good" cases, but also the "bad" cases. Follow the different steps of the framework to design the most complete test suite possible.

For all exercises below, use the `GhostMapParser` class to support your JUnit tests. Feel free to explore how the `Ghost` parent class work.

## Exercise 1
Open `npc.ghost.Clyde` class and devise and implement test cases, using the specification-based testing framework, for the method `Optional<Direction> nextAiMove()` in a `ClydeTest` class (remember that all test files should be placed in the `src/test` folder). Use the template `analysis-template.md` to help you with the different steps of the framework.

Here are some hints to help you get started with this exercise:

>Instantiating the `Clyde` class and adding to a map requires some understanding of JPacman. Let's have a head start. See the `GhostMapParser` class.
> - In the tests, we need to build a level, based on a map. This map should be designed based on what we want to exercise in that test. For example, if the map is a list of strings as the following: `{"############", "#P________C#", "############"}` (I put `_` instead of an empty space for the example, but the `_` characters should be replaced by empty spaces in the code), the PacMan and the Clyde are on the same row, 8 columns apart. Use this way of representing the map to design your own maps for your tests.
> - The provided `GhostMapParser#parseMap()` method receives the map, and returns a `Level` that uses the provided map.
> - If your test involves a player on the map, do not forget to create it (`PlayerFactory#createPacMan`), to register it in the level (`Level#registerPlayer`) as well as to set its direction (`Player#setDirection`).
> - You should assert the direction that the ghost takes based on its current position in the level. To do that, you need the ghost that exists in the created level. For that, we provide the `Navigation#findUnitInBoard` method.

## Exercise 2
Open `npc.ghost.Blinky` class. Note that some of its documented behaviour (patrolling its home corner, speeding up) is marked with `// TODO` and is **not implemented yet** — Blinky currently always chases the nearest player using the shortest path, with no extra twist (no "shyness" like Clyde, no bug like Pinky/Inky). Design your test cases based on the *actual, current* implementation, not on the aspirational Javadoc: this is the simplest ghost AI of the four, and a good exercise to practice writing your own map-based tests with less guidance than exercise 1.

Use specification-based testing to devise and implement tests for the method `Optional<Direction> nextAiMove()` in a `BlinkyTest` class.

> **Hints:**
> 
> `GhostMapParser` only knows how to place a `Clyde` on the map so far (see the `'C'` case in `addSquare`). You will need to extend it with a new case (e.g., `'S'`) that creates a Blinky using the `GhostFactory`.

## Exercise 3
Open `npc.ghost.Pinky` class and read its documentation and its `nextAiMove` method. Note the *targeting bug* described in the Javadoc: when Pac-Man is facing (or moving) north, Pinky's destination is computed differently than for the other three directions. This is a good example of a partition boundary you should explicitly test for.

Use the framework to devise the test cases for the method `Optional<Direction> nextAiMove()` in a `PinkyTest` class. Do not forget to test the targeting bug.

> **Hints:**
> 
> As with Blinky, you will need to extend `GhostMapParser#addSquare` with a new case (e.g., `'Y'`) that creates a Pinky using the `GhostFactory`.

## Exercise 4
Open `npc.ghost.Inky` class and see how this ghost works, and especially its `nextAiMove` method. Think about partitions/cases. What tests should you design to make sure this ghost works? Read its documentation and its source code. Use all the information you have in hands and use the specification-based testing framework! Inky has the most complicated AI of all four ghosts, since it combines Blinky's position and a point ahead of Pac-Man (like Pinky), so it also inherits Pinky's targeting bug.

Use the framework to implement the test cases of the method `Optional<Direction> nextAiMove()` in a `InkyTest` class.

### BONUS exercise
The method `squaresAheadOf` in the class `Unit` is not tested. Implement the tests cases for this method.