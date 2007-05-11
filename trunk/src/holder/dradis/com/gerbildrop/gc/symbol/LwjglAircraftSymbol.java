package com.gerbildrop.gc.symbol;

import static org.lwjgl.opengl.GL11.*;

public class LwjglAircraftSymbol {
    public static void display() {
        glColor3ub((byte) 255, (byte) 255, (byte) 0);
        glBegin(GL_QUADS);
        glVertex2f(-0.004f, +0.043f);
        glVertex2f(+0.004f, +0.043f);
        glVertex2f(+0.004f, -0.062f);
        glVertex2f(-0.004f, -0.062f);

        glVertex2f(-0.048f, +0.004f);
        glVertex2f(+0.048f, +0.004f);
        glVertex2f(+0.048f, -0.004f);
        glVertex2f(-0.048f, -0.004f);

        glVertex2f(-0.017f, -0.039f);
        glVertex2f(+0.017f, -0.039f);
        glVertex2f(+0.017f, -0.047f);
        glVertex2f(-0.017f, -0.047f);
        glEnd();
    }
}
