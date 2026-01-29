// $ANTLR 3.5.3 While_ast.g 2026-01-19 11:54:07
package antlr_grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class While_astParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGN", "ASSIGN_NODE", "BLOCK", 
		"CALL", "COMMENT", "CONS", "DO", "ELSE", "EQ", "EXPRS", "FALSE_NODE", 
		"FI", "FOR", "FOREACH", "FUNC", "FUNCTION", "HEAD", "IF", "IN", "LIST", 
		"NIL", "NOP", "NOP_NODE", "OD", "PARAM", "PROG", "READ", "RESULT", "SYMBOL", 
		"TAIL", "THEN", "TRUE_NODE", "VARIABLE", "VARS", "WHILE", "WRITE", "WS", 
		"'%'", "'('", "')'", "','", "':'", "';'", "'false'", "'true'"
	};
	public static final int EOF=-1;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int ASSIGN=4;
	public static final int ASSIGN_NODE=5;
	public static final int BLOCK=6;
	public static final int CALL=7;
	public static final int COMMENT=8;
	public static final int CONS=9;
	public static final int DO=10;
	public static final int ELSE=11;
	public static final int EQ=12;
	public static final int EXPRS=13;
	public static final int FALSE_NODE=14;
	public static final int FI=15;
	public static final int FOR=16;
	public static final int FOREACH=17;
	public static final int FUNC=18;
	public static final int FUNCTION=19;
	public static final int HEAD=20;
	public static final int IF=21;
	public static final int IN=22;
	public static final int LIST=23;
	public static final int NIL=24;
	public static final int NOP=25;
	public static final int NOP_NODE=26;
	public static final int OD=27;
	public static final int PARAM=28;
	public static final int PROG=29;
	public static final int READ=30;
	public static final int RESULT=31;
	public static final int SYMBOL=32;
	public static final int TAIL=33;
	public static final int THEN=34;
	public static final int TRUE_NODE=35;
	public static final int VARIABLE=36;
	public static final int VARS=37;
	public static final int WHILE=38;
	public static final int WRITE=39;
	public static final int WS=40;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public While_astParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public While_astParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return While_astParser.tokenNames; }
	@Override public String getGrammarFileName() { return "While_ast.g"; }


	public static class start_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "start"
	// While_ast.g:19:1: start : program EOF -> ^( PROG program ) ;
	public final While_astParser.start_return start() throws RecognitionException {
		While_astParser.start_return retval = new While_astParser.start_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EOF2=null;
		ParserRuleReturnScope program1 =null;

		CommonTree EOF2_tree=null;
		RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
		RewriteRuleSubtreeStream stream_program=new RewriteRuleSubtreeStream(adaptor,"rule program");

		try {
			// While_ast.g:20:5: ( program EOF -> ^( PROG program ) )
			// While_ast.g:20:7: program EOF
			{
			pushFollow(FOLLOW_program_in_start110);
			program1=program();
			state._fsp--;

			stream_program.add(program1.getTree());
			EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_start112);  
			stream_EOF.add(EOF2);


			// AST REWRITE
			// elements: program
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 20:19: -> ^( PROG program )
			{
				// While_ast.g:20:22: ^( PROG program )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PROG, "PROG"), root_1);
				adaptor.addChild(root_1, stream_program.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "start"


	public static class program_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "program"
	// While_ast.g:23:1: program : ( function )+ ;
	public final While_astParser.program_return program() throws RecognitionException {
		While_astParser.program_return retval = new While_astParser.program_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope function3 =null;


		try {
			// While_ast.g:24:5: ( ( function )+ )
			// While_ast.g:24:7: ( function )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// While_ast.g:24:7: ( function )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==FUNCTION) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// While_ast.g:24:8: function
					{
					pushFollow(FOLLOW_function_in_program138);
					function3=function();
					state._fsp--;

					adaptor.addChild(root_0, function3.getTree());

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "program"


	public static class function_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function"
	// While_ast.g:27:1: function : FUNCTION SYMBOL ':' definition -> ^( FUNC SYMBOL definition ) ;
	public final While_astParser.function_return function() throws RecognitionException {
		While_astParser.function_return retval = new While_astParser.function_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FUNCTION4=null;
		Token SYMBOL5=null;
		Token char_literal6=null;
		ParserRuleReturnScope definition7 =null;

		CommonTree FUNCTION4_tree=null;
		CommonTree SYMBOL5_tree=null;
		CommonTree char_literal6_tree=null;
		RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
		RewriteRuleTokenStream stream_SYMBOL=new RewriteRuleTokenStream(adaptor,"token SYMBOL");
		RewriteRuleTokenStream stream_FUNCTION=new RewriteRuleTokenStream(adaptor,"token FUNCTION");
		RewriteRuleSubtreeStream stream_definition=new RewriteRuleSubtreeStream(adaptor,"rule definition");

		try {
			// While_ast.g:28:5: ( FUNCTION SYMBOL ':' definition -> ^( FUNC SYMBOL definition ) )
			// While_ast.g:28:7: FUNCTION SYMBOL ':' definition
			{
			FUNCTION4=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_function158);  
			stream_FUNCTION.add(FUNCTION4);

			SYMBOL5=(Token)match(input,SYMBOL,FOLLOW_SYMBOL_in_function160);  
			stream_SYMBOL.add(SYMBOL5);

			char_literal6=(Token)match(input,45,FOLLOW_45_in_function162);  
			stream_45.add(char_literal6);

			pushFollow(FOLLOW_definition_in_function164);
			definition7=definition();
			state._fsp--;

			stream_definition.add(definition7.getTree());

			// AST REWRITE
			// elements: SYMBOL, definition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 29:7: -> ^( FUNC SYMBOL definition )
			{
				// While_ast.g:29:10: ^( FUNC SYMBOL definition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNC, "FUNC"), root_1);
				adaptor.addChild(root_1, stream_SYMBOL.nextNode());
				adaptor.addChild(root_1, stream_definition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function"


	public static class definition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "definition"
	// While_ast.g:32:1: definition : READ input '%' commands '%' WRITE output -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output ) ;
	public final While_astParser.definition_return definition() throws RecognitionException {
		While_astParser.definition_return retval = new While_astParser.definition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token READ8=null;
		Token char_literal10=null;
		Token char_literal12=null;
		Token WRITE13=null;
		ParserRuleReturnScope input9 =null;
		ParserRuleReturnScope commands11 =null;
		ParserRuleReturnScope output14 =null;

		CommonTree READ8_tree=null;
		CommonTree char_literal10_tree=null;
		CommonTree char_literal12_tree=null;
		CommonTree WRITE13_tree=null;
		RewriteRuleTokenStream stream_READ=new RewriteRuleTokenStream(adaptor,"token READ");
		RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
		RewriteRuleTokenStream stream_WRITE=new RewriteRuleTokenStream(adaptor,"token WRITE");
		RewriteRuleSubtreeStream stream_output=new RewriteRuleSubtreeStream(adaptor,"rule output");
		RewriteRuleSubtreeStream stream_input=new RewriteRuleSubtreeStream(adaptor,"rule input");
		RewriteRuleSubtreeStream stream_commands=new RewriteRuleSubtreeStream(adaptor,"rule commands");

		try {
			// While_ast.g:33:5: ( READ input '%' commands '%' WRITE output -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output ) )
			// While_ast.g:33:7: READ input '%' commands '%' WRITE output
			{
			READ8=(Token)match(input,READ,FOLLOW_READ_in_definition198);  
			stream_READ.add(READ8);

			pushFollow(FOLLOW_input_in_definition200);
			input9=input();
			state._fsp--;

			stream_input.add(input9.getTree());
			char_literal10=(Token)match(input,41,FOLLOW_41_in_definition202);  
			stream_41.add(char_literal10);

			pushFollow(FOLLOW_commands_in_definition204);
			commands11=commands();
			state._fsp--;

			stream_commands.add(commands11.getTree());
			char_literal12=(Token)match(input,41,FOLLOW_41_in_definition206);  
			stream_41.add(char_literal12);

			WRITE13=(Token)match(input,WRITE,FOLLOW_WRITE_in_definition208);  
			stream_WRITE.add(WRITE13);

			pushFollow(FOLLOW_output_in_definition210);
			output14=output();
			state._fsp--;

			stream_output.add(output14.getTree());

			// AST REWRITE
			// elements: commands, output, input
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 34:7: -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output )
			{
				// While_ast.g:34:10: ^( PARAM input )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PARAM, "PARAM"), root_1);
				adaptor.addChild(root_1, stream_input.nextTree());
				adaptor.addChild(root_0, root_1);
				}

				// While_ast.g:34:25: ^( BLOCK commands )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_1);
				adaptor.addChild(root_1, stream_commands.nextTree());
				adaptor.addChild(root_0, root_1);
				}

				// While_ast.g:34:43: ^( RESULT output )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RESULT, "RESULT"), root_1);
				adaptor.addChild(root_1, stream_output.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "definition"


	public static class input_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "input"
	// While_ast.g:37:1: input : ( vars | -> ^( VARS ) );
	public final While_astParser.input_return input() throws RecognitionException {
		While_astParser.input_return retval = new While_astParser.input_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope vars15 =null;


		try {
			// While_ast.g:38:5: ( vars | -> ^( VARS ) )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==VARIABLE) ) {
				alt2=1;
			}
			else if ( (LA2_0==41) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// While_ast.g:38:7: vars
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_vars_in_input256);
					vars15=vars();
					state._fsp--;

					adaptor.addChild(root_0, vars15.getTree());

					}
					break;
				case 2 :
					// While_ast.g:39:7: 
					{

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 39:7: -> ^( VARS )
					{
						// While_ast.g:39:10: ^( VARS )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARS, "VARS"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "input"


	public static class output_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "output"
	// While_ast.g:42:1: output : vars ;
	public final While_astParser.output_return output() throws RecognitionException {
		While_astParser.output_return retval = new While_astParser.output_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope vars16 =null;


		try {
			// While_ast.g:43:5: ( vars )
			// While_ast.g:43:7: vars
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_vars_in_output289);
			vars16=vars();
			state._fsp--;

			adaptor.addChild(root_0, vars16.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "output"


	public static class commands_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "commands"
	// While_ast.g:46:1: commands : command ( ';' command )* ;
	public final While_astParser.commands_return commands() throws RecognitionException {
		While_astParser.commands_return retval = new While_astParser.commands_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal18=null;
		ParserRuleReturnScope command17 =null;
		ParserRuleReturnScope command19 =null;

		CommonTree char_literal18_tree=null;

		try {
			// While_ast.g:47:5: ( command ( ';' command )* )
			// While_ast.g:47:7: command ( ';' command )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_command_in_commands307);
			command17=command();
			state._fsp--;

			adaptor.addChild(root_0, command17.getTree());

			// While_ast.g:47:15: ( ';' command )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==46) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// While_ast.g:47:16: ';' command
					{
					char_literal18=(Token)match(input,46,FOLLOW_46_in_commands310); 
					char_literal18_tree = (CommonTree)adaptor.create(char_literal18);
					adaptor.addChild(root_0, char_literal18_tree);

					pushFollow(FOLLOW_command_in_commands312);
					command19=command();
					state._fsp--;

					adaptor.addChild(root_0, command19.getTree());

					}
					break;

				default :
					break loop3;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "commands"


	public static class command_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "command"
	// While_ast.g:49:1: command : ( NOP -> ^( NOP_NODE ) | vars ASSIGN exprs -> ^( ASSIGN_NODE vars exprs ) | IF expression THEN commands ( ELSE commands )? FI -> ^( IF expression ^( BLOCK commands ) ( ^( ELSE commands ) )? ) | WHILE expression DO commands OD -> ^( WHILE expression ^( BLOCK commands ) ) | FOR expression DO commands OD -> ^( FOR expression ^( BLOCK commands ) ) | FOREACH VARIABLE IN expression DO commands OD -> ^( FOREACH VARIABLE expression ^( BLOCK commands ) ) );
	public final While_astParser.command_return command() throws RecognitionException {
		While_astParser.command_return retval = new While_astParser.command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NOP20=null;
		Token ASSIGN22=null;
		Token IF24=null;
		Token THEN26=null;
		Token ELSE28=null;
		Token FI30=null;
		Token WHILE31=null;
		Token DO33=null;
		Token OD35=null;
		Token FOR36=null;
		Token DO38=null;
		Token OD40=null;
		Token FOREACH41=null;
		Token VARIABLE42=null;
		Token IN43=null;
		Token DO45=null;
		Token OD47=null;
		ParserRuleReturnScope vars21 =null;
		ParserRuleReturnScope exprs23 =null;
		ParserRuleReturnScope expression25 =null;
		ParserRuleReturnScope commands27 =null;
		ParserRuleReturnScope commands29 =null;
		ParserRuleReturnScope expression32 =null;
		ParserRuleReturnScope commands34 =null;
		ParserRuleReturnScope expression37 =null;
		ParserRuleReturnScope commands39 =null;
		ParserRuleReturnScope expression44 =null;
		ParserRuleReturnScope commands46 =null;

		CommonTree NOP20_tree=null;
		CommonTree ASSIGN22_tree=null;
		CommonTree IF24_tree=null;
		CommonTree THEN26_tree=null;
		CommonTree ELSE28_tree=null;
		CommonTree FI30_tree=null;
		CommonTree WHILE31_tree=null;
		CommonTree DO33_tree=null;
		CommonTree OD35_tree=null;
		CommonTree FOR36_tree=null;
		CommonTree DO38_tree=null;
		CommonTree OD40_tree=null;
		CommonTree FOREACH41_tree=null;
		CommonTree VARIABLE42_tree=null;
		CommonTree IN43_tree=null;
		CommonTree DO45_tree=null;
		CommonTree OD47_tree=null;
		RewriteRuleTokenStream stream_FI=new RewriteRuleTokenStream(adaptor,"token FI");
		RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
		RewriteRuleTokenStream stream_FOR=new RewriteRuleTokenStream(adaptor,"token FOR");
		RewriteRuleTokenStream stream_DO=new RewriteRuleTokenStream(adaptor,"token DO");
		RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
		RewriteRuleTokenStream stream_FOREACH=new RewriteRuleTokenStream(adaptor,"token FOREACH");
		RewriteRuleTokenStream stream_NOP=new RewriteRuleTokenStream(adaptor,"token NOP");
		RewriteRuleTokenStream stream_OD=new RewriteRuleTokenStream(adaptor,"token OD");
		RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
		RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
		RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
		RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
		RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_exprs=new RewriteRuleSubtreeStream(adaptor,"rule exprs");
		RewriteRuleSubtreeStream stream_vars=new RewriteRuleSubtreeStream(adaptor,"rule vars");
		RewriteRuleSubtreeStream stream_commands=new RewriteRuleSubtreeStream(adaptor,"rule commands");

		try {
			// While_ast.g:50:5: ( NOP -> ^( NOP_NODE ) | vars ASSIGN exprs -> ^( ASSIGN_NODE vars exprs ) | IF expression THEN commands ( ELSE commands )? FI -> ^( IF expression ^( BLOCK commands ) ( ^( ELSE commands ) )? ) | WHILE expression DO commands OD -> ^( WHILE expression ^( BLOCK commands ) ) | FOR expression DO commands OD -> ^( FOR expression ^( BLOCK commands ) ) | FOREACH VARIABLE IN expression DO commands OD -> ^( FOREACH VARIABLE expression ^( BLOCK commands ) ) )
			int alt5=6;
			switch ( input.LA(1) ) {
			case NOP:
				{
				alt5=1;
				}
				break;
			case VARIABLE:
				{
				alt5=2;
				}
				break;
			case IF:
				{
				alt5=3;
				}
				break;
			case WHILE:
				{
				alt5=4;
				}
				break;
			case FOR:
				{
				alt5=5;
				}
				break;
			case FOREACH:
				{
				alt5=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// While_ast.g:50:7: NOP
					{
					NOP20=(Token)match(input,NOP,FOLLOW_NOP_in_command327);  
					stream_NOP.add(NOP20);


					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 50:11: -> ^( NOP_NODE )
					{
						// While_ast.g:50:14: ^( NOP_NODE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NOP_NODE, "NOP_NODE"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// While_ast.g:52:7: vars ASSIGN exprs
					{
					pushFollow(FOLLOW_vars_in_command346);
					vars21=vars();
					state._fsp--;

					stream_vars.add(vars21.getTree());
					ASSIGN22=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_command348);  
					stream_ASSIGN.add(ASSIGN22);

					pushFollow(FOLLOW_exprs_in_command350);
					exprs23=exprs();
					state._fsp--;

					stream_exprs.add(exprs23.getTree());

					// AST REWRITE
					// elements: vars, exprs
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 52:25: -> ^( ASSIGN_NODE vars exprs )
					{
						// While_ast.g:52:28: ^( ASSIGN_NODE vars exprs )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ASSIGN_NODE, "ASSIGN_NODE"), root_1);
						adaptor.addChild(root_1, stream_vars.nextTree());
						adaptor.addChild(root_1, stream_exprs.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// While_ast.g:54:7: IF expression THEN commands ( ELSE commands )? FI
					{
					IF24=(Token)match(input,IF,FOLLOW_IF_in_command373);  
					stream_IF.add(IF24);

					pushFollow(FOLLOW_expression_in_command375);
					expression25=expression();
					state._fsp--;

					stream_expression.add(expression25.getTree());
					THEN26=(Token)match(input,THEN,FOLLOW_THEN_in_command377);  
					stream_THEN.add(THEN26);

					pushFollow(FOLLOW_commands_in_command379);
					commands27=commands();
					state._fsp--;

					stream_commands.add(commands27.getTree());
					// While_ast.g:54:35: ( ELSE commands )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==ELSE) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// While_ast.g:54:36: ELSE commands
							{
							ELSE28=(Token)match(input,ELSE,FOLLOW_ELSE_in_command382);  
							stream_ELSE.add(ELSE28);

							pushFollow(FOLLOW_commands_in_command384);
							commands29=commands();
							state._fsp--;

							stream_commands.add(commands29.getTree());
							}
							break;

					}

					FI30=(Token)match(input,FI,FOLLOW_FI_in_command388);  
					stream_FI.add(FI30);


					// AST REWRITE
					// elements: ELSE, expression, commands, commands, IF
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 55:7: -> ^( IF expression ^( BLOCK commands ) ( ^( ELSE commands ) )? )
					{
						// While_ast.g:55:10: ^( IF expression ^( BLOCK commands ) ( ^( ELSE commands ) )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_IF.nextNode(), root_1);
						adaptor.addChild(root_1, stream_expression.nextTree());
						// While_ast.g:55:26: ^( BLOCK commands )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);
						adaptor.addChild(root_2, stream_commands.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						// While_ast.g:55:44: ( ^( ELSE commands ) )?
						if ( stream_ELSE.hasNext()||stream_commands.hasNext() ) {
							// While_ast.g:55:44: ^( ELSE commands )
							{
							CommonTree root_2 = (CommonTree)adaptor.nil();
							root_2 = (CommonTree)adaptor.becomeRoot(stream_ELSE.nextNode(), root_2);
							adaptor.addChild(root_2, stream_commands.nextTree());
							adaptor.addChild(root_1, root_2);
							}

						}
						stream_ELSE.reset();
						stream_commands.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// While_ast.g:57:7: WHILE expression DO commands OD
					{
					WHILE31=(Token)match(input,WHILE,FOLLOW_WHILE_in_command431);  
					stream_WHILE.add(WHILE31);

					pushFollow(FOLLOW_expression_in_command433);
					expression32=expression();
					state._fsp--;

					stream_expression.add(expression32.getTree());
					DO33=(Token)match(input,DO,FOLLOW_DO_in_command435);  
					stream_DO.add(DO33);

					pushFollow(FOLLOW_commands_in_command437);
					commands34=commands();
					state._fsp--;

					stream_commands.add(commands34.getTree());
					OD35=(Token)match(input,OD,FOLLOW_OD_in_command439);  
					stream_OD.add(OD35);


					// AST REWRITE
					// elements: expression, WHILE, commands
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 58:7: -> ^( WHILE expression ^( BLOCK commands ) )
					{
						// While_ast.g:58:10: ^( WHILE expression ^( BLOCK commands ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_WHILE.nextNode(), root_1);
						adaptor.addChild(root_1, stream_expression.nextTree());
						// While_ast.g:58:29: ^( BLOCK commands )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);
						adaptor.addChild(root_2, stream_commands.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// While_ast.g:60:7: FOR expression DO commands OD
					{
					FOR36=(Token)match(input,FOR,FOLLOW_FOR_in_command475);  
					stream_FOR.add(FOR36);

					pushFollow(FOLLOW_expression_in_command477);
					expression37=expression();
					state._fsp--;

					stream_expression.add(expression37.getTree());
					DO38=(Token)match(input,DO,FOLLOW_DO_in_command479);  
					stream_DO.add(DO38);

					pushFollow(FOLLOW_commands_in_command481);
					commands39=commands();
					state._fsp--;

					stream_commands.add(commands39.getTree());
					OD40=(Token)match(input,OD,FOLLOW_OD_in_command483);  
					stream_OD.add(OD40);


					// AST REWRITE
					// elements: commands, expression, FOR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 61:7: -> ^( FOR expression ^( BLOCK commands ) )
					{
						// While_ast.g:61:10: ^( FOR expression ^( BLOCK commands ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_FOR.nextNode(), root_1);
						adaptor.addChild(root_1, stream_expression.nextTree());
						// While_ast.g:61:27: ^( BLOCK commands )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);
						adaptor.addChild(root_2, stream_commands.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// While_ast.g:63:7: FOREACH VARIABLE IN expression DO commands OD
					{
					FOREACH41=(Token)match(input,FOREACH,FOLLOW_FOREACH_in_command519);  
					stream_FOREACH.add(FOREACH41);

					VARIABLE42=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_command521);  
					stream_VARIABLE.add(VARIABLE42);

					IN43=(Token)match(input,IN,FOLLOW_IN_in_command523);  
					stream_IN.add(IN43);

					pushFollow(FOLLOW_expression_in_command525);
					expression44=expression();
					state._fsp--;

					stream_expression.add(expression44.getTree());
					DO45=(Token)match(input,DO,FOLLOW_DO_in_command527);  
					stream_DO.add(DO45);

					pushFollow(FOLLOW_commands_in_command529);
					commands46=commands();
					state._fsp--;

					stream_commands.add(commands46.getTree());
					OD47=(Token)match(input,OD,FOLLOW_OD_in_command531);  
					stream_OD.add(OD47);


					// AST REWRITE
					// elements: FOREACH, commands, expression, VARIABLE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 64:7: -> ^( FOREACH VARIABLE expression ^( BLOCK commands ) )
					{
						// While_ast.g:64:10: ^( FOREACH VARIABLE expression ^( BLOCK commands ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_FOREACH.nextNode(), root_1);
						adaptor.addChild(root_1, stream_VARIABLE.nextNode());
						adaptor.addChild(root_1, stream_expression.nextTree());
						// While_ast.g:64:40: ^( BLOCK commands )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);
						adaptor.addChild(root_2, stream_commands.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "command"


	public static class vars_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "vars"
	// While_ast.g:67:1: vars : VARIABLE ( ',' VARIABLE )* -> ^( VARS ( VARIABLE )+ ) ;
	public final While_astParser.vars_return vars() throws RecognitionException {
		While_astParser.vars_return retval = new While_astParser.vars_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token VARIABLE48=null;
		Token char_literal49=null;
		Token VARIABLE50=null;

		CommonTree VARIABLE48_tree=null;
		CommonTree char_literal49_tree=null;
		CommonTree VARIABLE50_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");

		try {
			// While_ast.g:68:5: ( VARIABLE ( ',' VARIABLE )* -> ^( VARS ( VARIABLE )+ ) )
			// While_ast.g:68:7: VARIABLE ( ',' VARIABLE )*
			{
			VARIABLE48=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_vars571);  
			stream_VARIABLE.add(VARIABLE48);

			// While_ast.g:68:16: ( ',' VARIABLE )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==44) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// While_ast.g:68:17: ',' VARIABLE
					{
					char_literal49=(Token)match(input,44,FOLLOW_44_in_vars574);  
					stream_44.add(char_literal49);

					VARIABLE50=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_vars576);  
					stream_VARIABLE.add(VARIABLE50);

					}
					break;

				default :
					break loop6;
				}
			}


			// AST REWRITE
			// elements: VARIABLE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 68:32: -> ^( VARS ( VARIABLE )+ )
			{
				// While_ast.g:68:35: ^( VARS ( VARIABLE )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARS, "VARS"), root_1);
				if ( !(stream_VARIABLE.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_VARIABLE.hasNext() ) {
					adaptor.addChild(root_1, stream_VARIABLE.nextNode());
				}
				stream_VARIABLE.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "vars"


	public static class exprs_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "exprs"
	// While_ast.g:71:1: exprs : expression ( ',' expression )* -> ^( EXPRS ( expression )+ ) ;
	public final While_astParser.exprs_return exprs() throws RecognitionException {
		While_astParser.exprs_return retval = new While_astParser.exprs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal52=null;
		ParserRuleReturnScope expression51 =null;
		ParserRuleReturnScope expression53 =null;

		CommonTree char_literal52_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// While_ast.g:72:5: ( expression ( ',' expression )* -> ^( EXPRS ( expression )+ ) )
			// While_ast.g:72:7: expression ( ',' expression )*
			{
			pushFollow(FOLLOW_expression_in_exprs604);
			expression51=expression();
			state._fsp--;

			stream_expression.add(expression51.getTree());
			// While_ast.g:72:18: ( ',' expression )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==44) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// While_ast.g:72:19: ',' expression
					{
					char_literal52=(Token)match(input,44,FOLLOW_44_in_exprs607);  
					stream_44.add(char_literal52);

					pushFollow(FOLLOW_expression_in_exprs609);
					expression53=expression();
					state._fsp--;

					stream_expression.add(expression53.getTree());
					}
					break;

				default :
					break loop7;
				}
			}


			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 72:36: -> ^( EXPRS ( expression )+ )
			{
				// While_ast.g:72:39: ^( EXPRS ( expression )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EXPRS, "EXPRS"), root_1);
				if ( !(stream_expression.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_expression.nextTree());
				}
				stream_expression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exprs"


	public static class expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// While_ast.g:75:1: expression : expr_base ( EQ ^ expr_base )? ;
	public final While_astParser.expression_return expression() throws RecognitionException {
		While_astParser.expression_return retval = new While_astParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQ55=null;
		ParserRuleReturnScope expr_base54 =null;
		ParserRuleReturnScope expr_base56 =null;

		CommonTree EQ55_tree=null;

		try {
			// While_ast.g:76:5: ( expr_base ( EQ ^ expr_base )? )
			// While_ast.g:76:7: expr_base ( EQ ^ expr_base )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_base_in_expression637);
			expr_base54=expr_base();
			state._fsp--;

			adaptor.addChild(root_0, expr_base54.getTree());

			// While_ast.g:76:17: ( EQ ^ expr_base )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==EQ) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// While_ast.g:76:18: EQ ^ expr_base
					{
					EQ55=(Token)match(input,EQ,FOLLOW_EQ_in_expression640); 
					EQ55_tree = (CommonTree)adaptor.create(EQ55);
					root_0 = (CommonTree)adaptor.becomeRoot(EQ55_tree, root_0);

					pushFollow(FOLLOW_expr_base_in_expression643);
					expr_base56=expr_base();
					state._fsp--;

					adaptor.addChild(root_0, expr_base56.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class expr_base_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_base"
	// While_ast.g:79:1: expr_base : ( NIL | VARIABLE | 'true' -> ^( TRUE_NODE ) | 'false' -> ^( FALSE_NODE ) | '(' 'cons' lexpr ')' -> ^( CONS lexpr ) | '(' 'list' lexpr ')' -> ^( LIST lexpr ) | '(' 'hd' expr_base ')' -> ^( HEAD expr_base ) | '(' 'tl' expr_base ')' -> ^( TAIL expr_base ) | '(' SYMBOL lexpr ')' -> ^( CALL SYMBOL lexpr ) | '(' ! expression ')' !);
	public final While_astParser.expr_base_return expr_base() throws RecognitionException {
		While_astParser.expr_base_return retval = new While_astParser.expr_base_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NIL57=null;
		Token VARIABLE58=null;
		Token string_literal59=null;
		Token string_literal60=null;
		Token char_literal61=null;
		Token string_literal62=null;
		Token char_literal64=null;
		Token char_literal65=null;
		Token string_literal66=null;
		Token char_literal68=null;
		Token char_literal69=null;
		Token string_literal70=null;
		Token char_literal72=null;
		Token char_literal73=null;
		Token string_literal74=null;
		Token char_literal76=null;
		Token char_literal77=null;
		Token SYMBOL78=null;
		Token char_literal80=null;
		Token char_literal81=null;
		Token char_literal83=null;
		ParserRuleReturnScope lexpr63 =null;
		ParserRuleReturnScope lexpr67 =null;
		ParserRuleReturnScope expr_base71 =null;
		ParserRuleReturnScope expr_base75 =null;
		ParserRuleReturnScope lexpr79 =null;
		ParserRuleReturnScope expression82 =null;

		CommonTree NIL57_tree=null;
		CommonTree VARIABLE58_tree=null;
		CommonTree string_literal59_tree=null;
		CommonTree string_literal60_tree=null;
		CommonTree char_literal61_tree=null;
		CommonTree string_literal62_tree=null;
		CommonTree char_literal64_tree=null;
		CommonTree char_literal65_tree=null;
		CommonTree string_literal66_tree=null;
		CommonTree char_literal68_tree=null;
		CommonTree char_literal69_tree=null;
		CommonTree string_literal70_tree=null;
		CommonTree char_literal72_tree=null;
		CommonTree char_literal73_tree=null;
		CommonTree string_literal74_tree=null;
		CommonTree char_literal76_tree=null;
		CommonTree char_literal77_tree=null;
		CommonTree SYMBOL78_tree=null;
		CommonTree char_literal80_tree=null;
		CommonTree char_literal81_tree=null;
		CommonTree char_literal83_tree=null;
		RewriteRuleTokenStream stream_HEAD=new RewriteRuleTokenStream(adaptor,"token HEAD");
		RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
		RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
		RewriteRuleTokenStream stream_SYMBOL=new RewriteRuleTokenStream(adaptor,"token SYMBOL");
		RewriteRuleTokenStream stream_TAIL=new RewriteRuleTokenStream(adaptor,"token TAIL");
		RewriteRuleTokenStream stream_LIST=new RewriteRuleTokenStream(adaptor,"token LIST");
		RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
		RewriteRuleTokenStream stream_CONS=new RewriteRuleTokenStream(adaptor,"token CONS");
		RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
		RewriteRuleSubtreeStream stream_lexpr=new RewriteRuleSubtreeStream(adaptor,"rule lexpr");
		RewriteRuleSubtreeStream stream_expr_base=new RewriteRuleSubtreeStream(adaptor,"rule expr_base");

		try {
			// While_ast.g:80:5: ( NIL | VARIABLE | 'true' -> ^( TRUE_NODE ) | 'false' -> ^( FALSE_NODE ) | '(' 'cons' lexpr ')' -> ^( CONS lexpr ) | '(' 'list' lexpr ')' -> ^( LIST lexpr ) | '(' 'hd' expr_base ')' -> ^( HEAD expr_base ) | '(' 'tl' expr_base ')' -> ^( TAIL expr_base ) | '(' SYMBOL lexpr ')' -> ^( CALL SYMBOL lexpr ) | '(' ! expression ')' !)
			int alt9=10;
			switch ( input.LA(1) ) {
			case NIL:
				{
				alt9=1;
				}
				break;
			case VARIABLE:
				{
				alt9=2;
				}
				break;
			case 48:
				{
				alt9=3;
				}
				break;
			case 47:
				{
				alt9=4;
				}
				break;
			case 42:
				{
				switch ( input.LA(2) ) {
				case CONS:
					{
					alt9=5;
					}
					break;
				case LIST:
					{
					alt9=6;
					}
					break;
				case HEAD:
					{
					alt9=7;
					}
					break;
				case TAIL:
					{
					alt9=8;
					}
					break;
				case SYMBOL:
					{
					alt9=9;
					}
					break;
				case NIL:
				case VARIABLE:
				case 42:
				case 47:
				case 48:
					{
					alt9=10;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// While_ast.g:80:7: NIL
					{
					root_0 = (CommonTree)adaptor.nil();


					NIL57=(Token)match(input,NIL,FOLLOW_NIL_in_expr_base666); 
					NIL57_tree = (CommonTree)adaptor.create(NIL57);
					adaptor.addChild(root_0, NIL57_tree);

					}
					break;
				case 2 :
					// While_ast.g:81:7: VARIABLE
					{
					root_0 = (CommonTree)adaptor.nil();


					VARIABLE58=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_expr_base674); 
					VARIABLE58_tree = (CommonTree)adaptor.create(VARIABLE58);
					adaptor.addChild(root_0, VARIABLE58_tree);

					}
					break;
				case 3 :
					// While_ast.g:82:7: 'true'
					{
					string_literal59=(Token)match(input,48,FOLLOW_48_in_expr_base682);  
					stream_48.add(string_literal59);


					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 82:14: -> ^( TRUE_NODE )
					{
						// While_ast.g:82:17: ^( TRUE_NODE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TRUE_NODE, "TRUE_NODE"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// While_ast.g:83:7: 'false'
					{
					string_literal60=(Token)match(input,47,FOLLOW_47_in_expr_base696);  
					stream_47.add(string_literal60);


					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 83:15: -> ^( FALSE_NODE )
					{
						// While_ast.g:83:18: ^( FALSE_NODE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FALSE_NODE, "FALSE_NODE"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// While_ast.g:84:7: '(' 'cons' lexpr ')'
					{
					char_literal61=(Token)match(input,42,FOLLOW_42_in_expr_base710);  
					stream_42.add(char_literal61);

					string_literal62=(Token)match(input,CONS,FOLLOW_CONS_in_expr_base712);  
					stream_CONS.add(string_literal62);

					pushFollow(FOLLOW_lexpr_in_expr_base714);
					lexpr63=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr63.getTree());
					char_literal64=(Token)match(input,43,FOLLOW_43_in_expr_base716);  
					stream_43.add(char_literal64);


					// AST REWRITE
					// elements: lexpr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 84:28: -> ^( CONS lexpr )
					{
						// While_ast.g:84:31: ^( CONS lexpr )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONS, "CONS"), root_1);
						adaptor.addChild(root_1, stream_lexpr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// While_ast.g:85:7: '(' 'list' lexpr ')'
					{
					char_literal65=(Token)match(input,42,FOLLOW_42_in_expr_base732);  
					stream_42.add(char_literal65);

					string_literal66=(Token)match(input,LIST,FOLLOW_LIST_in_expr_base734);  
					stream_LIST.add(string_literal66);

					pushFollow(FOLLOW_lexpr_in_expr_base736);
					lexpr67=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr67.getTree());
					char_literal68=(Token)match(input,43,FOLLOW_43_in_expr_base738);  
					stream_43.add(char_literal68);


					// AST REWRITE
					// elements: lexpr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 85:28: -> ^( LIST lexpr )
					{
						// While_ast.g:85:31: ^( LIST lexpr )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LIST, "LIST"), root_1);
						adaptor.addChild(root_1, stream_lexpr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 7 :
					// While_ast.g:86:7: '(' 'hd' expr_base ')'
					{
					char_literal69=(Token)match(input,42,FOLLOW_42_in_expr_base754);  
					stream_42.add(char_literal69);

					string_literal70=(Token)match(input,HEAD,FOLLOW_HEAD_in_expr_base756);  
					stream_HEAD.add(string_literal70);

					pushFollow(FOLLOW_expr_base_in_expr_base758);
					expr_base71=expr_base();
					state._fsp--;

					stream_expr_base.add(expr_base71.getTree());
					char_literal72=(Token)match(input,43,FOLLOW_43_in_expr_base760);  
					stream_43.add(char_literal72);


					// AST REWRITE
					// elements: expr_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 86:30: -> ^( HEAD expr_base )
					{
						// While_ast.g:86:33: ^( HEAD expr_base )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(HEAD, "HEAD"), root_1);
						adaptor.addChild(root_1, stream_expr_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 8 :
					// While_ast.g:87:7: '(' 'tl' expr_base ')'
					{
					char_literal73=(Token)match(input,42,FOLLOW_42_in_expr_base776);  
					stream_42.add(char_literal73);

					string_literal74=(Token)match(input,TAIL,FOLLOW_TAIL_in_expr_base778);  
					stream_TAIL.add(string_literal74);

					pushFollow(FOLLOW_expr_base_in_expr_base780);
					expr_base75=expr_base();
					state._fsp--;

					stream_expr_base.add(expr_base75.getTree());
					char_literal76=(Token)match(input,43,FOLLOW_43_in_expr_base782);  
					stream_43.add(char_literal76);


					// AST REWRITE
					// elements: expr_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 87:30: -> ^( TAIL expr_base )
					{
						// While_ast.g:87:33: ^( TAIL expr_base )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TAIL, "TAIL"), root_1);
						adaptor.addChild(root_1, stream_expr_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 9 :
					// While_ast.g:88:7: '(' SYMBOL lexpr ')'
					{
					char_literal77=(Token)match(input,42,FOLLOW_42_in_expr_base798);  
					stream_42.add(char_literal77);

					SYMBOL78=(Token)match(input,SYMBOL,FOLLOW_SYMBOL_in_expr_base800);  
					stream_SYMBOL.add(SYMBOL78);

					pushFollow(FOLLOW_lexpr_in_expr_base802);
					lexpr79=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr79.getTree());
					char_literal80=(Token)match(input,43,FOLLOW_43_in_expr_base804);  
					stream_43.add(char_literal80);


					// AST REWRITE
					// elements: SYMBOL, lexpr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 88:30: -> ^( CALL SYMBOL lexpr )
					{
						// While_ast.g:88:33: ^( CALL SYMBOL lexpr )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CALL, "CALL"), root_1);
						adaptor.addChild(root_1, stream_SYMBOL.nextNode());
						adaptor.addChild(root_1, stream_lexpr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 10 :
					// While_ast.g:89:7: '(' ! expression ')' !
					{
					root_0 = (CommonTree)adaptor.nil();


					char_literal81=(Token)match(input,42,FOLLOW_42_in_expr_base824); 
					pushFollow(FOLLOW_expression_in_expr_base827);
					expression82=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression82.getTree());

					char_literal83=(Token)match(input,43,FOLLOW_43_in_expr_base829); 
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_base"


	public static class lexpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "lexpr"
	// While_ast.g:92:1: lexpr : ( expr_base )* ;
	public final While_astParser.lexpr_return lexpr() throws RecognitionException {
		While_astParser.lexpr_return retval = new While_astParser.lexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expr_base84 =null;


		try {
			// While_ast.g:93:5: ( ( expr_base )* )
			// While_ast.g:93:7: ( expr_base )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// While_ast.g:93:7: ( expr_base )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==NIL||LA10_0==VARIABLE||LA10_0==42||(LA10_0 >= 47 && LA10_0 <= 48)) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// While_ast.g:93:8: expr_base
					{
					pushFollow(FOLLOW_expr_base_in_lexpr849);
					expr_base84=expr_base();
					state._fsp--;

					adaptor.addChild(root_0, expr_base84.getTree());

					}
					break;

				default :
					break loop10;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lexpr"

	// Delegated rules



	public static final BitSet FOLLOW_program_in_start110 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_start112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_in_program138 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_FUNCTION_in_function158 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_SYMBOL_in_function160 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_45_in_function162 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_definition_in_function164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_in_definition198 = new BitSet(new long[]{0x0000021000000000L});
	public static final BitSet FOLLOW_input_in_definition200 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_41_in_definition202 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_definition204 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_41_in_definition206 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_WRITE_in_definition208 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_output_in_definition210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_input256 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_output289 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_command_in_commands307 = new BitSet(new long[]{0x0000400000000002L});
	public static final BitSet FOLLOW_46_in_commands310 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_command_in_commands312 = new BitSet(new long[]{0x0000400000000002L});
	public static final BitSet FOLLOW_NOP_in_command327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_command346 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ASSIGN_in_command348 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_exprs_in_command350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_command373 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_command375 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_THEN_in_command377 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_command379 = new BitSet(new long[]{0x0000000000008800L});
	public static final BitSet FOLLOW_ELSE_in_command382 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_command384 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_FI_in_command388 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_command431 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_command433 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_DO_in_command435 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_command437 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_OD_in_command439 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_command475 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_command477 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_DO_in_command479 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_command481 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_OD_in_command483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOREACH_in_command519 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_VARIABLE_in_command521 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_IN_in_command523 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_command525 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_DO_in_command527 = new BitSet(new long[]{0x0000005002230000L});
	public static final BitSet FOLLOW_commands_in_command529 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_OD_in_command531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_vars571 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_44_in_vars574 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_VARIABLE_in_vars576 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_expression_in_exprs604 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_44_in_exprs607 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_exprs609 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_expr_base_in_expression637 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_EQ_in_expression640 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expr_base_in_expression643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NIL_in_expr_base666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_expr_base674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_48_in_expr_base682 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_47_in_expr_base696 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base710 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_CONS_in_expr_base712 = new BitSet(new long[]{0x00018C1001000000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base714 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base732 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_LIST_in_expr_base734 = new BitSet(new long[]{0x00018C1001000000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base736 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base754 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_HEAD_in_expr_base756 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expr_base_in_expr_base758 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base760 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base776 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_TAIL_in_expr_base778 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expr_base_in_expr_base780 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base798 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_SYMBOL_in_expr_base800 = new BitSet(new long[]{0x00018C1001000000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base802 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_expr_base824 = new BitSet(new long[]{0x0001841001000000L});
	public static final BitSet FOLLOW_expression_in_expr_base827 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_base_in_lexpr849 = new BitSet(new long[]{0x0001841001000002L});
}
