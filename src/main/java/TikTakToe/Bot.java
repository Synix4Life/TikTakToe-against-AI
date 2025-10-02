package TikTakToe;

public class Bot {
    private final Logic logic;
    private final Evaluation evaluator;
    private final double temperature;

    /**
     * Constructor
     * @param logic Logic
     * @param temperature Temperature in [0,1]
     */
    public Bot(Logic logic, double temperature) {
        this.logic = logic;
        this.evaluator = new Evaluation();
        this.temperature = temperature;
    }

    /**
     * Determines the next move
     * @return The next move, null if none possible
     */
    public int[] nextMove(){
        return evaluator.evaluate(logic.getGameState(), temperature);
    }


}
