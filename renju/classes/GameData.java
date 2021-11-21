package renju.classes;


import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




public class GameData extends AbstractTableModel {


    public List<Game> games = new ArrayList<Game>();



    public Object getValueAt(int rowIndex, int columnIndex) {
        Game game = games.get(rowIndex);
        switch(columnIndex) {
            case 0: return game.p1Name();
            case 1: return game.p2Name();
            case 2: return game.hasSignature();
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
            case 2: return Boolean.class;
            default: return Boolean.class;
        }
    }


    public boolean isCellEditable(int rowIndex, int columnIndex){
        if (columnIndex == 2 || columnIndex == 3) {
            return true;
        } else {
            return false;
        }
    }


    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Game game = games.get(rowIndex);
        if (columnIndex == 2) {
            game.setSignature((Boolean) aValue);
        } else if (columnIndex == 3) {
            game.setGrade((Integer) aValue);
        }
    }


    public void addStudent(String p1Name, String p2Name) {
        games.add(new Game(new Player(p1Name, Color.BLACK), new Player(p2Name, Color.WHITE)));
        this.fireTableDataChanged();
    }

}
