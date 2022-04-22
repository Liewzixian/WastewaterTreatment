package com.example.demo1;

import javafx.beans.property.*;

/**
 * This Class is manage model data class
 */
public class Models {
    StringProperty stage = new SimpleStringProperty();
    StringProperty name = new SimpleStringProperty();
    StringProperty COD = new SimpleStringProperty();
    StringProperty BOD = new SimpleStringProperty();
    StringProperty TSS = new SimpleStringProperty();
    StringProperty area = new SimpleStringProperty();
    StringProperty energy = new SimpleStringProperty();
    StringProperty cost = new SimpleStringProperty();

    /**
     * This method return data  in string
     * @return data in string
     */
    public String toString() {
        return ""+ getStage() +
                "," + getName() +
                "," + getCOD() +
                "," + getBOD() +
                "," + getTSS() +
                "," + getArea() +
                "," + getEnergy()+
                "," + getCost()+
                "";
    }

    /**
     * @return treatment stage
     */
    public final StringProperty stageProperty() {
        return this.stage;
    }
    /**
     * @return string treatment stage
     */
    public java.lang.String getStage() {
        return this.stageProperty().get();
    }
    /**
     *setter for stage
     */
    public final void setStage(final java.lang.String Models) {
        this.stageProperty().set(Models);
    }
    /**
     * @return treatment name
     */
    public final StringProperty nameProperty() {
        return this.name;
    }
    /**
     * @return string treatment name
     */
    public final java.lang.String getName() {
        return this.nameProperty().get();
    }
    /**
     *setter for name
     */
    public final void setName(final java.lang.String Models) {
        this.nameProperty().set(Models);

    }
    /**
     * @return treatment cod
     */
    public final StringProperty codProperty() {
        return this.COD;
    }
    /**
     * @return string treatment cod
     */
    public final java.lang.String getCOD() {
        return this.codProperty().get();
    }
    /**
     *setter for cod
     */
    public final void setCOD(final java.lang.String Models) {
        this.codProperty().set(Models);
    }
    /**
     * @return treatment bod
     */
    public final StringProperty bodProperty() {
        return this.BOD;
    }
    /**
     * @return string treatment bod
     */
    public final java.lang.String getBOD() {
        return this.bodProperty().get();
    }
    /**
     *setter for bod
     */
    public final void setBOD(final java.lang.String Models) {
        this.bodProperty().set(Models);
    }
    /**
     * @return treatment tss
     */
    public final StringProperty tssProperty() {
        return this.TSS;
    }
    /**
     * @return string treatment tss
     */
    public final java.lang.String getTSS() {
        return this.tssProperty().get();
    }
    /**
     *setter for tss
     */
    public final void setTSS(final java.lang.String Models) {
        this.tssProperty().set(Models);
    }
    /**
     * @return String treatment area
     */
    public final StringProperty areaProperty() {
        return this.area;
    }
    /**
     * @return String treatment area
     */
    public final java.lang.String getArea() {
        return this.areaProperty().get();
    }
    /**
     *setter for area
     */
    public final void setArea(final java.lang.String Models) {
        this.areaProperty().set(Models);
    }
    /**
     * @return treatment energy
     */
    public final StringProperty energyProperty() {
        return this.energy;
    }
    /**
     * @return string treatment energy
     */
    public final java.lang.String getEnergy() {
        return this.energyProperty().get();
    }
    /**
     *setter for energy
     */
    public final void setEnergy(final java.lang.String Models) {
        this.energyProperty().set(Models);
    }
    /**
     * @return treatment cost
     */
    public final StringProperty costProperty() {
        return this.cost;
    }
    /**
     * @return string treatment cost
     */
    public java.lang.String getCost() {
        return this.costProperty().get();
    }
    /**
     *setter for cost 
     */
    public final void setCost(final java.lang.String Models) {
        this.costProperty().set(Models);
    }


}