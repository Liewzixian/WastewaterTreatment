package Coursework;

import java.util.Scanner;

public class Input {

    Scanner scanner;
    NumTest numTest;
    String input;

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

    public double getDouble(){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isDouble(input))
                break; //break loop if type is double
            else
                System.out.println("Invalid input.");
        }
        return Double.parseDouble(input);
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
                System.out.println("Invalid input.");
        }
        return input;
    }

    public void closeScanner(){
        scanner.close();
    }
}
