package com.example.michael.linearalgebrahelper.expressionlanguage;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static final String EXPRESSION = "EXPRESSION";

    public static final String MATRIX_EXPRESSION = "MATRIX_EXPRESSION";
    public static final String VECTOR_EXPRESSION = "VECTOR_EXPRESSION";
    public static final String NUMBER_EXPRESSION = "NUMBER_EXPRESSION";

    public static final String MATRIX_BRACKET_EXPRESSION = "MATRIX_BRACKET_EXPRESSION";
    public static final String MATRIX_PLUS_EXPRESSION = "MATRIX_PLUS_EXPRESSION";
    public static final String MATRIX_MINUS_EXPRESSION = "MATRIX_MINUS_EXPRESSION";
    public static final String MATRIX_MULT_EXPRESSION = "MATRIX_MULT_EXPRESSION";
    public static final String MATRIX_EXP_EXPRESSION = "MATRIX_EXP_EXPRESSION";
    public static final String MATRIX_SCALE_EXPRESSION = "MATRIX_SCALE_EXPRESSION";

    public static final String VECTOR_BRACKET_EXPRESSION = "VECTOR_BRACKET_EXPRESSION";
    public static final String VECTOR_PROJ_EXPRESSION = "VECTOR_PROJ_EXPRESSION";
    public static final String VECTOR_PLUS_EXPRESSION = "VECTOR_PLUS_EXPRESSION";
    public static final String VECTOR_MINUS_EXPRESSION = "VECTOR_MINUS_EXPRESSION";
    public static final String VECTOR_CROSS_EXPRESSION = "VECTOR_CROSS_EXPRESSION";
    public static final String VECTOR_MULT_MATRIX_EXPRESSION = "VECTOR_MULT_MATRIX_EXPRESSION";
    public static final String VECTOR_SCALE_EXPRESSION = "VECTOR_SCALE_EXPRESSION";

    public static final String NUMBER_BRACKET_EXPRESSION = "NUMBER_BRACKET_EXPRESSION";
    public static final String NUMBER_PLUS_EXPRESSION = "NUMBER_PLUS_EXPRESSION";
    public static final String NUMBER_MINUS_EXPRESSION = "NUMBER_TIMES_EXPRESSION";
    public static final String NUMBER_TIMES_EXPRESSION = "NUMBER_BRACKET_EXPRESSION";
    public static final String NUMBER_DIV_EXPRESSION = "NUMBER_DIV_EXPRESSION";
    public static final String NUMBER_EXP_EXPRESSION = "NUMBER_EXP_EXPRESSION";
    public static final String NUMBER_ABS_EXPRESSION = "NUMBER_ABS_EXPRESSION";
    public static final String VECTOR_DOT_EXPRESSION = "VECTOR_DOT_EXPRESSION";
    public static final String VECTOR_MAGNITUDE_EXPRESSION = "VECTOR_MAGNITUDE_EXPRESSION";
    public static final String MATRIX_DET_EXPRESSION = "MATRIX_DET_EXPRESSION";

    private static ContextFreeGrammar expressionLanguageGrammar;

    public static DFA getDfa() {
        if (expressionLanguageDfa == null) {
            expressionLanguageDfa = new DFA();

            DFA.State sourceState = new DFA.State("source", false);
            expressionLanguageDfa.setSourceState(sourceState);

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
        }

        return expressionLanguageDfa;
    }

    public static ContextFreeGrammar getContextFreeGrammar() {
        if (expressionLanguageGrammar == null) {
            expressionLanguageGrammar = new ContextFreeGrammar();

            expressionLanguageGrammar.addRule(EXPRESSION, Arrays.asList(MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(EXPRESSION, Arrays.asList(VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(EXPRESSION, Arrays.asList(NUMBER_EXPRESSION));

            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(TOKEN_MATRIX));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_BRACKET_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_PLUS_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_MINUS_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_MULT_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_EXP_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXPRESSION, Arrays.asList(MATRIX_SCALE_EXPRESSION));

            expressionLanguageGrammar.addRule(MATRIX_BRACKET_EXPRESSION, Arrays.asList(TOKEN_LEFT_BRACKET, MATRIX_EXPRESSION, TOKEN_RIGHT_BRACKET));
            expressionLanguageGrammar.addRule(MATRIX_PLUS_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_PLUS, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_MINUS_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_MINUS, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_MULT_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_MULT_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_TIMES, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_EXP_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_EXP, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_SCALE_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_SCALE_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_TIMES, MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_SCALE_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(MATRIX_SCALE_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_TIMES, NUMBER_EXPRESSION));

            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(TOKEN_VECTOR));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_BRACKET_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_PROJ_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_PLUS_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_MINUS_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_CROSS_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_MULT_MATRIX_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_EXPRESSION, Arrays.asList(VECTOR_SCALE_EXPRESSION));

            expressionLanguageGrammar.addRule(VECTOR_BRACKET_EXPRESSION, Arrays.asList(TOKEN_LEFT_BRACKET, VECTOR_EXPRESSION, TOKEN_RIGHT_BRACKET));
            expressionLanguageGrammar.addRule(VECTOR_PROJ_EXPRESSION, Arrays.asList(TOKEN_PROJ, TOKEN_LEFT_BRACKET, VECTOR_EXPRESSION, TOKEN_COMMA, VECTOR_EXPRESSION, TOKEN_RIGHT_BRACKET));
            expressionLanguageGrammar.addRule(VECTOR_PLUS_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, TOKEN_PLUS, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_MINUS_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, TOKEN_MINUS, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_CROSS_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, TOKEN_CROSS, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_MULT_MATRIX_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_MULT_MATRIX_EXPRESSION, Arrays.asList(MATRIX_EXPRESSION, TOKEN_TIMES, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_SCALE_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_SCALE_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_TIMES, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_SCALE_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_SCALE_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, TOKEN_TIMES, NUMBER_EXPRESSION));

            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(TOKEN_NUMBER));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_BRACKET_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_PLUS_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_MINUS_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_TIMES_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_DIV_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_EXP_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(NUMBER_ABS_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(VECTOR_DOT_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(VECTOR_MAGNITUDE_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXPRESSION, Arrays.asList(MATRIX_DET_EXPRESSION));

            expressionLanguageGrammar.addRule(NUMBER_BRACKET_EXPRESSION, Arrays.asList(TOKEN_LEFT_BRACKET, NUMBER_EXPRESSION, TOKEN_RIGHT_BRACKET));
            expressionLanguageGrammar.addRule(NUMBER_PLUS_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_PLUS, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_MINUS_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_MINUS, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_TIMES_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_TIMES_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_TIMES, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_DIV_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_DIV, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_EXP_EXPRESSION, Arrays.asList(NUMBER_EXPRESSION, TOKEN_EXP, NUMBER_EXPRESSION));
            expressionLanguageGrammar.addRule(NUMBER_ABS_EXPRESSION, Arrays.asList(TOKEN_ABS_SLASH, NUMBER_EXPRESSION, TOKEN_ABS_SLASH));
            expressionLanguageGrammar.addRule(VECTOR_DOT_EXPRESSION, Arrays.asList(VECTOR_EXPRESSION, TOKEN_DOT, VECTOR_EXPRESSION));
            expressionLanguageGrammar.addRule(VECTOR_MAGNITUDE_EXPRESSION, Arrays.asList(TOKEN_ABS_SLASH, VECTOR_EXPRESSION, TOKEN_ABS_SLASH));
            expressionLanguageGrammar.addRule(MATRIX_DET_EXPRESSION, Arrays.asList(TOKEN_DET, TOKEN_LEFT_BRACKET, MATRIX_EXPRESSION, TOKEN_RIGHT_BRACKET));

            expressionLanguageGrammar.setLeadingNonterminal(EXPRESSION);
        }


        return expressionLanguageGrammar;
    }
}
