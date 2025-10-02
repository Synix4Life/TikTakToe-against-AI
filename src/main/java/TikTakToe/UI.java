package TikTakToe;

import javax.swing.*;
import java.awt.*;

public class UI {
    private final JFrame frame;
    private final JButton[][] buttons;

    /**
     * UI constructor that constructs the game
     * @param logicController Logic
     * @param bot Bot
     */
    public UI(Logic logicController, Bot bot){
        frame = new JFrame("Tik Tak Tow");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));

        buttons = new JButton[3][3];

        for (int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                buttons[i][j] = new JButton();

                buttons[i][j].setFont(new Font("Arial",  Font.BOLD, 40));
                buttons[i][j].setBackground(Color.GRAY);
                buttons[i][j].setOpaque(true);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBorderPainted(true);

                final int row = i, col = j;
                buttons[i][j].addActionListener( e -> {
                    if(! logicController.isEmpty(row,col)) return;
                    logicController.update(row, col, true);
                    if(logicController.checkWin() == 1){
                        System.out.println("You win!");
                        System.exit(0);
                    }

                    buttons[row][col].setForeground(Color.RED);
                    buttons[row][col].setText("X");

                    if(logicController.tie()){
                        System.out.println("Tie!");
                        System.exit(0);
                    }

                    int[] move = bot.nextMove();
                    if(move == null) return;
                    int br = move[0], bc = move[1];
                    logicController.update(br, bc, false);
                    buttons[br][bc].setForeground(Color.BLUE);
                    buttons[br][bc].setText("O");
                    if(logicController.checkWin()==0) {
                        System.out.println("You lose!");
                        System.exit(0);
                    }
                });

                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);

    }
}
