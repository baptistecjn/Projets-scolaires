grammar While_ast;

options {
    output = AST;
    ASTLabelType = CommonTree;
}

tokens {
    PROG; FUNC; BLOCK; 
    PARAM; RESULT;
    VARS; EXPRS;
    NOP_NODE; ASSIGN_NODE;
    CALL; 
    TRUE_NODE; FALSE_NODE;
}

// --- PARSER ---

start
    : program EOF -> ^(PROG program)
    ;

program
    : (function)+ 
    ;

function
    : FUNCTION SYMBOL ':' definition 
      -> ^(FUNC SYMBOL definition)
    ;

definition
    : READ input '%' commands '%' WRITE output
      -> ^(PARAM input) ^(BLOCK commands) ^(RESULT output)
    ;

input   
    : vars  
    | -> ^(VARS)
    ;

output  
    : vars 
    ;

commands
    : command (';' command)* ;

command
    : NOP -> ^(NOP_NODE)
    
    | vars ASSIGN exprs -> ^(ASSIGN_NODE vars exprs)
    
    | IF expression THEN commands (ELSE commands)? FI 
      -> ^(IF expression ^(BLOCK commands) ^(ELSE commands)?)
      
    | WHILE expression DO commands OD 
      -> ^(WHILE expression ^(BLOCK commands))
      
    | FOR expression DO commands OD 
      -> ^(FOR expression ^(BLOCK commands))
      
    | FOREACH VARIABLE IN expression DO commands OD 
      -> ^(FOREACH VARIABLE expression ^(BLOCK commands))
    ;

vars
    : VARIABLE (',' VARIABLE)* -> ^(VARS VARIABLE+)
    ;

exprs
    : expression (',' expression)* -> ^(EXPRS expression+)
    ;

expression
    : expr_base (EQ^ expr_base)?
    ;
    
expr_base
    : NIL
    | VARIABLE
    | 'true' -> ^(TRUE_NODE)
    | 'false' -> ^(FALSE_NODE)
    | '(' 'cons' lexpr ')' -> ^(CONS lexpr)
    | '(' 'list' lexpr ')' -> ^(LIST lexpr)
    | '(' 'hd' expr_base ')' -> ^(HEAD expr_base)
    | '(' 'tl' expr_base ')' -> ^(TAIL expr_base)
    | '(' SYMBOL lexpr ')'   -> ^(CALL SYMBOL lexpr)
    | '('! expression ')'! 
    ;

lexpr
    : (expr_base)*
    ;

// --- LEXER ---

IF       : 'if';
THEN     : 'then';
ELSE     : 'else';
FI       : 'fi';
WHILE    : 'while';
DO       : 'do';
OD       : 'od';
FOR      : 'for';
FOREACH  : 'foreach';
IN       : 'in';

FUNCTION : 'function';
READ     : 'read';
WRITE    : 'write';
NOP      : 'nop';

ASSIGN   : ':=';
EQ       : '=?';
CONS     : 'cons';
LIST     : 'list';
HEAD     : 'hd';
TAIL     : 'tl';
NIL      : 'nil';

VARIABLE : ('A'..'Z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?; 
SYMBOL   : ('a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9')* ('!'|'?')?; 

COMMENT  : '//' ~('\n'|'\r')* ('\r'? '\n') { skip(); };
WS       : (' '|'\t'|'\n'|'\r')+ { skip(); };