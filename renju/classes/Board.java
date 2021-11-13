package renju.classes;

import java.awt.*;




public class Board {

    private Color board[][] = new Color[15][15];




    public boolean update(int rowIndex, int colIndex, Color color) {
        if (board[rowIndex][colIndex] != null) {  // tile is not free -> cannot update
            return false;
        }

        board[rowIndex][colIndex] = color;
        return true;
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
        return board[rowIndex][rowIndex];
    }




}
