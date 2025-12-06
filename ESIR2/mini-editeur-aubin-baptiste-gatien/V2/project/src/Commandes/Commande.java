package Commandes;

import principales.Buffer;

public abstract class Commande {
    protected Buffer buffer;

    public Commande(Buffer buffer) {
        this.buffer = buffer;
    }

    public abstract void execute();
}
