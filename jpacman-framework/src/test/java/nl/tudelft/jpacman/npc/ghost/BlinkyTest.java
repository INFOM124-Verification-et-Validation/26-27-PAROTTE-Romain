package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlinkyTest {
    PacManSprites sprites = new PacManSprites();
    GhostFactory ghostFactory = new GhostFactory(sprites);
    LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);
    BoardFactory boardFactory = new BoardFactory(sprites);
    public MapParser mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    public PlayerFactory playerFactory = new PlayerFactory(sprites);


    // T1
    @Test
    void blinkyShouldChasePac() {
        List<String> map = Arrays.asList(
            "############",
            "#P      B  #",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.WEST);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertNotNull(blinky);

        Optional<Direction> move = blinky.nextAiMove(); //cycling to corner Phase

        assertTrue(move.isPresent());
        assertEquals(Direction.EAST, move.get());
    }

    // T2
    @Test
    void blinkyShouldNotMoveTest() {
        List<String> map = Arrays.asList(
            "############",
            "#P      #B##",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertNotNull(blinky);

        Optional<Direction> move = blinky.nextAiMove();

        assertTrue(move.isEmpty());
    }

    // T4
    @Test
    void blinkyShouldGetToCorner() {
        List<String> map = Arrays.asList(
            "###############",
            "#P          B.#",
            "###############"
        );

        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        Pellet corner = Navigation.findUnitInBoard(Pellet.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertNotNull(blinky);
        int repeat=8;
        Optional<Direction> move;
        while(repeat>0){
         move= blinky.nextAiMove();
         repeat--;
        }
        move= blinky.nextAiMove();
        assertTrue(move.isPresent());
        assertEquals(blinky.getCornerTarget(), corner.getSquare());
        assertEquals(Blinky.State.CORNER, blinky.getCurrentState());
        assertEquals(Direction.EAST, move.get());
    }
}
