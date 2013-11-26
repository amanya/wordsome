package com.amanya;

/**
 * Created with IntelliJ IDEA.
 * User: albert.manya
 * Date: 26/11/13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class LetterFrequency<Character, Float> {
    private final Character letter;
    private final Float frequency;

    public LetterFrequency(Character letter, Float frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public Character getLetter() { return letter; }
    public Float getFrequency() { return frequency; }


}