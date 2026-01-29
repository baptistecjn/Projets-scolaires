package while_compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import antlr_grammar.While_astLexer;
import antlr_grammar.While_astParser;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import while_compiler.code_adresse.src.ThreeAdress;
import while_compiler.code_adresse.src.Optimiser;
import while_compiler.semantic.SemanticAnalyzer;
import while_compiler.semantic.SymbolTable;
import while_compiler.semantic.SemanticException;

public class Main {

    public static void main(String[] args) {
        // 1. Gestion du fichier d'entrée
        String inputFile = "src/main/while_compiler/exemple.txt";
        String outputFile = "output_optimized.txt";

        if (args.length > 0) {
            inputFile = args[0];
        }

        System.out.println("=== COMPILATEUR WHILE ===");
        System.out.println("Compiling file: " + inputFile);

        try {
            ANTLRFileStream input = new ANTLRFileStream(inputFile);
            While_astLexer lexer = new While_astLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            While_astParser parser = new While_astParser(tokens);
            While_astParser.start_return r = parser.start();

            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("ERREUR : Arrêt de la compilation à cause d'erreurs de syntaxe.");
                System.exit(1);
            }

            CommonTree ast = (CommonTree) r.getTree();
            if (ast == null) {
                System.err.println("ERREUR : L'arbre AST est null.");
                return;
            }

            System.out.println("\n--- Arbre de syntaxe abstraite (AST) ---");
            System.out.println(ast.toStringTree());

            System.out.println("\n--- Analyse Sémantique ---");
            SemanticAnalyzer analyzer = new SemanticAnalyzer();
            try {
                SymbolTable symbolTable = analyzer.analyze(ast);
                System.out.println("SUCCÈS : Aucune erreur sémantique détectée.");
            } catch (SemanticException e) {
                System.out.println("Erreur Sémantique : " + e.getMessage());
            }

            System.out.println("\n--- Code 3 adresses (Brut) ---");
            ThreeAdress generator = new ThreeAdress();
            String rawCode = generator.toString(ast);
            System.out.println(rawCode);

            System.out.println("\n--- Code 3 adresses (Optimisé) ---");
            Optimiser opti = new Optimiser(rawCode);
            opti.optimize();
            String optimizedCode = opti.toString();
            System.out.println(optimizedCode);

            Files.writeString(Paths.get(outputFile), optimizedCode);
            System.out.println("\n[INFO] Résultat optimisé sauvegardé dans : " + outputFile);

        } catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie : " + e.getMessage());
        } catch (RecognitionException e) {
            System.err.println("Erreur de reconnaissance ANTLR : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : ");
            e.printStackTrace();
        }
    }
}