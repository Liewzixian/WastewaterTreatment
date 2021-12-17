package Coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

class NumTest{ //class for testing if input is integer or double

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

class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)
    String t1,t2,t3,t4,t5; //names for all 5 types of wastewater treatment
    double TSS,COD,BOD,cost; //results for all calculations

    public Result(String t1,String t2,String t3,String t4,String t5,double TSS,double COD,double BOD,double cost) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.cost = cost;
    }

    public double getTSS() { //getter
        return TSS;
    }

    public double getCOD() { //getter
        return COD;
    }

    public double getBOD() { //getter
        return BOD;
    }

    public double getCost() { //getter
        return cost;
    }
}

class Tech { //class for linked list to hold all information on all treatment types
    int type,code; //type is from 1-5 and covers all 5 stages while code is the unique identifier given by the system to differentiate between methods in same type
    double TSS,COD,BOD,area,energy; //removal efficiencies for TSS,BOD,COD and values for area and energy/volume
    String name; //names for all treatments

    public Tech(int type,int code,String name,double TSS,double COD,double BOD,double area,double energy){
        this.type = type;
        this.code = code;
        this.name = name;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.area = area;
        this.energy = energy;
    }

    public int getType(){
        return type;
    } //getter

    public int getCode(){
        return code;
    } //getter
}

class Treatment { //class to get a specific combination of treatment and their results

    int c1,c2,c3,c4,c5; //5 numbers to choose 5 different types of treatment
    String code,sTSS,sCOD,sBOD; //code is a string of 5 numbers e.g. 11111, sTSS,sCOD,sBOD is the initial value of TSS,COD,BOD
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
                choice = input.nextInt();                                                        //9, then new code will be needed) or any single code exceeds possible
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

class Save{ //class to save treatment types to text file (can add code to save result in future)

    public Save(LinkedList<Tech> saveTech) throws IOException {

        PrintWriter writer = new PrintWriter("D:\\Download\\output.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Tech print : saveTech) { //save line by line with for loop
            writer.format("%d,%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", print.type, print.code, print.name, print.TSS, print.COD, print.BOD,print.area,print.energy);
        }
        writer.close(); //close writer
    }
}

class Load{ //class to load treatment types from text file (can add code to load result in future)

