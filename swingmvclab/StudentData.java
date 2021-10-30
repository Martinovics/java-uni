package swingmvclab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A hallgat�k adatait t�rol� oszt�ly.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagv�ltoz� t�rolja a hallgat�i adatokat.
     * NE M�DOS�TSD!
     */
    List<Student> students = new ArrayList<Student>();



    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch(columnIndex) {
            case 0: return student.getName();
            case 1: return student.getNeptun();
            case 2: return student.hasSignature();
            default: return student.getGrade();
        }
    }


    public int getColumnCount() {
        return 4;
    }


    public int getRowCount() {
        return this.students.size();
    }


    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Nev";
            case 1: return "Neptun";
            case 2: return "Alairas";
            default: return "Jegy";
        }
    }


    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Boolean.class;
            default: return Integer.class;
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
        Student student = students.get(rowIndex);
        if (columnIndex == 2) {
            student.setSignature((Boolean) aValue);
        } else if (columnIndex == 3) {
            student.setGrade((Integer) aValue);
        }
    }


    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, false, 0));
        this.fireTableDataChanged();
    }

}
