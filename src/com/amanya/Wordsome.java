package com.amanya;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Wordsome {
    ArrayList<ArrayList> frequencies = new ArrayList<ArrayList>();

    public Wordsome() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            frequencies = mapper.readValue(new File("frequencies.json"), ArrayList.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ArrayList item : frequencies) {
            System.out.println(item.get(0));
            System.out.println(item.get(1));
        }

    }
}
