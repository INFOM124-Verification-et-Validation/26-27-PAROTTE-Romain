package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.mock;

/**
 * Tests the game's interaction with its level.
 */
class GameTest {
    private Level mockLevel;
    private Game game;
    private Player player;

    /**
     * Creates a game backed by a mocked level.
     */
    @BeforeEach
    void setUp() {
        mockLevel = mock(Level.class);
        PacManSprites sprites = new PacManSprites();
        PlayerFactory playerFactory = new PlayerFactory(sprites);
        GameFactory gameFactory = new GameFactory(playerFactory);
        game = gameFactory.createSinglePlayerGame(mockLevel);
        player = game.getPlayers().get(0);
        reset(mockLevel);
    }


}
