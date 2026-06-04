package com.mycompany.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class TestMiniMax {

    static Stream<Arguments> generateTestData() {
        return Stream.of(
                Arguments.of(
                        new char[]{'X', 'O', 'X', 'O', 'X', ' ', ' ', ' ', ' '},
                        4,
                        new Integer[]{5, 6, 7, 8}
                ),
                Arguments.of(
                        new char[]{'X', 'O', 'X', 'O', ' ', 'X', ' ', ' ', ' '},
                        4,
                        new Integer[]{4, 6, 7, 8}
                ),
                Arguments.of(
                        new char[]{'X', 'O', 'X', 'O', ' ', ' ', 'X', ' ', ' '},
                        4,
                        new Integer[]{4, 5, 7, 8}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("generateTestData")
    void testGenerateMovesForAlghorithm(char[] board, int expectedSize, Integer[] expectedMoves) {
        Game game = new Game();
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(expectedSize, moves.size());
        for (Integer move : expectedMoves) {
            assertTrue(moves.contains(move));
        }
    }
}