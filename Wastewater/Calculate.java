package com.company;

import java.util.LinkedList;

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
