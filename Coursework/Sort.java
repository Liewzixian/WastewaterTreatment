package Coursework;

import Coursework.DataClasses.Result;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Sort{ //class to sort linked list of result by TSS,COD,BOD or cost in ascending or descending order

    Comparator<Result> TssAscending = Comparator.comparing(Result::getTSS);
    Comparator<Result> TssDescending = TssAscending.reversed();

    Comparator<Result> CodAscending = Comparator.comparing(Result::getCOD);
    Comparator<Result> CodDescending = CodAscending.reversed();

    Comparator<Result> BodAscending = Comparator.comparing(Result::getBOD);
    Comparator<Result> BodDescending = BodAscending.reversed();

    Comparator<Result> CostAscending = Comparator.comparing(Result::getCost);
    Comparator<Result> CostDescending = CostAscending.reversed();

    public LinkedList<Result> Order(int result, int order, LinkedList<Result> newResult) {

        Scanner input = new Scanner(System.in);

        while (true) {

            if (!(result < 1 || result > 5 || order < 1 || order > 2)) { //if input is valid

                if (result == 1) { //sort by TSS

                    if (order == 1) { //ascending
                        newResult.sort(TssAscending);
                    } else { //descending
                        newResult.sort(TssDescending);
                    }
                } else if (result == 2) { //sort by COD

                    if (order == 1) { //ascending
                        newResult.sort(CodAscending);
                    } else { //descending
                        newResult.sort(CodDescending);
                    }
                } else if (result == 3) { //sort by BOD

                    if (order == 1) { //ascending
                        newResult.sort(BodAscending);
                    } else { //descending
                        newResult.sort(BodDescending);
                    }
                } else { //sort by cost

                    if (order == 1) { //ascending
                        newResult.sort(CostAscending);
                    } else { //descending
                        newResult.sort(CostDescending);
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
