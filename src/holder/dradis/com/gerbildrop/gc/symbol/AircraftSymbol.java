package com.gerbildrop.gc.symbol;

import javax.media.opengl.GLAutoDrawable;

public class AircraftSymbol {//] extends BaseGauge {

    public static void display() {
        LwjglAircraftSymbol.display();
    }

    public static void display(GLAutoDrawable glAutoDrawable) {
        JoglAircraftSymbol.display(glAutoDrawable);
    }
}
