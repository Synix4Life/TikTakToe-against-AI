package io.github.synix4life.games.tiktaktoe.logic;


/**
 * Class to create a bot to play against
 */
public class Bot {
    private final Logic logic;
    private final Evaluation evaluator;
    private final double temperature;

    /**
     * Constructor
     * @param logic Logic
     * @param temperature Temperature in [0,1]
     */
    public Bot(Logic logic, double temperature) throws IllegalArgumentException {
        this.logic = logic;
        this.evaluator = new Evaluation();
        this.temperature = temperature;
        if(temperature > 1 || temperature < 0) {
            throw new IllegalArgumentException("Temperature must be between 0 and 1.");
        }
    }

    /**
     * Determines the next move
     * @return The next move, null if none possible
     */
    public int[] nextMove(){
        return evaluator.evaluate(logic.getGameState(), temperature);
    }


}
