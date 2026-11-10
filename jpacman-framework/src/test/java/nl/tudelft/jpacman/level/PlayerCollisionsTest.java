package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.EmptySprite;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCollisionsTest {
    PacManSprites sprites = new PacManSprites();
    BoardFactory boardFactory = new BoardFactory(sprites);
    GhostFactory ghostFactory = new GhostFactory(sprites);
    PlayerFactory playerFactory = new PlayerFactory(sprites);

    Ghost ghost;
    Player pacman;
    PlayerCollisions playerCollisions;

    @BeforeEach
    void init() {
        ghost = ghostFactory.createBlinky();
        pacman = playerFactory.createPacMan();
        playerCollisions = new PlayerCollisions();
    }

    @Test
    void playerCollideOnePelletTest() {
        Pellet pellet = new Pellet(10, new EmptySprite());
        Square square = boardFactory.createGround();
        pellet.occupy(square);

        playerCollisions.collide(pacman, pellet);

        assertEquals(10, pacman.getScore());
        assertFalse(pellet.hasSquare());
    }
}
