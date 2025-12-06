package Commandes;

import principales.Buffer;
import javax.swing.JOptionPane;


public class StopRecord extends Commande {
    public StopRecord(Buffer buffer) { super(buffer); }

    public void execute() {
        buffer.recorder.stop();
        JOptionPane.showMessageDialog(null, "Recording stopped!");
    }
}
