package com.gerbildrop.gc;

public class MasterCautionUtility {
    public static final String WHITE = 'WHITE';
    public static final String GREEN = 'GREEN';
    public static final String YELLOW = 'YELLOW';
    public static final String RED = 'RED';

    public static def checkFuelLevel = {lefty, fuelLowy->
        if (lefty.fuelLevel <= 172.5) {
            fuelLowy.displayColor = YELLOW
        } else {
            fuelLowy.displayColor = GREEN
        }
    }
}