package entity;

public interface Combatant {
    int getPA();
    int getHealth();
    void takeDamage(int damage);
    void usePA(int amount);
    boolean isInRange(Entity entity, int distance);
    int distanceTo(Entity entity);
    String getName();
    void heal(int amount);
}