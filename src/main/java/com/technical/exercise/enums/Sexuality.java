package com.technical.exercise.enums;

public enum Sexuality {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");


    public final String label;

    private Sexuality(String label) {
        this.label = label;
    }

}
