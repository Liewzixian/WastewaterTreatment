package com.example.demo1.dataclasses;

/**
 * Public class Selection is used to create pairs of tech stage and tech name to be used in the selection controller.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class Selection {

    /**
     * treatment stage name
     */
    private final String stage;
    /**
     * treatment tech name
     */
    private final String treatments;

    /**
     * This constructor creates a Selection object to be chosen by the user.
     * @param stage Tech stage name
     * @param treatments Tech name
     */
    public Selection(String stage , String treatments){
        this.stage = stage;
        this.treatments = treatments;
    }

    /**
     * Getter for treatment stage name
     * @return treatment stage name
     */
    public String getStage() {
        return stage;
    }
    /**
     * Getter for treatment tech name
     * @return treatment tech name
     */
    public String getTreatments() {
        return treatments;
    }
}
