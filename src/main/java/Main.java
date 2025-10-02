import TikTakToe.Bot;
import TikTakToe.Logic;
import TikTakToe.UI;

public class Main {
    public static void main(String[] args) {
        /*
         * Change temperature to a value in [0,1]. 
         * Temperature = 0 means the AI plays completely random
         * Temperature = 1 means the AI plays perfectly
         */
        double temperature = 1;
        
        Logic logic = new Logic();
        Bot bot = new Bot(logic, temperature);
        new UI(logic, bot);
    }
}
