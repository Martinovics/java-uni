package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

/*
 * A megjelen�tend� ablakunk oszt�lya.
 */
public class StudentFrame extends JFrame {

    /*
     * Ebben az objektumban vannak a hallgat�i adatok.
     * A program indul�s ut�n bet�lti az adatokat f�jlb�l, bez�r�skor pedig kimenti oda.
     *
     * NE M�DOS�TSD!
     */
    private StudentData data;
    private JTextField nameTextField;
    private JTextField neptunTextField;


    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            data.addStudent(nameTextField.getText(), neptunTextField.getText());
            nameTextField.setText("");
            neptunTextField.setText("");
        }

    }


    /*
     * Itt hozzuk l�tre �s adjuk hozz� az ablakunkhoz a k�l�nb�z� komponenseket
     * (t�bl�zat, beviteli mez�, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());

        JTable jt = new JTable(data);
        jt.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(jt);
        sp.setVisible(true);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Nev:");
        JLabel neptunLabel = new JLabel("Neptun:");

        nameTextField = new JTextField(20);
        neptunTextField = new JTextField(6);

        JButton button = new JButton("Felvesz");
        button.addActionListener(new ButtonActionListener());

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(neptunLabel);
        panel.add(neptunTextField);
        panel.add(button);

        this.add(sp, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }

    /*
     * Az ablak konstruktora.
     *
     * NE M�DOS�TSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatoi nyilvantartas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Indul�skor bet�ltj�k az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Bez�r�skor mentj�k az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program bel�p�si pontja.
     *
     * NE M�DOS�TSD!
     */
    public static void main(String[] args) {
        // Megjelen�tj�k az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
