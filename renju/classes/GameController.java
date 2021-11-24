package renju.classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/** A játék lebonyolítására szolgáló osztály.
*/
@SuppressWarnings("serial")
public class GameController extends JPanel {

	/**
     * Egy játék melyet le kell bonyolítani.
     */
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

            if (game.hasWinner()) {
                System.out.println("Game has ended!");
                return;
            }


            JButton button = (JButton) e.getSource();

            Color color = game.board.nextColor();
            if (game.board.update(this.rowIndex, this.colIndex, color)) {
                button.setBackground(color);
            }

            // check end game
            // white wins:
            //  black has double three or double four
            //  black has more than 5
            //  white has at least 5
            // black wins
            //  black has 5

            if (game.hasDoubleOpened()) {
                game.setWinner(game.whoHasColor(Color.WHITE));
                System.out.println(game.whoHasColor(Color.BLACK).getName() + " (BLACK) has double opened 3 or 4.");
            } else if (5 < game.longestStreak(Color.BLACK)) {
                game.setWinner(game.whoHasColor(Color.WHITE));
                System.out.println(game.whoHasColor(Color.BLACK).getName() + " (BLACK) has a streak greater than 5.");
            } else if (5 <= game.longestStreak(Color.WHITE)) {
                game.setWinner(game.whoHasColor(Color.WHITE));
                System.out.println(game.whoHasColor(Color.WHITE).getName() + " (WHITE) has streak 5<=");
            } else if (game.longestStreak(Color.BLACK) == 5) {
                game.setWinner(game.whoHasColor(Color.BLACK));
                System.out.println(game.whoHasColor(Color.BLACK).getName() + " (BLACK) has a 5 streak.");
            }

            if (game.hasWinner()) {
                game.save();
                Player winner = game.getWinner();
                String winningMsg = "'" + winner.getName() + "' (" + winner.getColorName() + ") has won the game!";
                System.out.println(winningMsg);
                JOptionPane.showMessageDialog(null, winningMsg);
                return;
            }


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

    


    /** Inicializál egy játékvezérlõt.
    * @param game A játék melyet le kell bonyolítani.
	*/
    public GameController(Game game) {
        this.game = game;
    }



    
    /** Inicializálja a játék paneljét.
     * @return Játékpanel.
	*/
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
