package com.waes.victor.api.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum to represent the results of the comparison
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
public enum DifferenceResult {

    /**
     * Contents are equal.
     */
    EQUALS("Contents are equals."),

    /**
     * Contents have different sizes.
     */
    DIFFERENT_SIZES("Contents have different sizes."),

    /**
     * Contents have same size but different contents.
     */
    SAME_SIZE_DIFFERENT_CONTENTS("Contents have same size but different contents.");

    /**
     * The description of the result.
     */
    private final String description;

    DifferenceResult(String description) {
        this.description = description;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @JsonValue
    public String getDescription() {
        return this.description;
    }

}
