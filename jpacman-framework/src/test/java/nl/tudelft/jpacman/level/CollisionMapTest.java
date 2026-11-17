package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.EmptySprite;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

abstract class CollisionMapTest {
    CollisionMap collisionMap;
    PacManSprites sprites = new PacManSprites();
    BoardFactory boardFactory = new BoardFactory(sprites);
    GhostFactory ghostFactory = new GhostFactory(sprites);
    PlayerFactory playerFactory = new PlayerFactory(sprites);

    Ghost ghost;
    Player pacman;
    PlayerCollisions playerCollisions;

    abstract CollisionMap createCollisionMap();

    @BeforeEach
    void init() {
        collisionMap = createCollisionMap();
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
