package Inventory;

import carte.Carte;

import java.util.ArrayList;
import java.util.List;

public class Defausse {
    private List<Carte> defausses;

    public Defausse(){
        defausses = new ArrayList<>();
    }

    //ajoute une carte à la defausse
    public void defausser(Carte carte){
        defausses.add(carte);
    }

    //ajoute une liste de cartes à la defausse
    public void defausser(List<Carte> cartes){
        defausses.addAll(cartes);
    }

    //vide la défausse et la remet dans le deck
    public void melangeDefausse(Inventory inventory){
        for (Carte defausse : defausses) {
            inventory.addItem(defausse); //on ajoute les cartes au deck
        }
        defausses.clear(); //on vide la défausse
    }

    public boolean isEmpty(){return defausses.isEmpty();}

}
