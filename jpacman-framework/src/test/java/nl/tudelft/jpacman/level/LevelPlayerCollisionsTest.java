package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

class LevelPlayerCollisionsTest {
 private PlayerCollisions collisions = new PlayerCollisions();
 @Test
    void collide_player_ghost(){
     //arrange
     Player player = mock(Player.class);
     Ghost ghost = mock(Ghost.class);
     //act
     collisions.collide(player,ghost);
     collisions.collide(ghost,player);
     //assert
     verify(player,times(2)).setAlive(false);
 }
 @Test
    void collide_player_pellet(){
     //arrange
     Player player = mock(Player.class);
     Pellet pellet = mock(Pellet.class);
     //act
     collisions.collide(player,pellet);
     collisions.collide(pellet,player);
     //assert
     verify(pellet,times(2)).leaveSquare();
     verify(player,times(2)).addPoints(pellet.getValue());
 }
 @Test
    void collide_ghost_pellet(){
     //arrange
     Pellet pellet = mock(Pellet.class);
     Ghost ghost = mock(Ghost.class);
     //act
     collisions.collide(pellet,ghost);
     collisions.collide(ghost,pellet);
     //assert
     verify(pellet,times(0)).leaveSquare();
 }
}
