package com.example.demo1;

import java.util.Scanner;

public class NumTest{ //class for testing if input is integer or double
    Scanner scanner;
    String input;
    
    public NumTest() {
        this.scanner = new Scanner(System.in);
    }
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);//change string to double
            return true;
        } 
        catch (NumberFormatException e) { //if string is not double
            return false;
        }
    }

    public String getDouble(String input){
        if (!isDouble(input)) {
            input = "Invalid input.";
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
