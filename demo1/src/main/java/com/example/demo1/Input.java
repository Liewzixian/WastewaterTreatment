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

    public int getIntBounded(int max){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isInt(input))
                if(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= max)
                    break;
                else
                    System.out.println("Invalid input.");
            else
                System.out.println("Invalid input.");
        }
        return Integer.parseInt(input);
    }

    public String getDouble(String input){
            if (!numTest.isDouble(input)) {
                input = "Invalid input.";
                validate = 1;
            }
             return input;
    }

    public double getDoubleBounded(){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isDouble(input))
                if(Double.parseDouble(input) >= 0 && Double.parseDouble(input) <= 1)
                    break;
                else
                    System.out.println("Invalid input.");
            else
                System.out.println("Invalid input.");
        }
        return Double.parseDouble(input);
    }

    public String getString(){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (input.length()>0)
                break; //break loop if type is double
            else
                input ="Invalid input";
        }
        return input;
    }

}
