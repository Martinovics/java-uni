package renju.classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class GameController extends JPanel {


    private Game game;


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
            System.out.println("button " + this.rowIndex + " " + this.colIndex + " clicked");
            button.setBackground(Color.BLACK);
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
                button.setBackground(Color.ORANGE);
                button.setBorderPainted(false);
                panel.add(button);
            }
        }

        panel.setFocusable(true);

        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Pressed " + e.getKeyChar());
            }
        });

        return panel;
    }

}
