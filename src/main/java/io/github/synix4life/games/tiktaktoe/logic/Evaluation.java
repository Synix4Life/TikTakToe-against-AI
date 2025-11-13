package io.github.synix4life.games.tiktaktoe.logic;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class that provides evaluation logic
 */
public class Evaluation {
    private final Random rand ;

    /**
     * Constructor
     */
    public Evaluation() {
        rand = new Random();
    }

    /**
     * Evaluates the next move
     * @param gameState GameState
     * @param temperature Temperature in [0,1]
     * @return Next move, null if none possible
     */
    public int[] evaluate(char[][] gameState, double temperature) {
        var move = win(gameState);

        if(move != null && rand.nextDouble() < temperature) {
            return move;
        }

        move = block(gameState);

        if(move != null && rand.nextDouble() < temperature) {
            return move;
        }

        move = center(gameState);

        if(move != null && rand.nextDouble() < temperature) {
            return move;
        }

        move = corner(gameState);

        if(move != null && rand.nextDouble() < temperature) {
            return move;
        }

        move = sides(gameState);

        if(move != null && rand.nextDouble() < temperature) {
            return move;
        }

        return randomMove(gameState);
    }

    /**
     * Checks for winning condition
     * @param gameState GameState
     * @return A move if possible, else null
     */
    private int[] win(char[][] gameState){
        return findCriticalMove(gameState, 'O');
    }

    /**
     * Checks for blocking condition
     * @param gameState GameState
     * @return A move if possible, else null
     */
    private int[] block(char[][] gameState){
        return findCriticalMove(gameState, 'X');
    }

    /**
     * Checks for centering condition
     * @param gameState GameState
     * @return A move if possible, else null
     */
    private int[] center(char[][] gameState){
        return gameState[1][1] == ' ' ? new int[]{1,1} : null;
    }

    /**
     * Checks for corner condition
     * @param gameState GameState
     * @return A move if possible, else null
     */
    private int[] corner(char[][] gameState){
        ArrayList<int[]> cells = new ArrayList<>();

        if (gameState[0][0] == ' ') cells.add(new int[]{0,0});
        if (gameState[0][2] == ' ') cells.add(new int[]{0,2});
        if (gameState[2][0] == ' ') cells.add(new int[]{2,0});
        if (gameState[2][2] == ' ') cells.add(new int[]{2,2});

        return cells.isEmpty() ? null : cells.get(rand.nextInt(cells.size()));
    }

    /**
     * Checks for side condition
     * @param gameState GameState
     * @return A move if possible, else null
     */
    private int[] sides(char[][] gameState){
        ArrayList<int[]> cells = new ArrayList<>();

        if (gameState[0][1] == ' ') cells.add(new int[]{0,1});
        if (gameState[1][0] == ' ') cells.add(new int[]{1,0});
        if (gameState[1][2] == ' ') cells.add(new int[]{1,2});
        if (gameState[2][1] == ' ') cells.add(new int[]{2,1});

        return cells.isEmpty() ? null : cells.get(rand.nextInt(cells.size()));
    }

    /**
     * Helper regarding block and win
     * @param gameState GameState
     * @param target X or O
     * @return A move if possible, else null
     */
    private int[] findCriticalMove(char[][] gameState, char target){
        int count, empty;
        ArrayList<int[]> cells = new ArrayList<>();

        // Check rows
        for(int i = 0; i < 3; i++){
            count = 0; empty = -1;
            for(int j = 0; j < 3; j++){
                if(gameState[i][j] == target) count++;
                if(gameState[i][j] == ' ') empty = j;
            }
            if(count == 2 && empty != -1) cells.add(new int[]{i,empty});
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            count = 0; empty = -1;
            for (int i = 0; i < 3; i++) {
                if (gameState[i][j] == target) count++;
                if (gameState[i][j] == ' ') empty = i;
            }
            if (count == 2 && empty != -1) cells.add(new int[]{empty, j});
        }

        // Check diagonal top-left → bottom-right
        count = 0; empty = -1;
        for (int i = 0; i < 3; i++) {
            if (gameState[i][i] == target) count++;
            if (gameState[i][i] == ' ') empty = i;
        }
        if (count == 2 && empty != -1) cells.add(new int[]{empty, empty});

        // Check diagonal top-right → bottom-left
        count = 0; empty = -1;
        for (int i = 0; i < 3; i++) {
            if (gameState[i][2 - i] == target) count++;
            if (gameState[i][2 - i] == ' ') empty = i;
        }
        if (count == 2 && empty != -1) cells.add(new int[]{empty, 2 - empty});

        return cells.isEmpty() ? null : cells.get(rand.nextInt(cells.size()));
    }

    /**
     * Returns a random move
     * @param gameState GameState
     * @return A move, null if none possible
     */
    private int[] randomMove(char[][] gameState){
        ArrayList<int[]> cells = new ArrayList<>();

        // Find all empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j] == ' ') {
                    cells.add(new int[]{i, j});
                }
            }
        }
        return cells.isEmpty() ? null : cells.get(rand.nextInt(cells.size()));
    }
}
