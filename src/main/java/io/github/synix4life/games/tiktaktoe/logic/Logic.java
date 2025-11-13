package io.github.synix4life.games.tiktaktoe.logic;


/**
 * Class that provides additional logic for the game-state
 */
public class Logic {
    // Private variable that stores the game-state
    private final char[][] gameState;

    /**
     * Logic constructor → Sets all values to "empty"
     */
    public Logic(){
        gameState = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                gameState[i][j] = ' ';
            }
        }
    }

    /**
     * Method to check if and who has won
     * @return Returns an int that represents the winner.
     *          -1: No winner, 0: Enemy wins, 1: You win
     */
    public int checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] != ' ' &&
                    gameState[i][0] == gameState[i][1] &&
                    gameState[i][1] == gameState[i][2]) {
                return (gameState[i][0] == 'X') ? 1 : 0;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (gameState[0][j] != ' ' &&
                    gameState[0][j] == gameState[1][j] &&
                    gameState[1][j] == gameState[2][j]) {
                return (gameState[0][j] == 'X') ? 1 : 0;
            }
        }

        // Check diagonals
        if (gameState[0][0] != ' ' &&
                gameState[0][0] == gameState[1][1] &&
                gameState[1][1] == gameState[2][2]) {
            return (gameState[0][0] == 'X') ? 1 : 0;
        }

        if (gameState[0][2] != ' ' &&
                gameState[0][2] == gameState[1][1] &&
                gameState[1][1] == gameState[2][0]) {
            return (gameState[0][2] == 'X') ? 1 : 0;
        }

        // No winner
        return -1;
    }

    /**
     * Checks for a tie
     * @return Is it a tie?
     */
    public boolean tie(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(gameState[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Updates the game state
     * @param i row
     * @param j column
     * @param is_x Is X or O?
     */
    public void update(int i, int j, boolean is_x){
        gameState[i][j] = is_x ? 'X' : 'O';
    }

    /**
     * Is a specific field empty?
     * @param i row
     * @param j col
     * @return If it is empty
     */
    public boolean isEmpty(int i, int j){
        return gameState[i][j] == ' ';
    }

    /**
     * Getter
     * @return gameState
     */
    public char[][] getGameState(){
        return gameState;
    }
}
