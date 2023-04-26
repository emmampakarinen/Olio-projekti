package com.example.olio_projekti;

public class Spa extends Storage {
    private static Spa spa = null;
    private Spa() {}

    public static Spa getInstance() {
        if (spa == null) {
            spa = new Spa();
        }
        return spa;
    }
    public void spaTreatment(Lutemon lutemon) {
        lutemon.setHealthToMax(lutemon.getMaxHealth());
    }

}
