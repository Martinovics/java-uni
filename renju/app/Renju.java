package renju.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import renju.classes.Player;
import renju.classes.Board;
import renju.classes.Colors;




public class Renju extends JFrame {

    private JFrame boardFrame;




    private class ButtonListener implements ActionListener {

        private int rowIndex;
        private int colIndex;

        public ButtonListener(int rowIndex, int colIndex) {
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




    private void showFrame() {
        boardFrame.setVisible(true);
    }




    public Renju() {

        boardFrame = new JFrame();

        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setTitle("Renju");
        boardFrame.setSize(720, 720);
        boardFrame.setResizable(false);


        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        GridLayout layout = new GridLayout(15, 15);
        layout.setHgap(1);
        layout.setVgap(1);
        panel.setLayout(layout);

        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(row, col));
                button.setBackground(Color.ORANGE);
                button.setBorderPainted(false);
                panel.add(button);
            }
        }

        boardFrame.add(panel);
    }




    public static void main(String[] args) {
        Renju app = new Renju();
        app.showFrame();
    }

}
