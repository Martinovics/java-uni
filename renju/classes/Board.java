package renju.classes;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


/** J�t�kmez� oszt�ly.
*/
@SuppressWarnings("serial")
public class Board implements Serializable{

	/** J�t�kmez�.
	*/
    private Color board[][] = new Color[15][15];
    /** L�p�sek sorrendje.
	*/
    private List<Color> turns = new ArrayList<>();


    
    
    /**
     * L�trehoz egy �res t�bl�t �s inicializ�lja a l�p�sek sorrendj�t.  
     */
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


    

    /**
    * Friss�t egy poz�ci�t a megadott sz�nre a j�t�kmez�n.  
    *
    * @param  rowIndex A mez� egyik sor�nak az indexe.
    * @param  colIndex A mez� egyik oszlop�nak az indexe.
    * @param  color A sz�n, amire friss�teni szeretn�k a megadott poz�ci�t.
    * @return True, ha sikeres, am�gy false.
    */
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



    
    /**
     * Megsz�molja, hogy a megadott sz�nb�l h�ny tal�lhat� a j�t�kmez�n.  
     *
     * @param  color A sz�n, amit meg szeretn�nk sz�molni.
     * @return Eg�sz sz�m.
     */
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




    /**
     * Megadja, hogy a megadott poz�ci�n milyen sz�n tal�lhat�.  
     *
     * @param  rowIndex A mez� egyik sor�nak az indexe.
     * @param  colIndex A mez� egyik oszlop�nak az indexe.
     * @return Sz�n.
     */
    public Color colorAt(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }




    /**
     * Megadja, hogy melyik sz�n k�vetkezik a l�p�sben.  
     *
     * @return Sz�n.
     */
    public Color nextColor() {
        return this.turns.get(0);
    }


}
