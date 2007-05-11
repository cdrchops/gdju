package com.gerbildrop.engine.gl;


import org.lwjgl.opengl.GL11;

public class GLUtil {
    public static double transformPitch(double t) {
        double x = 10.0 * t;
        double h = 55 * Math.sin((x - 10) / 60) + 10.875712 - (x / 9) - Math.pow(1.037, -x - 10);
        return h / 15.0;
    }

    public static void fnt_printf(double x, double y, String value) {
//        Font.display(glAutoDrawable, x, y, value);
    }

    public static void evalCircle(double x, double y, double r, int n, double start, double end) {
        double angle = 0;
        double step = 0;

        angle = start / 57.29578;
        if (n > 1) {
            step = (end - start) / (n - 1) / 57.29578;
        }

        for (int i = 0; i < n; ++i) {
            GL11.glVertex2d(r * Math.sin(angle) + x, r * Math.cos(angle) + y);
            angle += step;
        }
    }
}