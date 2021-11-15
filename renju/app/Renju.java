package renju.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import renju.classes.GameController;




public class Renju {

    private CardLayout cardLayout;
    private Container contentPane;




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


        JPanel menuPanel = new JPanel(new GridLayout(3, 1));

        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton statisticsButton = new JButton("Statistics");

        menuPanel.add(newGameButton);
        menuPanel.add(loadGameButton);
        menuPanel.add(statisticsButton);

        ActionListener menuButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // cardLayout.next(contentPane);
                JButton srcButton = (JButton) e.getSource();

                if (srcButton.getText().equals("New Game")) {
                    System.out.println("new game will be created");
                } else if (srcButton.getText().equals("Load Game")) {
                    System.out.println("game will be loaded");
                } else if (srcButton.getText().equals("Statistics")) {
                    System.out.println("you can read stats");
                }
            }
        };

        newGameButton.addActionListener(menuButtonListener);
        loadGameButton.addActionListener(menuButtonListener);
        statisticsButton.addActionListener(menuButtonListener);


        JPanel boardPanel = new GameController().initPanel();
        contentPane.add(menuPanel, "menuPanel");
        contentPane.add(boardPanel, "boardPanel");


        frame.add(menuPanel);
        frame.add(boardPanel);
    }



    public static void main(String[] args) {
        new Renju();
    }

 }