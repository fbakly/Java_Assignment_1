package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DictionaryWriter {
    private Path path;
    private StringBuilder stringBuilder;

    public DictionaryWriter(String path) {
        this.path = Paths.get(path);
        this.stringBuilder = new StringBuilder();
    }

    public boolean writeDictionary(HashMap<String, Integer> myDictionary) {

        try (BufferedWriter bw = Files.newBufferedWriter(this.path)) {

            for (var index : myDictionary.keySet()) {
                    stringBuilder.append(index);
                    stringBuilder.append(" ");
                    stringBuilder.append(myDictionary.get(index));
                    stringBuilder.append("\n");
            }
            bw.write(stringBuilder.toString());
            bw.close();
        } catch (IOException iox) {
        }

        return true;
    }
}
