package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Load{ //class to load treatment types from text file (can add code to load result in future)

    public Load(LinkedList<Tech> loadTech) throws FileNotFoundException {

        File file=new File("/Users/ziqin/Desktop/output.txt"); //load location
        Scanner sc=new Scanner(file);

        String[] hold = new String[8]; //make array of strings with 8 elements
        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop
                for (int count = 0; count<8; count++){
                    hold[count] = st.nextToken();
                }
                Tech input = new Tech(Integer.parseInt(hold[0]),Integer.parseInt(hold[1]),hold[2],Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]),Double.parseDouble(hold[7]));
                loadTech.add(input); //change each string to correct type and load in linked list
            }
        }
    }
}