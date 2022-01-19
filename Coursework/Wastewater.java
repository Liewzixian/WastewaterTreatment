package Coursework;

import Coursework.DataClasses.Initial;
import Coursework.DataClasses.Tech;

import java.io.IOException;

public class Wastewater { //main class

    public static void main(String[] args) throws IOException {

        int option,type,code,choice,standard;
        double TSS,COD,BOD,area,energy,weight;
        String name,newEntry;

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

                menu.add(type,new Tech(name,TSS,COD,BOD,area,energy));
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

                if(menu.getCode(Integer.toString(code))) {
                    text.getInitialText("TSS");
                    TSS = input.getDouble();
                    text.getInitialText("COD");
                    COD = input.getDouble();
                    text.getInitialText("BOD");
                    BOD = input.getDouble();

                    text.getHeader();
                    menu.getSpecificResult(new Initial(TSS,COD,BOD));
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

                text.getStandard();
                text.getTreatmentText("standard");
                standard = input.getIntBounded(0,2);

                text.getHeader();
                menu.showAllResults(new Initial(TSS,COD,BOD),standard);

            }
            else if(option==7){

                System.out.format("\nIn the event that results are not initialised or a change in data occurs,");
                System.out.format("default values of 1000 will be used for TSS, COD and BOD to calculate new results.\n");

                text.getSortType();
                text.getSortText("type");
                type = input.getIntBounded(1,4);

                text.getSortOrder();
                text.getSortText("order");
                code = input.getIntBounded(1,2);

                text.getStandard();
                text.getTreatmentText("standard");
                standard = input.getIntBounded(0,2);

                text.getHeader();
                menu.sortResults(type,code,standard);
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
