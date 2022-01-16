package Coursework;

import Coursework.DataClasses.Tech;

import java.util.LinkedList;
import java.util.Scanner;

public class Delete{ //class to delete treatment type

    Scanner input = new Scanner(System.in);
    int check=-1,index=0; //check is to verify if entered code exists within the linked list
    String type,code; //get the type and code

    public LinkedList<Tech> Remove(LinkedList<Tech> holdTech){

        NumTest test = new NumTest(); //to test if string is int or double
        Renumber renumber = new Renumber(); //to renumber the linked list after deletion

        while(true){

            System.out.println("Enter type:");
            type = input.nextLine(); //get type
            System.out.println("Enter code:");
            code = input.nextLine(); //get code

            if(test.isInt(type)&&test.isInt(code)){
                break; //break out of loop is both type and code are int
            }
            else{
                System.out.println("Invalid input."); //continue loop if input is invalid
            }
        }

        for(Tech find : holdTech){ //loop through linked list

            if (find.getType() == Integer.parseInt(type) && find.getCode() == Integer.parseInt(code)) {
                check=0; //if entry exists then set check flag
                break;
            }
            index++; //save the location of entry
        }

        if(check==0) { //if entry exists, remove entry
            holdTech.remove(index);
        }
        else{
            System.out.println("Entry does not exist.");
            return holdTech; //else returns old linked list
        }
        holdTech = renumber.Recode(holdTech); //renumber linked list after deletion
        return holdTech; //return new linked list
    }
}
