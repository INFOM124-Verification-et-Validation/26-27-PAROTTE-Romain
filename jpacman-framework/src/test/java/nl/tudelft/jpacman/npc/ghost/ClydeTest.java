package nl.tudelft.jpacman.npc.ghost;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ClydeTest {
    private Level level;
    private ArrayList<String> map= new ArrayList<>();
    private Clyde clyde;
    private GhostMapParser gmf;
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        gmf = new GhostMapParser(new LevelFactory(sprites,new GhostFactory(sprites)),new BoardFactory(sprites),new GhostFactory(sprites));
    }

    /*
    * Testing clyde behaviour when far from Pacman  >8square
    * */
    @Test
    void farFromPac(){
        map.add("##############");
        map.add("#P          C#");
        map.add("##############");
        level = gmf.parseMap(map);
        clyde = (Clyde) level.getBoard().squareAt(0,11).getOccupants().get(0);
        assertThat(clyde.nextAiMove()).isEqualTo(Direction.WEST);
    }

    /*
    * Testing clyde behaviour when near to Pacman =<8square*/
    @Test
    void nearToPac(){}

    /*
    * Testing behaviour switch when clyde come near the 8 square border from far to near
    * */
    @Test
    void farToNear(){}

    /*
     * Testing behaviour switch when clyde come near the 8 square border from near to far
     * */
    @Test
    void nearToFar(){}
}
