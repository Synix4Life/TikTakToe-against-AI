package io.github.synix4life.games.tiktaktoe.ui;

import io.github.synix4life.games.tiktaktoe.controller.GameActionListener;
import io.github.synix4life.games.tiktaktoe.logic.Bot;
import io.github.synix4life.games.tiktaktoe.logic.Logic;

import javax.swing.*;
import java.awt.*;


/**
 * Class to build and manage the swing-UI
 */
public class UI {
    /**
     * UI constructor that constructs the game
     * @param logicController Logic
     * @param bot Bot
     */
    public UI(Logic logicController, Bot bot, boolean botStarts){
        JFrame frame = new JFrame("TikTakToe");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));

        // Variable that stores the buttons
        JButton[][] buttons = new JButton[3][3];

        for (int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                buttons[i][j] = new JButton();

                buttons[i][j].setFont(new Font("Arial",  Font.BOLD, 40));
                buttons[i][j].setBackground(Color.GRAY);
                buttons[i][j].setOpaque(true);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBorderPainted(true);

                buttons[i][j].addActionListener(
                        new GameActionListener(i,j, buttons,logicController,bot, frame)
                );

                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);

        if(botStarts){
            // Unclickable button because out of range
            GameActionListener botStarterListener =
                    new GameActionListener(-1, -1, buttons, logicController, bot, frame);
            botStarterListener.handleBotMove();
        }
    }
}
