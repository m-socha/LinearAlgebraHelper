package com.example.michael.linearalgebrahelper.expressionlanguage;

import org.junit.Test;

import parsingtools.DFA;

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
    public void testNumNoDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "1234567890".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testNumWithDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "1234567890.1234567890".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testNumDecAtBeginning() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : ".44923".toCharArray()) {
            dfa.transition(c);
        }
        assertTrue(dfa.getCurrentState().isAcceptingState());
        assertEquals(ExpressionLanguageFactory.TOKEN_NUMBER, dfa.getCurrentState().getTokenId());
    }

    @Test
    public void testNumDecAtEnd() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "44923.".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.getCurrentState().isAcceptingState());
    }

    @Test
    public void testNumTwoDec() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "293.23".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.transition('.'));
    }

    @Test
    public void testNumTwoDecConsecutive() {
        DFA dfa = ExpressionLanguageFactory.getDfa();
        dfa.start();
        for (char c : "293.".toCharArray()) {
            dfa.transition(c);
        }
        assertFalse(dfa.transition('.'));
    }
}
