package renju.app;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import renju.classes.*;




public class Renju {

    private JFrame frame;
    private CardLayout cardLayout;
    private Container contentPane;




    private class NewGame {

        /*
        public NewGame() {
            System.out.println("new game will be created");

            String p1Name = JOptionPane.showInputDialog(frame, "First player's name:", null);
            System.out.println(p1Name);
            String p2Name = JOptionPane.showInputDialog(frame, "Second player's name:", null);
            System.out.println(p2Name);

            Player p1 = new Player(p1Name, Colors.BLACK);
            Player p2 = new Player(p1Name, Colors.WHITE);

            Game game = new Game(p1, p2);

            JPanel gamePanel = new GameController(game).initPanel();
            gamePanel.setFocusable(true);
            gamePanel.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.isControlDown() && e.getKeyChar() != 's' && e.getKeyCode() == 83) {
                        System.out.println("game saved");
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.out.println("game exited");
                        //cardLayout.previous(contentPane);
                        cardLayout.show(contentPane, "menuPanel");
                        cardLayout.removeLayoutComponent(gamePanel);
                    }
                }
            });

            contentPane.add(gamePanel, "gamePanel");
            //cardLayout.next(contentPane);
            cardLayout.show(contentPane, "gamePanel");
            // contentPane.remove(gamePanel);
        }
        */




        public void startNewGame() {
            System.out.println("new game will be created");

            String p1Name = JOptionPane.showInputDialog(frame, "First player's name:", null);
            if (p1Name == null) return;
            String p2Name = JOptionPane.showInputDialog(frame, "Second player's name:", null);
            if (p2Name == null) return;

            Game game = new Game(new Player(p1Name, Colors.BLACK), new Player(p2Name, Colors.WHITE));


            JPanel gamePanel = new GameController(game).initPanel();
            gamePanel.setFocusable(true);
            gamePanel.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.isControlDown() && e.getKeyChar() != 's' && e.getKeyCode() == 83) {
                        System.out.println("game saved");
                        try {
                            game.save();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.out.println("game exited");
                        cardLayout.show(contentPane, "menuPanel");
                        cardLayout.removeLayoutComponent(gamePanel);
                    }
                }
            });

            contentPane.add(gamePanel, "gamePanel");
            cardLayout.show(contentPane, "gamePanel");
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
                    new NewGame().startNewGame();
                } else if (srcButton.getText().equals("Load Game")) {
                    System.out.println("game will be loaded");
                    // jtable
                } else if (srcButton.getText().equals("Statistics")) {
                    System.out.println("you can read stats");
                }
            }
        };

        newGameButton.addActionListener(menuButtonListener);
        loadGameButton.addActionListener(menuButtonListener);
        statisticsButton.addActionListener(menuButtonListener);


        // JPanel boardPanel = new GameController().initPanel();
        contentPane.add(menuPanel, "menuPanel");
        // contentPane.add(boardPanel, "boardPanel");


        frame.add(menuPanel);
        // frame.add(boardPanel);
    }



    public static void main(String[] args) {
        new Renju();
    }

 }