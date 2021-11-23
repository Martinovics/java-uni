package renju.classes;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;



@SuppressWarnings("serial")
public class Board implements Serializable{

    private Color board[][] = new Color[15][15];
    private List<Color> turns = new ArrayList<>();


    public Board() {
        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                this.board[row][col] = Color.ORANGE;
            }
        }
        initTurns();
    }




    private void initTurns() {

        // 2 black, 1 white
        turns.add(Color.BLACK);
        turns.add(Color.BLACK);
        turns.add(Color.WHITE);
        // decision

        // 1 white
        turns.add(Color.WHITE);
        // 2 black
        turns.add(Color.BLACK);
        turns.add(Color.BLACK);
        // -1 black
        turns.add(Color.ORANGE);
        // 1 white
        turns.add(Color.WHITE);

        for (int i=0; i != 120; i+=1) {
            turns.add(Color.BLACK);
            turns.add(Color.WHITE);
        }
    }




    public boolean update(int rowIndex, int colIndex, Color color) {

        if (color.equals(Color.ORANGE)) {  // special case -> remove black
            if (board[rowIndex][colIndex].equals(Color.BLACK)) {
                board[rowIndex][colIndex] = color;
                this.turns.remove(0);
                return true;
            } else {
                System.out.println("You can only remove black colors!");
                return false;
            }
        }

        if (board[rowIndex][colIndex].equals(Color.ORANGE)) {
            board[rowIndex][colIndex] = color;
            this.turns.remove(0);
            return true;
        } else {
            System.out.println("Field is already taken!");
            return false;
        }
    }




    public int countColor(Color color) {
        int c = 0;
        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                if (board[row][col].equals(color)) {
                    c += 1;
                }
            }
        }
        return c;
    }




    public Color colorAt(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }




    public Color nextColor() {
        return this.turns.get(0);
    }


}
