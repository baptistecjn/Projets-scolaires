package principales;

import Commandes.Commande;
import Commandes.Reversible;
import java.util.Stack;

public class Historique {
    private Stack<Commande> undoStack = new Stack<>();
    private Stack<Commande> redoStack = new Stack<>();

    public void add(Commande c) {
        undoStack.push(c);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Commande c = undoStack.pop();
            if (c instanceof Reversible) {
                ((Reversible) c).undo();
                redoStack.push(c);
            }
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Commande c = redoStack.pop();
            if (c instanceof Reversible) {
                ((Reversible) c).redo();
                undoStack.push(c);
            }
        }
    }
}
