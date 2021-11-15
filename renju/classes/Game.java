package renju.classes;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Game implements Serializable {

    private Player p1;
    private Player p2;
    private Board board;


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



    public void save() throws FileNotFoundException, IOException{
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        FileOutputStream fo = new FileOutputStream("./saves/" + filename + ".ser");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(this);
        so.close();
    }



}
