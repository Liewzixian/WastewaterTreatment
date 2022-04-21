package com.example.demo1.dataclasses;

/**
 * Public class PollutionLevels is used to store the initial pollution levels chosen by the user to be used on the
 * combination of wastewater technologies.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */

public class PollutionLevels {

    /**
     * Initial TSS of pollution levels
     */
    private final double TSS;

    /**
     * Initial COD of pollution levels
     */
    private final double COD;

    /**
     * Initial BOD of pollution levels
     */
    private final double BOD;

    /**
     * This constructor creates a PollutionLevel object to store pollution levels for use in generating results.
     * @param COD Initial COD of pollution levels
     * @param BOD Initial BOD of pollution levels
     * @param TSS Initial TSS of pollution levels
     */
    public PollutionLevels(double COD, double BOD, double TSS){
        this.COD = COD;
        this.BOD = BOD;
        this.TSS = TSS;
    }

    /**
     * Getter for TSS levels
     * @return TSS levels
     */
    public double getTSS() {
        return TSS;
    }

    /**
     * Getter for COD levels
     * @return COD levels
     */
    public double getCOD() {
        return COD;
    }

    /**
     * Getter for BOD levels
     * @return BOD levels
     */
    public double getBOD() {
        return BOD;
    }
}