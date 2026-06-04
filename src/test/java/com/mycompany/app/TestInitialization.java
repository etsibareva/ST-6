package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInitialization {
    Game game;
    Player player;

    @Test
    void testCreateGame() {
        game = new Game();
        assertEquals(game.state, State.PLAYING);
    }

    @Test
    void testCreatePlayer() {
        player = new Player();
        player.symbol = 'O';
        player.win = false;
        assertNotEquals(player.symbol, 'X');
        assertEquals(player.symbol, 'O');
        assertFalse(player.win);
    }
}
