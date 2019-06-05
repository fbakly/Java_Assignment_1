package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryCompiler{
    private HashMap<String, Integer> myDictionary;
    private ArrayList<String> corpusFiles;

    public DictionaryCompiler(ArrayList<String> corpusFiles) {
        this.myDictionary = new HashMap<>();
        this.corpusFiles = corpusFiles;
    }

    private void filterDictionary() {
        var iterator = this.myDictionary.keySet().iterator();

        while (iterator.hasNext()) {
            if (myDictionary.get(iterator.next()) < 5)
                iterator.remove();
        }
    }

    public void compileDictionary() {
        String line;
        var sb = new StringBuilder();
        List<String> lineAsArray = new ArrayList<>();
        var pattern = Pattern.compile("\\w+");
        Matcher matcher;
        for (var index : corpusFiles) {
            try(BufferedReader reader = Files.newBufferedReader(Paths.get(index))) {
                while ((line = reader.readLine()) != null) {
                    matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        sb.setLength(0);
                        matcher.appendReplacement(sb, matcher.group());
                        myDictionary.put(matcher.group().toLowerCase(), myDictionary.getOrDefault(matcher.group().toLowerCase(), 0) + 1);
                    }
                }
            }catch (IOException iox){
            }
            filterDictionary();
        }
    }

    public HashMap<String, Integer> getMyDictionary() {
        return myDictionary;
    }
}
