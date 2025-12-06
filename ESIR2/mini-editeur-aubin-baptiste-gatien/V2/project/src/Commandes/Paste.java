package Commandes;

import principales.Buffer;

public class Paste extends Commande implements Reversible {
    private int position;
    private String pastedText;

    public Paste(Buffer buffer) {
        super(buffer);
    }

    public void execute() {
        if (buffer.notes != null && !buffer.notes.isEmpty()) {
            position = buffer.textArea.getCaretPosition();
            pastedText = buffer.notes;
            buffer.textArea.insert(pastedText, position);
            buffer.historique.add(this);
            buffer.recorder.add(this);
        }
    }

    public void undo() {
        buffer.textArea.replaceRange("", position, position + pastedText.length());
    }

    public void redo() {
        buffer.textArea.insert(pastedText, position);
    }
}
