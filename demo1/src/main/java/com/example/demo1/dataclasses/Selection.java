package com.example.demo1.dataclasses;

public class Selection {

    public String stage;
    public String treatments;
    public Selection(String a , String b){
        stage=a;
        treatments=b;
    }

    public String getStage() {
        return stage;
    }

    public String getTreatments() {
        return treatments;
    }
}
