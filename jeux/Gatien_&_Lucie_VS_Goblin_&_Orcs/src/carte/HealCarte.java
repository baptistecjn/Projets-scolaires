package carte;

import entity.Combatant;

public class HealCarte extends Carte {
    private int healAmount;

    public HealCarte(String name, int cost, int range, String description, int healAmount) {
        super(name, cost, range, description);
        this.healAmount = healAmount;
    }

    public int getHealAmount() { return healAmount; }

    @Override
    public void play(Combatant caster, Combatant target) {
        if (caster.getPA() < cost) {
            System.out.println("Pas assez de PA pour jouer le sort !");
            return;
        }
        if (!caster.isInRange((entity.Entity)target, range)) {
            System.out.println("Cible hors de portée !");
            return;
        }
        target.heal(healAmount);
        caster.usePA(cost);

        System.out.println(caster.getName() + " soigne " + target.getName() + " de " + healAmount + " points.");
    }
}