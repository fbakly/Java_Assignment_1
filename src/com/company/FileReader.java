package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    private StringBuilder fileText;
    private String path;

    public FileReader(String path) {
        this.path = path;
        fileText = new StringBuilder();
    }

    public String getFileText() {
        try(BufferedReader myReader = Files.newBufferedReader(Paths.get(this.path))) {
            String line;
            while ((line = myReader.readLine()) != null) {
                fileText.append(line);
                fileText.append("\n");
            }
        } catch(IOException iox){
            System.out.println("Input file not found");
            return "";
        }
        return fileText.toString();
    }
}
