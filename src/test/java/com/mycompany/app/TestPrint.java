package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPrint {
    @Test
    void testPrintCharArray() {
        char[] testBoard = {'X', 'O', 'X', 'X', ' ', 'X', 'X', 'X', 'X'};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testPrintIntArray() {
        int[] testBoard = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(9);
        moves.add(1);
        moves.add(1);
        moves.add(2);
        moves.add(3);
        moves.add(4);
        moves.add(5);
        assertDoesNotThrow(() -> Utility.print(moves));
    }
}
