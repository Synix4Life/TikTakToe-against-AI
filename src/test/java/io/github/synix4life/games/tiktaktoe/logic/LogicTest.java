package io.github.synix4life.games.tiktaktoe.logic;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the Logic
*/
@DisplayName("Logic Test")
public class LogicTest {
    @Test
    public void testLogicConstructor(){
        char[][] expected = new  char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        Logic actual = new Logic();
        assertTrue(Arrays.deepEquals(expected, actual.getGameState()));
    }

    @Test
    public void testLogicUpdate(){
        char[][] expected = new  char[][]{
                {' ', ' ', ' '},
                {' ', 'X', ' '},
                {' ', ' ', ' '}
        };
        Logic actual = new Logic();
        actual.update(1,1, true);
        assertTrue(Arrays.deepEquals(expected, actual.getGameState()));
    }

    @Test
    public void testLogicCheckWin(){
        char[][] reference = new  char[][]{
                {'X', 'O', ' '},
                {'O', 'X', ' '},
                {' ', 'O', 'X'}
        };
        Logic actual = new Logic();
        setAll(actual, reference);
        assertEquals(1, actual.checkWin());
    }

    @Test
    public void testLogicTie(){
        char[][] reference = new  char[][]{
                {'X', 'O', 'O'},
                {'O', 'X', 'X'},
                {'X', 'O', 'O'}
        };
        Logic actual = new Logic();
        setAll(actual, reference);
        assertTrue(actual.tie());
    }

    private void setAll(Logic logic, char[][] reference){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                logic.update(i,j,reference[i][j]=='X');
            }
        }
    }
}
