package com.example.michael.linearalgebrahelper.expressionlanguage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import parsingtools.CYKParser;
import parsingtools.ContextFreeGrammar;
import parsingtools.DFA;
import parsingtools.Parser;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by michael on 6/13/17.
 */

public class ExpressionLanguageFactoryTest {

    @Test
    public void testDfaComma() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition(',');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_COMMA, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaLeftBracket() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('(');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaRightBracket() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition(')');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaAbsSlash() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('|');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_ABS_SLASH, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaPlus() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('+');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_PLUS, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaMinus() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('-');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_MINUS, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaTimes() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('*');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_TIMES, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaDiv() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('/');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_DIV, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaExp() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('^');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_EXP, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaVector() {
        for (char c : "abcdefg".toCharArray()) {
            DFA dfa = ExpressionLanguageFactory.getDfa();
            dfa.start();
            dfa.transition(c);
            assertTrue(dfa.getCurrentState().isAcceptingState());
            assertEquals(ExpressionLanguageFactory.TOKEN_VECTOR, dfa.getCurrentState().getTokenId());
        }
    }

    @Test
    public void testDfaMatrix() {
        for (char c : "ABCDEFG".toCharArray()) {
            DFA dfa = ExpressionLanguageFactory.getDfa();
            dfa.start();
            dfa.transition(c);
            assertTrue(dfa.getCurrentState().isAcceptingState());
            assertEquals(ExpressionLanguageFactory.TOKEN_MATRIX, dfa.getCurrentState().getTokenId());
        }
    }

    @Test
    public void testDfaProj() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "proj".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_PROJ, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaDet() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "det".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_DET, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaDot() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('·');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_DOT, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaCross() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        dfa.transition('X');
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_CROSS, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaNumNoDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "1234567890".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaNumWithDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "1234567890.1234567890".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaNumDecAtBeginning() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : ".44923".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testDfaNumDecAtEnd() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "44923.".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.getCurrentState().isAcceptingState());
    }

    @Test
    public void testDfaNumTwoDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "293.23".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.transition('.'));
    }

    @Test
    public void testDfaNumTwoDecConsecutive() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "293.".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.transition('.'));
    }

    @Test
    public void testGrammarMatrixTokenExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixBracketExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "("));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_BRACKET_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "(")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixPlusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_PLUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixMinusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_MINUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixMultExpressionNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_MULT_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixMultExpressionWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_MULT_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "B")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixExpExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_EXP, "^"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXP_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_EXP, "^")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixScaleExpressionNumFirstNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixScaleExpressionNumFirstWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixScaleExpressionMatrixFirstNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixScaleExpressionMatrixFirstWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorTokenExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorBracketExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "("));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_BRACKET_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "(")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorProjExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_PROJ, "proj"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "("));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_COMMA, ","));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_PROJ_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_PROJ, "proj")));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "(")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_COMMA, ",")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorPlusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_PLUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorMinusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_MINUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorCrossExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_CROSS, "x"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_CROSS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_CROSS, "x")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorMultMatrixExpressionNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_MULT_MATRIX_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorMultMatrixExpressionWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_MULT_MATRIX_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorScaleExpressionNumFirstNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorScaleExpressionNumFirstWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorScaleExpressionVectorFirstNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));


        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorScaleExpressionVectorFirstWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));


        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_SCALE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberTokenExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1")));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberBracketExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "("));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_BRACKET_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "(")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberPlusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_PLUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_PLUS, "+")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberMinusExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_MINUS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "1")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MINUS, "-")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberTimesExpressionNoTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "7"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "8"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_TIMES_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "7")));
                    }}));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "8")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberTimesExpressionWithTimes() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "7"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "8"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_TIMES_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "7")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_TIMES, "*")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "8")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberDivExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "10"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_DIV, "/"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_DIV_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "10")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_DIV, "/")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberExpExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "10"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_EXP, "^"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXP_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "10")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_EXP, "^")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "2")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarNumberAbsExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_ABS_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_NUMBER, "4")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorDotExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_DOT, "·"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_DOT_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_DOT, "·")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "b")));
                    }}));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarVectorMagnitudeExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_MAGNITUDE_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.VECTOR_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_VECTOR, "a")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_ABS_SLASH, "|")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }

    @Test
    public void testGrammarMatrixDetExpression() {
        ContextFreeGrammar grammar = ExpressionLanguageFactory.getContextFreeGrammar();

        List<Parser.Token> tokens = new ArrayList();
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_DET, "det"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "("));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A"));
        tokens.add(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")"));

        Parser parser = new CYKParser();
        Parser.ParseTree parseTree = parser.parse(tokens, grammar);

        Parser.ParseTree expectedParseTree = new Parser.ParseTree(ExpressionLanguageFactory.EXPRESSION, new ArrayList() {{
            add(new Parser.ParseTree(ExpressionLanguageFactory.NUMBER_EXPRESSION, new ArrayList() {{
                add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_DET_EXPRESSION, new ArrayList() {{
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_DET, "det")));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_LEFT_BRACKET, "(")));
                    add(new Parser.ParseTree(ExpressionLanguageFactory.MATRIX_EXPRESSION, new ArrayList() {{
                        add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_MATRIX, "A")));
                    }}));
                    add(new Parser.ParseTree(new Parser.Token(ExpressionLanguageFactory.TOKEN_RIGHT_BRACKET, ")")));
                }}));
            }}));
        }});

        assertEquals(expectedParseTree, parseTree);
    }
}
