package com.example.demo1.dataclasses;

/**
 * Public class Print is used to display the model data to the user
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class Print {
    private final String PreliminaryTreatments;
    private final String ChemicalTreatments;
    private final String BiologicalTreatments;
    private final String TertiaryTreatments;
    private final String SludgeTreatments;

    private final double TSS;
    private final double COD;
    private final double BOD;
    private final double cost;
    private final double energy;
    private final double areaOfFootprint;

    private final double[] fullTSS;
    private final double[] fullBOD;
    private final double[] fullCOD;
    /**
    Constructor
     */
    public Print(String Preliminary, String Chemical, String Biological, String Tertiary, String Sludge, double TTss, double TCod, double TBod, double TCost, double []cod, double []bod, double []tss, double energy, double areaOfFootprint){
        PreliminaryTreatments =Preliminary;
        ChemicalTreatments =Chemical;
        BiologicalTreatments =Biological;
        TertiaryTreatments =Tertiary;
        SludgeTreatments =Sludge;
        TSS=TTss;
        COD=TCod;
        BOD=TBod;
        cost=TCost;
        fullCOD=cod;
        fullBOD=bod;
        fullTSS=tss;
        this.energy=energy;
        this.areaOfFootprint=areaOfFootprint;
    }
    /**
     Getter of ChemicalTreatments
     */
    public String getChemicalTreatments() {
        return ChemicalTreatments;
    }
    /**
     Getter of TertiaryTreatments
     */
    public String getTertiaryTreatments() {
        return TertiaryTreatments;
    }
    /**
     Getter of SludgeTreatments
     */
    public String getSludgeTreatments() {
        return SludgeTreatments;
    }
    /**
     Getter of TSS
     */
    public double getTSS() {
        return TSS;
    }
    /**
     Getter of COD
     */
    public double getCOD() {
        return COD;
    }
    /**
     Getter of BOD
     */
    public double getBOD() {
        return BOD;
    }
    /**
     Getter of Cost
     */
    public double getCost() {
        return cost;
    }
    /**
     Getter of BiologicalTreatments
     */
    public String getBiologicalTreatments(){
        return BiologicalTreatments;
    }
    /**
     Getter of PreliminaryTreatments
     */
    public String getPreliminaryTreatments(){
        return PreliminaryTreatments;
    }
    /**
     Getter of energy
     */
    public double getEnergy(){
        return energy;
    }
    /**
     Getter of areaOfFootprint
     */
    public double getAreaOfFootprint() {return areaOfFootprint;}
    /**
     Getter of FullBod
     */
    public double[] getFullBOD() {
        return fullBOD;
    }
    /**
     Getter of FullCod
     */
    public double[] getFullCOD() {
        return fullCOD;
    }
    /**
     Getter of FullTss
     */
    public double[] getFullTSS() {
        return fullTSS;
    }
}
