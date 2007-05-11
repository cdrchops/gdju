package com.gerbildrop.gc.gauges.pfd;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.util.CircleEvaluator;

public class CircleGauge extends BaseGauge {
    public CircleGauge() {
        super(100, 100, 180, 180, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        double radius = 40.0;
        double indicatorDegreesPerTrueDegrees = 1.5;

        double bigFontSize = 5.0;
        double littleFontSize = 3.5;

        // Draw in gray
        gl.glColor3ub((byte) 51, (byte) 51, (byte) 76);
        gl.glLineWidth(1.5f);

        // Set up the circle
        CircleEvaluator aCircle = new CircleEvaluator(gl);
        aCircle.SetOrigin(47, 49);
        aCircle.SetRadius(radius);
        aCircle.SetDegreesPerPoint(5);
        aCircle.SetArcStartEnd(0.0, 360.0);

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        aCircle.Evaluate();
        gl.glEnd();
        gl.glTranslated(47, 49, 0);
        // Draw the center detent
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.0f, (float) radius);
        gl.glVertex2f(-2.8f, (float) radius + 3.25f);
        gl.glVertex2f(2.8f, (float) radius + 3.25f);
        gl.glEnd();

        Font.display(glAutoDrawable, -25, 38, String.valueOf(heading));
        for (int i = 0; i <= 360; i += 5) {
            // The length of the tickmarks on the compass rose
            double tickLength;

            // Make sure the display heading is between 0 and 360
            int displayHeading = (i + 720) % 360;

            // If the heading is a multiple of ten, it gets a long tick
            if (displayHeading % 10 == 0) {
                tickLength = 4;
            } else { // Otherwise it gets a short tick
                tickLength = 2.5;
            }

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0, (float) radius);
            gl.glVertex2f(0, (float) radius - (float) tickLength);
            gl.glEnd();
            gl.glRotated(5.0 * indicatorDegreesPerTrueDegrees, 0, 0, -1);
        }

        // Figure out the nearest heading that's a multiple of 10
        double nearestTen = (double) ((int) heading - (int) heading % 10);

        // Derotate by this offset
        gl.glRotated(-1.0 * (heading - nearestTen) * indicatorDegreesPerTrueDegrees, 0, 0, -1);

        // Now derotate by 40 "virtual" degrees
        gl.glRotated(-40 * indicatorDegreesPerTrueDegrees, 0, 0, -1);

        // Now draw lines 5 virtual degrees apart around the perimeter of the circle
        for (int i = 360 - (int) nearestTen - 40; i <= 360 + nearestTen + 40; i += 5) {
//        for (int i = (int) nearestTen - 40; i <= 360; i += 5) {
            // The length of the tickmarks on the compass rose
            double tickLength;

            // Make sure the display heading is between 0 and 360
            int displayHeading = (i + 720) % 360;

            // If the heading is a multiple of ten, it gets a long tick
            if (displayHeading % 10 == 0) {
                tickLength = 4;

                // The x-position of the font (depends on the number of characters in the heading)
                double fontx;

                // Convert the display heading to a string
                double tmp = displayHeading / 10;
                if (displayHeading % 30 == 0) {
                    if (displayHeading >= 100) {
                        fontx = -bigFontSize;
                    } else {
                        fontx = -bigFontSize / 2;
                    }

                    Font.display(glAutoDrawable, fontx, radius - tickLength - bigFontSize, String.valueOf(tmp));
                } else {
                    if (displayHeading >= 100) {
                        fontx = -littleFontSize;
                    } else {
                        fontx = -littleFontSize / 2;
                    }

                    Font.display(glAutoDrawable, fontx, radius - tickLength - littleFontSize, String.valueOf(tmp));
                }

            } else { // Otherwise it gets a short tick
                tickLength = 2.5;
            }

//            gl.glBegin(GL.GL_LINES);
//                gl.glVertex2f(0, (float) radius);
//                gl.glVertex2f(0, (float) radius - (float) tickLength);
//            gl.glEnd();

            gl.glRotated(5.0 * indicatorDegreesPerTrueDegrees, 0, 0, -1);
        }
    }
}
