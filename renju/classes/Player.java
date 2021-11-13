package renju.classes;




public class Player {

    private String name;
    private Colors color;

    public Player(String name, Colors color) {
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return name;
    }


    public Colors getColor() {
        return color;
    }


    public void setColor(Colors color) {
        this.color = color;
    }


    public boolean isBlack() {
        return this.color.equals(Colors.BLACK);
    }


    public boolean isWhite() {
        return this.color.equals(Colors.WHITE);
    }


}
