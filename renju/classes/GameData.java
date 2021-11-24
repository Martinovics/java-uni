package renju.classes;



import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




/**
 * J�t�kok t�rol�s�ra alkalmas oszt�ly.
 */
@SuppressWarnings("serial")
public class GameData extends AbstractTableModel {


	/**
     * J�t�kok list�ja.
     */
    public List<Game> games = new ArrayList<Game>();




    /**
     * Megadja, hogy a t�bl�zat megadott poz�ci�j�ban milyen �rt�k szerepel.  
     *
     * @param  rowIndex A t�bl�zat egyik sor�nak az indexe.
     * @param  colIndex A t�bl�zat egyik oszlop�nak az indexe.
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
     * Megadja, hogy a t�bl�zatnak h�ny oszlopa van.  
     *
     * @return A t�bl�zat oszlopainak sz�ma (eg�sz sz�m).
     */
    public int getColumnCount() {
        return 4;
    }




    /**
     * Megadja, hogy a t�bl�zatnak h�ny sora van.  
     *
     * @return A t�bl�zat sorainak sz�ma (eg�sz sz�m).
     */
    public int getRowCount() {
        return this.games.size();
    }




    /**
     * Megadja, hogy a t�bl�zat megadott oszlop�nak mi a neve.  
     *
     * @param  colIndex A t�bl�zat egyik oszlop�nak az indexe.
     * @return A t�bl�zat oszlopainak neve.
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
     * Megadja, hogy a t�bl�zat megadott oszlop�hoz milyen oszt�ly� �rt�kek tartoznak.  
     *
     * @param  colIndex A t�bl�zat egyik oszlop�nak az indexe.
     * @return A t�bl�zat, megadott oszlop�hoz tartoz� �rt�kek oszt�lya.
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
