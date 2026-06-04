package com.mycompany.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class TestStates {
    static Stream<Arguments> generateTestData() {
        return Stream.of(
                // Win
                Arguments.of(new char[]{'X', 'X', 'X', 'O', ' ', ' ', ' ', ' ', ' '}, 'X', State.XWIN),
                Arguments.of(new char[]{'O', ' ', ' ', 'X', 'X', 'X', 'O', ' ', ' '}, 'X', State.XWIN),
                Arguments.of(new char[]{' ', ' ', 'O', ' ', ' ', ' ', 'X', 'X', 'X'}, 'X', State.XWIN),

                Arguments.of(new char[]{'X', 'O', ' ', 'X', ' ', 'O', 'X', ' ', ' '}, 'X', State.XWIN),
                Arguments.of(new char[]{'O', 'X', 'O', ' ', 'X', ' ', ' ', 'X', ' '}, 'X', State.XWIN),
                Arguments.of(new char[]{' ', ' ', 'X', 'O', ' ', 'X', ' ', 'O', 'X'}, 'X', State.XWIN),

                Arguments.of(new char[]{'X', 'O', ' ', ' ', 'X', 'O', ' ', ' ', 'X'}, 'X', State.XWIN),
                Arguments.of(new char[]{' ', ' ', 'X', 'O', 'X', ' ', 'X', 'O', ' '}, 'X', State.XWIN),

                Arguments.of(new char[]{'O', 'O', 'O', 'X', ' ', ' ', ' ', ' ', ' '}, 'O', State.OWIN),
                Arguments.of(new char[]{'X', ' ', ' ', 'O', 'O', 'O', 'X', ' ', ' '}, 'O', State.OWIN),

                Arguments.of(new char[]{'O', 'X', ' ', 'O', ' ', 'X', 'O', ' ', ' '}, 'O', State.OWIN),
                Arguments.of(new char[]{'X', 'O', 'X', ' ', 'O', ' ', ' ', 'O', ' '}, 'O', State.OWIN),

                Arguments.of(new char[]{'O', 'X', ' ', ' ', 'O', 'X', ' ', ' ', 'O'}, 'O', State.OWIN),
                Arguments.of(new char[]{' ', ' ', 'O', 'X', 'O', ' ', 'O', 'X', ' '}, 'O', State.OWIN),

                // Draw
                Arguments.of(new char[]{'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'}, 'X', State.DRAW),

                // Playing
                Arguments.of(new char[]{'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 'X', State.PLAYING),
                Arguments.of(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 'O', State.PLAYING)
        );
    }

    @ParameterizedTest
    @MethodSource("generateTestData")
    void testStateAll(char[] board, char currentSymbol, State expectedState) {
        Game game = new Game();
        game.symbol = currentSymbol;
        State result = game.checkState(board);
        assertEquals(expectedState, result);
    }
}