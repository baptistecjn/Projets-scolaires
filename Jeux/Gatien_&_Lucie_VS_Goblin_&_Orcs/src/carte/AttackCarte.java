package carte;

import entity.Combatant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AttackCarte extends Carte {
    private int damage;
    private BufferedImage image;

    //constructeur
    public AttackCarte(String name, int cost, int range, String description, int damage) {
        super(name, cost, range, description);
        this.damage = damage;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/carte_sort/" + name.toLowerCase() + ".png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erreur lors du chargement de l'image pour la carte : " + name);
            image = null;
        }
    }

    public int getDamage() {
        return damage;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    //joue la carte
    public void play(Combatant caster, Combatant target) {
        if (caster.getPA() < cost) {
            System.out.println("Pas assez de PA pour jouer le sort !");
            return;
        }
        if (!caster.isInRange((entity.Entity) target, range)) {
            System.out.println("Cible hors de portée !");
            return;
        }
        System.out.println(caster.getPA());
        target.takeDamage(damage);
        caster.usePA(cost);
        System.out.println(caster.getPA());


        System.out.println(caster.getName() + " utilise " + name + " sur " + target.getName() + " et inflige " + damage + " dégâts.");
    }
}