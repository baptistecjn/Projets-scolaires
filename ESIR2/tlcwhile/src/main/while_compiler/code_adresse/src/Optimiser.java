package while_compiler.code_adresse.src;

import java.util.*;

public class Optimiser {
    private List<Instruction> instructions = new ArrayList<>();

    public Optimiser(String code) {
        for (String line : code.split("\n")) {
            if (!line.trim().isEmpty()) {
                instructions.add(new Instruction(line));
            }
        }
    }

    public void optimize() {
        Map<String, String> replacements = new HashMap<>();

        for (Instruction inst : instructions) {
            if (inst.res != null && inst.res.startsWith("R") && inst.op == null && inst.args.size() == 1) {
                replacements.put(inst.res, inst.args.get(0));
            } else {
                for (int i = 0; i < inst.args.size(); i++) {
                    String currentArg = inst.args.get(i);
                    if (replacements.containsKey(currentArg)) {
                        inst.args.set(i, replacements.get(currentArg));
                    }
                }
            }
        }

        instructions.removeIf(inst -> inst.res != null && inst.res.matches("R\\d+") && inst.op == null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Instruction inst : instructions) {
            sb.append(inst.toString()).append("\n");
        }
        return sb.toString();
    }
}