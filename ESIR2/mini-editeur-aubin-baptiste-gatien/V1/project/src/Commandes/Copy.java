package Commandes;

import principales.Buffer;

public class Copy extends Commande{
    public Copy(Buffer buffer) {
        super(buffer);
    }
    public void execute() {
        String selection = buffer.textArea.getSelectedText();
        if(!selection.isEmpty()){
            buffer.notes = selection;
        }
    }
}
