package com.amanya.wordsome;

public final class Board {
    private final Alphabet alphabet;
    private final char[][] board;
    private final int width = 9;
    private final int height = 6;

    public Board(Alphabet alphabet) {
        this.alphabet = alphabet;
        this.board = initBoard();
    }

    private char[][] initBoard() {
        char[][] board = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = alphabet.getLetter();
            }
        }
        return board;
    }

    public StringBuffer getRow(int num) {
        StringBuffer row = new StringBuffer();
        for (int x = 0; x < width; x++) {
            row.append(board[num][x]);
        }
        return row;
    }

    public StringBuffer getCol(int num) {
        StringBuffer col = new StringBuffer();
        for (int y = 0; y < height; y++) {
            col.append(board[y][num]);
        }
        return col;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder s = new StringBuilder();
            for (int x = 0; x < width; x++) {
                s.append(board[y][x]);
            }
            result.append(s).append("\n");
        }
        return result.toString();
    }
}
