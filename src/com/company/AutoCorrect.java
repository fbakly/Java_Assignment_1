package com.company;

import java.util.*;
import java.util.regex.Pattern;

public class AutoCorrect {

    private String userText;
    private Dictionary<String, Integer> myDictionary;
    private final int maximum_edits = 2;

    public AutoCorrect(String userText, Dictionary<String, Integer> myDictionary) {
        this.userText = userText;
        this.myDictionary = myDictionary;
    }


    public String getMostLikelyWord(String word) {
        var mostLikelyWord = "";
        var levenshtein = new LevenshteinDistance();
        var minDistance = Integer.MAX_VALUE;
        var highestFrequency = 0;

        Map.Entry<String, Integer> best = new AbstractMap.SimpleEntry<>("", 0);

        for (var index : myDictionary.entrySet()) {
            if (Math.abs(index.getKey().length() - word.length()) <= maximum_edits) {
                var distance = levenshtein.distance(word.toLowerCase(), index.getKey().toLowerCase());
                if (distance <= maximum_edits) {
                    if ( (distance < minDistance ) || (distance == minDistance && index.getValue() > highestFrequency)){
                        minDistance = distance;
                        best = index;
                    }
                }
            }
        }

        return best.getKey();
    }


    public String replaceWords() {
        var pattern = Pattern.compile("\\w+");
        var matcher = pattern.matcher(userText);
        var sb = new StringBuilder();

        while (matcher.find()) {
            if (myDictionary.exists(matcher.group().toLowerCase())){
                matcher.appendReplacement(sb, matcher.group());
            }
            else if (matcher.group().equals(matcher.group().toUpperCase())) {
                // The code for the capitalization was inspired by Luciano Orlandini Student number 460952
                var temp = getMostLikelyWord(matcher.group().toLowerCase()).toUpperCase();
                matcher.appendReplacement(sb, temp);
            } else if (Character.isUpperCase(matcher.group().charAt(0)) && Character.isLowerCase(matcher.group().charAt(1))) {
                var temp = matcher.group().substring(0, 1).toLowerCase() + matcher.group().substring(1);
                temp = getMostLikelyWord(temp);
                temp = Character.toUpperCase(temp.charAt(0)) + temp.substring(1) ;
                matcher.appendReplacement(sb, temp);
            } else {
                matcher.appendReplacement(sb, getMostLikelyWord(matcher.group()));
            }
        }
        return sb.toString();
    }
}
