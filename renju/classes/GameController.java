package renju.classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class GameController extends JPanel {

    public Game game;


    private class BoardButtonListener implements ActionListener {

        private int rowIndex;
        private int colIndex;

        public BoardButtonListener(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            Color color = game.board.nextColor();
            if (game.board.update(this.rowIndex, this.colIndex, color)) {
                button.setBackground(color);
            }

            System.out.println(color + " longest streak=" + game.longestStreak(color));
            System.out.println(color + " has double opened=" + game.hasDoubleOpened());

            // 2 black and 1 white -> players choose color
            if (game.board.countColor(Color.BLACK) == 2 && game.board.countColor(Color.WHITE) == 1) {
                int choice = JOptionPane.showConfirmDialog(null, "'" + game.p1.getName() + "' will be BLACK!");
                if (choice == JOptionPane.YES_OPTION) {
                    game.p1.setColor(Color.BLACK);
                    game.p2.setColor(Color.WHITE);
                } else {
                    game.p1.setColor(Color.WHITE);
                    game.p2.setColor(Color.BLACK);
                }
            }


            color = game.board.nextColor();
            Player player = game.whoHasColor(color);
            if (game.board.countColor(Color.BLACK) + game.board.countColor(Color.WHITE) < 3) {
                System.out.println("P1: " + player.getColorName());
            } else {
                if (color.equals(Color.ORANGE)) {
                    player = game.whoHasColor(Color.WHITE);
                    System.out.println(player.getName() + ": remove a BLACK tile"); // white removes black
                } else {
                    System.out.println(player.getName() + ": " + player.getColorName());
                }
            }

        }
    }




    public GameController(Game game) {
        this.game = game;
    }




    public JPanel initPanel() {
        JPanel panel = new JPanel();

        panel.setBackground(Color.DARK_GRAY);

        GridLayout layout = new GridLayout(15, 15);
        layout.setHgap(1);
        layout.setVgap(1);
        panel.setLayout(layout);

        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                JButton button = new JButton();
                button.addActionListener(new BoardButtonListener(row, col));
                button.setBackground(game.board.colorAt(row, col));
                button.setFocusable(false);
                button.setBorderPainted(false);
                panel.add(button);
            }
        }

        System.out.println("P1: BLACK");
        return panel;
    }

}
