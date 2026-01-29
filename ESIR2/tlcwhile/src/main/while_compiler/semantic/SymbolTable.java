package while_compiler.semantic; 

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Gère la table des symboles, distinguant la portée globale (fonctions)
 * et la portée locale (variables de la fonction courante).
 */
public class SymbolTable {
    private final Map<String, FunctionSymbol> functionScope;

    private Map<String, VariableSymbol> currentVariableScope;

    public SymbolTable() {
        this.functionScope = new HashMap<>();
        this.currentVariableScope = new HashMap<>();
    }

    public void addFunction(FunctionSymbol symbol) throws SemanticException {
        if (functionScope.containsKey(symbol.getName())) {
            throw new SemanticException("Erreur: La fonction '" + symbol.getName() + "'est déjà définie.");
        }
        System.out.println(symbol.getName());
        functionScope.put(symbol.getName(), symbol);
    }

    public FunctionSymbol getFunction(String name) {

        return functionScope.get(name);
    }

    public Set<String> getAllFunctionNames() {

        return functionScope.keySet();
    }

    public void enterFunctionScope() {

        this.currentVariableScope = new HashMap<>();
    }

    public void addLocalVariable(VariableSymbol symbol) {
        if (!currentVariableScope.containsKey(symbol.getName())) {
            currentVariableScope.put(symbol.getName(), symbol);
        }
    }

    public VariableSymbol getLocalVariable(String name) {
        return currentVariableScope.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== TABLE DES SYMBOLES =====\n");
        
        for (FunctionSymbol func : functionScope.values()) {
            sb.append(func.toString()).append("\n");
            sb.append("------------------------------\n");
        }
        
        return sb.toString();
    }
}