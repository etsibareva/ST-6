package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new Game();
        player = new Player();
    }

    @AfterEach
    void tearDown() {
        game = null;
        player = null;
    }

    @Test
    void testPlayer01Creation() {
        player.symbol = 'X';
        player.move = 4;
        player.selected = true;
        player.win = false;
        assertEquals('X', player.symbol);
        assertEquals(4, player.move);
        assertTrue(player.selected);
        assertFalse(player.win);
    }

    @Test
    void testPlayer02Defaults() {
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }

    @Test
    void testState03Values() {
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }

    @Test
    void testGame04CheckWinHorizontalX() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void testGame05CheckWinVerticalO() {
        char[] board = {' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    void testGame06CheckWinDiagonalX() {
        char[] board = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void testGame07CheckDraw() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    void testGame08CheckPlaying() {
        char[] board = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    void testGame09GenerateMovesEmpty() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    void testGame10GenerateMovesPartial() {
        char[] board = {'X', ' ', 'O', ' ', 'X', ' ', ' ', 'O', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(5, moves.size());
    }

    @Test
    void testGame11GenerateMovesFull() {
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(0, moves.size());
    }

    @Test
    void testGame12EvaluateWin() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        player.symbol = 'X';
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(board, player));
    }

    @Test
    void testGame13EvaluateLoss() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        player.symbol = 'O';
        game.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(board, player));
    }

    @Test
    void testGame14EvaluateDraw() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        player.symbol = 'X';
        assertEquals(0, game.evaluatePosition(board, player));
    }

    @Test
    void testGame15MiniMaxBlockWin() {
        char[] board = {'X', 'X', ' ', ' ', 'O', ' ', ' ', ' ', ' '};
        player.symbol = 'O';
        int move = game.MiniMax(board, player);
        assertEquals(3, move);
    }

    @Test
    void testGame16MaxMoveWin() {
        char[] board = {'X', 'X', ' ', ' ', 'O', ' ', 'O', ' ', ' '};
        player.symbol = 'X';
        int val = game.MaxMove(board, player);
        assertEquals(Game.INF, val);
    }

    @Test
    void testGame17MinMove() {
        char[] board = {'X', 'X', ' ', ' ', 'O', ' ', ' ', ' ', ' '};
        player.symbol = 'O';
        assertDoesNotThrow(() -> game.MinMove(board, player));
    }

    @Test
    void testGame18MaxMove() {
        char[] board = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        player.symbol = 'X';
        assertDoesNotThrow(() -> game.MaxMove(board, player));
    }

    @Test
    void testGame19Initialization() {
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(State.PLAYING, game.state);
    }

    @Test
    void testUtility20PrintCharBoard() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        assertDoesNotThrow(() -> Utility.print(board));
    }

    @Test
    void testUtility21PrintIntBoard() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertDoesNotThrow(() -> Utility.print(board));
    }

    @Test
    void testUtility22PrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(3);
        moves.add(5);
        assertDoesNotThrow(() -> Utility.print(moves));
    }

    @Test
    void testCell23Creation() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertNotNull(cell);
        assertEquals(" ", cell.getText());
    }

    @Test
    void testCell24SetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals("X", cell.getText());
        assertEquals('X', cell.getMarker());
    }

    @Test
    void testCell25GetRowColNum() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getCol());
        assertEquals(5, cell.getNum());
    }

    @Test
    void testPanel26Creation() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(panel);
        assertEquals(9, panel.getComponentCount());
    }

    @Test
    void testPanel28ActionPerformed() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToeCell cell = (TicTacToeCell) panel.getComponent(0);
        ActionEvent event = new ActionEvent(cell, ActionEvent.ACTION_PERFORMED, "command");
        panel.actionPerformed(event);
        assertEquals('X', cell.getMarker());
    }

    @Test
    void testPanel29AIResponse() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToeCell cell = (TicTacToeCell) panel.getComponent(0);
        ActionEvent event = new ActionEvent(cell, ActionEvent.ACTION_PERFORMED, "command");
        panel.actionPerformed(event);

        boolean aiMoved = false;
        for (Component comp : panel.getComponents()) {
            if (((TicTacToeCell) comp).getMarker() == 'O') {
                aiMoved = true;
                break;
            }
        }
        assertTrue(aiMoved);
    }

    @Test
    void testProgram30Main() {
        assertDoesNotThrow(() -> {
            Thread thread = new Thread(() -> {
                try {
                    Program.main(new String[]{});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            Thread.sleep(100);
            thread.interrupt();
        });
    }

    @Test
    void testApp31Exists() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    void testApp32Main() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}