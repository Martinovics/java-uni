package renju.app;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import renju.classes.*;




public class Renju {

    private JFrame frame;
    private CardLayout cardLayout;
    private Container contentPane;




    private class NewGame {

        public void startGame() {

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
                        game.save();
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        // game.save();
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
                                gameController.game.save();
                            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                // gameController.game.save();
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



    private class Statistics {

        private JPanel panel;
        private List<Game> games = new ArrayList<Game>();

        private class StatisticsButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "menuPanel");
                cardLayout.removeLayoutComponent(panel);
            }
        }


        public Statistics() {
            try {
                File dir = new File("saves");
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + file.getName()));
                        this.games.add((Game)ois.readObject());
                        ois.close();
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }


        @SuppressWarnings("serial")
		public void initPanel() {

            panel = new JPanel(new GridLayout(4, 1));


            JPanel panel1 = new JPanel(new GridLayout(5, 1));
            panel1.add(new JLabel("Stats:", JLabel.CENTER));

            int placedBlacks = 0;
            int placedWhites = 0;
            int finishedGames = 0;
            for (int i=0; i != this.games.size(); i+=1) {
                Game game = this.games.get(i);
                placedBlacks += game.board.countColor(Color.BLACK);
                placedWhites += game.board.countColor(Color.WHITE);
                if (game.hasWinner()) finishedGames += 1;
            }

            panel1.add(new JLabel("Number of saves: " + this.games.size(), JLabel.CENTER));
            panel1.add(new JLabel("Number of finished games: " + finishedGames, JLabel.CENTER));
            panel1.add(new JLabel("Number of BLACKs: " + placedBlacks, JLabel.CENTER));
            panel1.add(new JLabel("Number of WHITEs: " + placedWhites, JLabel.CENTER));


            JPanel panel2 = new JPanel(new GridLayout(10, 1));

            Map<String, Map<String, Integer>> playerData = new HashMap<>();
            for (Game game : this.games) {

                List<Player> players = new ArrayList<>();
                players.add(game.p1);
                players.add(game.p2);

                for (Player p : players) {

                    String name = p.getName();

                    if (!playerData.containsKey(name)) {
                        playerData.put(name, new HashMap<>(){ {put("wins", 0); put("blacks", 0); put("whites", 0); }});
                    }

                    if (game.hasWinner()) {
                        if (game.getWinner().getName().equals(name)) {
                            playerData.get(name).put("wins", playerData.get(name).get("wins") + 1);
                        }
                    }

                    if (p.getColor().equals(Color.BLACK)) {
                        playerData.get(name).put("blacks", playerData.get(name).get("blacks") + game.board.countColor(Color.BLACK));
                    } else {
                        playerData.get(name).put("whites", playerData.get(name).get("whites") + game.board.countColor(Color.WHITE));
                    }

                }
            }

            SortedSet<String> names = new TreeSet<>();
            for (Game game : games) {
                names.add(game.p1.getName());
                names.add(game.p2.getName());
            }

            int i = 0;
            for (String name : names) {
                if (i == 10) break;

                String txt = name + ":  wins=" + playerData.get(name).get("wins") + " ";
                txt += "blacks=" + playerData.get(name).get("blacks") + " ";
                txt += "whites=" + playerData.get(name).get("whites");
                panel2.add(new JLabel(txt, JLabel.CENTER));

                i += 1;
            }


            JPanel panel3 = new JPanel(new GridLayout(10, 1));

            List<Integer> wins = new ArrayList<>();
            for (String name : names) {
                wins.add(playerData.get(name).get("wins"));
            }
            Collections.sort(wins, Collections.reverseOrder());

            i = 0;
            SortedSet<String> checkedNames = new TreeSet<>();
            for (int w : wins) {
                if (i == 10) break;

                for (String name : names) {
                    if (checkedNames.contains(name)) continue;

                    if (playerData.get(name).get("wins") == w) {
                        panel3.add(new JLabel(name + ":  wins=" + playerData.get(name).get("wins"), JLabel.CENTER));
                        checkedNames.add(name);
                    }
                }

                i += 1;
            }


            JPanel panel4 = new JPanel(new FlowLayout());
            JButton button = new JButton("Back");
            button.addActionListener(new StatisticsButtonListener());
            panel4.add(button);

            panel.add(panel1);
            panel.add(panel2);
            panel.add(panel3);
            panel.add(panel4);

            contentPane.add(panel, "gamePanel");
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
                    new NewGame().startGame();
                } else if (srcButton.getText().equals("Load Game")) {
                    new LoadGame();
                } else if (srcButton.getText().equals("Statistics")) {
                    new Statistics().initPanel();
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