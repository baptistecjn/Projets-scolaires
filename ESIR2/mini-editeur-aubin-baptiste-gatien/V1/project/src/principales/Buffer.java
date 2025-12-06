package principales;

import javax.swing.*;
import java.awt.*;

public class Buffer {
    public JTextArea textArea;
    private JFrame frame;
    public String notes;

    public Buffer() {
        frame = new JFrame("Mini éditeur de texte");
        textArea = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(500, 500);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setDisabledTextColor(Color.BLACK);
    }


    public void addPanel(JPanel panel) {
        frame.add(panel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
}
