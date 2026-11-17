package nl.tudelft.jpacman.board;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        //arrange
        //act
        // assert
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        // arrange
        BasicSquare target = new BasicSquare();
        // act
        unit.occupy(target);
        // assert
        assertThat(unit.getSquare()).isEqualTo(target);
        assertThat(target.getOccupants().get(0)).isEqualTo(unit);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        BasicSquare target = new BasicSquare();
        unit.occupy(target);
        Square square1 = unit.getSquare();
        unit.leaveSquare();
        assertThatThrownBy(()->unit.getSquare()).isInstanceOf(AssertionError.class);
        unit.occupy(target);
        assertThat(square1).isEqualTo(unit.getSquare());
    }
}
