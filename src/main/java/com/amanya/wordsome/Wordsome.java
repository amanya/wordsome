package com.amanya.wordsome;

import java.util.ArrayList;

public class Wordsome {
    private ArrayList<String> words_3 = new ArrayList<String>();
    private ArrayList<String> words_4 = new ArrayList<String>();
    private ArrayList<String> words_5 = new ArrayList<String>();

    public Wordsome() {
        /*
        ObjectMapper mapper = new ObjectMapper();
        try {
            words_3 = mapper.readValue(
                    new File(ClassLoader.getSystemResource("words_3_en.json").toURI()),
                    ArrayList.class);
            words_4 = mapper.readValue(
                    new File(ClassLoader.getSystemResource("words_4_en.json").toURI()),
                    ArrayList.class);
            words_5 = mapper.readValue(
                    new File(ClassLoader.getSystemResource("words_5_en.json").toURI()),
                    ArrayList.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        */
        Board board = new Board(FileAlphabet.getInstance());
        System.out.println("---------");
        System.out.print(board);
        System.out.println("---------");
        /*
        slideVertical(0, 1); // Left
        printBoard();
        System.out.println("---------");
        slideVertical(0, -1); // Right
        printBoard();

        for (int n = 0; n < 4; n++) {
            ArrayList words = findHorizontalWords(5);
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
        */
    }


    /*
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
    */

    /*
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
    */

    /*
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
    */

    /*
    private void slideHorizontal(int num, int movement) {
        char[] newCol = new char[width];
        for (int x = 0; x < width; x++) {
            int col = (((x + movement) % width) + width) % width;
            newCol[x] = board[num][col];
        }
        System.arraycopy(newCol, 0, board[num], 0, width);
    }
    */

    /*
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
    */
}
