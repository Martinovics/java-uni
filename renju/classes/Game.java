package renju.classes;

import java.io.Serializable;




public class Game implements Serializable {

    private Player p1;
    private Player p2;
    private Board board;


    public Game (Player p1, Player p2, Board board) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
    }





}
