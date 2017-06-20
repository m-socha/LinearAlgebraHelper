package com.example.michael.linearalgebrahelper.expressionlanguage;

import parsingtools.ContextFreeGrammar;
import parsingtools.DFA;

/**
 * Created by michael on 6/12/17.
 */

public class ExpressionLanguageFactory {

    public static final char[] VECTOR_CHARS = "abcdefg".toCharArray();
    public static final char[] MATRIX_CHARS = "ABCDEFG".toCharArray();

    public static final char[] DIGITS = "1234567890".toCharArray();

    public static final String TOKEN_COMMA = "COMMA";
    public static final String TOKEN_LEFT_BRACKET = "LEFT_BRACKET";
    public static final String TOKEN_RIGHT_BRACKET = "RIGHT_BRACKET";
    public static final String TOKEN_ABS_SLASH = "ABS_SLASH";
    public static final String TOKEN_PLUS = "PLUS";
    public static final String TOKEN_MINUS = "MINUS";
    public static final String TOKEN_TIMES = "TIMES";
    public static final String TOKEN_DIV = "DIV";
    public static final String TOKEN_EXP = "EXP";
    public static final String TOKEN_VECTOR = "VECTOR";
    public static final String TOKEN_MATRIX = "MATRIX";
    public static final String TOKEN_PROJ = "PROJ";
    public static final String TOKEN_DET = "DET";
    public static final String TOKEN_DOT = "DOT";
    public static final String TOKEN_CROSS = "CROSS";
    public static final String TOKEN_NUMBER = "NUMBER";

    private static DFA expressionLanguageDfa;

    public static DFA getDfa() {
        DFA dfa = new DFA();

        DFA.State sourceState = new DFA.State("source", false);
        dfa.setSourceState(sourceState);

        DFA.State commaState = new DFA.State(TOKEN_COMMA, true);
        sourceState.addTransition(',', commaState);

        DFA.State leftBracketState = new DFA.State(TOKEN_LEFT_BRACKET, true);
        sourceState.addTransition('(', leftBracketState);

        DFA.State rightBracketState = new DFA.State(TOKEN_RIGHT_BRACKET, true);
        sourceState.addTransition(')', rightBracketState);

        DFA.State absSlashState = new DFA.State(TOKEN_ABS_SLASH, true);
        sourceState.addTransition('|', absSlashState);

        DFA.State plusState = new DFA.State(TOKEN_PLUS, true);
        sourceState.addTransition('+', plusState);

        DFA.State minusState = new DFA.State(TOKEN_MINUS, true);
        sourceState.addTransition('-', minusState);

        DFA.State timesState = new DFA.State(TOKEN_TIMES, true);
        sourceState.addTransition('*', timesState);

        DFA.State divState = new DFA.State(TOKEN_DIV, true);
        sourceState.addTransition('/', divState);

        DFA.State expState = new DFA.State(TOKEN_EXP, true);
        sourceState.addTransition('^', expState);

        DFA.State letterDState = null;
        for (char c : VECTOR_CHARS) {
            DFA.State charState = new DFA.State(TOKEN_VECTOR, true);
            sourceState.addTransition(c, charState);

            if (c == 'd') {
                letterDState = charState;
            }
        }

        for (char c : MATRIX_CHARS) {
            DFA.State charState = new DFA.State(TOKEN_MATRIX, true);
            sourceState.addTransition(c, charState);
        }

        DFA.State letterPState = new DFA.State("letter p", false);
        sourceState.addTransition('p', letterPState);
        DFA.State lettersPRState = new DFA.State("letters pr", false);
        letterPState.addTransition('r', lettersPRState);
        DFA.State lettersPROState = new DFA.State("letters pro", false);
        lettersPRState.addTransition('o', lettersPROState);
        DFA.State projState = new DFA.State(TOKEN_PROJ, true);
        lettersPROState.addTransition('j', projState);

        DFA.State lettersDEState = new DFA.State("letters de", false);
        letterDState.addTransition('e', lettersDEState);
        DFA.State detState = new DFA.State(TOKEN_DET, true);
        lettersDEState.addTransition('t', detState);

        DFA.State dotState = new DFA.State(TOKEN_DOT, true);
        sourceState.addTransition('·', dotState);

        DFA.State crossState = new DFA.State(TOKEN_CROSS, true);
        sourceState.addTransition('X', crossState);

        DFA.State numNoDecState = new DFA.State(TOKEN_NUMBER, true);
        DFA.State decState = new DFA.State("decimal", false);
        DFA.State numAfterDecState = new DFA.State(TOKEN_NUMBER, true);

        for (char c : DIGITS) {
            sourceState.addTransition(c, numNoDecState);
            numNoDecState.addTransition(c, numNoDecState);
            decState.addTransition(c, numAfterDecState);
            numAfterDecState.addTransition(c, numAfterDecState);
        }
        sourceState.addTransition('.', decState);
        numNoDecState.addTransition('.', decState);

        return dfa;
    }

    private static ContextFreeGrammar expressionLanguageGrammar;

    public static ContextFreeGrammar getContextFreeGrammar() {
        ContextFreeGrammar grammar = new ContextFreeGrammar();

        return grammar;
    }
}
