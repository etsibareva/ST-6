package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private Game game;
    private Player player;

    @BeforeEach
    void setup() {
        game = new Game();
        player = new Player();
    }

    @Test
    void testInitPlayer() {
        player.symbol = 'X';
        player.move = 6;
        player.selected = false;
        player.win = true;
        assertEquals('X', player.symbol);
        assertEquals(6, player.move);
        assertFalse(player.selected);
        assertTrue(player.win);
    }

    @Test
    void testState01WinX() {
        char[] testBoard = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(testBoard));
    }

    @Test
    void testState02WinO() {
        char[] testBoard = {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(testBoard));
    }

    @Test
    void testState03WinO() {
        char[] testBoard = {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'};
        game.symbol = 'X';
        assertNotEquals(State.OWIN, game.checkState(testBoard));
    }

    @Test
    void testState04WinO() {
        char[] testBoard = {' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(testBoard));
    }

    @Test
    void testState05WinO() {
        char[] testBoard = {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(testBoard));
    }

    @Test
    void testState06WinO() {
        char[] testBoard = {'O', 'O', 'X', 'O', 'X', 'X', 'X', 'O', 'O'};
        game.symbol = 'O';
        assertEquals(State.DRAW, game.checkState(testBoard));
    }

    @Test
    void testStatePlayingOFirst() {
        char[] testBoard = {'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.PLAYING, game.checkState(testBoard));
    }

    @Test
    void testStatePlayingXFirst() {
        char[] testBoard = {' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(testBoard));
    }

    @Test
    void testGenerateMovesFromEmpty() {
        char[] testBoard = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        ArrayList<Integer> arr = new ArrayList<>();
        game.generateMoves(testBoard, arr);
        assertEquals(9, arr.size());
    }

    @Test
    void testGenerateMovesFromXFirst() {
        char[] testBoard = {' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'O'};
        ArrayList<Integer> arr = new ArrayList<>();
        game.generateMoves(testBoard, arr);
        assertEquals(7, arr.size());
    }

    @Test
    void testMinMaxAlgorithm() {
        char[] testBoard = {'X', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'};
        player.symbol = 'X';
        int move = game.MiniMax(testBoard, player);
        assertEquals(3, move);
    }

    @Test
    void testTicTacToeCellCreating() {
        TicTacToeCell ticTacToeCell = new TicTacToeCell(1, 2, 3);
        ticTacToeCell.setMarker("X");
        assertEquals("X", ticTacToeCell.getText());
        assertEquals('X', ticTacToeCell.getMarker());
    }

    @Test
    void testTicTacToeCellClick() {
        TicTacToePanel ticTacToePanel = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToeCell ticTacToeCell = (TicTacToeCell) ticTacToePanel.getComponent(0);
        String iText = ticTacToeCell.getText();
        ticTacToeCell.doClick();
        assertNotEquals(iText, ticTacToeCell.getText());
        assertTrue(ticTacToeCell.getMarker() == 'X' || ticTacToeCell.getMarker() == 'O');
    }

    @Test
    void testTicTacToePanelCreate() {
        TicTacToePanel ticTacToePanel = new TicTacToePanel(new GridLayout(3, 3));

        for (Component comp : ticTacToePanel.getComponents()) {
            TicTacToeCell ticTacToeCell = (TicTacToeCell) comp;
            int expectedR = ticTacToeCell.getNum() / 3;
            int expectedC = ticTacToeCell.getNum() % 3;
            assertEquals(expectedR, ticTacToeCell.getRow());
            assertEquals(expectedC, ticTacToeCell.getCol());
        }
    }

    @Test
    void testPrintChar() {
        char[] testBoard = {'X', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'X'};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testPrintInt() {
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }

    @Test
    void testPrintArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(5);
        assertDoesNotThrow(() -> Utility.print(arr));
    }
}