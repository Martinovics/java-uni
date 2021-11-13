package renju.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class Renju {

    private CardLayout cardLayout;
    private Container contentPane;




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

            if (this.rowIndex == 0 && this.colIndex == 0) {
                cardLayout.previous(contentPane);
            }

        }
    }




    public Renju() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Renju");
        frame.setSize(720, 720);
        frame.setResizable(false);
        frame.setVisible(true);

        cardLayout = new CardLayout();
        contentPane = frame.getContentPane();
        contentPane.setLayout(cardLayout);

        JPanel panelMenu = new JPanel();
        JPanel panelBoard = new JPanel();
        JButton button1 = new JButton("previous frame!");
        JButton button2 = new JButton("next frame");
        contentPane.add(panelMenu, "Panel 1");
        contentPane.add(panelBoard, "Panel 2");



        panelMenu.add(button2);


        panelBoard.setBackground(Color.DARK_GRAY);

        GridLayout layout = new GridLayout(15, 15);
        layout.setHgap(1);
        layout.setVgap(1);
        panelBoard.setLayout(layout);

        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                JButton button = new JButton();
                button.addActionListener(new BoardButtonListener(row, col));
                button.setBackground(Color.ORANGE);
                button.setBorderPainted(false);
                panelBoard.add(button);
            }
        }

        ActionListener btnListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(contentPane);
            }
        };

        button1.addActionListener(btnListener);
        button2.addActionListener(btnListener);


        frame.add(panelMenu);
        frame.add(panelBoard);
    }



    public static void main(String[] args) {
        Renju app = new Renju();
    }

 }