package com.company;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public String getUserPath() {
        System.out.print("Please enter the path of the desired file: ");
        return scanner.nextLine();
    }

}
