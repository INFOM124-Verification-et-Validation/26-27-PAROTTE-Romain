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

class ClydeTest {
    PacManSprites sprites = new PacManSprites();
    GhostFactory ghostFactory = new GhostFactory(sprites);
    LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);
    BoardFactory boardFactory = new BoardFactory(sprites);
    public MapParser mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    public PlayerFactory playerFactory = new PlayerFactory(sprites);


    // T1
    @Test
    void clydeShouldGoAwayFromPacmanWhenCloseTest() {
        List<String> map = Arrays.asList(
            "############",
            "#P      C  #",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);

        Optional<Direction> move = clyde.nextAiMove();

        assertTrue(move.isPresent());
        assertEquals(Direction.EAST, move.get());
    }

    // T2
    @Test
    void clydeShouldNotMoveTest() {
        List<String> map = Arrays.asList(
            "############",
            "#P      #C##",
            "############"
        );
        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);

        Optional<Direction> move = clyde.nextAiMove();

        assertTrue(move.isEmpty());
    }

    // T4
    @Test
    void clydeShouldGetCloserToPacManWhenFarTest() {
        List<String> map = Arrays.asList(
            "############",
            "#P        C#",
            "############"
        );

        Level level = mapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);

        Optional<Direction> move = clyde.nextAiMove();

        assertTrue(move.isPresent());
        assertEquals(Direction.WEST, move.get());
    }
}
