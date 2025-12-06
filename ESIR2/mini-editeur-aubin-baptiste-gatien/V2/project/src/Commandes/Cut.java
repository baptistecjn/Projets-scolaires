package Commandes;

import principales.Buffer;

public class Cut extends Commande implements Reversible {
    private String oldText;
    private int start;
    private int end;

    public Cut(Buffer buffer) {
        super(buffer);
    }

    public void execute() {
        start = buffer.textArea.getSelectionStart();
        end = buffer.textArea.getSelectionEnd();
        oldText = buffer.textArea.getSelectedText();
        if (oldText != null && !oldText.isEmpty()) {
            buffer.notes = oldText;
            buffer.textArea.replaceRange("", start, end);
            buffer.historique.add(this);
            buffer.recorder.add(this);
        }
    }

    public void undo() {
        buffer.textArea.insert(oldText, start);
    }

    public void redo() {
        buffer.textArea.replaceRange("", start, start + oldText.length());
    }
}
