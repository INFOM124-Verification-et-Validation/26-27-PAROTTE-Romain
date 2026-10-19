# Specification-based Testing

## 1. Goal, inputs and outputs
<!--
Goal: Business rules of the element under test, what's the purpose of the element under test
Input domain: All possible inputs of the element under test, containing the parameters, the current object and its attributes, and inputs from the environment (user inputs, files, database, etc.)
Output domain: All possible outputs of the element under test, containing the return value, the current object and its attributes, and outputs to the environment (user outputs, files, database, etc.) 
-->

- Goal:
- Input domain:
- Output domain:

## 2. Explore the program (if needed)
<!-- Explore the program to increase the understanding of what the program does. Consider the "happy paths" -->

## 3. Identify input and output partitions
<!-- Identify input and output partitions, which are sets of inputs or outputs that are treated the same by the program -->

### Input partitions

#### Individual inputs
<!-- Consider each input individually, derived from the specification and its own testing experience. For example, if the input is a String, individual inputs would be: null, empty string, string with one character, and string with multiple characters -->

#### Combinations of input values
<!-- Consider combinations of input values, from the previous step. The idea is to find dependencies between input values that are relevant -->

### Output partitions

## 4. Identify boundaries
<!-- Once the different input and output partitions have been identified, identify the values at the boundaries of those partitions, and see if there are any special cases that need to be tested -->

## 5. Select test cases
<!-- After devising all the partitions, a complete test suite would be the product of all partitions. However, this is usually not feasible, so we need to select a subset of test cases that are representative of the different partitions. This is done in function of the domain knowledge of the program and the experience. For example, is it interesting to test null or empty values more than once? -->

## 6. Augment test cases
<!-- Test suite can be augmented, based on creativity and experience. Do you find any interesting test cases that are not covered by the previous steps? Are there any special cases that you can think of? -->
