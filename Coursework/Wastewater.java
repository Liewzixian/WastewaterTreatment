package Coursework;

import Coursework.DataClasses.Tech;

import java.io.IOException;

public class Wastewater { //main class

    public static void main(String[] args) throws IOException {

        String[] choices = {"Type","Name","TSS","COD","BOD","Area","Energy"};

        int option,type,code,choice;
        double TSS,COD,BOD,area,energy,weight;
        String name,newEntry;
        boolean exists;
        Tech newTech;

        Menu menu = new Menu("D:\\Download\\output.txt");
        menu.load();

        Input input = new Input();
        Text text = new Text();

        while (true){ //while loop until user exits

            text.getOptions();
            option = input.getIntBounded(1,9);

            if(option==1){

                menu.showAllTreatments();

                System.out.println("Enter treatment type:");
                type = input.getIntBounded(1,5);
                System.out.println("Enter treatment name:");
                name = input.getString();
                System.out.println("Enter treatment TSS:");
                TSS = input.getDouble();
                System.out.println("Enter treatment COD:");
                COD = input.getDouble();
                System.out.println("Enter treatment BOD:");
                BOD = input.getDouble();
                System.out.println("Enter treatment area:");
                area = input.getDouble();
                System.out.println("Enter treatment energy:");
                energy = input.getDouble();

                newTech = new Tech(name,TSS,COD,BOD,area,energy);
                menu.add(type,newTech);
            }
            else if(option==2){

                menu.showAllTreatments();

                System.out.println("Enter treatment type:");
                type = input.getIntBounded(1,5);
                System.out.println("Enter treatment code:");
                code = input.getInt();

                menu.delete(type,code);

            }
            else if(option==3){

                menu.showAllTreatments();

                System.out.println("Enter treatment type:");
                type = input.getIntBounded(1,5);
                System.out.println("Enter treatment code:");
                code = input.getInt();

                text.getChoices();

                System.out.println("Enter treatment choice:");
                choice = input.getIntBounded(1,7);

                System.out.println("Enter new " + choices[choice-1] + ":");
                if (choice==1)
                    newEntry = Integer.toString(input.getIntBounded(1,5));
                else if (choice==2)
                    newEntry = input.getString();
                else
                    newEntry = Double.toString(input.getDouble());

                menu.change(type,code,choice,newEntry);
            }
            else if(option==4){
                menu.showAllTreatments();
            }
            else if(option==5){

                menu.showAllTreatments();

                System.out.println("Enter 5-digit code:");
                code = input.getIntBounded(11111,99999);
                exists = menu.getCode(Integer.toString(code));

                if(exists) {
                    System.out.println("Enter initial TSS:");
                    TSS = input.getDouble();
                    System.out.println("Enter initial COD:");
                    COD = input.getDouble();
                    System.out.println("Enter initial BOD:");
                    BOD = input.getDouble();

                    text.getHeader();
                    menu.getSpecificResult(TSS, COD, BOD);
                }
                else
                    System.out.println("Invalid input.");
            }
            else if(option==6){

                System.out.println("Enter initial TSS:");
                TSS = input.getDouble();
                System.out.println("Enter initial COD:");
                COD = input.getDouble();
                System.out.println("Enter initial BOD:");
                BOD = input.getDouble();

                text.getHeader();
                menu.showAllResults(TSS,COD,BOD);

            }
            else if(option==7){

                text.getSortType();
                System.out.println("Enter sort type:");
                type = input.getIntBounded(1,4);

                text.getSortOrder();
                System.out.println("Enter sort order:");
                code = input.getIntBounded(1,2);

                if(menu.checkResults()){
                    System.out.println("No record of previous calculations or change in data detected");
                    System.out.println("Default TSS, COD and BOD values of 1000 used to calculate new results");
                }

                text.getHeader();
                menu.sortResults(type,code);
            }
            else if(option==8){

                System.out.println("Enter treatment weight:");
                weight = input.getDoubleBounded(0,1);

                menu.uniformCost(weight);
            }
            else if(option==9){
                input.closeScanner();
                menu.save();
                break;
            }
        }
    }
}
