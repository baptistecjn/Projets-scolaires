// $ANTLR 3.5.3 While_grammar.g 2025-12-12 16:05:48

    package antlr_grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class While_grammarParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGN", "BLOCK", "CALL", "COMMENT", 
		"CONS", "FUNC", "HEAD", "LIST", "NAME", "NIL", "PARAM", "PROG", "RESULT", 
		"SYMBOL", "TAIL", "VARIABLE", "WS", "'%'", "'('", "')'", "','", "':'", 
		"':='", "';'", "'=?'", "'cons'", "'do'", "'else'", "'fi'", "'for'", "'foreach'", 
		"'function'", "'hd'", "'if'", "'in'", "'list'", "'od'", "'read'", "'then'", 
		"'tl'", "'while'", "'write'"
	};
	public static final int EOF=-1;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int ASSIGN=4;
	public static final int BLOCK=5;
	public static final int CALL=6;
	public static final int COMMENT=7;
	public static final int CONS=8;
	public static final int FUNC=9;
	public static final int HEAD=10;
	public static final int LIST=11;
	public static final int NAME=12;
	public static final int NIL=13;
	public static final int PARAM=14;
	public static final int PROG=15;
	public static final int RESULT=16;
	public static final int SYMBOL=17;
	public static final int TAIL=18;
	public static final int VARIABLE=19;
	public static final int WS=20;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public While_grammarParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public While_grammarParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return While_grammarParser.tokenNames; }
	@Override public String getGrammarFileName() { return "While_grammar.g"; }


	public static class start_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "start"
	// While_grammar.g:34:1: start : program -> ^( PROG program ) ;
	public final While_grammarParser.start_return start() throws RecognitionException {
		While_grammarParser.start_return retval = new While_grammarParser.start_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope program1 =null;

		RewriteRuleSubtreeStream stream_program=new RewriteRuleSubtreeStream(adaptor,"rule program");

		try {
			// While_grammar.g:35:5: ( program -> ^( PROG program ) )
			// While_grammar.g:35:7: program
			{
			pushFollow(FOLLOW_program_in_start151);
			program1=program();
			state._fsp--;

			stream_program.add(program1.getTree());

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
			// 35:15: -> ^( PROG program )
			{
				// While_grammar.g:35:18: ^( PROG program )
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
	// While_grammar.g:39:1: program : ( function )* ;
	public final While_grammarParser.program_return program() throws RecognitionException {
		While_grammarParser.program_return retval = new While_grammarParser.program_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope function2 =null;


		try {
			// While_grammar.g:40:5: ( ( function )* )
			// While_grammar.g:40:7: ( function )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// While_grammar.g:40:7: ( function )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==35) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// While_grammar.g:40:8: function
					{
					pushFollow(FOLLOW_function_in_program178);
					function2=function();
					state._fsp--;

					adaptor.addChild(root_0, function2.getTree());

					}
					break;

				default :
					break loop1;
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
	// $ANTLR end "program"


	public static class function_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function"
	// While_grammar.g:43:1: function : 'function' SYMBOL ':' definition -> ^( FUNC ^( NAME SYMBOL ) definition ) ;
	public final While_grammarParser.function_return function() throws RecognitionException {
		While_grammarParser.function_return retval = new While_grammarParser.function_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal3=null;
		Token SYMBOL4=null;
		Token char_literal5=null;
		ParserRuleReturnScope definition6 =null;

		CommonTree string_literal3_tree=null;
		CommonTree SYMBOL4_tree=null;
		CommonTree char_literal5_tree=null;
		RewriteRuleTokenStream stream_35=new RewriteRuleTokenStream(adaptor,"token 35");
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleTokenStream stream_SYMBOL=new RewriteRuleTokenStream(adaptor,"token SYMBOL");
		RewriteRuleSubtreeStream stream_definition=new RewriteRuleSubtreeStream(adaptor,"rule definition");

		try {
			// While_grammar.g:44:5: ( 'function' SYMBOL ':' definition -> ^( FUNC ^( NAME SYMBOL ) definition ) )
			// While_grammar.g:44:7: 'function' SYMBOL ':' definition
			{
			string_literal3=(Token)match(input,35,FOLLOW_35_in_function197);  
			stream_35.add(string_literal3);

			SYMBOL4=(Token)match(input,SYMBOL,FOLLOW_SYMBOL_in_function199);  
			stream_SYMBOL.add(SYMBOL4);

			char_literal5=(Token)match(input,25,FOLLOW_25_in_function201);  
			stream_25.add(char_literal5);

			pushFollow(FOLLOW_definition_in_function203);
			definition6=definition();
			state._fsp--;

			stream_definition.add(definition6.getTree());

			// AST REWRITE
			// elements: definition, SYMBOL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 46:7: -> ^( FUNC ^( NAME SYMBOL ) definition )
			{
				// While_grammar.g:46:10: ^( FUNC ^( NAME SYMBOL ) definition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNC, "FUNC"), root_1);
				// While_grammar.g:46:17: ^( NAME SYMBOL )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NAME, "NAME"), root_2);
				adaptor.addChild(root_2, stream_SYMBOL.nextNode());
				adaptor.addChild(root_1, root_2);
				}

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
	// While_grammar.g:49:1: definition : 'read' input '%' commands '%' 'write' output -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output ) ;
	public final While_grammarParser.definition_return definition() throws RecognitionException {
		While_grammarParser.definition_return retval = new While_grammarParser.definition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal7=null;
		Token char_literal9=null;
		Token char_literal11=null;
		Token string_literal12=null;
		ParserRuleReturnScope input8 =null;
		ParserRuleReturnScope commands10 =null;
		ParserRuleReturnScope output13 =null;

		CommonTree string_literal7_tree=null;
		CommonTree char_literal9_tree=null;
		CommonTree char_literal11_tree=null;
		CommonTree string_literal12_tree=null;
		RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
		RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
		RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");
		RewriteRuleSubtreeStream stream_output=new RewriteRuleSubtreeStream(adaptor,"rule output");
		RewriteRuleSubtreeStream stream_input=new RewriteRuleSubtreeStream(adaptor,"rule input");
		RewriteRuleSubtreeStream stream_commands=new RewriteRuleSubtreeStream(adaptor,"rule commands");

		try {
			// While_grammar.g:50:5: ( 'read' input '%' commands '%' 'write' output -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output ) )
			// While_grammar.g:50:7: 'read' input '%' commands '%' 'write' output
			{
			string_literal7=(Token)match(input,41,FOLLOW_41_in_definition247);  
			stream_41.add(string_literal7);

			pushFollow(FOLLOW_input_in_definition249);
			input8=input();
			state._fsp--;

			stream_input.add(input8.getTree());
			char_literal9=(Token)match(input,21,FOLLOW_21_in_definition251);  
			stream_21.add(char_literal9);

			pushFollow(FOLLOW_commands_in_definition253);
			commands10=commands();
			state._fsp--;

			stream_commands.add(commands10.getTree());
			char_literal11=(Token)match(input,21,FOLLOW_21_in_definition255);  
			stream_21.add(char_literal11);

			string_literal12=(Token)match(input,45,FOLLOW_45_in_definition257);  
			stream_45.add(string_literal12);

			pushFollow(FOLLOW_output_in_definition259);
			output13=output();
			state._fsp--;

			stream_output.add(output13.getTree());

			// AST REWRITE
			// elements: output, input, commands
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 51:7: -> ^( PARAM input ) ^( BLOCK commands ) ^( RESULT output )
			{
				// While_grammar.g:51:10: ^( PARAM input )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PARAM, "PARAM"), root_1);
				adaptor.addChild(root_1, stream_input.nextTree());
				adaptor.addChild(root_0, root_1);
				}

				// While_grammar.g:51:25: ^( BLOCK commands )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_1);
				adaptor.addChild(root_1, stream_commands.nextTree());
				adaptor.addChild(root_0, root_1);
				}

				// While_grammar.g:51:43: ^( RESULT output )
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
	// While_grammar.g:54:1: input : ( vars |);
	public final While_grammarParser.input_return input() throws RecognitionException {
		While_grammarParser.input_return retval = new While_grammarParser.input_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope vars14 =null;


		try {
			// While_grammar.g:54:9: ( vars |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==VARIABLE) ) {
				alt2=1;
			}
			else if ( (LA2_0==21) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// While_grammar.g:54:11: vars
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_vars_in_input300);
					vars14=vars();
					state._fsp--;

					adaptor.addChild(root_0, vars14.getTree());

					}
					break;
				case 2 :
					// While_grammar.g:54:18: 
					{
					root_0 = (CommonTree)adaptor.nil();


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
	// While_grammar.g:55:1: output : vars ;
	public final While_grammarParser.output_return output() throws RecognitionException {
		While_grammarParser.output_return retval = new While_grammarParser.output_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope vars15 =null;


		try {
			// While_grammar.g:55:9: ( vars )
			// While_grammar.g:55:11: vars
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_vars_in_output312);
			vars15=vars();
			state._fsp--;

			adaptor.addChild(root_0, vars15.getTree());

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
	// While_grammar.g:57:1: commands : command ( ';' ! command )* ;
	public final While_grammarParser.commands_return commands() throws RecognitionException {
		While_grammarParser.commands_return retval = new While_grammarParser.commands_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal17=null;
		ParserRuleReturnScope command16 =null;
		ParserRuleReturnScope command18 =null;

		CommonTree char_literal17_tree=null;

		try {
			// While_grammar.g:58:5: ( command ( ';' ! command )* )
			// While_grammar.g:58:7: command ( ';' ! command )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_command_in_commands324);
			command16=command();
			state._fsp--;

			adaptor.addChild(root_0, command16.getTree());

			// While_grammar.g:58:15: ( ';' ! command )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==27) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// While_grammar.g:58:16: ';' ! command
					{
					char_literal17=(Token)match(input,27,FOLLOW_27_in_commands327); 
					pushFollow(FOLLOW_command_in_commands330);
					command18=command();
					state._fsp--;

					adaptor.addChild(root_0, command18.getTree());

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
	// While_grammar.g:61:1: command : ( 'if' ^ expression 'then' ! command ( 'else' ! commands )? 'fi' !| 'while' ^ expression 'do' ! commands 'od' !| 'for' ^ expression 'do' ! commands 'od' !| 'foreach' ^ VARIABLE 'in' ! expression 'do' ! commands 'od' !| vars ':=' exprs -> ^( ASSIGN vars exprs ) );
	public final While_grammarParser.command_return command() throws RecognitionException {
		While_grammarParser.command_return retval = new While_grammarParser.command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal19=null;
		Token string_literal21=null;
		Token string_literal23=null;
		Token string_literal25=null;
		Token string_literal26=null;
		Token string_literal28=null;
		Token string_literal30=null;
		Token string_literal31=null;
		Token string_literal33=null;
		Token string_literal35=null;
		Token string_literal36=null;
		Token VARIABLE37=null;
		Token string_literal38=null;
		Token string_literal40=null;
		Token string_literal42=null;
		Token string_literal44=null;
		ParserRuleReturnScope expression20 =null;
		ParserRuleReturnScope command22 =null;
		ParserRuleReturnScope commands24 =null;
		ParserRuleReturnScope expression27 =null;
		ParserRuleReturnScope commands29 =null;
		ParserRuleReturnScope expression32 =null;
		ParserRuleReturnScope commands34 =null;
		ParserRuleReturnScope expression39 =null;
		ParserRuleReturnScope commands41 =null;
		ParserRuleReturnScope vars43 =null;
		ParserRuleReturnScope exprs45 =null;

		CommonTree string_literal19_tree=null;
		CommonTree string_literal21_tree=null;
		CommonTree string_literal23_tree=null;
		CommonTree string_literal25_tree=null;
		CommonTree string_literal26_tree=null;
		CommonTree string_literal28_tree=null;
		CommonTree string_literal30_tree=null;
		CommonTree string_literal31_tree=null;
		CommonTree string_literal33_tree=null;
		CommonTree string_literal35_tree=null;
		CommonTree string_literal36_tree=null;
		CommonTree VARIABLE37_tree=null;
		CommonTree string_literal38_tree=null;
		CommonTree string_literal40_tree=null;
		CommonTree string_literal42_tree=null;
		CommonTree string_literal44_tree=null;
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleSubtreeStream stream_exprs=new RewriteRuleSubtreeStream(adaptor,"rule exprs");
		RewriteRuleSubtreeStream stream_vars=new RewriteRuleSubtreeStream(adaptor,"rule vars");

		try {
			// While_grammar.g:62:5: ( 'if' ^ expression 'then' ! command ( 'else' ! commands )? 'fi' !| 'while' ^ expression 'do' ! commands 'od' !| 'for' ^ expression 'do' ! commands 'od' !| 'foreach' ^ VARIABLE 'in' ! expression 'do' ! commands 'od' !| vars ':=' exprs -> ^( ASSIGN vars exprs ) )
			int alt5=5;
			switch ( input.LA(1) ) {
			case 37:
				{
				alt5=1;
				}
				break;
			case 44:
				{
				alt5=2;
				}
				break;
			case 33:
				{
				alt5=3;
				}
				break;
			case 34:
				{
				alt5=4;
				}
				break;
			case VARIABLE:
				{
				alt5=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// While_grammar.g:62:7: 'if' ^ expression 'then' ! command ( 'else' ! commands )? 'fi' !
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal19=(Token)match(input,37,FOLLOW_37_in_command349); 
					string_literal19_tree = (CommonTree)adaptor.create(string_literal19);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal19_tree, root_0);

					pushFollow(FOLLOW_expression_in_command352);
					expression20=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression20.getTree());

					string_literal21=(Token)match(input,42,FOLLOW_42_in_command354); 
					pushFollow(FOLLOW_command_in_command357);
					command22=command();
					state._fsp--;

					adaptor.addChild(root_0, command22.getTree());

					// While_grammar.g:62:40: ( 'else' ! commands )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==31) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// While_grammar.g:62:41: 'else' ! commands
							{
							string_literal23=(Token)match(input,31,FOLLOW_31_in_command360); 
							pushFollow(FOLLOW_commands_in_command363);
							commands24=commands();
							state._fsp--;

							adaptor.addChild(root_0, commands24.getTree());

							}
							break;

					}

					string_literal25=(Token)match(input,32,FOLLOW_32_in_command367); 
					}
					break;
				case 2 :
					// While_grammar.g:63:7: 'while' ^ expression 'do' ! commands 'od' !
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal26=(Token)match(input,44,FOLLOW_44_in_command376); 
					string_literal26_tree = (CommonTree)adaptor.create(string_literal26);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal26_tree, root_0);

					pushFollow(FOLLOW_expression_in_command379);
					expression27=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression27.getTree());

					string_literal28=(Token)match(input,30,FOLLOW_30_in_command381); 
					pushFollow(FOLLOW_commands_in_command384);
					commands29=commands();
					state._fsp--;

					adaptor.addChild(root_0, commands29.getTree());

					string_literal30=(Token)match(input,40,FOLLOW_40_in_command386); 
					}
					break;
				case 3 :
					// While_grammar.g:64:7: 'for' ^ expression 'do' ! commands 'od' !
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal31=(Token)match(input,33,FOLLOW_33_in_command395); 
					string_literal31_tree = (CommonTree)adaptor.create(string_literal31);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal31_tree, root_0);

					pushFollow(FOLLOW_expression_in_command398);
					expression32=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression32.getTree());

					string_literal33=(Token)match(input,30,FOLLOW_30_in_command400); 
					pushFollow(FOLLOW_commands_in_command403);
					commands34=commands();
					state._fsp--;

					adaptor.addChild(root_0, commands34.getTree());

					string_literal35=(Token)match(input,40,FOLLOW_40_in_command405); 
					}
					break;
				case 4 :
					// While_grammar.g:65:7: 'foreach' ^ VARIABLE 'in' ! expression 'do' ! commands 'od' !
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal36=(Token)match(input,34,FOLLOW_34_in_command414); 
					string_literal36_tree = (CommonTree)adaptor.create(string_literal36);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal36_tree, root_0);

					VARIABLE37=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_command417); 
					VARIABLE37_tree = (CommonTree)adaptor.create(VARIABLE37);
					adaptor.addChild(root_0, VARIABLE37_tree);

					string_literal38=(Token)match(input,38,FOLLOW_38_in_command419); 
					pushFollow(FOLLOW_expression_in_command422);
					expression39=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression39.getTree());

					string_literal40=(Token)match(input,30,FOLLOW_30_in_command424); 
					pushFollow(FOLLOW_commands_in_command427);
					commands41=commands();
					state._fsp--;

					adaptor.addChild(root_0, commands41.getTree());

					string_literal42=(Token)match(input,40,FOLLOW_40_in_command429); 
					}
					break;
				case 5 :
					// While_grammar.g:66:7: vars ':=' exprs
					{
					pushFollow(FOLLOW_vars_in_command438);
					vars43=vars();
					state._fsp--;

					stream_vars.add(vars43.getTree());
					string_literal44=(Token)match(input,26,FOLLOW_26_in_command440);  
					stream_26.add(string_literal44);

					pushFollow(FOLLOW_exprs_in_command442);
					exprs45=exprs();
					state._fsp--;

					stream_exprs.add(exprs45.getTree());

					// AST REWRITE
					// elements: exprs, vars
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 66:23: -> ^( ASSIGN vars exprs )
					{
						// While_grammar.g:66:26: ^( ASSIGN vars exprs )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ASSIGN, "ASSIGN"), root_1);
						adaptor.addChild(root_1, stream_vars.nextTree());
						adaptor.addChild(root_1, stream_exprs.nextTree());
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


	public static class exprs_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "exprs"
	// While_grammar.g:69:1: exprs : expression ( ',' ! expression )* ;
	public final While_grammarParser.exprs_return exprs() throws RecognitionException {
		While_grammarParser.exprs_return retval = new While_grammarParser.exprs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal47=null;
		ParserRuleReturnScope expression46 =null;
		ParserRuleReturnScope expression48 =null;

		CommonTree char_literal47_tree=null;

		try {
			// While_grammar.g:70:5: ( expression ( ',' ! expression )* )
			// While_grammar.g:70:7: expression ( ',' ! expression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_exprs470);
			expression46=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression46.getTree());

			// While_grammar.g:70:18: ( ',' ! expression )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==24) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// While_grammar.g:70:19: ',' ! expression
					{
					char_literal47=(Token)match(input,24,FOLLOW_24_in_exprs473); 
					pushFollow(FOLLOW_expression_in_exprs476);
					expression48=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression48.getTree());

					}
					break;

				default :
					break loop6;
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
	// $ANTLR end "exprs"


	public static class vars_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "vars"
	// While_grammar.g:73:1: vars : VARIABLE ( ',' ! vars )? ;
	public final While_grammarParser.vars_return vars() throws RecognitionException {
		While_grammarParser.vars_return retval = new While_grammarParser.vars_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token VARIABLE49=null;
		Token char_literal50=null;
		ParserRuleReturnScope vars51 =null;

		CommonTree VARIABLE49_tree=null;
		CommonTree char_literal50_tree=null;

		try {
			// While_grammar.g:74:5: ( VARIABLE ( ',' ! vars )? )
			// While_grammar.g:74:7: VARIABLE ( ',' ! vars )?
			{
			root_0 = (CommonTree)adaptor.nil();


			VARIABLE49=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_vars495); 
			VARIABLE49_tree = (CommonTree)adaptor.create(VARIABLE49);
			adaptor.addChild(root_0, VARIABLE49_tree);

			// While_grammar.g:74:16: ( ',' ! vars )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==24) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// While_grammar.g:74:17: ',' ! vars
					{
					char_literal50=(Token)match(input,24,FOLLOW_24_in_vars498); 
					pushFollow(FOLLOW_vars_in_vars501);
					vars51=vars();
					state._fsp--;

					adaptor.addChild(root_0, vars51.getTree());

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
	// $ANTLR end "vars"


	public static class expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// While_grammar.g:77:1: expression : expr_base ( '=?' ^ expr_base )* ;
	public final While_grammarParser.expression_return expression() throws RecognitionException {
		While_grammarParser.expression_return retval = new While_grammarParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal53=null;
		ParserRuleReturnScope expr_base52 =null;
		ParserRuleReturnScope expr_base54 =null;

		CommonTree string_literal53_tree=null;

		try {
			// While_grammar.g:78:5: ( expr_base ( '=?' ^ expr_base )* )
			// While_grammar.g:78:7: expr_base ( '=?' ^ expr_base )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_base_in_expression521);
			expr_base52=expr_base();
			state._fsp--;

			adaptor.addChild(root_0, expr_base52.getTree());

			// While_grammar.g:78:17: ( '=?' ^ expr_base )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==28) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// While_grammar.g:78:18: '=?' ^ expr_base
					{
					string_literal53=(Token)match(input,28,FOLLOW_28_in_expression524); 
					string_literal53_tree = (CommonTree)adaptor.create(string_literal53);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal53_tree, root_0);

					pushFollow(FOLLOW_expr_base_in_expression527);
					expr_base54=expr_base();
					state._fsp--;

					adaptor.addChild(root_0, expr_base54.getTree());

					}
					break;

				default :
					break loop8;
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
	// $ANTLR end "expression"


	public static class expr_base_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_base"
	// While_grammar.g:81:1: expr_base : ( '(' 'cons' lexpr ')' -> ^( CONS lexpr ) | '(' 'list' lexpr ')' -> ^( LIST lexpr ) | '(' 'hd' expr_base ')' -> ^( HEAD expr_base ) | '(' 'tl' expr_base ')' -> ^( TAIL expr_base ) | '(' SYMBOL lexpr ')' -> ^( CALL SYMBOL lexpr ) | NIL | VARIABLE );
	public final While_grammarParser.expr_base_return expr_base() throws RecognitionException {
		While_grammarParser.expr_base_return retval = new While_grammarParser.expr_base_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token char_literal55=null;
		Token string_literal56=null;
		Token char_literal58=null;
		Token char_literal59=null;
		Token string_literal60=null;
		Token char_literal62=null;
		Token char_literal63=null;
		Token string_literal64=null;
		Token char_literal66=null;
		Token char_literal67=null;
		Token string_literal68=null;
		Token char_literal70=null;
		Token char_literal71=null;
		Token SYMBOL72=null;
		Token char_literal74=null;
		Token NIL75=null;
		Token VARIABLE76=null;
		ParserRuleReturnScope lexpr57 =null;
		ParserRuleReturnScope lexpr61 =null;
		ParserRuleReturnScope expr_base65 =null;
		ParserRuleReturnScope expr_base69 =null;
		ParserRuleReturnScope lexpr73 =null;

		CommonTree char_literal55_tree=null;
		CommonTree string_literal56_tree=null;
		CommonTree char_literal58_tree=null;
		CommonTree char_literal59_tree=null;
		CommonTree string_literal60_tree=null;
		CommonTree char_literal62_tree=null;
		CommonTree char_literal63_tree=null;
		CommonTree string_literal64_tree=null;
		CommonTree char_literal66_tree=null;
		CommonTree char_literal67_tree=null;
		CommonTree string_literal68_tree=null;
		CommonTree char_literal70_tree=null;
		CommonTree char_literal71_tree=null;
		CommonTree SYMBOL72_tree=null;
		CommonTree char_literal74_tree=null;
		CommonTree NIL75_tree=null;
		CommonTree VARIABLE76_tree=null;
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
		RewriteRuleTokenStream stream_SYMBOL=new RewriteRuleTokenStream(adaptor,"token SYMBOL");
		RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
		RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
		RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
		RewriteRuleSubtreeStream stream_lexpr=new RewriteRuleSubtreeStream(adaptor,"rule lexpr");
		RewriteRuleSubtreeStream stream_expr_base=new RewriteRuleSubtreeStream(adaptor,"rule expr_base");

		try {
			// While_grammar.g:82:5: ( '(' 'cons' lexpr ')' -> ^( CONS lexpr ) | '(' 'list' lexpr ')' -> ^( LIST lexpr ) | '(' 'hd' expr_base ')' -> ^( HEAD expr_base ) | '(' 'tl' expr_base ')' -> ^( TAIL expr_base ) | '(' SYMBOL lexpr ')' -> ^( CALL SYMBOL lexpr ) | NIL | VARIABLE )
			int alt9=7;
			switch ( input.LA(1) ) {
			case 22:
				{
				switch ( input.LA(2) ) {
				case 29:
					{
					alt9=1;
					}
					break;
				case 39:
					{
					alt9=2;
					}
					break;
				case 36:
					{
					alt9=3;
					}
					break;
				case 43:
					{
					alt9=4;
					}
					break;
				case SYMBOL:
					{
					alt9=5;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case NIL:
				{
				alt9=6;
				}
				break;
			case VARIABLE:
				{
				alt9=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// While_grammar.g:82:9: '(' 'cons' lexpr ')'
					{
					char_literal55=(Token)match(input,22,FOLLOW_22_in_expr_base548);  
					stream_22.add(char_literal55);

					string_literal56=(Token)match(input,29,FOLLOW_29_in_expr_base550);  
					stream_29.add(string_literal56);

					pushFollow(FOLLOW_lexpr_in_expr_base552);
					lexpr57=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr57.getTree());
					char_literal58=(Token)match(input,23,FOLLOW_23_in_expr_base554);  
					stream_23.add(char_literal58);


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
					// 82:36: -> ^( CONS lexpr )
					{
						// While_grammar.g:82:39: ^( CONS lexpr )
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
				case 2 :
					// While_grammar.g:83:9: '(' 'list' lexpr ')'
					{
					char_literal59=(Token)match(input,22,FOLLOW_22_in_expr_base578);  
					stream_22.add(char_literal59);

					string_literal60=(Token)match(input,39,FOLLOW_39_in_expr_base580);  
					stream_39.add(string_literal60);

					pushFollow(FOLLOW_lexpr_in_expr_base582);
					lexpr61=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr61.getTree());
					char_literal62=(Token)match(input,23,FOLLOW_23_in_expr_base584);  
					stream_23.add(char_literal62);


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
					// 83:36: -> ^( LIST lexpr )
					{
						// While_grammar.g:83:39: ^( LIST lexpr )
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
				case 3 :
					// While_grammar.g:84:9: '(' 'hd' expr_base ')'
					{
					char_literal63=(Token)match(input,22,FOLLOW_22_in_expr_base608);  
					stream_22.add(char_literal63);

					string_literal64=(Token)match(input,36,FOLLOW_36_in_expr_base610);  
					stream_36.add(string_literal64);

					pushFollow(FOLLOW_expr_base_in_expr_base612);
					expr_base65=expr_base();
					state._fsp--;

					stream_expr_base.add(expr_base65.getTree());
					char_literal66=(Token)match(input,23,FOLLOW_23_in_expr_base614);  
					stream_23.add(char_literal66);


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
					// 84:36: -> ^( HEAD expr_base )
					{
						// While_grammar.g:84:39: ^( HEAD expr_base )
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
				case 4 :
					// While_grammar.g:85:9: '(' 'tl' expr_base ')'
					{
					char_literal67=(Token)match(input,22,FOLLOW_22_in_expr_base637);  
					stream_22.add(char_literal67);

					string_literal68=(Token)match(input,43,FOLLOW_43_in_expr_base639);  
					stream_43.add(string_literal68);

					pushFollow(FOLLOW_expr_base_in_expr_base641);
					expr_base69=expr_base();
					state._fsp--;

					stream_expr_base.add(expr_base69.getTree());
					char_literal70=(Token)match(input,23,FOLLOW_23_in_expr_base643);  
					stream_23.add(char_literal70);


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
					// 85:36: -> ^( TAIL expr_base )
					{
						// While_grammar.g:85:39: ^( TAIL expr_base )
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
				case 5 :
					// While_grammar.g:86:9: '(' SYMBOL lexpr ')'
					{
					char_literal71=(Token)match(input,22,FOLLOW_22_in_expr_base665);  
					stream_22.add(char_literal71);

					SYMBOL72=(Token)match(input,SYMBOL,FOLLOW_SYMBOL_in_expr_base667);  
					stream_SYMBOL.add(SYMBOL72);

					pushFollow(FOLLOW_lexpr_in_expr_base669);
					lexpr73=lexpr();
					state._fsp--;

					stream_lexpr.add(lexpr73.getTree());
					char_literal74=(Token)match(input,23,FOLLOW_23_in_expr_base671);  
					stream_23.add(char_literal74);


					// AST REWRITE
					// elements: lexpr, SYMBOL
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 86:36: -> ^( CALL SYMBOL lexpr )
					{
						// While_grammar.g:86:39: ^( CALL SYMBOL lexpr )
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
				case 6 :
					// While_grammar.g:87:9: NIL
					{
					root_0 = (CommonTree)adaptor.nil();


					NIL75=(Token)match(input,NIL,FOLLOW_NIL_in_expr_base697); 
					NIL75_tree = (CommonTree)adaptor.create(NIL75);
					adaptor.addChild(root_0, NIL75_tree);

					}
					break;
				case 7 :
					// While_grammar.g:88:9: VARIABLE
					{
					root_0 = (CommonTree)adaptor.nil();


					VARIABLE76=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_expr_base708); 
					VARIABLE76_tree = (CommonTree)adaptor.create(VARIABLE76);
					adaptor.addChild(root_0, VARIABLE76_tree);

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
	// While_grammar.g:92:1: lexpr : ( expr_base )* ;
	public final While_grammarParser.lexpr_return lexpr() throws RecognitionException {
		While_grammarParser.lexpr_return retval = new While_grammarParser.lexpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expr_base77 =null;


		try {
			// While_grammar.g:93:5: ( ( expr_base )* )
			// While_grammar.g:93:7: ( expr_base )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// While_grammar.g:93:7: ( expr_base )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==NIL||LA10_0==VARIABLE||LA10_0==22) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// While_grammar.g:93:7: expr_base
					{
					pushFollow(FOLLOW_expr_base_in_lexpr726);
					expr_base77=expr_base();
					state._fsp--;

					adaptor.addChild(root_0, expr_base77.getTree());

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



	public static final BitSet FOLLOW_program_in_start151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_in_program178 = new BitSet(new long[]{0x0000000800000002L});
	public static final BitSet FOLLOW_35_in_function197 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_SYMBOL_in_function199 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_function201 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_definition_in_function203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_definition247 = new BitSet(new long[]{0x0000000000280000L});
	public static final BitSet FOLLOW_input_in_definition249 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_definition251 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_commands_in_definition253 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_definition255 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_45_in_definition257 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_output_in_definition259 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_input300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_output312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_command_in_commands324 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_27_in_commands327 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_command_in_commands330 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_37_in_command349 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expression_in_command352 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_42_in_command354 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_command_in_command357 = new BitSet(new long[]{0x0000000180000000L});
	public static final BitSet FOLLOW_31_in_command360 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_commands_in_command363 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_command367 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_44_in_command376 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expression_in_command379 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_command381 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_commands_in_command384 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_command386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_command395 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expression_in_command398 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_command400 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_commands_in_command403 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_command405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_command414 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_VARIABLE_in_command417 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_command419 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expression_in_command422 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_command424 = new BitSet(new long[]{0x0000102600080000L});
	public static final BitSet FOLLOW_commands_in_command427 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_command429 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vars_in_command438 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_command440 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_exprs_in_command442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_exprs470 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_24_in_exprs473 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expression_in_exprs476 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_VARIABLE_in_vars495 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_24_in_vars498 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_vars_in_vars501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_base_in_expression521 = new BitSet(new long[]{0x0000000010000002L});
	public static final BitSet FOLLOW_28_in_expression524 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expr_base_in_expression527 = new BitSet(new long[]{0x0000000010000002L});
	public static final BitSet FOLLOW_22_in_expr_base548 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_expr_base550 = new BitSet(new long[]{0x0000000000C82000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base552 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr_base554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr_base578 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_39_in_expr_base580 = new BitSet(new long[]{0x0000000000C82000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base582 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr_base584 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr_base608 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_expr_base610 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expr_base_in_expr_base612 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr_base614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr_base637 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_expr_base639 = new BitSet(new long[]{0x0000000000482000L});
	public static final BitSet FOLLOW_expr_base_in_expr_base641 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr_base643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_expr_base665 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_SYMBOL_in_expr_base667 = new BitSet(new long[]{0x0000000000C82000L});
	public static final BitSet FOLLOW_lexpr_in_expr_base669 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_expr_base671 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NIL_in_expr_base697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_expr_base708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_base_in_lexpr726 = new BitSet(new long[]{0x0000000000482002L});
}
