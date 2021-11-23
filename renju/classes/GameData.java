package renju.classes;



import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




@SuppressWarnings("serial")
public class GameData extends AbstractTableModel {


    public List<Game> games = new ArrayList<Game>();




    public Object getValueAt(int rowIndex, int columnIndex) {
        Game game = games.get(rowIndex);
        switch(columnIndex) {
            case 0: return game.whoHasColor(Color.BLACK).getName();
            case 1: return game.whoHasColor(Color.WHITE).getName();
            case 2: return game.getLastSaveTime();
            default: return game.hasWinner();
        }
    }




    public int getColumnCount() {
        return 4;
    }




    public int getRowCount() {
        return this.games.size();
    }




    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "BLACK";
            case 1: return "WHITE";
            case 2: return "Last Saved";
            default: return "Finished";
        }
    }




    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            default: return Boolean.class;
        }
    }


}
