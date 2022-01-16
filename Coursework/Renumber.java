package Coursework;

import Coursework.DataClasses.Tech;

import java.util.LinkedList;

public class Renumber{ //class to renumber linked list

    public LinkedList<Tech> Recode(LinkedList<Tech> holdTech){

        Tech temp; //temporary variable to hold linked list element
        int[] count = new int[6]; //array of int to count number of treatment of each type
        int loop=1; //default number of loop

        for(Tech cycle : holdTech){ //count number of treatment of each type
            count[cycle.getType()]++;
        }

        //break when loop reaches 6
        do { //while loop forces code to loop through linked list 5 times for the 5 treatment types
            int key = 1; //lowest number of code
            for (Tech cycle : holdTech) { //loop through linked list
                if (count[loop] > 0 && cycle.getType() == loop) { //if count > 0 and number of type == loop
                    temp = holdTech.get(holdTech.indexOf(cycle)); //hold element of linked list
                    temp.setCode(key); //change code number
                    holdTech.set(holdTech.indexOf(cycle), temp); //save element with new code back to linked list
                    count[loop]--; //decrement number in count
                    key++;//increment number of key for next loop
                }
            }
            loop++; //increment loop number

        } while (loop <= 5);

        return holdTech; //return renumbered linked list
    }
}
