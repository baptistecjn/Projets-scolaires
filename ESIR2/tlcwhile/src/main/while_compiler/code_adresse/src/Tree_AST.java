package while_compiler.code_adresse.src;

import antlr_grammar.While_astLexer;
import antlr_grammar.While_astParser;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.RecognitionException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.tree.CommonTree;

public class Tree_AST {
    
    private CommonTree tree;

    public Tree_AST(ANTLRFileStream fileStream){
    While_astLexer lex = new While_astLexer(fileStream);
    TokenRewriteStream tokens = new TokenRewriteStream(lex);
    While_astParser grammar = new While_astParser(tokens);
    try{
        While_astParser.start_return ret = grammar.start();
        this.tree = (CommonTree)ret.getTree();
    }catch(RecognitionException e){
        System.err.println("Erreur");
    }
    
    }

    public CommonTree getTree() {
        return tree;
    }
    
}
