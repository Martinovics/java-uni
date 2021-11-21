package renju.classes;

import java.awt.*;
import java.io.Serializable;




public class Player implements Serializable {

    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return name;
    }


    public Color getColor() {
        return color;
    }


    public String getColorName() {
        if (this.color.equals(Color.BLACK)) {
            return "BLACK";
        } else {
            return "WHITE";
        }
    }


    public void setColor(Color color) {
        this.color = color;
    }


    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }


    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }


}
