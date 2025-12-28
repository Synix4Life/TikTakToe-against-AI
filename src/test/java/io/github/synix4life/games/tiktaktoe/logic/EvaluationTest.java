package io.github.synix4life.games.tiktaktoe.logic;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class to test the Evaluation.
 * Forces a choice in a specific rule in order, since they are private by design
 */
@DisplayName("Evaluation Test")
public class EvaluationTest {

    private Evaluation evaluation;

    @BeforeEach
    public void setUp() { // Setup
        evaluation = new Evaluation();
    }

    @Test
    public void testEvaluateWin() { // Win
        char[][] board = new  char[][]{
                {'O', ' ', 'O'},
                {'O', 'X', ' '},
                {'X', 'O', ' '}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        assertArrayEquals(new int[]{0,1}, move);
    }

    @Test
    public void testEvaluateBlock() { // Block
        char[][] board = new  char[][]{
                {' ', ' ', 'O'},
                {'X', 'X', ' '},
                {' ', 'O', ' '}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        assertArrayEquals(new int[]{1,2}, move);
    }

    @Test
    public void testEvaluateCenter() { // Center
        char[][] board = new  char[][]{
                {' ', ' ', ' '},
                {'X', ' ', ' '},
                {' ', ' ', ' '}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        assertArrayEquals(new int[]{1,1}, move);
    }

    @Test
    public void testEvaluateOppositeCorner(){ // Opposite Corner
        char[][] board = new  char[][]{
                {' ', ' ', ' '},
                {' ', 'O', ' '},
                {' ', ' ', 'X'}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        System.out.print(move);
        assertArrayEquals(new int[]{0,0}, move);
    }

    @Test
    public void testEvaluateCorner() { // Corner
        char[][] board = new  char[][]{
                {'O', ' ', ' '},
                {'X', 'X', 'O'},
                {'O', ' ', 'X'}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        assertArrayEquals(new int[]{0,2}, move);
    }

    @Test
    public void testEvaluateEdge() { // Edge
        char[][] board = new  char[][]{
                {'O', ' ', 'X'},
                {'X', 'X', 'O'},
                {'O', 'O', 'X'}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNotNull(move);
        assertArrayEquals(new int[]{0,1}, move);
    }

    @Test
    public void testEvaluateIsNull() { // Null → No move possible
        char[][] board = new  char[][]{
                {'O', 'O', 'X'},
                {'X', 'X', 'O'},
                {'O', 'O', 'X'}
        };
        int[] move = evaluation.evaluate(board, 1);
        assertNull(move);
    }

}
