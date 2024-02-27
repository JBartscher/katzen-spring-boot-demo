package org.cat.katzendemo.model;

import java.util.Random;

public enum Breed {
    ABYSSINIAN,
    BENGAL,
    BRITISH_SHORTHAIR,
    BALINESE,
    BIRMAN,
    DEVON_REX,
    MAINE_COON,
    AMERICAN_BOBTAIL,
    BURMESE;

    private static final Random random = new Random();

    public static Breed getRandom()  {
        Breed[] directions = values();
        return directions[random.nextInt(directions.length)];
    }
}
