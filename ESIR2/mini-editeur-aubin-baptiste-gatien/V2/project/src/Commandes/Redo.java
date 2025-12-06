package Commandes;

import principales.Buffer;

public class Redo extends Commande {
    public Redo(Buffer buffer) { super(buffer); }

    public void execute() {
        buffer.historique.redo();
    }
}
