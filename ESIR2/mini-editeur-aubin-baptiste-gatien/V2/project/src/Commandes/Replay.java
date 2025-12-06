package Commandes;

import principales.Buffer;

public class Replay extends Commande {
    public Replay(Buffer buffer) { super(buffer); }

    public void execute() {
        buffer.recorder.replay();
    }
}
