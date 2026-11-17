package nl.tudelft.jpacman.level;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;
import java.util.ArrayList;
import java.util.List;
public class MapParserTest {
    private MapParser parser;
    private LevelFactory levelFactoryMock;
    private BoardFactory boardFactoryMock;
    @BeforeEach
    void setupTest(){
        levelFactoryMock = mock(LevelFactory.class);
        boardFactoryMock = mock(BoardFactory.class);
        parser = new MapParser(levelFactoryMock,boardFactoryMock);
        reset(levelFactoryMock);
        reset(boardFactoryMock);
    }

    @Test
    void checkMapFormatTestInvalidEmpty(){
        //Arrange
        List<String> map= new ArrayList<>() {
        };
        //Act Assert
        assertThatThrownBy(()->{parser.checkMapFormat(map);}).isInstanceOf(PacmanConfigurationException.class).hasMessageContaining("at least 1 row");
    }
    //CHECK MAP TEST
    //*******************************************
    @Test
    void checkMapFormatTestInvalidNull(){
        //Arrange
        List<String> map= null;
        //Act Assert
        assertThatThrownBy(()->{parser.checkMapFormat(map);}).isInstanceOf(PacmanConfigurationException.class).hasMessageContaining("cannot be null");
    }
    @Test
    void checkMapFormatTestInvalidEmptyLine(){
        //Arrange
        List<String> map= new ArrayList<>();
        map.add("");
        //Act Assert
        assertThatThrownBy(()->{parser.checkMapFormat(map);}).isInstanceOf(PacmanConfigurationException.class).hasMessageContaining("cannot be empty");
    }
    @Test
    void checkMapFormatTestInvalidWeirdLine(){
        //Arrange
        List<String> map= new ArrayList<>();
        map.add("####");
        map.add("#  #");
        map.add("# #");
        map.add("####");
        //Act Assert
        assertThatThrownBy(()->{parser.checkMapFormat(map);}).isInstanceOf(PacmanConfigurationException.class).hasMessageContaining("not of equal width");
    }
    @Test
    void checkMapFormatTestValid(){
        //Arrange
        List<String> map= new ArrayList<>();
        map.add("####");
        map.add("#  #");
        map.add("#  #");
        map.add("####");
        //Act Assert
        parser.checkMapFormat(map);
    }
    //*******************************************
    //make ghost square test
    //*******************************************
    @Test
    void makeGhostSquareTest(){
        List<Ghost> ghosts= new ArrayList<>();
        Ghost ghost= mock(Ghost.class);
        Square ghostSquare = mock(Square.class);
        when(boardFactoryMock.createGround()).thenReturn(ghostSquare);
        assertThat(parser.makeGhostSquare(ghosts,ghost)).isEqualTo(ghostSquare);
        verify(ghost).occupy(ghostSquare);
    }
    //*******************************************
    //parseMap Test
    //*******************************************
    @Test
    void parseMapTestValidMap(){
        List<String> map= new ArrayList<>();
        map.add("####");
        map.add("#P #");
        map.add("#.G#");
        map.add("####");
        Board boardMock= mock(Board.class);
        Square ghostSquare = mock(Square.class);
        Level levelMock = mock(Level.class);
        Ghost ghost = mock(Ghost.class);
        Pellet pellet= mock(Pellet.class);
        when(boardFactoryMock.createGround()).thenReturn(ghostSquare);
        when(boardFactoryMock.createBoard(any())).thenReturn(boardMock);
        when(boardFactoryMock.createWall()).thenReturn(ghostSquare);
        when(levelFactoryMock.createLevel(any(),any(),any())).thenReturn(levelMock);
        when(levelFactoryMock.createGhost()).thenReturn(ghost);
        when(levelFactoryMock.createPellet()).thenReturn(pellet);
        assertThat(parser.parseMap(map)).isEqualTo(levelMock);
        verify(boardFactoryMock,times(12)).createWall();
        verify(pellet).occupy(ghostSquare);
        verify(ghost).occupy(ghostSquare);
    }
    @Test
    void parseMapTestInvalidMap() {
        List<String> map = new ArrayList<>();
        map.add("####");
        map.add("#P -");
        map.add("#.G#");
        map.add("####");
        Board boardMock = mock(Board.class);
        Square ghostSquare = mock(Square.class);
        Level levelMock = mock(Level.class);
        Ghost ghost = mock(Ghost.class);
        Pellet pellet = mock(Pellet.class);
        when(boardFactoryMock.createGround()).thenReturn(ghostSquare);
        when(boardFactoryMock.createBoard(any())).thenReturn(boardMock);
        when(boardFactoryMock.createWall()).thenReturn(ghostSquare);
        when(levelFactoryMock.createLevel(any(), any(), any())).thenReturn(levelMock);
        when(levelFactoryMock.createGhost()).thenReturn(ghost);
        when(levelFactoryMock.createPellet()).thenReturn(pellet);
        assertThatThrownBy(() -> parser.parseMap(map)).isInstanceOf(PacmanConfigurationException.class).hasMessageContaining("Invalid character");
    }
}
