package renju.classes;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Game implements Serializable {

    private Player p1;
    private Player p2;
    public Board board;


    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board();
    }


    public Game (Player p1, Player p2, Board board) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
    }


    public String p1Name() {
        return this.p1.getName();
    }

    public String p2Name() {
        return this.p2.getName();
    }

    public Board getBoard() {
        return this.board;
    }


    public boolean hasSignature() {
        return true;
    }


    public void setSignature(boolean foo) {

    }


    public int getGrade() {
        return 5;
    }


    public void setGrade(int foo) {

    }


    public boolean isFinished() {
        return false;
    }


    public void save() throws FileNotFoundException, IOException{
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        FileOutputStream fo = new FileOutputStream("./saves/" + filename + ".ser");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(this);
        so.close();
    }



}
