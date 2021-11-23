package com.company;

import java.util.LinkedList;
import java.util.Scanner;

class Sort{ //class to sort linked list of result by TSS,COD,BOD or cost in ascending or descending order

    public LinkedList<Result> Order(int result,int order,LinkedList<Result> newResult) {

        Scanner input = new Scanner(System.in);
        
        while (true) {

            if (!(result < 1 || result > 5 || order < 1 || order > 2)) { //if input is valid

                if (result == 1) { //sort by TSS

                    if (order == 1) { //ascending
                        newResult.sort(new TSSCompAs());
                    } else { //descending
                        newResult.sort(new TSSCompDs());
                    }
                } else if (result == 2) { //sort by COD

                    if (order == 1) { //ascending
                        newResult.sort(new CODCompAs());
                    } else { //descending
                        newResult.sort(new CODCompDs());
                    }
                } else if (result == 3) { //sort by BOD

                    if (order == 1) { //ascending
                        newResult.sort(new BODCompAs());
                    } else { //descending
                        newResult.sort(new BODCompDs());
                    }
                } else { //sort by cost

                    if (order == 1) { //ascending
                        newResult.sort(new CostCompAs());
                    } else { //descending
                        newResult.sort(new CostCompDs());
                    }
                }
                return newResult; //return sorted linked list
            }
            else{
                System.out.println("Invalid input."); //if invalid input
                result = input.nextInt(); //sort by TSS,COD,BOD or cost
                String junk = input.nextLine(); //hold ENTER key
                order = input.nextInt(); //sort by ascending or descending order
            }
        }
    }
}
