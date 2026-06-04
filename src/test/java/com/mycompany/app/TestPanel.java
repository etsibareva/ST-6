package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

public class TestPanel {

    @Test
    void testCellInit() {
        TicTacToeCell ticTacToeCell = new TicTacToeCell(2, 0, 1);
        assertEquals(2, ticTacToeCell.getNum());
        assertEquals(1, ticTacToeCell.getRow());
        assertEquals(0, ticTacToeCell.getCol());
        assertEquals(' ', ticTacToeCell.getMarker());
        ticTacToeCell.setMarker("O");
        assertEquals('O', ticTacToeCell.getMarker());
    }

    @Test
    void testPanelInitAndGame() {
        GridLayout gridLayout = new GridLayout(3, 3);
        TicTacToePanel[] panel = {null};

        assertDoesNotThrow(() -> panel[0] = new TicTacToePanel(gridLayout));
        assertEquals(9, panel[0].getComponentCount());

        assertDoesNotThrow(() -> {
            TicTacToeCell ticTacToeCell = (TicTacToeCell) panel[0].getComponent(5);
            ticTacToeCell.doClick();
        });
    }
}
