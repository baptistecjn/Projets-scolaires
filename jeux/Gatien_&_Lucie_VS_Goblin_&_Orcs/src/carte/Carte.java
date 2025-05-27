package carte;

import entity.Combatant;
import entity.Monster;

public abstract class Carte {
    protected String name;
    protected int cost; // cout en PA
    protected int range; // portée
    protected String description;

    // Constructeur
    public Carte(String name, int cost, int range, String description) {
        this.name = name;
        this.cost = cost;
        this.range = range;
        this.description = description;
    }

    // Getters
    public String getName() { return name; }
    public int getCost() { return cost; }
    public int getRange() { return range; }
    public String getDescription() { return description; }

    // Action de la carte (méthode à redéfinir)
    public abstract void play(Combatant caster, Combatant target);
}