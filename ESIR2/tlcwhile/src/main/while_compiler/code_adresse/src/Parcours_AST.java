package while_compiler.code_adresse.src;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import java.io.IOException;

public class Parcours_AST {

    private CommonTree tree;

    public Parcours_AST(CommonTree new_tree){
        this.tree = new_tree;
    }

    public void visiter(){
        for(int i=0; i<this.tree.getChildCount(); i++){
            Parcours_AST child = new Parcours_AST((CommonTree)this.tree.getChild(i));
            System.out.println(child);
            child.visiter();
        }
    }

    public String toStringtree(){
        return this.tree.toStringTree();
    }

    public String toString(){
        return  this.tree.toString();
    }

    public  CommonTree getTree() {
        return tree;
    }

}
