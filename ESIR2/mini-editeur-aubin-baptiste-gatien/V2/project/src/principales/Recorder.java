package principales;

import Commandes.Commande;
import java.util.ArrayList;
import java.util.List;

public class Recorder {
    private boolean recording = false;
    private List<Commande> recorded = new ArrayList<>();

    public void start() {
        recording = true;
        recorded.clear();
    }

    public void stop() {
        recording = false;
    }

    public void add(Commande c) {
        if (recording) recorded.add(c);
    }

    public void replay() {
        for (Commande c : recorded) {
            if (c != null) {
                c.execute();
            }
        }
    }

    public boolean isRecording() {
        return recording;
    }
}
