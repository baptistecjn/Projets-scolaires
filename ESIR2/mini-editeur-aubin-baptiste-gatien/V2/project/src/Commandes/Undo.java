package Commandes;

import principales.Buffer;

public class Undo extends Commande {
    public Undo(Buffer buffer) { super(buffer); }

    public void execute() {
        buffer.historique.undo();
    }
}
