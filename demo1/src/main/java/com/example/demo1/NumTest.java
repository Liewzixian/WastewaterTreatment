package com.example.demo1;

public class NumTest{ //class for testing if input is integer or double

    public boolean isInt(String str) { //takes a string as input because for a Scanner, if you use more than one .nextInt and you press ENTER all of them
        try {                          //take ENTER as an input. If you use .nextLine, it only registers ENTER once. So, instead of having to use a junk
            @SuppressWarnings("unused")//variable to hold the ENTER key every time you use .nextInt, you don't need one.
            int x = Integer.parseInt(str);//changes string to int
            return true; //String is an Integer
        }
        catch (NumberFormatException e) { //if string is not int
            return false; //String is not an Integer
        }
    }

    public boolean isDouble(String str) { //same reason as above except .nextInt is .nextDouble
        try {
            Double.parseDouble(str);//change string to double
            return true;
        } catch (NumberFormatException e) { //if string is not double
            return false;
        }
    }
}
