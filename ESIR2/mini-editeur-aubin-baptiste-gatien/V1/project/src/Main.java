import principales.Buffer;
import Commandes.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();


        JButton copy_button = new JButton("Copy");
        JButton paste_button = new JButton("Paste");
        JButton cut_button = new JButton("Cut");
        JButton insert_button = new JButton("Insert");
        JButton select_button = new JButton("Select");
        JButton cursor_button = new JButton("Cursor");

        copy_button.addActionListener(e -> {new Copy(buffer).execute();});
        paste_button.addActionListener(e -> {new Paste(buffer).execute();});
        cut_button.addActionListener(e -> {new Cut(buffer).execute();});
        insert_button.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(null, "Enter text to Insert", "Insert", JOptionPane.QUESTION_MESSAGE);
            new Insert(buffer, text).execute();
        });

        select_button.addActionListener(e -> {
            JTextField startField = new JTextField(3);
            JTextField endField = new JTextField(3);

            JPanel panel = new JPanel();
            panel.add(new JLabel("Start:"));
            panel.add(startField);
            panel.add(new JLabel("End:"));
            panel.add(endField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Select Text", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (!startField.getText().isEmpty() && !endField.getText().isEmpty()) {
                    int start = Integer.parseInt(startField.getText());
                    int end = Integer.parseInt(endField.getText());

                    new Select(start, end, buffer).execute();
                }
            }
        });

        cursor_button.addActionListener(e -> {
            JTextField posField = new JTextField(5);
            JPanel panel = new JPanel();
            panel.add(new JLabel("New cursor position :"));
            panel.add(posField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Move cursor", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String text = posField.getText();
                if (!text.isEmpty()) {
                    int pos = Integer.parseInt(text);
                    int max = buffer.textArea.getDocument().getLength();
                    pos = Math.max(0, Math.min(pos, max));
                    buffer.textArea.setCaretPosition(pos);
                    buffer.textArea.select(pos, pos);
                }
            }

        });



        JPanel panel = new JPanel();
        panel.add(insert_button);
        panel.add(copy_button);
        panel.add(cut_button);
        panel.add(paste_button);
        panel.add(select_button);
        panel.add(cursor_button);

        buffer.addPanel(panel);

    }
}