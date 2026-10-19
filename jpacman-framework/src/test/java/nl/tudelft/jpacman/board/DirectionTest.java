package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */
class DirectionTest {

    /**
     * Do we get the correct delta when moving north?
     */
    @Test
    void testNorth() {
        //arrange
        Direction north =Direction.NORTH;
        //act
        int x = north.getDeltaX();
        int y = north.getDeltaY();
        //assert
        assertThat(x).isEqualTo(0);
        assertThat(y).isEqualTo(-1);
    }

    @Test
    void testSouth() {
        //arrange
        Direction south =Direction.SOUTH;
        //act
        int x = south.getDeltaX();
        int y = south.getDeltaY();
        //assert
        assertThat(x).isEqualTo(0);
        assertThat(y).isEqualTo(1);
    }

    @Test
    void testEast() {
        //arrange
        Direction east =Direction.EAST;
        //act
        int x = east.getDeltaX();
        int y = east.getDeltaY();
        //assert
        assertThat(x).isEqualTo(1);
        assertThat(y).isEqualTo(0);

    }

    @Test
    void testWest() {
        //arrange
        Direction west =Direction.WEST;
        //act
        int x = west.getDeltaX();
        int y = west.getDeltaY();
        //assert
        assertThat(x).isEqualTo(-1);
        assertThat(y).isEqualTo(0);
    }
}
