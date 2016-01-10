package com.amanya.wordsome;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

public final class FileAlphabet implements Alphabet {
    private static Alphabet instance = null;
    private static List<List> frequencies = null;
    private static StringBuffer alphabet = new StringBuffer();

    private FileAlphabet() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            frequencies = mapper.readValue(
                    new File(ClassLoader.getSystemResource("frequencies.json").toURI()),
                    List.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        initAlphabet();
    }

    public static Alphabet getInstance() {
        if (instance == null) {
            instance = new FileAlphabet();
        }
        return instance;
    }

    private void initAlphabet() {
        for (List item : frequencies) {
            String letter = (String)item.get(0);
            Double repeat = (Double)item.get(1) * 100;
            for (int n = 0; n < repeat; n++) {
                alphabet.append(letter);
            }
        }
    }

    @Override
    public char getLetter() {
        Random r = new Random();
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }
}
