package Coursework;

import Coursework.DataClasses.Tech;

import java.io.IOException;

public class Wastewater { //main class

    public static void main(String[] args) throws IOException {

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

                text.getTreatmentText("type");
                type = input.getIntBounded(1,5);
                text.getTreatmentText("name");
                name = input.getString();
                text.getTreatmentText("TSS");
                TSS = input.getDouble();
                text.getTreatmentText("COD");
                COD = input.getDouble();
                text.getTreatmentText("BOD");
                BOD = input.getDouble();
                text.getTreatmentText("area");
                area = input.getDouble();
                text.getTreatmentText("energy");
                energy = input.getDouble();

                newTech = new Tech(name,TSS,COD,BOD,area,energy);
                menu.add(type,newTech);
            }
            else if(option==2){

                menu.showAllTreatments();

                text.getTreatmentText("type");
                type = input.getIntBounded(1,5);
                text.getTreatmentText("code");
                code = input.getInt();

                menu.delete(type,code);

            }
            else if(option==3){

                String[] choices = {"Type","Name","TSS","COD","BOD","Area","Energy"};

                menu.showAllTreatments();

                text.getTreatmentText("type");
                type = input.getIntBounded(1,5);
                text.getTreatmentText("code");
                code = input.getInt();

                text.getChoices();

                text.getTreatmentText("choice");
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
                    text.getInitialText("TSS");
                    TSS = input.getDouble();
                    text.getInitialText("COD");
                    COD = input.getDouble();
                    text.getInitialText("BOD");
                    BOD = input.getDouble();

                    text.getHeader();
                    menu.getSpecificResult(TSS, COD, BOD);
                }
                else
                    text.invalidText();
            }
            else if(option==6){

                text.getInitialText("TSS");
                TSS = input.getDouble();
                text.getInitialText("COD");
                COD = input.getDouble();
                text.getInitialText("BOD");
                BOD = input.getDouble();

                text.getHeader();
                menu.showAllResults(TSS,COD,BOD);

            }
            else if(option==7){

                text.getSortType();
                text.getSortText("type");
                type = input.getIntBounded(1,4);

                text.getSortOrder();
                text.getSortText("order");
                code = input.getIntBounded(1,2);

                if(menu.checkResults()){
                    System.out.println("No record of previous calculations or change in data detected");
                    System.out.println("Default TSS, COD and BOD values of 1000 used to calculate new results");
                }

                text.getHeader();
                menu.sortResults(type,code);
            }
            else if(option==8){

                text.getTreatmentText("weight");
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
