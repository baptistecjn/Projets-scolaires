package while_compiler.code_adresse.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instruction {
    public String label = null;
    public String res = null;
    public String op = null;
    public List<String> args = new ArrayList<>();
    public String raw;
    public boolean isJump = false;

    public Instruction(String line) {
        this.raw = line.trim();
        parse();
    }

    private void parse() {
        String l = this.raw;

        if (l.endsWith(":")) {
            this.label = l.substring(0, l.length() - 1);
            return;
        }

        if (l.contains(" = ")) {
            String[] parts = l.split(" = ");
            this.res = parts[0].trim();
            String rightPart = parts[1].trim();

            String[] rightParts = rightPart.split(" ");
            if (rightParts.length == 1) {
                this.args.add(rightParts[0]);
            } else {
                this.op = rightParts[0];
                for (int i = 1; i < rightParts.length; i++) {
                    this.args.add(rightParts[i]);
                }
            }
        }
        else {
            String[] parts = l.split(" ");
            this.op = parts[0];
            if (l.startsWith("goto") || l.startsWith("if") || l.startsWith("ifz") || l.startsWith("ifnot")) {
                this.isJump = true;
            }
            for (int i = 1; i < parts.length; i++) {
                this.args.add(parts[i]);
            }
        }
    }

    @Override
    public String toString() {
        if (label != null) return label + ":";
        if (res != null) {
            String s = res + " = ";
            if (op != null) s += op + " ";
            for (String arg : args) s += arg + " ";
            return s.trim();
        }
        return raw;
    }
}