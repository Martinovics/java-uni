package lab08.prog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;






public class CaesarFrame {


    private JFrame frame;
    private JComboBox<String> comboBox;
    private JTextField inputTextField;
    private JButton button;
    private JTextField outputTextField;




    private class OkButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String letter = (String) comboBox.getSelectedItem();
            outputTextField.setText(caesarCode(inputTextField.getText(), letter.charAt(0)));
        }

    }




    public CaesarFrame() {

        frame = new JFrame();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SwingLab");
        frame.setSize(400, 110);
        frame.setResizable(false);


        // create panel elements
        String[] letters = new String[26];
        for(char letter = 'A'; letter <= 'Z'; letter+=1) {
            letters[letter - 'A'] = "" + letter;
        }
        comboBox = new JComboBox<String>(letters);

        button = new JButton("Code!");
        button.addActionListener(new OkButtonActionListener());

        JLabel outputLabel = new JLabel("Output:");

        inputTextField = new JTextField();
        inputTextField.setColumns(20);

        outputTextField = new JTextField();
        outputTextField.setColumns(20);
        outputTextField.setEditable(false);


        // main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));


        // upper panel in main
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());

        upperPanel.add(comboBox);
        upperPanel.add(inputTextField);
        upperPanel.add(button);


        // lower panel in main
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        lowerPanel.add(outputLabel);
        lowerPanel.add(outputTextField);


        // add panels
        panel.add(upperPanel);
        panel.add(lowerPanel);

        frame.add(panel);
    }




    private void showFrame() {
        frame.setVisible(true);
    }




    private String caesarCode(String input, char offset) {

        input = input.toUpperCase();
        offset = Character.toUpperCase(offset);

        int off = (int) offset - 'A';

        String result = "";
        char[] charArray = input.toCharArray();

        for (char letter: charArray) {
            int charCode = letter;

            if ('A' <= charCode && charCode <= 'Z') {
                result += (char) ((((charCode + off) - 'A') % 26) + 'A');
            } else {
                result += letter;
            }
        }

        return result;
    }




    public static void main(String[] args) {
        CaesarFrame app = new CaesarFrame();
        app.showFrame();
    }


}
