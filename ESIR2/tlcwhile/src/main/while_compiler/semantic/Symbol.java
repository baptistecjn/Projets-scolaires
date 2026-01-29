package while_compiler.semantic; 

/**
 * Classe de base abstraite pour tous les symboles (fonctions et variables).
 * Elle stocke les informations communes.
 */
public abstract class Symbol {
    protected String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}