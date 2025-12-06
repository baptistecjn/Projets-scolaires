package Commandes;

import principales.Buffer;

public class Insert extends Commande implements Reversible {
    private String texte;
    private int position;

    public Insert(Buffer buffer, String texte) {
        super(buffer);
        this.texte = texte;
        this.position = buffer.textArea.getCaretPosition();
    }

    public void execute() {
        buffer.textArea.insert(texte, position);
        buffer.historique.add(this);
        buffer.recorder.add(this);
    }

    public void undo() {
        buffer.textArea.replaceRange("", position, position + texte.length());
    }

    public void redo() {
        buffer.textArea.insert(texte, position);
    }
}
