package com.company;

import java.util.*;


class Treatment { //class to get a specific combination of treatment and their results

    int c1,c2,c3,c4,c5; //5 numbers to choose 5 different types of treatment
    String code,sTSS,sCOD,sBOD; //code is a string of 5 numbers eg 11111, sTSS,sCOD,sBOD is the initial value of TSS,COD,BOD
    String n1,n2,n3,n4,n5; //hold name of all 5 treatment types
    double TSS,COD,BOD,cost; //to hold the final result of TSS,COD,BOD and cost
    int[] count = new int[6]; //to count number of times a treatment type appears

    Scanner input = new Scanner(System.in);

    public void getCode(int choice, LinkedList<Tech> newTech) { //takes a 5 digit integer and linked list for all treatments

        while(true) { //loop forever unless input is correct

            code = Integer.toString(choice); //changes 5 digit integer to string

            try {
                c1 = Integer.parseInt(code.charAt(0) + ""); //takes 1st digit of integer
                c2 = Integer.parseInt(code.charAt(1) + ""); //takes 2nd digit of integer
                c3 = Integer.parseInt(code.charAt(2) + ""); //takes 3rd digit of integer
                c4 = Integer.parseInt(code.charAt(3) + ""); //takes 4th digit of integer
                c5 = Integer.parseInt(code.charAt(4) + ""); //takes 5th digit of integer
            }
            catch (StringIndexOutOfBoundsException e) { //if integer is not at least 5 digits
                System.out.println("Invalid input. Try again.\n");
                choice = input.nextInt(); //get new value of 5 integers
                continue;
            }

            for(Tech cycle : newTech){ //loop through linked list and gets number of times a treatment type appears
                count[cycle.type]++;
            }

            if(choice>100000||c1>count[1]||c2>count[2]||c3>count[3]||c4>count[4]||c5>count[5]){ //if entered int exceeds 5 digits (all methods do not exceed 9,
                System.out.println("Invalid input. Try again.\n");                              //this check should suffice for now, but if methods should exceed
                choice = input.nextInt();                                                        //9, then new code will be need) or any single code exceeds possible
            }                                                                                    //choices, then error and get new input
            else{
                break; //if input is correct, break out of while loop
            }
        }
    }

    public void getValues(){ //get values of initial TSS,COD,BOD

        NumTest test = new NumTest();

        while(true){

            System.out.println("Enter TSS:");
            sTSS = input.nextLine(); //initial value of TSS

            System.out.println("Enter COD:");
            sCOD = input.nextLine(); //initial value of COD

            System.out.println("Enter BOD:");
            sBOD = input.nextLine(); //initial value of BOD

            if(test.isDouble(sTSS)&&test.isDouble(sCOD)&&test.isDouble(sBOD)){ //if TSS,COD,BOD are all doubles, then save input
                TSS=Double.parseDouble(sTSS); //change string to double
                COD=Double.parseDouble(sCOD); //change string to double
                BOD=Double.parseDouble(sBOD); //change string to double
                break;
            }
            else{
                System.out.println("Invalid input."); //else restart loop
            }
        }
    }

    public void Calculate(LinkedList<Tech> newTech){ //calculate final TSS,COD,BOD and cost for selected type

        for(Tech find : newTech){ //loop through linked list
            if((find.type==1&&c1==find.code)||(find.type==2&&c2==find.code)||(find.type==3&&c3==find.code)||(find.type==4&&c4==find.code)||(find.type==5&&c5==find.code)){ //if type and code matches
                TSS = TSS*(1-find.TSS); //calculate TSS
                BOD = BOD*(1-find.BOD); //calculate BOD
                COD = COD*(1-find.COD); //calculate COD
                cost = cost + find.area*find.energy; //calculate cost
            }
            if(find.type==1&&c1==find.code){
                n1 = find.name; //get name of 1st treatment
            }
            if(find.type==2&&c2==find.code){
                n2 = find.name; //get name of 2nd treatment
            }
            if(find.type==3&&c3==find.code){
                n3 = find.name; //get name of 3rd treatment
            }
            if(find.type==4&&c4==find.code){
                n4 = find.name; //get name of 4th treatment
            }
            if(find.type==5&&c5==find.code){
                n5 = find.name; //get name of 5th treatment
            }
        }
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n",n1,n2,n3,n4,n5,TSS,BOD,COD,cost); //print out results
    }
}