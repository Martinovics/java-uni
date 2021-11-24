package renju.classes;



import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




/**
 * Játékok tárolására alkalmas osztály.
 */
@SuppressWarnings("serial")
public class GameData extends AbstractTableModel {


	/**
     * Játékok listája.
     */
    public List<Game> games = new ArrayList<Game>();




    /**
     * Megadja, hogy a táblázat megadott pozíciójában milyen érték szerepel.  
     *
     * @param  rowIndex A táblázat egyik sorának az indexe.
     * @param  colIndex A táblázat egyik oszlopának az indexe.
     * @return Object.
     */
    public Object getValueAt(int rowIndex, int colIndex) {
        Game game = games.get(rowIndex);
        switch(colIndex) {
            case 0: return game.whoHasColor(Color.BLACK).getName();
            case 1: return game.whoHasColor(Color.WHITE).getName();
            case 2: return game.getLastSaveTime();
            default: return game.hasWinner();
        }
    }




    /**
     * Megadja, hogy a táblázatnak hány oszlopa van.  
     *
     * @return A táblázat oszlopainak száma (egész szám).
     */
    public int getColumnCount() {
        return 4;
    }




    /**
     * Megadja, hogy a táblázatnak hány sora van.  
     *
     * @return A táblázat sorainak száma (egész szám).
     */
    public int getRowCount() {
        return this.games.size();
    }




    /**
     * Megadja, hogy a táblázat megadott oszlopának mi a neve.  
     *
     * @param  colIndex A táblázat egyik oszlopának az indexe.
     * @return A táblázat oszlopainak neve.
     */
    public String getColumnName(int colIndex) {
        switch(colIndex) {
            case 0: return "BLACK";
            case 1: return "WHITE";
            case 2: return "Last Saved";
            default: return "Finished";
        }
    }




    /**
     * Megadja, hogy a táblázat megadott oszlopához milyen osztályú értékek tartoznak.  
     *
     * @param  colIndex A táblázat egyik oszlopának az indexe.
     * @return A táblázat, megadott oszlopához tartozó értékek osztálya.
     */
    public Class<?> getColumnClass(int colIndex) {
        switch(colIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            default: return Boolean.class;
        }
    }


}
