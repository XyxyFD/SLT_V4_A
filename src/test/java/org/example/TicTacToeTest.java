package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {
    private TicTacToe game;
    private Board board;
    private Player player1;
    private Player player2;

    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        board = new Board();
        player1 = new Player('X');
        player2 = new Player('O');
        game = new TicTacToe(board, player1, player2, new Scanner(System.in));
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game);
        assertNotNull(game.getBoard());
        assertNotNull(game.getCurrentPlayer());
    }

    @Test
    public void testGetPlayerMoveValidInput() {
        String input = "0\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        int[] move = game.getPlayerMove();
        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    public void testGetPlayerMoveInvalidInput() {
        String input = "3\n0\n0\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        int[] move = game.getPlayerMove();
        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    public void testSwitchCurrentPlayer() {
        game.switchCurrentPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        game.switchCurrentPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testBoardClearedBeforeStart() {
        board.place(0, 0, 'X');
        board.place(0, 1, 'O');
        board.place(0, 2, 'X');
        board.place(1, 0, 'O');
        board.place(1, 1, 'X');
        board.place(1, 2, 'O');
        board.place(2, 0, 'X');
        board.place(2, 1, 'O');
        board.place(2, 2, 'X');

        board.clear();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    public void placeMarker() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));

    }

    @Test
    public void testDefaultConstructor() {
        TicTacToe defaultGame = new TicTacToe();
        assertNotNull(defaultGame.getBoard());
        assertNotNull(defaultGame.getCurrentPlayer());
        assertEquals('X', defaultGame.getCurrentPlayer().getMarker());
    }

    @Test
    public void testHasWinner() {
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        board.place(0, 2, 'X');
        assertTrue(game.hasWinner());

        board.clear();
        assertFalse(game.hasWinner());
    }

    @Test
    public void testGameDraw() {
        String input = "0\n0\n1\n1\n2\n2\n2\n1\n2\n0\n1\n0\n0\n1\n0\n2\n1\n2\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        game.start();


        assertFalse(game.hasWinner());
        assertTrue(board.isFull());
    }

    @Test
    public void testGameWin() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        game.start();

        assertTrue(game.hasWinner());
    }

    @Test
    public void testStartNewGameAfterPlayAgain() {
        String input = "0\n0\n1\n1\n0\n1\n2\n1\n0\n2\nyes\n0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        game.start();

        assertTrue(game.getCounter() == 2);
    }



    @Test
    public void testStartGameEndsWhenBoardFull() {
        String input = "1\n1\n0\n0\n2\n2\n2\n1\n2\n0\n1\n0\n0\n1\n0\n2\n1\n2\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        game = new TicTacToe(board, player1, player2, scanner);
        game.start();

        assertTrue(board.isFull());
    }

}