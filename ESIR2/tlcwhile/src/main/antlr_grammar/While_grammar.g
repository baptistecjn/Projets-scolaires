grammar While_grammar;

program	:	 FUNCTION program | FUNCTION;

FUNCTION 
	:	'function' SYMBOL ':' DEFINITION;
	
DEFINITION 
	:	'read' INPUT '%' COMMANDS '%' 'write' OUTPUT;

INPUT	:	
		INPUTSUB;
		
INPUTSUB 
	:	VARIABLE ',' INPUTSUB | VARIABLE;
	
OUTPUT 	:	 VARIABLE ',' OUTPUT | VARIABLE;


COMMANDS 
	:	 COMMAND ';' COMMANDS;
COMMAND	:	 ('nop' | (VARS ':=' EXPRS) | '(if' EXPRESSION 'then' COMMANDS ['else' COMMANDS] 'fi') | ('while' EXPRESSION 'do' COMMANDS 'od') | ('for' EXPRESSION 'do' COMMANDS 'od') | ('foreach' VARIABLE 'in' EXPRESSION 'do' COMMANDS 'od'):	':=' EXPRS;

VARS	:	 VARIABLE ',' VARS | VARIABLE;

EXPRS	:	 EXPRESSION ',' EXPRS	 | EXPRESSION;

EXPRBASE 
	:	 ('nil' | VARIABLE | SYMBOL) | ('(' 'cons' LEXPR ')' | '(' 'list' LEXPR ')') | ('(' 'hd' EXPRBASE ')' | '(' 'tl' EXPRBASE ')') | ('(' 'hd' EXPRBASE ')' | '(' 'tl' EXPRBASE ')') | ('(' SYMBOL LEXPR ')');
	:	 
	
EXPRESSION
	:	 EXPRBASE | (EXPRBASE '=?' EXPRBASE); 

LEXPR	:	EXPRBASE LEXPR;

VARIABLE 
	:	 ('A'..'Z') ('A'..'Z' | 'a'..'z' | '0'..'9')* ('!' | '?')?;

SYMBOL	:	('a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9')* ('!' | '?')?; 

 
	  

