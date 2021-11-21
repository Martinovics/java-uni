package renju.classes;

import java.awt.*;
import java.io.Serializable;




public class Board implements Serializable{

    private Color board[][] = new Color[15][15];


    public Board() {
        for (int row=0; row != 15; row+=1) {
            for (int col=0; col != 15; col+=1) {
                this.board[row][col] = Color.ORANGE;
            }
        }
    }




    public boolean update(int rowIndex, int colIndex, Color color) {
        if (board[rowIndex][colIndex].equals(Color.ORANGE)) {
            board[rowIndex][colIndex] = color;
            System.out.println("updated: " + colIndex + " " + rowIndex);
            return true;
        }
        System.out.println("could not update");
        return false;
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




}
