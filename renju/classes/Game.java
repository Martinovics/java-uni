package renju.classes;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Game implements Serializable {

    public Player p1;
    public Player p2;
    public Board board;
    private String lastSaved;


    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board();
    }


    public Game (Player p1, Player p2, Board board) {
        this(p1, p2);
        this.board = board;
    }



    public boolean isFinished() {
        return false;
    }




    public String getLastSaveTime() {
        return this.lastSaved;
    }




    public Player whoHasColor(Color color) {
        if (this.p1.getColor().equals(color)) {
            return this.p1;
        } else {
            return this.p2;
        }
    }




    public void save() throws FileNotFoundException, IOException {
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        this.lastSaved = filename;

        FileOutputStream fo = new FileOutputStream("./saves/" + filename + ".ser");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(this);
        so.close();
    }



}
