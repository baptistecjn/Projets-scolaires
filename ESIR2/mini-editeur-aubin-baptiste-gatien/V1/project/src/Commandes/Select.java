package Commandes;

import principales.Buffer;

public class Select extends Commande {
    private int debut;
    private int fin;

    public Select(int d, int f, Buffer buffer) {
        this.debut = d;
        this.fin = f;
        super(buffer);
    }
    public void execute() {
        buffer.textArea.select(debut, fin);
    }

}