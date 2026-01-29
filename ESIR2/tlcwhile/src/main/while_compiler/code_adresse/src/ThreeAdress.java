package while_compiler.code_adresse.src;
import antlr_grammar.While_astLexer;
import antlr_grammar.While_astParser;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreeAdress implements Visitor_AST {

    private int nb_registre;
    private int nb_label;
    private StringBuilder code;
    private int call_assign;
    private int result_register;
    private List<String> declare_var;

    public ThreeAdress() {
        nb_registre = 0;
        nb_label = 0;
        code = new StringBuilder();
        declare_var = new ArrayList<>();
    }

    public void translate(CommonTree tree) {
        switch (tree.getType()) {
            case While_astParser.PARAM ->  {
                StringBuilder param = new StringBuilder();
                CommonTree param_tree = (CommonTree) tree.getChild(0);
                if (param_tree.getChildCount()==0){
                    code.append("\nread null");
                }
                for (int i = 0; i < param_tree.getChildCount(); i++) {
                    nb_registre++;
                    param.append("\nR" + nb_registre + " = " + visiter(param_tree, i));
                    code.append("\nread " + visiter(param_tree, i) );
                }
                code.append(param);
            }

            case While_astParser.NOP -> {
                code.append("\n nop ");
            }


            case While_astParser.HEAD, While_astParser.TAIL -> {
                translate((CommonTree) tree.getChild(0));
                System.out.println(tree.getChild(0));
                int registre_save = nb_registre;
                code.append("\nR"+(result_register)+" = "+tree.getText()+" 1 ");
            }

            case While_astParser.CONS -> {
                if ((tree.getChild(0).getType() == While_astParser.NIL ||tree.getChild(0).getType() == While_astParser.VARIABLE) && (tree.getChild(1).getType()==While_astParser.VARIABLE || tree.getChild(1).getType() == While_astParser.NIL)) {
                    code.append("\nparam ").append(tree.getChild(0).getText());
                    code.append("\nparam ").append(tree.getChild(1).getText());
                    code.append("\nR").append(result_register).append(" = ").append(tree.getText()).append(" 2");
                } else if ((tree.getChild(0).getType() == While_astParser.NIL ||tree.getChild(0).getType() == While_astParser.VARIABLE) && (tree.getChild(1).getType()!=While_astParser.VARIABLE || tree.getChild(1).getType() != While_astParser.NIL)) {
                    translate((CommonTree) tree.getChild(1));
                    code.append("\nparam "+tree.getChild(0));
                    code.append("\nparam R"+nb_registre);
                    code.append("\nR"+(result_register)+" = "+tree.getText()+" 2 ");

                } else if ((tree.getChild(0).getType() != While_astParser.NIL ||tree.getChild(0).getType() != While_astParser.VARIABLE) && (tree.getChild(1).getType()==While_astParser.VARIABLE || tree.getChild(1).getType() == While_astParser.NIL)) {
                    translate((CommonTree) tree.getChild(0));
                    code.append("\nparam R"+nb_registre);
                    code.append("\nparam "+tree.getChild(1));
                    code.append("\nR"+(result_register)+" = "+tree.getText()+" 2 ");

                } else{
                    translate((CommonTree) tree.getChild(0));
                    int reg_left = nb_registre;
                    translate((CommonTree) tree.getChild(1));
                    int reg_right = nb_registre;
                    code.append("\n param R"+reg_left);
                    code.append("\n param R"+reg_right);
                    code.append("\nR"+(result_register)+" = "+tree.getText()+" 2 ");
                }
            }

            case While_astParser.VARIABLE,While_astParser.NIL -> {
                code.append("\nparam "+tree.getText());
            }

            case While_astParser.CALL -> {
                int a = 0;
                for (int i = 1; i < tree.getChildCount(); i++) {
                    if (tree.getChild(i).getType() == While_astParser.VARIABLE) {
                        a+=1;
                    }
                }
                if (a==tree.getChildCount()){
                    for (int i = 1; i < tree.getChildCount(); i++) {
                        code.append("\n param "+tree.getChild(i));
                    }
                    code.append("R"+nb_registre+" = "+tree.getChild(0)+" "+tree.getChildCount());
                }else{
                    for (int i = 1; i < tree.getChildCount(); i++) {
                        if(tree.getChild(i).getType() != While_astParser.VARIABLE) {
                            translate((CommonTree) tree.getChild(i));
                        }
                    }
                    for (int i = 1; i < tree.getChildCount(); i++) {
                        if(tree.getChild(i).getType() == While_astParser.VARIABLE || tree.getChild(i).getType() == While_astParser.NIL) {
                            code.append("\n param "+tree.getChild(i));
                        }else{
                            code.append("\n param R"+nb_registre);
                            nb_registre--;
                        }
                    }
                    code.append("\nR"+(result_register)+" = call "+tree.getChild(0)+" "+(tree.getChildCount()-1));

                }
            }

            case While_astParser.LIST -> {
                for (int i=0; i<tree.getChildCount();i++){
                    if(tree.getChild(i).getType() != While_astParser.VARIABLE){
                        translate((CommonTree) tree.getChild(i));
                    }
                }
                for (int i=0; i<tree.getChildCount();i++){
                    if(tree.getChild(i).getType() == While_astParser.VARIABLE || tree.getChild(i).getType() == While_astParser.NIL) {
                        code.append("\n param "+tree.getChild(i));
                    }else{
                        code.append("\n param R"+nb_registre);
                    }
                }
                code.append("\nR"+(result_register)+" = "+tree.getText()+" "+(tree.getChildCount()));
            }

            case While_astParser.ASSIGN_NODE -> {
                nb_registre++;
                CommonTree child_left = (CommonTree) tree.getChild(0);
                CommonTree child_right = (CommonTree) tree.getChild(1);
                if (!declare_var.contains(child_left.getText())) {
                    declare_var.add(child_left.getText());
                    code.append("\n" + child_left.getChild(0).getText() + " = nil");
                    code.append("\nR" + nb_registre + " = "+child_left.getChild(0).getText());
                    result_register = nb_registre;
                }
                if(child_right.getChild(0).getType()==While_astParser.VARIABLE){
                    code.append("\nR" + nb_registre + " = "+child_right.getChild(0).getText());
                } else if (child_right.getChild(0).getType()==While_astParser.FALSE_NODE) {
                    code.append("\nR" + nb_registre + " = false");
                } else if (child_right.getChild(0).getType()==While_astParser.TRUE_NODE) {
                    code.append("\nR" + nb_registre + " = true");
                } else{
                    translate(child_right);
                }

            }
            case While_astParser.RESULT -> {
                CommonTree child = (CommonTree) tree.getChild(0);
                code.append("\nwrite "+child.getChild(0).getText());
                for (int i=1;i<child.getChildCount();i++){
                    code.append("," + child.getChild(i).getText());
                }

            }
            case While_astParser.FOR -> {
                nb_label++;
                code.append("\nL"+nb_label+" : ifz "+tree.getChild(0)+" goto break_for_L"+(nb_label+1));
                translate((CommonTree) tree.getChild(1));
                code.append("\nparam "+tree.getChild(0).getText());
                code.append("\n"+tree.getChild(0).getText()+" = TAIL 1");
                code.append("\ngoto L"+nb_label);
                nb_label++;
                code.append("\nbreak_for_L"+nb_label--+":");

            }
            case While_astParser.WHILE -> {
                nb_label++;
                code.append("\n L"+nb_label+" : if "+tree.getChild(0)+" goto break_while_L"+nb_label);
                translate((CommonTree) tree.getChild(1));
                code.append("\ngoto L"+nb_label);
                nb_label++;
                code.append("\nbreak_while_L"+nb_label--+":");

            }
            case While_astParser.FOREACH -> {
                int startLabel = nb_label++;
                int endLabel = nb_label++;
                code.append("\n "+tree.getChild(0)+" = nil");
                code.append("\nL"+nb_label+" ifz " + tree.getChild(1) + " goto FOREACH_end_L" + endLabel);
                code.append("\nparam "+tree.getChild(1));
                code.append("\n"+tree.getChild(0)+" =  TAIL 1");
                translate((CommonTree) tree.getChild(2));
                code.append("\nparam "+tree.getChild(1));
                code.append("\n"+tree.getChild(1)+" =  HEAD 1");
                code.append("\ngoto L" + startLabel);
                code.append("\nFOREACH_end_L" + endLabel + ":");

            }
            case While_astParser.IF -> {
                int labelFalse = nb_label++;
                int labelEnd = nb_label++;
                if (tree.getChild(0).toStringTree().contains("not")){
                    code.append("\nL"+nb_label+" : ifnot " + tree.getChild(0).getChild(1) + " goto if_break_L" + labelFalse);
                }else {
                    code.append("\nL"+nb_label+" : if " + tree.getChild(0).toStringTree() + " goto if_break_L" + labelFalse);
                }
                translate((CommonTree) tree.getChild(1));
                code.append("\ngoto if_break_L" + labelEnd);
                code.append("\nif_break_L" + labelFalse + ":");
                translate((CommonTree) tree.getChild(2));
                code.append("\nif_break_L" + labelEnd + ":");
            }

            case While_astParser.FUNC -> {
                nb_label=0;
                nb_registre=0;
                code.append("\nfunc begin "+tree.getChild(0).getText());
                for (int i = 1; i < tree.getChildCount(); i++) {
                    translate((CommonTree) tree.getChild(i));
                }
                code.append("\nfunc end "+tree.getChild(0));
            }
            default -> {
                for (int i = 0; i < tree.getChildCount(); i++) {
                    translate((CommonTree) tree.getChild(i));
                }
            }
        }
    }

    @Override
    public String visiter(CommonTree tree, int index) {
        return tree.getChild(index).toString();
    }

    public String toString(CommonTree tree) {
        translate(tree);
        return code.toString();
    }

    public static void main(String[] args) throws IOException, RecognitionException {
        ANTLRFileStream input = new ANTLRFileStream("while_compiler/code_adresse/src/example.txt");
        While_astLexer lexer = new While_astLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        While_astParser parser = new While_astParser(tokens);
        While_astParser.start_return r = parser.start();
        CommonTree ast = (CommonTree) r.getTree();
        System.out.println("\n--- Code 3 adresses (C3A) ---");
        ThreeAdress tree = new ThreeAdress();
        System.out.println(tree.toString(ast));
    }
}
