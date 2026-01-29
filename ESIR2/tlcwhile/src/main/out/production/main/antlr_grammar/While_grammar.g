grammar While_grammar;

options {
    output=AST;
    ASTLabelType=CommonTree;
}

tokens {
    PROG;
    FUNC;
    NAME;
    PARAM;
    RESULT;
    BLOCK;
    HEAD;
    TAIL;
    CALL;
    ASSIGN; // Ajouté pour que le Java puisse l'utiliser
    CONS;   // Ajouté pour cons
    LIST;   // Ajouté pour list
}

@header {
    package antlr_grammar;
}

@lexer::header {
    package antlr_grammar;
}

// --- RÈGLES DU PARSER & AST ---

// Point d'entrée qui crée un noeud racine PROG
start
    : program -> ^(PROG program)
    ;

// On ignore les commentaires dans l'AST, on garde juste les fonctions
program
    : (function)*
    ;

function
    : 'function' SYMBOL ':' definition
      // Structure de l'arbre : (FUNC (NAME sym) (PARAM...) (BLOCK...) (RESULT...))
      -> ^(FUNC ^(NAME SYMBOL) definition)
    ;

definition
    : 'read' input '%' commands '%' 'write' output
      -> ^(PARAM input) ^(BLOCK commands) ^(RESULT output)
    ;

input   : vars | ; // Si vide, ne produit rien
output  : vars;

commands
    : command (';'! command)*
    ;

command
    : 'if'^ expression 'then'! command ('else'! commands)? 'fi'!
    | 'while'^ expression 'do'! commands 'od'!
    | 'for'^ expression 'do'! commands 'od'!
    | 'foreach'^ VARIABLE 'in'! expression 'do'! commands 'od'!
    | vars ':=' exprs -> ^(ASSIGN vars exprs) // On force le token ASSIGN ici
    ;

exprs
    : expression (','! expression)*
    ;

vars
    : VARIABLE (','! vars)? // Crée une liste plate de VARIABLE grâce au '!'
    ;

expression
    : expr_base ('=?'^ expr_base)*
    ;

expr_base
    :   '(' 'cons' lexpr ')'       -> ^(CONS lexpr)
    |   '(' 'list' lexpr ')'       -> ^(LIST lexpr)
    |   '(' 'hd' expr_base ')'     -> ^(HEAD expr_base) 
    |   '(' 'tl' expr_base ')'     -> ^(TAIL expr_base)
    |   '(' SYMBOL lexpr ')'       -> ^(CALL SYMBOL lexpr)
    |   NIL 
    |   VARIABLE
    ;

// CORRECTION CRITIQUE ICI : lexpr doit accepter 0 ou plusieurs expr_base
lexpr
    : expr_base*
    ;

// --- RÈGLES LEXICALES (LEXER) ---

// Les mots-clés sont définis implicitement dans le parser ('if', 'while'...), 
// mais on définit ici les tokens complexes.

NIL      : 'nil';
VARIABLE : ('A'..'Z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?;
SYMBOL   : ('a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?;

// Gestion des commentaires : on les cache (HIDDEN) pour ne pas polluer le parser
COMMENT  : '//' ~('\n'|'\r')* ('\r')? '\n' { $channel=HIDDEN; };

WS       : (' '|'\t'|'\n'|'\r')+ { $channel=HIDDEN; };