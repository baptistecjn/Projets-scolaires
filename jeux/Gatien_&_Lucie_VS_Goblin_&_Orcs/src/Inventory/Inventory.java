package Inventory;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import carte.Carte;

public class Inventory {

    private final List<Carte> cards;
    private final int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.cards = new ArrayList<>();
    }

    public void melange() {
        Collections.shuffle(cards);
    }

    public Carte pioche(){
        if (cards.isEmpty()) return null; //si le deck est vide on renvoie null (pas de carte piocher)
        return cards.removeFirst(); //on enleve la carte
    }

    public boolean isEmpty() {return cards.isEmpty();}

    public void addItem(Carte card) {
        if (cards.size() >= capacity) {
            System.out.println("Inventaire plein !");
        } else {
            cards.add(card);
            System.out.println(card.getName() + " ajouté à l'inventaire.");
        }
    }

    public List<Carte> getItems() {
        return new ArrayList<>(cards);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return cards.size();
    }
}