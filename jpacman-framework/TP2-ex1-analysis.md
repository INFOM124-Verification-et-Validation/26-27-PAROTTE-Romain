# Specification-based Testing

## 1. Goal, inputs and outputs
<!--
Goal: Business rules of the element under test, what's the purpose of the element under test
Input domain: All possible inputs of the element under test, containing the parameters, the current object and its attributes, and inputs from the environment (user inputs, files, database, etc.)
Output domain: All possible outputs of the element under test, containing the return value, the current object and its attributes, and outputs to the environment (user outputs, files, database, etc.) 
-->

- Goal: nextAiMove() method of the Clyde class will compute the next move of the Clyde ghost based on its current position and the position of Pac-Man.
- Input domain: current position of Clyde, position of Pac-Man, and the game board layout.
- Output domain: next position of Clyde.

## 3. Identify input and output partitions
<!-- Identify input and output partitions, which are sets of inputs or outputs that are treated the same by the program -->

### Input partitions

#### Individual inputs
<!-- Consider each input individually, derived from the specification and its own testing experience. For example, if the input is a String, individual inputs would be: null, empty string, string with one character, and string with multiple characters -->
Distance:
- Clyde distance < 8 squares from Pac-Man
- Clyde distance > 8 squares from Pac-Man
- Clyde distance = 8 squares from Pac-Man

Obstacles:
- Path of Clyde free
- Path of Clyde blocked
- Multiple valid moves for Clyde

#### Combinations of input values
<!-- Consider combinations of input values, from the previous step. The idea is to find dependencies between input values that are relevant -->

- Distance < 8 and path free
- Distance < 8 and path blocked
- Distance < 8 and multiple valid moves

- Distance > 8 and path free
- Distance > 8 and path blocked
- Distance > 8 and multiple valid moves

- Distance = 8 and path free
- Distance = 8 and path blocked
- Distance = 8 and multiple valid moves

### Output partitions

- Empty direction
- Direction towards Pac-Man
- Direction to the opposite of Pac-Man

## 4. Identify boundaries
<!-- Once the different input and output partitions have been identified, identify the values at the boundaries of those partitions, and see if there are any special cases that need to be tested -->

- Clyde at the edge of the board
- Pac-Man at the edge of the board
- Not valid moves:
  - Multiple Clydes on the board
  - Multiple Pac-Mans on the board
  - Clyde and Pac-Man on the same square
  - No Pac-Man on the board

## 5. Select test cases
<!-- After devising all the partitions, a complete test suite would be the product of all partitions. However, this is usually not feasible, so we need to select a subset of test cases that are representative of the different partitions. This is done in function of the domain knowledge of the program and the experience. For example, is it interesting to test null or empty values more than once? -->

Distance < 8:
- T1: Path free => Direction to the opposite of Pac-Man
- T2: Path blocked => Empty direction
- T3: Multiple valid moves => Direction to the opposite of Pac-Man

Distance > 8:
- T4: Path free => Direction towards Pac-Man
- T5: Path blocked => Empty direction
- T6: Multiple valid moves => Direction towards Pac-Man

Direction = 8:
- T7: Path free => Direction to the opposite of Pac-Man
- T8: Path blocked => Empty direction
- T9: Multiple valid moves => Direction to the opposite of Pac-Man

Boundaries:
- T10: Clyde at the edge of the board
- T11: Pac-Man at the edge of the board
- T12: Multiple Clydes on the board
- T13: Clyde ande Pac-Man on the same square
- T14: No Pac-Man on the board
