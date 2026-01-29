package while_compiler.semantic; 

import java.util.List;
import java.util.ArrayList;
/**
 * Représente un symbole de fonction.
 * Contient les informations nécessaires pour valider les appels de fonction.
 */
public class FunctionSymbol extends Symbol {
    private final int nbInputs;

    private final int nbOutputs;

    private final List<String> localVariableNames;

    public FunctionSymbol(String name, int nbInputs, int nbOutputs) {
        super(name);
        this.nbInputs = nbInputs;
        this.nbOutputs = nbOutputs;
        this.localVariableNames = new ArrayList<>();
    }

    public int getNbInputs() {
        return nbInputs;
    }

    public int getNbOutputs() {
        return nbOutputs;
    }

    /**
     * Ajoute une variable locale (input, output ou assignée) à la liste.
     * @param varName Le nom de la variable.
     */
    public void addLocalVariable(String varName) {
        if (!localVariableNames.contains(varName)) {
            System.out.println(varName);
            localVariableNames.add(varName);
        }
    }

    public List<String> getLocalVariableNames() {
        return localVariableNames;
    }

    @Override
    public String toString() {
        return "  FUNCTION " + name + ":\n" +
               "    - Inputs: " + nbInputs + "\n" +
               "    - Outputs: " + nbOutputs + "\n" +
               "    - Variables locales: " + localVariableNames;
    }
}