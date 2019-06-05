package com.company;

import java.util.Map;
import java.util.Set;

public class SpellingDictionary<F> extends Dictionary<String, F> {
    Map<String, F> words;
    F absentFrequency;

    public SpellingDictionary(Map<String, F> words, F absentValue){
        this.words = words;
        this.absentFrequency = absentValue;
    }

    @Override
    public boolean exists(String word) {
        return words.containsKey(word);
    }

    @Override
    public F getFrequency(String word) {
        return words.getOrDefault(word, absentFrequency);
    }

    @Override
    public Set<Map.Entry<String, F>> entrySet() {
        return words.entrySet();
    }
}
