grammar While_ast;

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
}

start
    : program -> ^(PROG program)
    ;

program
    : (COMMENT? (function))*
    ;

COMMENT : '//' ~('\n'|'\r')* ('\r')? '\n';

function
    : 'function' SYMBOL ':' definition
    -> ^(FUNC ^(NAME SYMBOL) definition)
    ;

definition
    : 'read' input '%' commands '%' 'write' output
      -> ^(PARAM input) ^(BLOCK commands) ^(RESULT output)
    ;

input   : vars | ;
output  : vars;

commands
    : command (';'! command)*
    ;

command
    : ('if'^ expression 'then'! command ('else'! commands)? 'fi'!)
    | ('while'^ expression 'do'! commands 'od'!)
    | ('for'^ expression 'do'! commands 'od'!)
    | ('foreach'^ VARIABLE 'in'! expression 'do'! commands 'od'!)
    | (vars ':='^ exprs)
    ;

exprs
    : expression (','! expression)*
    ;

vars
    : VARIABLE (','! vars)?
    ;

expression
    : expr_base ('=?'^ expr_base)*
    ;

expr_base
    :    '(' 'cons'^ lexpr ')'!
    |    '(' 'list'^ lexpr ')'!
    |    '(' 'hd' expr_base ')' -> ^(HEAD expr_base) 
    |    '(' 'tl' expr_base ')' -> ^(TAIL expr_base)
    |    '(' SYMBOL lexpr ')'   -> ^(CALL SYMBOL lexpr)
    |    '(' SYMBOL ')'   -> ^(SYMBOL)
     |    NIL 
    |    VARIABLE
    ;
lexpr
    : expr_base;

NIL      : 'nil';
VARIABLE : ('A'..'Z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?;
SYMBOL   : ('a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?;
WS       : (' '|'\t'|'\n'|'\r')+ { $channel=HIDDEN; };