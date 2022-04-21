package com.example.demo1;
public class NumTest{ //class for testing if input is integer or double
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
        if (!isDouble(input))
            input = "Invalid input.";
        return input;
    }

    public boolean isString(String input){
        return input.length() > 0;
    }

    public String getString(String input){
        if (input.length() == 0)
            input = "Invalid input.";
        return input;
    }
}