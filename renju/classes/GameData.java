package renju.classes;


import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




public class GameData extends AbstractTableModel {


    public List<Game> games = new ArrayList<Game>();




    public Object getValueAt(int rowIndex, int columnIndex) {
        Game game = games.get(rowIndex);
        switch(columnIndex) {
            case 0: return game.p1.getName();
            case 1: return game.p2.getName();
            case 2: return game.getLastSaveTime();
            default: return game.isFinished();
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
            case 0: return "Player 1";
            case 1: return "Player 2";
            case 2: return "Saved";
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
