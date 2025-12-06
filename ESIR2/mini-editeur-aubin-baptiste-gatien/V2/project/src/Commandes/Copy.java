package Commandes;

import principales.Buffer;

public class Copy extends Commande implements Reversible {
    private String oldNotes;

    public Copy(Buffer buffer) {
        super(buffer);
    }

    public void execute() {
        String selection = buffer.textArea.getSelectedText();
        if (selection != null && !selection.isEmpty()) {
            oldNotes = buffer.notes;
            buffer.notes = selection;
            buffer.historique.add(this);
            buffer.recorder.add(this);
        }
    }

    public void undo() {
        buffer.notes = oldNotes;
    }

    public void redo() {
        execute();
    }
}
