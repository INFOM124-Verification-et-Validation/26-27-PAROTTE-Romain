package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    @Test
    void start_test(){
        //arrange
        assertThat(game.isInProgress()).isFalse();
        when(mockLevel.remainingPellets()).thenReturn(5);
        when(mockLevel.isAnyPlayerAlive()).thenReturn(true);
        //act
        game.start();
        game.start();
        //assert
        assertThat(game.isInProgress()).isTrue();
        verify(mockLevel).addObserver(game);
        verify(mockLevel).start();

    }
    @Test
    void stop_test(){
        //arrange
        assertThat(game.isInProgress()).isFalse();
        when(mockLevel.remainingPellets()).thenReturn(5);
        when(mockLevel.isAnyPlayerAlive()).thenReturn(true);
        //act
        game.start();
        game.stop();
        game.stop();
        //assert
        assertThat(game.isInProgress()).isFalse();
        verify(mockLevel).stop();
    }
    @Test
    void level_won_test(){
        //arrange
        when(mockLevel.remainingPellets()).thenReturn(5);
        when(mockLevel.isAnyPlayerAlive()).thenReturn(true);
        //act
        game.start();
        game.levelWon();
        //assert
        assertThat(game.isInProgress()).isFalse();
        verify(mockLevel).stop();
    }
    @Test
    void level_lost_test(){
        //arrange
        when(mockLevel.remainingPellets()).thenReturn(5);
        when(mockLevel.isAnyPlayerAlive()).thenReturn(true);
        //act
        game.start();
        game.levelLost();
        //assert
        assertThat(game.isInProgress()).isFalse();
        verify(mockLevel).stop();
    }
    @Test
    void move_test(){
        //arrange
        when(mockLevel.remainingPellets()).thenReturn(5);
        when(mockLevel.isAnyPlayerAlive()).thenReturn(true);

        //act
        game.start();
        game.move(player, Direction.NORTH);
        game.stop();
        game.move(player,Direction.NORTH);
        //assert
        verify(mockLevel,times(1)).move(player,Direction.NORTH);

    }

}
