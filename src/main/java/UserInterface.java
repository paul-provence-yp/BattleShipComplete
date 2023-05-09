import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class UserInterface {
    JFrame frame;
    JPanel panel;
    JTextPane textPane;
    JTextField textField;
    JButton submitButton;
    JLabel questionField;
    String userInput;
    BattleShip bs;

    public UserInterface(BattleShip bs) {
        this.bs = bs;
        frame = new JFrame("BattleShip");

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        textField = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        textPane = new JTextPane();
        textPane.setText("Loading board...");
        textPane.setEditable(false);

        questionField = new JLabel("Welcome to BattleShip!");

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(textPane, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(questionField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(textField, gbc);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public String getUserInput() {
        return userInput;
    }

    public void updateInterfaceGameDisplay(String newText) {
        textPane.setText(newText);
    }

    public void updateInterfaceQuestionField(String newText) {
        questionField.setText(newText);
    }

    private class SubmitButtonListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (bs.getGameTurn() == GameProperties.GET_ROW) {
                userInput = textField.getText();
                questionField.setText("You entered: " + userInput);
                try{
                    bs.setRowChoice(Integer.valueOf(userInput));
                    System.out.println("rowChoice set to" + userInput);
                } catch (Exception exception) {
                    updateInterfaceQuestionField("Enter a valid row");
                }
                textField.setText("");
            }

            else if (bs.getGameTurn() == GameProperties.GET_COL) {
                userInput = textField.getText();
                questionField.setText("You entered: " + userInput);
                try{
                    bs.setColChoice(Integer.valueOf(userInput));
                } catch (Exception exception) {
                    updateInterfaceQuestionField("Enter a valid column");
                }
                textField.setText("");
            }

            userInput = "-1";

        }
    }

}
