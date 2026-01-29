package while_compiler.semantic; 

/**
 * Représente une variable locale à l'intérieur d'une fonction While.
 */
public class VariableSymbol extends Symbol {
    private int memoryIndex;

    public VariableSymbol(String name) {
        super(name);
        this.memoryIndex = -1;
    }

    public int getMemoryIndex() {

        return memoryIndex;
    }

    public void setMemoryIndex(int memoryIndex) {

        this.memoryIndex = memoryIndex;
    }
}