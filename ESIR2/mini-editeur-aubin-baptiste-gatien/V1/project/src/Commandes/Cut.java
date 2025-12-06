package Commandes;

import principales.Buffer;


public class Cut extends Commande {
    public Cut(Buffer buffer) {
        super(buffer);
    }
    public void execute() {
        String selection = buffer.textArea.getSelectedText();
        if (!selection.isEmpty()) {
            buffer.notes = selection;
            buffer.textArea.replaceSelection("");
        }
    }

}
