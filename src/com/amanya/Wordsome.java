package com.amanya;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Wordsome {
    private ArrayList<ArrayList> frequencies = new ArrayList<ArrayList>();
    private ArrayList<String> words_3 = new ArrayList<String>();
    private ArrayList<String> words_4 = new ArrayList<String>();
    private ArrayList<String> words_5 = new ArrayList<String>();
    private StringBuffer alphabet = new StringBuffer();
    private char[][] board;
    private int width = 9;
    private int height = 6;

    public Wordsome() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            frequencies = mapper.readValue(new File("res/frequencies.json"), ArrayList.class);
            words_3 = mapper.readValue(new File("res/words_3_en.json"), ArrayList.class);
            words_4 = mapper.readValue(new File("res/words_4_en.json"), ArrayList.class);
            words_5 = mapper.readValue(new File("res/words_5_en.json"), ArrayList.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initAlphabet();
        initBoard();
        printBoard();
        System.out.println("---------");
        /*
        slideVertical(0, 1); // Left
        printBoard();
        System.out.println("---------");
        slideVertical(0, -1); // Right
        printBoard();
        */

        for (int n = 0; n < 4; n++) {
            ArrayList<ArrayList> words = findHorizontalWords(5);
            System.out.println(words);
            squashWords(words);
            words = findHorizontalWords(4);
            System.out.println(words);
            squashWords(words);
            words = findHorizontalWords(3);
            System.out.println(words);
            squashWords(words);
            printBoard();
            while(cascadeBoard()) {
                System.out.println("---------");
                printBoard();
            }
        }
    }

    private void printBoard() {
        for (int y = 0; y < height; y++) {
            StringBuffer s = new StringBuffer();
            for (int x = 0; x < width; x++) {
                s.append(board[y][x]);
            }
            System.out.println(s);
        }
    }

    private void initAlphabet() {
        for (ArrayList item : frequencies) {
            String letter = (String)item.get(0);
            Double repeat = (Double)item.get(1) * 100;
            for (int n = 0; n < repeat; n++) {
                alphabet.append(letter);
            }
        }
    }

    public char getLetter() {
        Random r = new Random();
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }

    private void initBoard() {
        board = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = getLetter();
            }
        }
    }

    private StringBuffer getRow(int num) {
        StringBuffer row = new StringBuffer();
        for (int x = 0; x < width; x++) {
            row.append(board[num][x]);
        }
        return row;
    }

    private StringBuffer getCol(int num) {
        StringBuffer col = new StringBuffer();
        for (int y = 0; y < height; y++) {
            col.append(board[y][num]);
        }
        return col;
    }

    private ArrayList findHorizontalWords(int length) {
        ArrayList<ArrayList> words = new ArrayList<ArrayList>();
        ArrayList<String> corpus = new ArrayList<String>();
        switch (length) {
            case 3:
                corpus = words_3;
                break;
            case 4:
                corpus = words_4;
                break;
            case 5:
                corpus = words_5;
                break;
        }
        for (int y = 0; y < height; y++) {
            StringBuffer row = getRow(y);
            int chunks = row.length() - length + 1;
            for (int n = 0; n < chunks; n++) {
                String chunk = row.substring(n, n + length);
                if (corpus.contains(chunk)) {
                    ArrayList wordInfo = new ArrayList();
                    wordInfo.add(chunk);
                    wordInfo.add(y);
                    wordInfo.add(n);
                    words.add(wordInfo);
                }
            }
        }
        return words;
    }

    private void squashWords(ArrayList<ArrayList> words) {
        for (ArrayList word : words) {
            String chunk = (String)word.get(0);
            Integer y = (Integer)word.get(1);
            Integer x = (Integer)word.get(2);
            for (int n = 0; n < chunk.length(); n++) {
                board[y][x + n] = ' ';
            }
        }
    }

    private boolean cascadeBoard() {
        boolean moved = false;
        for (int y = height - 1; y >= 1; y--) {
            for (int x = 0; x < width; x++) {
                if (board[y][x] == ' ' && board[y - 1][x] != ' ') {
                    board[y][x] = board[y - 1][x];
                    board[y - 1][x] = ' ';
                    moved = true;
                }
            }
        }
        for (int x = 0; x < width; x++) {
            if (board[0][x] == ' ') {
                board[0][x] = getLetter();
                moved = true;
            }
        }
        return moved;
    }

    private void slideHorizontal(int num, int movement) {
        char[] newCol = new char[width];
        for (int x = 0; x < width; x++) {
            int col = (((x + movement) % width) + width) % width;
            newCol[x] = board[num][col];
        }
        for (int x = 0; x < width; x++) {
            board[num][x] = newCol[x];
        }
    }

    private void slideVertical(int num, int movement) {
        char[] newRow = new char[height];
        for (int y = 0; y < height; y++) {
            int col = (((y + movement) % height) + height) % height;
            newRow[y] = board[col][num];
        }
        for (int y = 0; y < height; y++) {
            board[y][num] = newRow[y];
        }
    }
}
