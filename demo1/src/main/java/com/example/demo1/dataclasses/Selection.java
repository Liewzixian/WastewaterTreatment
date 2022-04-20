package com.example.demo1.dataclasses;

public class Selection {

    private final String stage; //treatment stage name
    private final String treatments; //treatment tech name
    public Selection(String a , String b){ //constructor
        stage=a;
        treatments=b;
    }

    public String getStage() {
        return stage;
    } //get treatment stage name
    public String getTreatments() {
        return treatments;
    } //get treatment tech name
}
