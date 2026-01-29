package Backend;

import java.io.*;
import java.util.*;

public class Translator {

    // Fonction principale de traduction 3 adresses vers C++
    public static void translate(String inputFile, String outputFile) throws IOException {
        List<String> tacLines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.equals("%") || line.startsWith("//")) continue;

            tacLines.add(line);
        }
        reader.close();

        PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
        writer.println("#include \"while_runtime.h\"");
        writer.println("#include <stack>");
        writer.println("#include <vector>");
        writer.println("#include <iostream>\n");
        writer.println("typedef Tree TreeNode;\n");

        // Générer la fonction add
        String name = "";
        StringBuilder params = new StringBuilder();
        StringBuilder body = new StringBuilder();
        List<String> tacVar = new ArrayList<>();
        String else_name = "";
        String finalResultVar = "";
        Stack<String> javaStack = new Stack<>();
        StringBuilder cppCode = new StringBuilder();
        Boolean main = false;

        for (String tacLine : tacLines) {
            String[] str = tacLine.split("\\s+");
            if (tacLine.startsWith("func begin")) {
                // Réinitialiser les variables pour une nouvelle fonction
                name = str[2];
                params.setLength(0); // Réinitialiser params
                body.setLength(0);   // Réinitialiser body
                tacVar.clear();      // Réinitialiser tacVar
                finalResultVar = ""; // Réinitialiser finalResultVar
                javaStack.clear();   // Réinitialiser la pile
            }
            if (tacLine.contains(":") && tacLine.startsWith("L")) {
                body.append("   " + str[0] + ": ");
            }
            if (tacLine.startsWith("func begin")) {
                name = str[2];
            } else if (tacLine.startsWith("read ")) {
                params.append("TreeNode* ").append(str[1]).append(", ");
                tacVar.add(str[1]);
            } else if (tacLine.startsWith("param")) {
                javaStack.push(str[1]);
            } else if (tacLine.contains("=")) {
                String dest = str[0];
                if (!tacVar.contains(dest)) {
                    body.append("    TreeNode* ");
                    tacVar.add(dest);
                } else {
                    body.append("    ");
                }
                if (tacLine.contains("CONS")) {
                    String op2 = javaStack.pop();
                    op2 = (op2 == null || op2.equals("nil")) ? "nullptr" : op2;
                    String op1 = javaStack.pop();
                    op1 = (op1 == null || op1.equals("nil")) ? "nullptr" : op1;
                    body.append(dest).append(" = cons(").append(op1).append(", ").append(op2).append(");\n");
                } else if (tacLine.contains("TAIL")) {
                    String op1 = javaStack.pop();
                    op1 = (op1 == null || op1.equals("nil")) ? "nullptr" : op1;
                    body.append(dest).append(" = tl(").append(op1).append(");\n");
                } else if (tacLine.contains("call")) {
                    String op2 = javaStack.pop();
                    op2 = (op2 == null || op2.equals("nil")) ? "nullptr" : op2;
                    String op1 = javaStack.pop();
                    op1 = (op1 == null || op1.equals("nil")) ? "nullptr" : op1;
                    body.append(dest).append(" = add(").append(op1).append(", ").append(op2).append(");\n");
                } else if (tacLine.contains("HEAD")) {
                    String op1 = javaStack.pop();
                    op1 = (op1 == null || op1.equals("nil")) ? "nullptr" : op1;
                    body.append(dest).append(" = hd(").append(op1).append(");\n");
                } else if (tacLine.contains("nil")) {
                    body.append(dest).append(" = nullptr;\n");
                } else if (tacLine.contains("LIST")) {
                    int nb = Integer.parseInt(str[3]);
                    List<String> temp = new ArrayList<>();
                    for (int i = 0; i < nb; i++) temp.add(0, javaStack.pop());

                    StringBuilder args = new StringBuilder("{");
                    for (int i = 0; i < temp.size(); i++) {
                        args.append(temp.get(i)).append(i == temp.size() - 1 ? "" : ", ");
                    }
                    args.append("}");
                    body.append(dest).append(" = list(").append(args).append(");\n");
                } else {
                    body.append(dest).append(" = ").append(str[2]).append(";\n");
                }
            }
            if (tacLine.contains("if ")) {
                String condition = str[3];
                body.append("if (").append(condition).append(") {\n");
            } else if (tacLine.contains("ifz ")) {
                String condition = str[3];
                body.append("if (").append(condition).append(" == nullptr) {\n");
            } else if (tacLine.contains("ifnot")) {
                String condition = tacLine.split("\\s+")[3];
                body.append("if (!(is_true(").append(condition).append(")) {\n");
            } else if (tacLine.startsWith("break")) {
                body.append("}\n");
            } else if (tacLine.startsWith("goto")) {
                String label = tacLine.substring(5).trim();
                body.append("   goto ").append(label).append(";\n");
            } else if (tacLine.endsWith(":")) {
                body.append(tacLine).append("\n");
            } else if (tacLine.startsWith("write")) {
                finalResultVar = str[1];
            }
            if (tacLine.startsWith("func end")) {
                // Générer la fonction C++ et l'ajouter au code final
                if (params.length() > 0) {
                    params.setLength(params.length() - 2);
                }
                cppCode.append("TreeNode* ").append(name).append("(").append(params).append(") {\n");
                cppCode.append(body);
                cppCode.append("    return ").append(finalResultVar).append(";\n}\n\n");
            }
            if (name.equals("main")){

            }
        }
        writer.println(cppCode);

        writer.println("int main() {");
        writer.println("    std::vector<int> numbers;");
        writer.println("    std::string input;");
        writer.println("");
        writer.println("    std::cout << \"Entrez des nombres . Pour terminer, entrez 'fin' : \" << std::endl;");
        writer.println("");
        writer.println("    while (true) {");
        writer.println("        std::getline(std::cin, input);");
        writer.println("        if (input == \"fin\") {");
        writer.println("            break;");
        writer.println("        }");
        writer.println("        TreeNode* Result = int_to_tree(std::stoi(input));");
        writer.println("    std::cout << \"Voici le retour : \" << std::endl;");
        writer.println("        pp(Result);");
        writer.println("    std::cout << \"Entrez un nouveau nombre : \" << std::endl;");
        writer.println("    }");
        writer.println("}");
        writer.close();
    }


}
