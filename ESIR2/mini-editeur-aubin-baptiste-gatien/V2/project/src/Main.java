import principales.*;
import Commandes.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Recorder recorder = new Recorder();
        UndoManager undoManager = new UndoManager();

        JButton copy_button = new JButton("Copy");
        JButton paste_button = new JButton("Paste");
        JButton cut_button = new JButton("Cut");
        JButton insert_button = new JButton("Insert");
        JButton select_button = new JButton("Select");
        JButton cursor_button = new JButton("Cursor");

        JButton record_button = new JButton("Start Record");
        JButton stop_record_button = new JButton("Stop Record");
        JButton replay_button = new JButton("Replay");
        JButton undo_button = new JButton("Undo");
        JButton redo_button = new JButton("Redo");

        copy_button.addActionListener(e -> {
            Commande cmd = new Copy(buffer);
            cmd.execute();
            recorder.add(cmd);
            undoManager.add(cmd);
        });

        paste_button.addActionListener(e -> {
            Commande cmd = new Paste(buffer);
            cmd.execute();
            recorder.add(cmd);
            undoManager.add(cmd);
        });

        cut_button.addActionListener(e -> {
            Commande cmd = new Cut(buffer);
            cmd.execute();
            recorder.add(cmd);
            undoManager.add(cmd);
        });

        insert_button.addActionListener(e -> {
            String text = JOptionPane.showInputDialog(null, "Enter text to Insert", "Insert", JOptionPane.QUESTION_MESSAGE);
            if (text != null) {
                Commande cmd = new Insert(buffer, text);
                cmd.execute();
                recorder.add(cmd);
                undoManager.add(cmd);
            }
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

                    Commande cmd = new Select(start, end, buffer);
                    cmd.execute();
                    recorder.add(cmd);
                    undoManager.add(cmd);
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

        // --- Version 2 Buttons ---

        record_button.addActionListener(e -> {
            recorder.start();
            JOptionPane.showMessageDialog(null, "Recording started!");
        });

        stop_record_button.addActionListener(e -> {
            recorder.stop();
            JOptionPane.showMessageDialog(null, "Recording stopped!");
        });

        replay_button.addActionListener(e -> {
            recorder.replay();
        });

        undo_button.addActionListener(e -> {
            undoManager.undo();
        });

        redo_button.addActionListener(e -> {
            undoManager.redo();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.add(insert_button);
        panel.add(copy_button);
        panel.add(cut_button);
        panel.add(paste_button);
        panel.add(select_button);
        panel.add(cursor_button);
        panel.add(record_button);
        panel.add(stop_record_button);
        panel.add(replay_button);
        panel.add(undo_button);
        panel.add(redo_button);

        buffer.addPanel(panel);
    }
}
