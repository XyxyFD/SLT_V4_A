package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class gti BoardTest {



    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardInitialization() {
        assertNotNull(board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    public void testPlaceMarker() {
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
        assertEquals('X', board.getCells()[0][0]);
    }

    @Test
    public void testClearBoard() {
        board.place(0, 0, 'X');
        board.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }
}
