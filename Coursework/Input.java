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

    public int getInt(){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isInt(input))
                break; //break loop if type is double
            else
                System.out.println("Invalid input.");
        }
        return Integer.parseInt(input);
    }

    public int getIntBounded(int lower, int upper){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isInt(input))
                if(Integer.parseInt(input) >= lower && Integer.parseInt(input) <= upper)
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

    public double getDoubleBounded(double lower, double upper){
        while (true){
            input = scanner.nextLine(); //get TSS

            if (numTest.isDouble(input))
                if(Double.parseDouble(input) >= lower && Double.parseDouble(input) <= upper)
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
