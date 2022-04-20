package com.example.demo1;

import java.util.Scanner;

public class Input {

    Scanner scanner;
    NumTest numTest;
    String input;
    static int validate=0 ;

    public Input(){
        this.scanner = new Scanner(System.in);
        this.numTest = new NumTest(); //testing if input is integer or double
    }

    public String getDouble(String input){
        if (!numTest.isDouble(input)) {
            input = "Invalid input.";
            validate = 1;
        }
        return input;
    }

    public String getString(){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (input.length() > 0)
                break; //break loop if type is double
            else
                input ="Invalid input";
        }
        return input;
    }

}