    public Load(LinkedList<Tech> loadTech) throws FileNotFoundException {

        File file=new File("D:\\Download\\output.txt"); //load location
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

class Delete{ //class to delete treatment type

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

            if (find.type == Integer.parseInt(type) && find.code == Integer.parseInt(code)) {
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

class Add{

    Scanner input = new Scanner(System.in);
    String type,name,TSS,COD,BOD,area,energy; //all parameters of linked list except code, since code is automatically generated by system
    int code=1; //default code number

    public LinkedList<Tech> Insert(LinkedList<Tech> holdTech) {

        NumTest test = new NumTest(); //to test if string is int or double

        while(true){

            System.out.println("Enter type:");
            type = input.nextLine(); //get type

            if(test.isInt(type)){
                break; //break loop if type is int
            }
            else{
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Enter name:");
        name = input.nextLine(); //get name

        while(true) {

            System.out.println("Enter TSS:");
            TSS = input.nextLine(); //get TSS

            if (test.isDouble(TSS)) {
                break; //break loop if type is double
            } else {
                System.out.println("Invalid input.");
            }
        }

        while (true) {

            System.out.println("Enter COD:");
            COD = input.nextLine(); //get COD

            if(test.isDouble(COD)){
                break; //break loop if type is double
            }
            else{
                System.out.println("Invalid input.");
            }
        }

        while (true) {

            System.out.println("Enter BOD:");
            BOD = input.nextLine(); //get BOD

            if(test.isDouble(BOD)){
                break; //break loop if type is double
            }
            else{
                System.out.println("Invalid input.");
            }
        }

        while (true) {

            System.out.println("Enter area:");
            area = input.nextLine(); //get area

            if(test.isDouble(area)){
                break; //break loop if type is double
            }
            else{
                System.out.println("Invalid input.");
            }
        }

        while (true) {
            System.out.println("Enter energy:");
            energy = input.nextLine(); //get energy

            if(test.isDouble(energy)){
                break; //break loop if type is double
            }
            else{
                System.out.println("Invalid input.");
            }
        }

        for(Tech count : holdTech){ //count number of original codes in linked list
            if (Integer.parseInt(type)==count.type){
                code++; //get new code number
            }
        }

        Tech newTech = new Tech(Integer.parseInt(type),code, name, Double.parseDouble(TSS), Double.parseDouble(COD), Double.parseDouble(BOD), Double.parseDouble(area), Double.parseDouble(energy));
        holdTech.add(newTech); //add new entry into linked list

        return holdTech; //return new linked list
    }
}

class Renumber{ //class to renumber linked list

    public LinkedList<Tech> Recode(LinkedList<Tech> holdTech){

        Tech temp; //temporary variable to hold linked list element
        int[] count = new int[6]; //array of int to count number of treatment of each type
        int loop=1; //default number of loop

        for(Tech cycle : holdTech){ //count number of treatment of each type
            count[cycle.type]++;
        }

        //break when loop reaches 6
        do { //while loop forces code to loop through linked list 5 times for the 5 treatment types
            int key = 1; //lowest number of code
            for (Tech cycle : holdTech) { //loop through linked list
                if (count[loop] > 0 && cycle.type == loop) { //if count > 0 and number of type == loop
                    temp = holdTech.get(holdTech.indexOf(cycle)); //hold element of linked list
                    temp.code = key; //change code number
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

class CodeComp implements Comparator<Tech> { //comparator to sort linked list of treatment by type then by code in ascending order
    @Override
    public int compare(Tech comp1, Tech comp2) {
        if(comp1.getType() == comp2.getType()){
            return Integer.compare(comp1.getCode(), comp2.getCode());
        }
        else if (comp1.getType() > comp2.getType()){
            return 1;
        }
        else {
            return -1;
        }
    }
}

class TSSCompAs implements Comparator<Result> { //comparator to sort final TSS in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getTSS(), comp2.getTSS());
    }
}

class TSSCompDs implements Comparator<Result> { //comparator to sort final TSS in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getTSS(), comp1.getTSS());
    }
}

class CODCompAs implements Comparator<Result> { //comparator to sort final COD in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getCOD(), comp2.getCOD());
    }
}

class CODCompDs implements Comparator<Result> { //comparator to sort final COD in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getCOD(), comp1.getCOD());
    }
}

class BODCompAs implements Comparator<Result> { //comparator to sort final BOD in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getBOD(), comp2.getBOD());
    }
}

class BODCompDs implements Comparator<Result> { //comparator to sort final BOD in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getBOD(), comp1.getBOD());
    }
}

class CostCompAs implements Comparator<Result> { //comparator to sort final cost in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getCost(), comp2.getCost());
    }
}

class CostCompDs implements Comparator<Result> { //comparator to sort final cost in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getCost(), comp1.getCost());
    }
}

class Calculate { //class to calculate and show all possible combinations of the 5 treatment types and their final TSS,COD,BOD and cost

    LinkedList<Result> newResult = new LinkedList<>(); //linked list to hold final results

    public LinkedList<Result> ShowAll(LinkedList<Tech> loadTech){

        int[] index = new int[6]; //array of int to hold index of the final code of each type

        for(Tech cycle : loadTech){ //get index of the final code of each type
            index[cycle.type]=loadTech.indexOf(cycle);
        }

        Tech tech1,tech2,tech3,tech4,tech5; //hold 5 elements of each type
        Result result; //hold final result

        for(int t1 = 0;t1 != index[1]+1;t1++){ //loop from 1st to last element of treatment type 1
            tech1 = loadTech.get(t1); //hold element of treatment type 1

            for(int t2 = index[1]+1;t2 != index[2]+1;t2++){ //loop from 1st to last element of treatment type 2
                tech2 = loadTech.get(t2); //hold element of treatment type 2

                for(int t3 = index[2]+1;t3 != index[3]+1;t3++){ //loop from 1st to last element of treatment type 3
                    tech3 = loadTech.get(t3); //hold element of treatment type 3

                    for(int t4 = index[3]+1;t4 != index[4]+1;t4++){ //loop from 1st to last element of treatment type 4
                        tech4 = loadTech.get(t4); //hold element of treatment type 4

                        for(int t5 = index[4]+1;t5 != index[5]+1;t5++){ //loop from 1st to last element of treatment type 5
                            tech5 = loadTech.get(t5); //hold element of treatment type 5
                            double TSS=1000,COD=1000,BOD=1000,cost=0; //initial value of TSS,COD,BOD,cost (haven't implemented code to get TSS,COD,BOD so default is 1000)

                            for(Tech find : loadTech){ //loop through linked list
                                if((find.type==1&&tech1.code==find.code)||(find.type==2&&tech2.code==find.code)||(find.type==3&&tech3.code==find.code)||(find.type==4&&tech4.code==find.code)||(find.type==5&&tech5.code==find.code)){
                                    TSS = TSS*(1-find.TSS); //get final TSS
                                    BOD = BOD*(1-find.BOD); //get final BOD
                                    COD = COD*(1-find.COD); //get final COD
                                    cost = cost + find.area*find.energy; //get final cost
                                }
                            }

                            result = new Result(tech1.name,tech2.name,tech3.name,tech4.name,tech5.name,TSS,BOD,COD,cost); //save names of each 5 types and their results
                            newResult.add(result); //add to new linked list
                        }
                    }
                }
            }
        }
        return newResult; //return new linked list
    }
}

class Change{ //class to change a single parameter in the element of the linked list

    public LinkedList<Tech> Replace(int type, int code, int choice, LinkedList<Tech> loadTech){

        Scanner input = new Scanner(System.in);
        Renumber test = new Renumber(); //renumber list
        int check=-1,hold=0; //check to see if entry exists and hold to get index of the entry
        String ans; //hold new value for changed parameter

        for(Tech find : loadTech){
            if (find.type == type && find.code == code) {
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
                        temp.type = Integer.parseInt(ans); //change old value to new
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter name:");
                    ans = input.nextLine();
                    temp.name = ans; //change old name to new
                }
                case 3 -> {
                    System.out.println("Enter TSS:");
                    ans = input.nextLine();
                    temp.TSS = Double.parseDouble(ans); //change old value to new
                }
                case 4 -> {
                    System.out.println("Enter COD:");
                    ans = input.nextLine();
                    temp.COD = Double.parseDouble(ans); //change old value to new
                }
                case 5 -> {
                    System.out.println("Enter BOD:");
                    ans = input.nextLine();
                    temp.BOD = Double.parseDouble(ans); //change old value to new
                }
                case 6 -> {
                    System.out.println("Enter area:");
                    ans = input.nextLine();
                    temp.area = Double.parseDouble(ans); //change old value to new
                }
                case 7 -> {
                    System.out.println("Enter energy:");
                    ans = input.nextLine();
                    temp.energy = Double.parseDouble(ans); //change old value to new
                }
                default -> System.out.println("Invalid input."); //if choice is not 1-8
            }

            loadTech.set(hold,temp); //load changed element back into list
        }
        else{ //if entry does not exist
            System.out.println("Entry does not exist.");
            return loadTech; //return old linked list
        }
        loadTech.sort(new CodeComp()); //code to sort list in ascending order
        loadTech = test.Recode(loadTech); //code to renumber list
        return loadTech; //return new linked list
    }
}

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

class UniformCostSearchAlgo{

    public void UniformCostSearch(LinkedList<Tech> loadTech){

        Scanner input = new Scanner(System.in);
        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] nodes;
        int currentNum;
        int count = 0;
        int distance;
        double weight;

        while (true) {
            System.out.println("\nEnter weight:");
            weight = input.nextDouble();

            if(weight>0 && weight<1){
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println();

        for(Tech cycle : loadTech){ //get index of the final code of each type
            index[cycle.type]=loadTech.indexOf(cycle)+1;
            count++;
        }

        int[][] adjacency_matrix = new int[count+2][count+2];

        for(int loop = 0; loop <= count; loop++) {
            currentNum = 0;
            for (Tech cycle : loadTech) {
                if((loop==0 && cycle.type==1)||((loop>0 && loop<=index[1]) && cycle.type==2)||((loop>index[1]&&loop<=index[2]) && cycle.type==3)||((loop>index[2]&&loop<=index[3]) && cycle.type==4)||((loop>index[3]&&loop<=index[4]) && cycle.type==5))
                    adjacency_matrix[loop][currentNum+1] = 9999 - (int) (((cycle.TSS + cycle.COD + cycle.BOD)*weight - cycle.energy*(1-weight)) * 1000);
                else if(loop>index[4]&&loop<=index[5]) {
                    adjacency_matrix[loop][count+1] = 1;
                    break;
                }
                currentNum++;
            }
        }

        //Cycles through rows
        for (int[] adjacencyMatrix : adjacency_matrix) {
            //Cycles through columns
            for (int matrix : adjacencyMatrix) {
                System.out.printf("%6d", matrix); //change the %5d to however much space you want
            }
            System.out.println(); //Makes a new row
        }

        UniformCostSearch uniformCostSearch = new UniformCostSearch(count+2);
        distance = uniformCostSearch.uniformCostSearch(adjacency_matrix,0, count+1);
        nodes = uniformCostSearch.printPath();

        System.out.println();

        for(int i = 1; i < 6; i++){
            newTech.add(loadTech.get(nodes[i]-1));
        }

        System.out.println();

        double TSS = 1000, BOD = 1000, COD = 1000, cost = 0;
        String[] names = new String[5];

        currentNum = 0;
        for(Tech calculate : newTech){
            System.out.format("%d %d %S %.2f %.2f %.2f %.2f %.3f\n", calculate.type, calculate.code, calculate.name, calculate.TSS, calculate.COD, calculate.BOD,calculate.area,calculate.energy);
            TSS = TSS*(1-calculate.TSS); //get final TSS
            BOD = BOD*(1-calculate.BOD); //get final BOD
            COD = COD*(1-calculate.COD); //get final COD
            cost = cost + calculate.area*calculate.energy;
            if(calculate.type==(currentNum+1))
                names[currentNum] = calculate.name;
            currentNum++;
        }

        System.out.println();

        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n",names[0],names[1],names[2],names[3],names[4],TSS,BOD,COD,cost);

        System.out.println("\nThe Distance between source " + 0 + " and destination " + (count+1) + " is " + distance);
    }
}

class AdjacencyList{

    public void UniformCostSearch(LinkedList<Tech> loadTech){

        Scanner input = new Scanner(System.in);
        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;
        int currentNum;
        int count = 0;
        int distance;
        double weight;

        while (true) {
            System.out.println("\nEnter weight:");
            weight = input.nextDouble();

            if(weight>0 && weight<1){
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }

        for(Tech cycle : loadTech){ //get index of the final code of each type
            index[cycle.type]=loadTech.indexOf(cycle)+1;
            count++;
        }

        WeightedGraph weightedGraph = new WeightedGraph(count+2);

        for(int loop = 0; loop <= count; loop++) {
            currentNum = 0;
            for (Tech cycle : loadTech) {
                if((loop==0 && cycle.type==1)||((loop>0 && loop<=index[1]) && cycle.type==2)||((loop>index[1]&&loop<=index[2]) && cycle.type==3)||((loop>index[2]&&loop<=index[3]) && cycle.type==4)||((loop>index[3]&&loop<=index[4]) && cycle.type==5))
                    weightedGraph.addEdge(loop,currentNum + 1,9999 - (int) (((cycle.TSS + cycle.COD + cycle.BOD)*weight - cycle.energy*(1-weight)) * 1000));
                else if(loop>index[4]&&loop<=index[5]) {
                    weightedGraph.addEdge(loop, count + 1, 1);
                    break;
                }
                currentNum++;
            }
        }
        distance = weightedGraph.uniformCostSearch(0, count+1);
        path = weightedGraph.printPath(count+1);

        for(int i = 5; i > 0; i--){
            newTech.add(loadTech.get(path[i]-1));
        }
        System.out.println();

        double TSS = 1000, BOD = 1000, COD = 1000, cost = 0;
        String[] names = new String[5];

        currentNum = 0;
        for(Tech calculate : newTech){
            System.out.format("%d %d %S %.2f %.2f %.2f %.2f %.3f\n", calculate.type, calculate.code, calculate.name, calculate.TSS, calculate.COD, calculate.BOD,calculate.area,calculate.energy);
            TSS = TSS*(1-calculate.TSS); //get final TSS
            BOD = BOD*(1-calculate.BOD); //get final BOD
            COD = COD*(1-calculate.COD); //get final COD
            cost = cost + calculate.area*calculate.energy;
            if(calculate.type==(currentNum+1))
                names[currentNum] = calculate.name;
            currentNum++;
        }

        System.out.println();

        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n",names[0],names[1],names[2],names[3],names[4],TSS,BOD,COD,cost);

        System.out.println("\nThe Distance between source " + 0 + " and destination " + (count+1) + " is " + distance);
    }
}

public class Wastewater { //main class

    public static void main(String[] args) throws IOException {

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
        newTech.sort(new CodeComp()); //sort all data from linked list

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
                            System.out.format("%d %d %S %.2f %.2f %.2f %.2f %.3f\n", print.type, print.code, print.name, print.TSS, print.COD, print.BOD,print.area,print.energy);
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
                            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.t1, print.t2, print.t3, print.t4, print.t5, print.TSS,print.COD,print.BOD,print.cost);
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
                            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.t1, print.t2, print.t3, print.t4, print.t5, print.TSS,print.COD,print.BOD,print.cost);
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
