package Commandes;

import principales.Buffer;

public class Paste extends Commande{
    public Paste(Buffer buffer) {
        super(buffer);
    }

    public void execute() {
        if(!buffer.notes.isEmpty()) {
            buffer.textArea.insert(buffer.notes, buffer.textArea.getCaretPosition());
        }
    }
}
