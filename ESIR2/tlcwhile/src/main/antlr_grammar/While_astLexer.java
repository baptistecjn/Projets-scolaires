// $ANTLR 3.5.3 While_ast.g 2026-01-19 11:54:08
package antlr_grammar;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class While_astLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public While_astLexer() {} 
	public While_astLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public While_astLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "While_ast.g"; }

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:2:7: ( '%' )
			// While_ast.g:2:9: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:3:7: ( '(' )
			// While_ast.g:3:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:4:7: ( ')' )
			// While_ast.g:4:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:5:7: ( ',' )
			// While_ast.g:5:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:6:7: ( ':' )
			// While_ast.g:6:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:7:7: ( ';' )
			// While_ast.g:7:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:8:7: ( 'false' )
			// While_ast.g:8:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:9:7: ( 'true' )
			// While_ast.g:9:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:98:10: ( 'if' )
			// While_ast.g:98:12: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:99:10: ( 'then' )
			// While_ast.g:99:12: 'then'
			{
			match("then"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:100:10: ( 'else' )
			// While_ast.g:100:12: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "FI"
	public final void mFI() throws RecognitionException {
		try {
			int _type = FI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:101:10: ( 'fi' )
			// While_ast.g:101:12: 'fi'
			{
			match("fi"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FI"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:102:10: ( 'while' )
			// While_ast.g:102:12: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "DO"
	public final void mDO() throws RecognitionException {
		try {
			int _type = DO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:103:10: ( 'do' )
			// While_ast.g:103:12: 'do'
			{
			match("do"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DO"

	// $ANTLR start "OD"
	public final void mOD() throws RecognitionException {
		try {
			int _type = OD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:104:10: ( 'od' )
			// While_ast.g:104:12: 'od'
			{
			match("od"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OD"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:105:10: ( 'for' )
			// While_ast.g:105:12: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "FOREACH"
	public final void mFOREACH() throws RecognitionException {
		try {
			int _type = FOREACH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:106:10: ( 'foreach' )
			// While_ast.g:106:12: 'foreach'
			{
			match("foreach"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOREACH"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:107:10: ( 'in' )
			// While_ast.g:107:12: 'in'
			{
			match("in"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "FUNCTION"
	public final void mFUNCTION() throws RecognitionException {
		try {
			int _type = FUNCTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:109:10: ( 'function' )
			// While_ast.g:109:12: 'function'
			{
			match("function"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUNCTION"

	// $ANTLR start "READ"
	public final void mREAD() throws RecognitionException {
		try {
			int _type = READ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:110:10: ( 'read' )
			// While_ast.g:110:12: 'read'
			{
			match("read"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ"

	// $ANTLR start "WRITE"
	public final void mWRITE() throws RecognitionException {
		try {
			int _type = WRITE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:111:10: ( 'write' )
			// While_ast.g:111:12: 'write'
			{
			match("write"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WRITE"

	// $ANTLR start "NOP"
	public final void mNOP() throws RecognitionException {
		try {
			int _type = NOP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:112:10: ( 'nop' )
			// While_ast.g:112:12: 'nop'
			{
			match("nop"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOP"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:114:10: ( ':=' )
			// While_ast.g:114:12: ':='
			{
			match(":="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:115:10: ( '=?' )
			// While_ast.g:115:12: '=?'
			{
			match("=?"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "CONS"
	public final void mCONS() throws RecognitionException {
		try {
			int _type = CONS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:116:10: ( 'cons' )
			// While_ast.g:116:12: 'cons'
			{
			match("cons"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONS"

	// $ANTLR start "LIST"
	public final void mLIST() throws RecognitionException {
		try {
			int _type = LIST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:117:10: ( 'list' )
			// While_ast.g:117:12: 'list'
			{
			match("list"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LIST"

	// $ANTLR start "HEAD"
	public final void mHEAD() throws RecognitionException {
		try {
			int _type = HEAD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:118:10: ( 'hd' )
			// While_ast.g:118:12: 'hd'
			{
			match("hd"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEAD"

	// $ANTLR start "TAIL"
	public final void mTAIL() throws RecognitionException {
		try {
			int _type = TAIL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:119:10: ( 'tl' )
			// While_ast.g:119:12: 'tl'
			{
			match("tl"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TAIL"

	// $ANTLR start "NIL"
	public final void mNIL() throws RecognitionException {
		try {
			int _type = NIL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:120:10: ( 'nil' )
			// While_ast.g:120:12: 'nil'
			{
			match("nil"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NIL"

	// $ANTLR start "VARIABLE"
	public final void mVARIABLE() throws RecognitionException {
		try {
			int _type = VARIABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:122:10: ( ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )? )
			// While_ast.g:122:12: ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )?
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// While_ast.g:122:23: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// While_ast.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			// While_ast.g:122:53: ( '!' | '?' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='!'||LA2_0=='?') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// While_ast.g:
					{
					if ( input.LA(1)=='!'||input.LA(1)=='?' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VARIABLE"

	// $ANTLR start "SYMBOL"
	public final void mSYMBOL() throws RecognitionException {
		try {
			int _type = SYMBOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:123:10: ( ( 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )? )
			// While_ast.g:123:12: ( 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )?
			{
			if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// While_ast.g:123:23: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// While_ast.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			// While_ast.g:123:53: ( '!' | '?' )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='!'||LA4_0=='?') ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// While_ast.g:
					{
					if ( input.LA(1)=='!'||input.LA(1)=='?' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SYMBOL"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:125:10: ( '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\r' )? '\\n' ) )
			// While_ast.g:125:12: '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\r' )? '\\n' )
			{
			match("//"); 

			// While_ast.g:125:17: (~ ( '\\n' | '\\r' ) )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// While_ast.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			// While_ast.g:125:31: ( ( '\\r' )? '\\n' )
			// While_ast.g:125:32: ( '\\r' )? '\\n'
			{
			// While_ast.g:125:32: ( '\\r' )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='\r') ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// While_ast.g:125:32: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_ast.g:126:10: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// While_ast.g:126:12: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// While_ast.g:126:12: ( ' ' | '\\t' | '\\n' | '\\r' )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '\t' && LA7_0 <= '\n')||LA7_0=='\r'||LA7_0==' ') ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// While_ast.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// While_ast.g:1:8: ( T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | IF | THEN | ELSE | FI | WHILE | DO | OD | FOR | FOREACH | IN | FUNCTION | READ | WRITE | NOP | ASSIGN | EQ | CONS | LIST | HEAD | TAIL | NIL | VARIABLE | SYMBOL | COMMENT | WS )
		int alt8=33;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// While_ast.g:1:10: T__41
				{
				mT__41(); 

				}
				break;
			case 2 :
				// While_ast.g:1:16: T__42
				{
				mT__42(); 

				}
				break;
			case 3 :
				// While_ast.g:1:22: T__43
				{
				mT__43(); 

				}
				break;
			case 4 :
				// While_ast.g:1:28: T__44
				{
				mT__44(); 

				}
				break;
			case 5 :
				// While_ast.g:1:34: T__45
				{
				mT__45(); 

				}
				break;
			case 6 :
				// While_ast.g:1:40: T__46
				{
				mT__46(); 

				}
				break;
			case 7 :
				// While_ast.g:1:46: T__47
				{
				mT__47(); 

				}
				break;
			case 8 :
				// While_ast.g:1:52: T__48
				{
				mT__48(); 

				}
				break;
			case 9 :
				// While_ast.g:1:58: IF
				{
				mIF(); 

				}
				break;
			case 10 :
				// While_ast.g:1:61: THEN
				{
				mTHEN(); 

				}
				break;
			case 11 :
				// While_ast.g:1:66: ELSE
				{
				mELSE(); 

				}
				break;
			case 12 :
				// While_ast.g:1:71: FI
				{
				mFI(); 

				}
				break;
			case 13 :
				// While_ast.g:1:74: WHILE
				{
				mWHILE(); 

				}
				break;
			case 14 :
				// While_ast.g:1:80: DO
				{
				mDO(); 

				}
				break;
			case 15 :
				// While_ast.g:1:83: OD
				{
				mOD(); 

				}
				break;
			case 16 :
				// While_ast.g:1:86: FOR
				{
				mFOR(); 

				}
				break;
			case 17 :
				// While_ast.g:1:90: FOREACH
				{
				mFOREACH(); 

				}
				break;
			case 18 :
				// While_ast.g:1:98: IN
				{
				mIN(); 

				}
				break;
			case 19 :
				// While_ast.g:1:101: FUNCTION
				{
				mFUNCTION(); 

				}
				break;
			case 20 :
				// While_ast.g:1:110: READ
				{
				mREAD(); 

				}
				break;
			case 21 :
				// While_ast.g:1:115: WRITE
				{
				mWRITE(); 

				}
				break;
			case 22 :
				// While_ast.g:1:121: NOP
				{
				mNOP(); 

				}
				break;
			case 23 :
				// While_ast.g:1:125: ASSIGN
				{
				mASSIGN(); 

				}
				break;
			case 24 :
				// While_ast.g:1:132: EQ
				{
				mEQ(); 

				}
				break;
			case 25 :
				// While_ast.g:1:135: CONS
				{
				mCONS(); 

				}
				break;
			case 26 :
				// While_ast.g:1:140: LIST
				{
				mLIST(); 

				}
				break;
			case 27 :
				// While_ast.g:1:145: HEAD
				{
				mHEAD(); 

				}
				break;
			case 28 :
				// While_ast.g:1:150: TAIL
				{
				mTAIL(); 

				}
				break;
			case 29 :
				// While_ast.g:1:155: NIL
				{
				mNIL(); 

				}
				break;
			case 30 :
				// While_ast.g:1:159: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 31 :
				// While_ast.g:1:168: SYMBOL
				{
				mSYMBOL(); 

				}
				break;
			case 32 :
				// While_ast.g:1:175: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 33 :
				// While_ast.g:1:183: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\5\uffff\1\31\1\uffff\11\25\1\uffff\3\25\6\uffff\1\25\1\57\4\25\1\64\1"+
		"\65\1\66\3\25\1\72\1\73\5\25\1\101\1\25\1\uffff\1\104\3\25\3\uffff\3\25"+
		"\2\uffff\1\25\1\114\1\115\2\25\1\uffff\2\25\1\uffff\1\25\1\123\1\124\1"+
		"\125\2\25\1\130\2\uffff\1\131\1\132\1\133\2\25\3\uffff\1\136\1\137\4\uffff"+
		"\2\25\2\uffff\1\142\1\25\1\uffff\1\144\1\uffff";
	static final String DFA8_eofS =
		"\145\uffff";
	static final String DFA8_minS =
		"\1\11\4\uffff\1\75\1\uffff\1\141\1\150\1\146\1\154\1\150\1\157\1\144\1"+
		"\145\1\151\1\uffff\1\157\1\151\1\144\6\uffff\1\154\1\41\1\162\1\156\1"+
		"\165\1\145\3\41\1\163\2\151\2\41\1\141\1\160\1\154\1\156\1\163\1\41\1"+
		"\163\1\uffff\1\41\1\143\1\145\1\156\3\uffff\1\145\1\154\1\164\2\uffff"+
		"\1\144\2\41\1\163\1\164\1\uffff\1\145\1\141\1\uffff\1\164\3\41\2\145\1"+
		"\41\2\uffff\3\41\1\143\1\151\3\uffff\2\41\4\uffff\1\150\1\157\2\uffff"+
		"\1\41\1\156\1\uffff\1\41\1\uffff";
	static final String DFA8_maxS =
		"\1\172\4\uffff\1\75\1\uffff\1\165\1\162\1\156\1\154\1\162\1\157\1\144"+
		"\1\145\1\157\1\uffff\1\157\1\151\1\144\6\uffff\1\154\1\172\1\162\1\156"+
		"\1\165\1\145\3\172\1\163\2\151\2\172\1\141\1\160\1\154\1\156\1\163\1\172"+
		"\1\163\1\uffff\1\172\1\143\1\145\1\156\3\uffff\1\145\1\154\1\164\2\uffff"+
		"\1\144\2\172\1\163\1\164\1\uffff\1\145\1\141\1\uffff\1\164\3\172\2\145"+
		"\1\172\2\uffff\3\172\1\143\1\151\3\uffff\2\172\4\uffff\1\150\1\157\2\uffff"+
		"\1\172\1\156\1\uffff\1\172\1\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\uffff\1\6\11\uffff\1\30\3\uffff\1\36\1\37\1"+
		"\40\1\41\1\27\1\5\25\uffff\1\14\4\uffff\1\34\1\11\1\22\3\uffff\1\16\1"+
		"\17\5\uffff\1\33\2\uffff\1\20\7\uffff\1\26\1\35\5\uffff\1\10\1\12\1\13"+
		"\2\uffff\1\24\1\31\1\32\1\7\2\uffff\1\15\1\25\2\uffff\1\21\1\uffff\1\23";
	static final String DFA8_specialS =
		"\145\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\27\2\uffff\1\27\22\uffff\1\27\4\uffff\1\1\2\uffff\1\2\1\3\2\uffff"+
			"\1\4\2\uffff\1\26\12\uffff\1\5\1\6\1\uffff\1\20\3\uffff\32\24\6\uffff"+
			"\2\25\1\21\1\14\1\12\1\7\1\25\1\23\1\11\2\25\1\22\1\25\1\17\1\15\2\25"+
			"\1\16\1\25\1\10\2\25\1\13\3\25",
			"",
			"",
			"",
			"",
			"\1\30",
			"",
			"\1\32\7\uffff\1\33\5\uffff\1\34\5\uffff\1\35",
			"\1\37\3\uffff\1\40\5\uffff\1\36",
			"\1\41\7\uffff\1\42",
			"\1\43",
			"\1\44\11\uffff\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\52\5\uffff\1\51",
			"",
			"\1\53",
			"\1\54",
			"\1\55",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\56",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\102",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\4\25\1\103\25"+
			"\25",
			"\1\105",
			"\1\106",
			"\1\107",
			"",
			"",
			"",
			"\1\110",
			"\1\111",
			"\1\112",
			"",
			"",
			"\1\113",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\116",
			"\1\117",
			"",
			"\1\120",
			"\1\121",
			"",
			"\1\122",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\126",
			"\1\127",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\134",
			"\1\135",
			"",
			"",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"",
			"",
			"",
			"",
			"\1\140",
			"\1\141",
			"",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\143",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | IF | THEN | ELSE | FI | WHILE | DO | OD | FOR | FOREACH | IN | FUNCTION | READ | WRITE | NOP | ASSIGN | EQ | CONS | LIST | HEAD | TAIL | NIL | VARIABLE | SYMBOL | COMMENT | WS );";
		}
	}

}
