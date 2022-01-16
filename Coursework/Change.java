package Coursework;

import Coursework.DataClasses.Tech;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Change{ //class to change a single parameter in the element of the linked list

    Comparator<Tech> typeComp = Comparator.comparing(Tech::getType);
    Comparator<Tech> codeComp = Comparator.comparing(Tech::getCode);

    public LinkedList<Tech> Replace(int type, int code, int choice, LinkedList<Tech> loadTech){

        Scanner input = new Scanner(System.in);
        Renumber test = new Renumber(); //renumber list
        int check=-1,hold=0; //check to see if entry exists and hold to get index of the entry
        String ans; //hold new value for changed parameter

        for(Tech find : loadTech){
            if (find.getType() == type && find.getCode() == code) {
                check=0; //if entry exists set check flag
                break;
            }
            hold++; //get index of entry
        }

        if(check==0) { //if entry exists

            Tech temp = loadTech.get(hold); //hold element of linked list to be changed

            //1-8 to change one of the 8 parameters
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter type:");
                    ans = input.nextLine();
                    if (!(Integer.parseInt(ans) < 1 || Integer.parseInt(ans) > 5)) { //if choice is between 1-5
                        temp.setType(Integer.parseInt(ans)); //change old value to new
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter name:");
                    ans = input.nextLine();
                    temp.setName(ans); //change old name to new
                }
                case 3 -> {
                    System.out.println("Enter TSS:");
                    ans = input.nextLine();
                    temp.setTSS(Double.parseDouble(ans)); //change old value to new
                }
                case 4 -> {
                    System.out.println("Enter COD:");
                    ans = input.nextLine();
                    temp.setCOD(Double.parseDouble(ans)); //change old value to new
                }
                case 5 -> {
                    System.out.println("Enter BOD:");
                    ans = input.nextLine();
                    temp.setBOD(Double.parseDouble(ans)); //change old value to new
                }
                case 6 -> {
                    System.out.println("Enter area:");
                    ans = input.nextLine();
                    temp.setArea(Double.parseDouble(ans)); //change old value to new
                }
                case 7 -> {
                    System.out.println("Enter energy:");
                    ans = input.nextLine();
                    temp.setEnergy(Double.parseDouble(ans)); //change old value to new
                }
                default -> System.out.println("Invalid input."); //if choice is not 1-8
            }

            loadTech.set(hold,temp); //load changed element back into list
        }
        else{ //if entry does not exist
            System.out.println("Entry does not exist.");
            return loadTech; //return old linked list
        }
        loadTech.sort(typeComp.thenComparing(codeComp)); //code to sort list in ascending order
        loadTech = test.Recode(loadTech); //code to renumber list
        return loadTech; //return new linked list
    }
}
