package while_compiler.code_adresse.src;

import org.antlr.runtime.tree.CommonTree;

public interface Visitor_AST {
    String visiter(CommonTree tree, int index);
}
