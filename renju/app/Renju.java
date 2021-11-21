package renju.app;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;

import renju.classes.*;




public class Renju {

    private JFrame frame;
    private CardLayout cardLayout;
    private Container contentPane;




    private class NewGame {

        public void startGame() {

            System.out.println("new game will be created");

            String p1Name = JOptionPane.showInputDialog(frame, "First player's name:", null);
            if (p1Name == null) return;
            String p2Name = JOptionPane.showInputDialog(frame, "Second player's name:", null);
            if (p2Name == null) return;
            if (p1Name.equals(p2Name)) {
                System.out.println("Names cannot match!");
                return;
            }

            Game game = new Game(new Player(p1Name, Color.BLACK), new Player(p2Name, Color.WHITE));


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




    private class LoadGame {

        private GameData data;
        private JTable jt;
        private JPanel loadPanel;


        public LoadGame() {
            try {
                data = new GameData();
                File dir = new File("saves");
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        System.out.println(file.getName());
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + file.getName()));
                        data.games.add((Game)ois.readObject());
                        ois.close();
                    }
                }
                Collections.reverse(data.games);
            } catch(Exception ex) {
                ex.printStackTrace();
            }


            jt = new JTable(data);
            jt.setFillsViewportHeight(true);
            JScrollPane sp = new JScrollPane(jt);
            sp.setVisible(true);

            loadPanel = new JPanel();
            loadPanel.setFocusable(true);
            JButton loadButton = new JButton("Load");
            JButton backButton = new JButton("Back");
            loadButton.addActionListener(new ButtonListener());
            backButton.addActionListener(new ButtonListener());
            loadPanel.add(loadButton);
            loadPanel.add(backButton);
            loadPanel.add(sp);

            contentPane.add(loadPanel, "loadPanel");
            cardLayout.show(contentPane, "loadPanel");
        }


        private class ButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcButton = (JButton) e.getSource();

                if (srcButton.getText().equals("Load")) {

                    if (jt.getSelectedRow() == -1) return;


                    GameController gameController = new GameController(data.games.get(jt.getSelectedRow()));
                    JPanel gamePanel = gameController.initPanel();
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
                                    gameController.game.save();
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
                    gamePanel.setFocusable(true);
                    gamePanel.requestFocusInWindow();
                } else if (srcButton.getText().equals("Back")) {
                    cardLayout.show(contentPane, "menuPanel");
                    cardLayout.removeLayoutComponent(loadPanel);
                }
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
                    new NewGame().startGame();
                } else if (srcButton.getText().equals("Load Game")) {
                    System.out.println("game will be loaded");
                    new LoadGame();
                } else if (srcButton.getText().equals("Statistics")) {
                    System.out.println("you can read stats");
                    // collections
                }
            }
        };

        newGameButton.addActionListener(menuButtonListener);
        loadGameButton.addActionListener(menuButtonListener);
        statisticsButton.addActionListener(menuButtonListener);


        // JPanel boardPanel = new GameController().initPanel();
        contentPane.add(menuPanel, "menuPanel");
        //contentPane.add(loadPanel, "loadPanel");


        frame.add(menuPanel);
        //frame.add(loadPanel);
    }



    public static void main(String[] args) {
        new Renju();
    }

 }