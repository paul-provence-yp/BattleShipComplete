import javax.swing.*;
import java.awt.*;

public class SolutionsInterface {
    JFrame frame;
    JPanel panel;
    GridBagConstraints gbc;
      JTextArea solutionsArea;

    public SolutionsInterface() {

        frame = new JFrame("Solutions");
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        solutionsArea = new JTextArea();

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(solutionsArea, gbc);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setLocation(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public void updateSolutionsArea(String newText) {
        solutionsArea.setText(newText);
    }


}
