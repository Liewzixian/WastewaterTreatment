package com.example.demo1;

import javafx.beans.property.*;

public class Models {
 //  IntegerProperty stage = new SimpleIntegerProperty();
    StringProperty stage = new SimpleStringProperty();
    StringProperty name = new SimpleStringProperty();
    StringProperty COD = new SimpleStringProperty();
    StringProperty BOD = new SimpleStringProperty();
    StringProperty TSS = new SimpleStringProperty();
    StringProperty area = new SimpleStringProperty();
    StringProperty energy = new SimpleStringProperty();
    StringProperty cost = new SimpleStringProperty();


    public String toString() {
        return ""+ getStage() +
                "," + getName() +
                "," + getCOD() +
                "," + getBOD() +
                "," + getTSS() +
                "," + getArea() +
                "," + getEnergy()+
                "";
    }
// DoubleProperty COD,BOD,TSS,area,energy = new SimpleDoubleProperty();

    public final StringProperty stageProperty() {
        return this.stage;
    }

    public java.lang.String getStage() {
        return this.stageProperty().get();
    }

    public final void setStage(final java.lang.String Models) {
        this.stageProperty().set(Models);
    }
    public final StringProperty nameProperty() {
        return this.name;
    }

    public final java.lang.String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final java.lang.String Models) {
        this.nameProperty().set(Models);
    }
    public final StringProperty codProperty() {
        return this.COD;
    }

    public final java.lang.String getCOD() {
        return this.codProperty().get();
    }

    public final void setCOD(final java.lang.String Models) {
        this.codProperty().set(Models);
    }

    public final StringProperty bodProperty() {
        return this.BOD;
    }

    public final java.lang.String getBOD() {
        return this.bodProperty().get();
    }

    public final void setBOD(final java.lang.String Models) {
        this.bodProperty().set(Models);
    }
    public final StringProperty tssProperty() {
        return this.TSS;
    }

    public final java.lang.String getTSS() {
        return this.tssProperty().get();
    }

    public final void setTSS(final java.lang.String Models) {
        this.tssProperty().set(Models);
    }
    public final StringProperty areaProperty() {
        return this.area;
    }

    public final java.lang.String getArea() {
        return this.areaProperty().get();
    }
    public final void setArea(final java.lang.String Models) {
        this.areaProperty().set(Models);
    }
    public final StringProperty energyProperty() {
        return this.energy;
    }

    public final java.lang.String getEnergy() {
        return this.energyProperty().get();
    }

    public final void setEnergy(final java.lang.String Models) {
        this.energyProperty().set(Models);
    }

    public final StringProperty costProperty() {
        return this.cost;
    }

    public java.lang.String getCost() {
        return this.costProperty().get();
    }

    public final void setCost(final java.lang.String Models) {
        this.costProperty().set(Models);
    }


}