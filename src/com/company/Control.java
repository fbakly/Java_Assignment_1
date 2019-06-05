package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Control {

    HashMap<String, Integer> myDictionary;
    String myDictionaryPath;

    public Control() {
        myDictionary = new HashMap<>();
        myDictionaryPath = "myDictionary.txt";
    }

    private void createDictionary() {
        var dictionaryCreator = new DictionaryCreator(myDictionary, myDictionaryPath);
        dictionaryCreator.createDictionary();
        myDictionary = dictionaryCreator.getMyDictionary();
    }

    private void readDictionary() {
        var myDictionaryReader = new DictionaryReader(myDictionaryPath);
        myDictionary = myDictionaryReader.getMyDictionary();
    }

    public void run() {
        var myDictionaryDir = new File(myDictionaryPath);
        var outputPath = "./Output.txt";
        var dictionaryExists = myDictionaryDir.exists();

        if (!dictionaryExists)
            createDictionary();
        else
            readDictionary();

        var userInput = new UserInput();
        var userPath = userInput.getUserPath();
        var userPathDir = new File(userPath);

        if (!userPathDir.exists()) {
            System.out.println("No such file");
        } else {
            userPath = userPath.substring(0, userPath.length());
            var userTextFileReader = new FileReader(userPath);
            var userText = userTextFileReader.getFileText();
            if (!userText.isEmpty()) {
                var dick = new SpellingDictionary<Integer>(myDictionary, -1);
                var autoCorrect = new AutoCorrect(userText, dick);
                var resultText = autoCorrect.replaceWords();
                try (var outputFileWriter = Files.newBufferedWriter(Paths.get(outputPath))) {
                    outputFileWriter.write(resultText);
                } catch (IOException iox) {
                }
            } else {
                System.out.println("File is empty");
            }
        }
    }
}
