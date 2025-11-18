package io.github.synix4life.games.tiktaktoe;

import io.github.synix4life.games.tiktaktoe.logic.*;
import io.github.synix4life.games.tiktaktoe.ui.UI;


public class Main {
    public static void main(String[] args) {
        /*
         * Change temperature to a value in [0,1]. 
         * Temperature = 0 means the AI plays completely random
         * Temperature = 1 means the AI plays perfectly
         */
        double temperature = 1;

        /*
         * Value that determines if the bot should start
         * botStarts = true means that the bot starts
         * botStarts = false means that you start
         */
        boolean botStarts = false;
        
        Logic logic = new Logic();
        Bot bot = new Bot(logic, temperature);
        new UI(logic, bot, botStarts);
    }
}
