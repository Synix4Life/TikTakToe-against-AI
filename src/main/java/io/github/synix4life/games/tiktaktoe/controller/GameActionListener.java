package io.github.synix4life.games.tiktaktoe.controller;

import io.github.synix4life.games.tiktaktoe.logic.Bot;
import io.github.synix4life.games.tiktaktoe.logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class that provides ActionListener logic
 */
public class GameActionListener implements ActionListener {
    private final int row;
    private final int col;
    private final JButton[][] buttons;
    private final Logic logicController;
    private final Bot bot;
    private final JFrame frame;

    /**
     * Constructor
     * @param row Row
     * @param col Col
     * @param buttons JButtons
     * @param logicController Logic
     * @param bot Bot
     * @param frame JFrame
     */
    public GameActionListener(int row, int col, JButton[][] buttons, Logic logicController, Bot bot, JFrame frame) {
        this.row = row;
        this.col = col;
        this.buttons = buttons;
        this.logicController = logicController;
        this.bot = bot;
        this.frame = frame;
    }

    /**
     * ActionListener
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!handlePlayerMove()) { return; }
        checkTie();
        handleBotMove();
        checkTie();
    }

    /**
     * Handles player move
     * @return Move on the specific field possible
     */
    private boolean handlePlayerMove(){
        if(! logicController.isEmpty(row,col)) return false;
        logicController.update(row, col, true);

        buttons[row][col].setForeground(Color.GREEN);
        buttons[row][col].setText("X");

        if(logicController.checkWin() == 1){
            JOptionPane.showMessageDialog(frame, "You win!");
            System.exit(0);
        }
        return true;
    }

    /**
     * Handles Bot move
     */
    public void handleBotMove(){
        int[] move = bot.nextMove();
        if(move == null) return;
        int br = move[0], bc = move[1];
        logicController.update(br, bc, false);
        buttons[br][bc].setForeground(Color.RED);
        buttons[br][bc].setText("O");

        if(logicController.checkWin()==0) {
            JOptionPane.showMessageDialog(frame, "You lose!");
            System.exit(0);
        }
    }

    /**
     * Method to check winning and show the message
     */
    private void checkTie(){
        if(logicController.tie()){
            JOptionPane.showMessageDialog(frame, "Tie!");
            System.exit(0);
        }
    }
}
