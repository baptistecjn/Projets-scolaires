package Commandes;

import principales.Buffer;
import javax.swing.JOptionPane;

public class StartRecord extends Commande {
    public StartRecord(Buffer buffer) { super(buffer); }

    public void execute() {
        buffer.recorder.start();
        JOptionPane.showMessageDialog(null, "Recording started!");
    }
}
