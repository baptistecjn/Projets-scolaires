package Inventory;

import carte.Carte;

import java.util.ArrayList;
import java.util.List;

import entity.Combatant;

public class Hand {
    private List<Carte> cartes;
    private final int MAX_CARTES = 6; //max de carte pour la main

    //constructeur
    public Hand() {
        cartes = new ArrayList<>();
    }

    //méthode pour remplire la main du joueur
    public void piocheHand(Inventory inventory) {
        while (cartes.size() < MAX_CARTES && !inventory.isEmpty()) {
            Carte piocher = inventory.pioche();
            if (piocher != null) {
                cartes.add(piocher);
            }
        }
    }

    //méthode pour piocher que une carte
    public void piocheOneCarte(Inventory inventory){
        Carte piocher = inventory.pioche();
        if (piocher != null) {
            cartes.add(piocher);
        }
    }

    //méthode pour jouer une carte
    public void playCarte(Carte carte, Combatant caster, Combatant target, Defausse defausse) {
        if (!cartes.contains(carte)) {
            return;
        }

        carte.play(caster, target); // il faut que play accepte Combatant aussi (plus bas)
        cartes.remove(carte);
        defausse.defausser(carte);
    }

    //méthode pour retourner la liste des cartes en main
    public List<Carte> getCartes() {return cartes;}

    //méthode pour défausser la main actuelle
    public void defausserAll(Defausse defausse){
        defausse.defausser(cartes); //on ajoute toute la main a la defausse
        cartes.clear(); //on vide le main
    }

    //méthode pour vérifier si la main est pleine
    public boolean isFull(){
        return cartes.size() == MAX_CARTES;
    }
}
