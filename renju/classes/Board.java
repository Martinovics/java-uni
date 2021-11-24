package renju.classes;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


/** Játékmezõ osztály.
*/
@SuppressWarnings("serial")
public class Board implements Serializable{

	/** Játékmezõ.
	*/
    private Color board[][] = new Color[15][15];
    /** Lépések sorrendje.
	*/
    private List<Color> turns = new ArrayList<>();


    
    
    /**
     * Létrehoz egy üres táblát és inicializálja a lépések sorrendjét.  
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
    * Frissít egy pozíciót a megadott színre a játékmezõn.  
    *
    * @param  rowIndex A mezõ egyik sorának az indexe.
    * @param  colIndex A mezõ egyik oszlopának az indexe.
    * @param  color A szín, amire frissíteni szeretnék a megadott pozíciót.
    * @return True, ha sikeres, amúgy false.
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
     * Megszámolja, hogy a megadott színbõl hány található a játékmezõn.  
     *
     * @param  color A szín, amit meg szeretnénk számolni.
     * @return Egész szám.
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
     * Megadja, hogy a megadott pozíción milyen szín található.  
     *
     * @param  rowIndex A mezõ egyik sorának az indexe.
     * @param  colIndex A mezõ egyik oszlopának az indexe.
     * @return Szín.
     */
    public Color colorAt(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }




    /**
     * Megadja, hogy melyik szín következik a lépésben.  
     *
     * @return Szín.
     */
    public Color nextColor() {
        return this.turns.get(0);
    }


}
