package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryCreator {
    HashMap<String, Integer> myDictionary;
    String myDictionaryPath;

    public DictionaryCreator(HashMap<String, Integer> myDictionary, String myDictionaryPath) {
        this.myDictionary = myDictionary;
        this.myDictionaryPath = myDictionaryPath;
    }

    public void createDictionary() {
        var filePathArray = new ArrayList<String>();
        filePathArray.add("./Corpus/eng_news_2016_300K-sentences.txt");
        filePathArray.add("./Corpus/eng_wikipedia_2016_300K-sentences.txt");

        var dictionaryCompiler = new DictionaryCompiler(filePathArray);
        var dictionaryWriter = new DictionaryWriter(myDictionaryPath);
        dictionaryCompiler.compileDictionary();
        myDictionary = dictionaryCompiler.getMyDictionary();
        dictionaryWriter.writeDictionary(myDictionary);
    }

    public HashMap<String, Integer> getMyDictionary() {
        return myDictionary;
    }
}
