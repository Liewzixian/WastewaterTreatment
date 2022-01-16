package Coursework;

import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Wastewater { //main class

    public static void main(String[] args) throws IOException {

        Comparator<Tech> typeComp = Comparator.comparing(Tech::getType);
        Comparator<Tech> codeComp = Comparator.comparing(Tech::getCode);

        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans
        LinkedList<Result> newResult; //linked list to hold results

        Scanner input = new Scanner(System.in);

        NumTest num = new NumTest(); //testing if input is integer or double
        Renumber renum = new Renumber(); //renumber linked list

        Add add = new Add(); //add new element to linked list
        Delete del = new Delete(); //delete element from linked list
        Change change = new Change(); //change one parameter of element of linked list
        Treatment treat = new Treatment(); //get one specific combination and results
        Calculate cal = new Calculate(); //show all possible combinations and results
        Sort sort = new Sort(); //sort all possible results in ascending or descending order
        UniformCostSearchAlgo uniformCostSearchAlgo = new UniformCostSearchAlgo();
        AdjacencyList adjacencyList = new AdjacencyList();

        String type,code,option,choice,five,result,order;

        new Load(newTech); //load treatment types into linked list
        System.out.println("Treatment data loaded to linked list.");
        newTech = renum.Recode(newTech); //renumber all data from linked list
        newTech.sort(typeComp.thenComparing(codeComp)); //sort all data from linked list

        while (true){ //while loop until user exits

            System.out.println("\nEnter option:");
            System.out.println("1.Add new treatment");
            System.out.println("2.Delete existing treatment");
            System.out.println("3.Change existing treatment");
            System.out.println("4.Show all treatment plans");
            System.out.println("5.Get specific result");
            System.out.println("6.Show all possible results");
            System.out.println("7.Sort all possible results");
            System.out.println("8.Uniform Cost Search");
            System.out.println("9.Exit and save data");

            option = input.nextLine(); //get option

            if(num.isInt(option)) { //if option is int

                if(Integer.parseInt(option)==9){ //if user choose to exit, break while loop
                    break;
                }

                switch(Integer.parseInt(option)){ //if option is not 8
                    case 1:
                        newTech = add.Insert(newTech); //add new element to linked list
                        break;
                    case 2:
                        newTech = del.Remove(newTech); //delete element from linked list
                        break;
                    case 3:
                        while (true) {
                            System.out.println("Enter type:");
                            type = input.nextLine(); //get treatment type

                            if(num.isInt(type)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }

                        while (true) {
                            System.out.println("Enter code:");
                            code = input.nextLine(); //get treatment code

                            if(num.isInt(code)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }

                        while (true) {
                            System.out.println("Enter choice:");
                            System.out.println("1.Type");
                            System.out.println("2.Name");
                            System.out.println("3.TSS");
                            System.out.println("4.COD");
                            System.out.println("5.BOD");
                            System.out.println("6.Area");
                            System.out.println("7.Energy");
                            choice = input.nextLine(); //choose which parameter to change

                            if(num.isInt(choice)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }

                        newTech = change.Replace(Integer.parseInt(type),Integer.parseInt(code),Integer.parseInt(choice),newTech); //change parameter of linked list
                        break;
                    case 4:
                        for(Tech print : newTech) { //print out all treatment plans
                            System.out.format("%d %d %S %.2f %.2f %.2f %.2f %.3f\n", print.getType(), print.getCode(), print.getName(), print.getTSS(), print.getCOD(), print.getBOD(), print.getArea(), print.getEnergy());
                        }
                        break;
                    case 5:
                        while (true) {
                            System.out.println("Enter 5 digit code:");
                            five = input.nextLine(); //get 5-digit number

                            if(num.isInt(five)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }
                        treat.getCode(Integer.parseInt(five),newTech); //get combination of treatment plan from 5 digit input
                        treat.getValues(); //get values of TSS,BOD,COD
                        System.out.println("Treatment Types");
                        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
                        treat.Calculate(newTech); //get linked list to calculate results
                        break;
                    case 6:
                        newResult = cal.ShowAll(newTech); //get default linked list of results

                        System.out.println("Treatment Types");
                        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
                        for(Result print : newResult) { //print out linked list of results
                            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.getT1(), print.getT2(), print.getT3(), print.getT4(), print.getT5(), print.getTSS(),print.getCOD(),print.getBOD(), print.getCost());
                        }
                        break;
                    case 7:
                        newResult = cal.ShowAll(newTech); //get default linked list of results

                        while (true){
                            System.out.println("Enter result to be sorted:");
                            System.out.println("1.TSS");
                            System.out.println("2.BOD");
                            System.out.println("3.COD");
                            System.out.println("4.Cost");
                            result = input.nextLine(); //get choice to be sorted

                            if(num.isInt(result)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }

                        while (true){
                            System.out.println("Enter order to be sorted");
                            System.out.println("1.Ascending");
                            System.out.println("2.Descending");
                            order = input.nextLine(); //get order to be sorted

                            if(num.isInt(order)){
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        }
                        newResult = sort.Order(Integer.parseInt(result),Integer.parseInt(order),newResult); //sort linked list according to choice

                        System.out.println("Treatment Types");
                        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
                        for(Result print : newResult) { //print out sorted linked list
                            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.getT1(), print.getT2(), print.getT3(), print.getT4(), print.getT5(), print.getTSS(),print.getCOD(),print.getBOD(), print.getCost());
                        }
                        break;
                    case 8:
                        //uniformCostSearchAlgo.UniformCostSearch(newTech);
                        adjacencyList.UniformCostSearch(newTech);
                        break;
                    default: //any number except 1-8
                        System.out.println("Invalid input.");
                }
            }
            else { //if input is not int
                System.out.println("Invalid input.");
            }
        }

        input.close(); //close scanner
        new Save(newTech); //save linked list of treatment types into text file
        System.out.println("\nTreatment data saved to text file.");
    }
}
