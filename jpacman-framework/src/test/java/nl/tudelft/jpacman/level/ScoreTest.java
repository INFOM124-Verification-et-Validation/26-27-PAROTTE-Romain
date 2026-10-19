package nl.tudelft.jpacman.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite to confirm that {@link Score} correctly stores and
 * accumulates points.
 */
class ScoreTest {

    /**
     * The score under test.
     */
    private Score score;

    /**
     * Resets the score under test.
     */
    @BeforeEach
    void setUp() {
        score = new Score(0);
    }

    /**
     * Asserts that a freshly created score starts at the given amount of
     * points.
     */
    @Test
    void testInitialPoints() {
        // arrange
        // act
        //both done is setUp()
        // assert
        assertThat(score.getPoints()).isEqualTo(0);
    }

    /**
     * Asserts that adding points increases the total by that amount.
     */
    @Test
    void testAddPoints() {
        // arrange
        int value =5;
        // act
        score.addPoints(value);
        // assert
        assertThat(score.getPoints()).isEqualTo(value);
    }

    /**
     * Asserts that adding points twice accumulates both amounts.
     */
    @Test
    void testAddPointsTwice() {
        // arrange
        int value=5;
        // act
        score.addPoints(value);
        score.addPoints(value);
        // assert
        assertThat(score.getPoints()).isEqualTo(value*2);
    }

    /**
     * Asserts that adding negative points decreases the total by that amount.
     */
    @Test
    void testAddNegativePoints() {
        // arrange
        int value=-5;
        // act
        score.addPoints(value);
        // assert
        assertThat(score.getPoints()).isEqualTo(value);
    }
}
