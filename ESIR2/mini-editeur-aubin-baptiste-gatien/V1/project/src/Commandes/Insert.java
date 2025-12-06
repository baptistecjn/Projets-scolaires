package Commandes;

import principales.Buffer;

public class Insert extends Commande {

    private String texte;

    public Insert(Buffer buffer, String texte) {
        super(buffer);
        this.texte = texte;
    }
    public void execute() {
        buffer.textArea.insert(texte, buffer.textArea.getCaretPosition());
    }
}
