package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class DictionaryReader {
    private HashMap<String, Integer> myDictionary;
    private String path;

    public DictionaryReader(String path) {
        this.path = path;
        myDictionary = new HashMap<>();
    }

    public HashMap<String, Integer> getMyDictionary() {
        var fileReader = new FileReader(path);
        var text = fileReader.getFileText();
        text.toLowerCase();
        var textAsArray = Arrays.asList(text.split("\\n"));

        for (var index : textAsArray) {
            var lineSplit = Arrays.asList(index.split("\\s++"));
            myDictionary.put(lineSplit.get(0), Integer.parseInt(lineSplit.get(1)));
        }

        return myDictionary;
    }
}
