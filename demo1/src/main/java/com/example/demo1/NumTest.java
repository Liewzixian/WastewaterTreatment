package com.example.demo1;

/**
 * Public class NumTest is used to check if a string is a double and if a string is longer than 0.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class NumTest{
    /**
     * This method checks if the given string is a double
     * @param str string to be checked
     * @return boolean denoting if the give string is a double
     */
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);//change string to double
            return true;
        }
        catch (NumberFormatException e) { //if string is not double
            return false;
        }
    }

    /**
     * This method checks if the given string is a double and return a warning if it is not
     * @param input string to be checked
     * @return original string or warning string
     */
    public String getDouble(String input){
        if (!isDouble(input))
            input = "Invalid input.";
        return input;
    }

    /**
     * This method checks if the given string is longer than 0
     * @param input string to be checked
     * @return boolean denoting if the give string is longer than 0
     */
    public boolean isString(String input){
        return input.length() > 0;
    }

    /**
     * This method checks if the given string is longer than 0 and return a warning if it is not
     * @param input string to be checked
     * @return original string or warning string
     */
    public String getString(String input){
        if (input.length() == 0)
            input = "Invalid input.";
        return input;
    }
}