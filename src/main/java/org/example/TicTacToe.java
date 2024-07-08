package org.example;

import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    private Scanner scanner = new Scanner(System.in);

    private int counter = 0;

    public TicTacToe() {
        this.board = new Board();
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.currentPlayer = player1;
    }

    public TicTacToe(Board board, Player player1, Player player2, Scanner scanner) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.scanner = scanner;
    }

    public int[] getPlayerMove() {
        int row, col;
        while (true) {
            System.out.print("Enter row (0-2): ");
            row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            col = scanner.nextInt();
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col)) {
                break;
            }
            System.out.println("Invalid input. Please enter a row and " +
                    "column between 0 and 2 that are not already occupied.");
        }
        return new int[]{row, col};
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }


    public void start() {
        board.clear();
        while (!board.isFull() && !hasWinner()) {
            board.print();
            System.out.println("Current Player: " + currentPlayer.getMarker());
            int[] move = getPlayerMove();
            board.place(move[0], move[1], currentPlayer.getMarker());
            if (hasWinner()) {
                board.print();
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                return;
            }
            switchCurrentPlayer();
        }
        board.print();
        if (board.isFull()) {
            System.out.println("It's a draw!");
        }
    }



    public boolean hasWinner() {
        return board.hasWinner();
    }
}