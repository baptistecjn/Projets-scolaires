// $ANTLR 3.5.3 While_grammar.g 2025-12-12 16:05:48

    package antlr_grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class While_grammarLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public While_grammarLexer() {} 
	public While_grammarLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public While_grammarLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "While_grammar.g"; }

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:6:7: ( '%' )
			// While_grammar.g:6:9: '%'
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
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:7:7: ( '(' )
			// While_grammar.g:7:9: '('
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
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:8:7: ( ')' )
			// While_grammar.g:8:9: ')'
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
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:9:7: ( ',' )
			// While_grammar.g:9:9: ','
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
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:10:7: ( ':' )
			// While_grammar.g:10:9: ':'
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
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:11:7: ( ':=' )
			// While_grammar.g:11:9: ':='
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
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:12:7: ( ';' )
			// While_grammar.g:12:9: ';'
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
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:13:7: ( '=?' )
			// While_grammar.g:13:9: '=?'
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
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:14:7: ( 'cons' )
			// While_grammar.g:14:9: 'cons'
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
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:15:7: ( 'do' )
			// While_grammar.g:15:9: 'do'
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
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:16:7: ( 'else' )
			// While_grammar.g:16:9: 'else'
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
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:17:7: ( 'fi' )
			// While_grammar.g:17:9: 'fi'
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
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:18:7: ( 'for' )
			// While_grammar.g:18:9: 'for'
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
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:19:7: ( 'foreach' )
			// While_grammar.g:19:9: 'foreach'
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
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:20:7: ( 'function' )
			// While_grammar.g:20:9: 'function'
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
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:21:7: ( 'hd' )
			// While_grammar.g:21:9: 'hd'
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
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:22:7: ( 'if' )
			// While_grammar.g:22:9: 'if'
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
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:23:7: ( 'in' )
			// While_grammar.g:23:9: 'in'
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
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:24:7: ( 'list' )
			// While_grammar.g:24:9: 'list'
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
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:25:7: ( 'od' )
			// While_grammar.g:25:9: 'od'
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
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:26:7: ( 'read' )
			// While_grammar.g:26:9: 'read'
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
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:27:7: ( 'then' )
			// While_grammar.g:27:9: 'then'
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
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:28:7: ( 'tl' )
			// While_grammar.g:28:9: 'tl'
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
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:29:7: ( 'while' )
			// While_grammar.g:29:9: 'while'
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
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:30:7: ( 'write' )
			// While_grammar.g:30:9: 'write'
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
	// $ANTLR end "T__45"

	// $ANTLR start "NIL"
	public final void mNIL() throws RecognitionException {
		try {
			int _type = NIL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// While_grammar.g:101:10: ( 'nil' )
			// While_grammar.g:101:12: 'nil'
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
			// While_grammar.g:102:10: ( ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )? )
			// While_grammar.g:102:12: ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )?
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// While_grammar.g:102:23: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// While_grammar.g:
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

			// While_grammar.g:102:53: ( '!' | '?' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='!'||LA2_0=='?') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// While_grammar.g:
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
			// While_grammar.g:103:10: ( ( 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )? )
			// While_grammar.g:103:12: ( 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )* ( '!' | '?' )?
			{
			if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// While_grammar.g:103:23: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// While_grammar.g:
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

			// While_grammar.g:103:53: ( '!' | '?' )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='!'||LA4_0=='?') ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// While_grammar.g:
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
			// While_grammar.g:106:10: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// While_grammar.g:106:12: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match("//"); 

			// While_grammar.g:106:17: (~ ( '\\n' | '\\r' ) )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// While_grammar.g:
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

			// While_grammar.g:106:31: ( '\\r' )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='\r') ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// While_grammar.g:106:32: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			 _channel=HIDDEN; 
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
			// While_grammar.g:108:10: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// While_grammar.g:108:12: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// While_grammar.g:108:12: ( ' ' | '\\t' | '\\n' | '\\r' )+
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
					// While_grammar.g:
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

			 _channel=HIDDEN; 
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
		// While_grammar.g:1:8: ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | NIL | VARIABLE | SYMBOL | COMMENT | WS )
		int alt8=30;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// While_grammar.g:1:10: T__21
				{
				mT__21(); 

				}
				break;
			case 2 :
				// While_grammar.g:1:16: T__22
				{
				mT__22(); 

				}
				break;
			case 3 :
				// While_grammar.g:1:22: T__23
				{
				mT__23(); 

				}
				break;
			case 4 :
				// While_grammar.g:1:28: T__24
				{
				mT__24(); 

				}
				break;
			case 5 :
				// While_grammar.g:1:34: T__25
				{
				mT__25(); 

				}
				break;
			case 6 :
				// While_grammar.g:1:40: T__26
				{
				mT__26(); 

				}
				break;
			case 7 :
				// While_grammar.g:1:46: T__27
				{
				mT__27(); 

				}
				break;
			case 8 :
				// While_grammar.g:1:52: T__28
				{
				mT__28(); 

				}
				break;
			case 9 :
				// While_grammar.g:1:58: T__29
				{
				mT__29(); 

				}
				break;
			case 10 :
				// While_grammar.g:1:64: T__30
				{
				mT__30(); 

				}
				break;
			case 11 :
				// While_grammar.g:1:70: T__31
				{
				mT__31(); 

				}
				break;
			case 12 :
				// While_grammar.g:1:76: T__32
				{
				mT__32(); 

				}
				break;
			case 13 :
				// While_grammar.g:1:82: T__33
				{
				mT__33(); 

				}
				break;
			case 14 :
				// While_grammar.g:1:88: T__34
				{
				mT__34(); 

				}
				break;
			case 15 :
				// While_grammar.g:1:94: T__35
				{
				mT__35(); 

				}
				break;
			case 16 :
				// While_grammar.g:1:100: T__36
				{
				mT__36(); 

				}
				break;
			case 17 :
				// While_grammar.g:1:106: T__37
				{
				mT__37(); 

				}
				break;
			case 18 :
				// While_grammar.g:1:112: T__38
				{
				mT__38(); 

				}
				break;
			case 19 :
				// While_grammar.g:1:118: T__39
				{
				mT__39(); 

				}
				break;
			case 20 :
				// While_grammar.g:1:124: T__40
				{
				mT__40(); 

				}
				break;
			case 21 :
				// While_grammar.g:1:130: T__41
				{
				mT__41(); 

				}
				break;
			case 22 :
				// While_grammar.g:1:136: T__42
				{
				mT__42(); 

				}
				break;
			case 23 :
				// While_grammar.g:1:142: T__43
				{
				mT__43(); 

				}
				break;
			case 24 :
				// While_grammar.g:1:148: T__44
				{
				mT__44(); 

				}
				break;
			case 25 :
				// While_grammar.g:1:154: T__45
				{
				mT__45(); 

				}
				break;
			case 26 :
				// While_grammar.g:1:160: NIL
				{
				mNIL(); 

				}
				break;
			case 27 :
				// While_grammar.g:1:164: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 28 :
				// While_grammar.g:1:173: SYMBOL
				{
				mSYMBOL(); 

				}
				break;
			case 29 :
				// While_grammar.g:1:180: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 30 :
				// While_grammar.g:1:188: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\5\uffff\1\31\2\uffff\14\25\6\uffff\1\25\1\54\1\25\1\56\2\25\1\61\1\62"+
		"\1\63\1\25\1\65\2\25\1\70\4\25\1\uffff\1\25\1\uffff\1\77\1\25\3\uffff"+
		"\1\25\1\uffff\2\25\1\uffff\2\25\1\106\1\107\1\110\1\25\1\uffff\1\25\1"+
		"\113\1\114\1\115\2\25\3\uffff\2\25\3\uffff\1\122\1\123\2\25\2\uffff\1"+
		"\126\1\25\1\uffff\1\130\1\uffff";
	static final String DFA8_eofS =
		"\131\uffff";
	static final String DFA8_minS =
		"\1\11\4\uffff\1\75\2\uffff\2\157\1\154\1\151\1\144\1\146\1\151\1\144\1"+
		"\145\2\150\1\151\6\uffff\1\156\1\41\1\163\1\41\1\162\1\156\3\41\1\163"+
		"\1\41\1\141\1\145\1\41\2\151\1\154\1\163\1\uffff\1\145\1\uffff\1\41\1"+
		"\143\3\uffff\1\164\1\uffff\1\144\1\156\1\uffff\1\154\1\164\3\41\1\141"+
		"\1\uffff\1\164\3\41\2\145\3\uffff\1\143\1\151\3\uffff\2\41\1\150\1\157"+
		"\2\uffff\1\41\1\156\1\uffff\1\41\1\uffff";
	static final String DFA8_maxS =
		"\1\172\4\uffff\1\75\2\uffff\2\157\1\154\1\165\1\144\1\156\1\151\1\144"+
		"\1\145\1\154\1\162\1\151\6\uffff\1\156\1\172\1\163\1\172\1\162\1\156\3"+
		"\172\1\163\1\172\1\141\1\145\1\172\2\151\1\154\1\163\1\uffff\1\145\1\uffff"+
		"\1\172\1\143\3\uffff\1\164\1\uffff\1\144\1\156\1\uffff\1\154\1\164\3\172"+
		"\1\141\1\uffff\1\164\3\172\2\145\3\uffff\1\143\1\151\3\uffff\2\172\1\150"+
		"\1\157\2\uffff\1\172\1\156\1\uffff\1\172\1\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\uffff\1\7\1\10\14\uffff\1\33\1\34\1\35\1\36"+
		"\1\6\1\5\22\uffff\1\12\1\uffff\1\14\2\uffff\1\20\1\21\1\22\1\uffff\1\24"+
		"\2\uffff\1\27\6\uffff\1\15\6\uffff\1\32\1\11\1\13\2\uffff\1\23\1\25\1"+
		"\26\4\uffff\1\30\1\31\2\uffff\1\16\1\uffff\1\17";
	static final String DFA8_specialS =
		"\131\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\27\2\uffff\1\27\22\uffff\1\27\4\uffff\1\1\2\uffff\1\2\1\3\2\uffff"+
			"\1\4\2\uffff\1\26\12\uffff\1\5\1\6\1\uffff\1\7\3\uffff\32\24\6\uffff"+
			"\2\25\1\10\1\11\1\12\1\13\1\25\1\14\1\15\2\25\1\16\1\25\1\23\1\17\2\25"+
			"\1\20\1\25\1\21\2\25\1\22\3\25",
			"",
			"",
			"",
			"",
			"\1\30",
			"",
			"",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\35\5\uffff\1\36\5\uffff\1\37",
			"\1\40",
			"\1\41\7\uffff\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46\3\uffff\1\47",
			"\1\50\11\uffff\1\51",
			"\1\52",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\53",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\55",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\57",
			"\1\60",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\64",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\66",
			"\1\67",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\71",
			"\1\72",
			"\1\73",
			"\1\74",
			"",
			"\1\75",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\4\25\1\76\25"+
			"\25",
			"\1\100",
			"",
			"",
			"",
			"\1\101",
			"",
			"\1\102",
			"\1\103",
			"",
			"\1\104",
			"\1\105",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\111",
			"",
			"\1\112",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\116",
			"\1\117",
			"",
			"",
			"",
			"\1\120",
			"\1\121",
			"",
			"",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\124",
			"\1\125",
			"",
			"",
			"\1\25\16\uffff\12\25\5\uffff\1\25\1\uffff\32\25\6\uffff\32\25",
			"\1\127",
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
			return "1:1: Tokens : ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | NIL | VARIABLE | SYMBOL | COMMENT | WS );";
		}
	}

}
