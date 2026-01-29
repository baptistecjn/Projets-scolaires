package Backend;

import java.io.IOException;

public class MainBackend {
    public static void main(String[] args) {
        
        String tacInput = (args.length > 0) ? args[0] : "output_optimized.txt";
        String cOutput = (args.length > 1) ? args[1] : "src/main/Backend/main.cpp";

        System.out.println("--- Démarrage de la traduction TAC vers C ---");
        
        try {
            Translator.translate(tacInput, cOutput);
            System.out.println("Succès ! Fichier généré : " + cOutput);
        } catch (IOException e) {
            System.err.println("Erreur fatale lors de la traduction : " + e.getMessage());
            e.printStackTrace();
        }
    }
}   
