package com.gerbildrop.gc.gauges.pfd.opengc;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class SpeedTape extends BaseGauge {
    private float indent_x;

    public SpeedTape() {
        super(8, 32, 34, 136, 4, 4, 0, 0, 1);
        indent_x = m_PhysicalSizex - 10;
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // The speed tape doesn't display speeds < 30 or > 999
        if (ias < 30.0) {
            ias = 30.0;
            spd = 30;
        }

        if (ias > 999.0) {
            ias = 999.0;
            spd = 999;
        }

        // Save matrix
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();

        // Draw in gray-blue
        gl.glColor3ub((byte) 51, (byte) 51, (byte) 76);

        // Draw the background rectangle
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(0.0f, m_PhysicalSizey);
        gl.glVertex2f(indent_x, m_PhysicalSizey);
        gl.glVertex2f(indent_x, 0.0f);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glEnd();

        // Tick marks are space every 10 kph vertically
        // The tick spacing represents how far apart they are in physical
        // units
        double tickSpacing = 11.3;
        double tickWidth = 3.7;
        double fontHeight = 5;
        double fontWidth = 5;
        double fontIndent = 6;

        int nextHighestIAS = (int) (spd / 10) * 10;

        if (nextHighestIAS < spd)
            nextHighestIAS += 10;

        // The vertical offset is how high in physical units the next highest 10's
        // airspeed is above the arrow. For airspeeds divisible by 10, this is 0. I.e. 120, 130
        // etc. are all aligned with the arrow
        double vertOffset = (double) nextHighestIAS - ias;

        // Vertical location of the tick mark
        double tickLocation = 0;

        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(2.0f);

        double i = 0; // counter
        int tickSpeed; // speed represented by tick mark
        int charSpd; // speed for computing text digits

        // Draw ticks up from the center
        for (i = 0; i <= ((m_PhysicalSizey / 2) / tickSpacing) + 1; i += 1.0) {
            tickSpeed = nextHighestIAS + (int) (i * 10.0);
            tickLocation = (m_PhysicalSizey / 2) + i * tickSpacing + vertOffset;

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(indent_x - (float) tickWidth, (float) tickLocation);
            gl.glVertex2f(indent_x, (float) tickLocation);
            gl.glEnd();

            if ((tickSpeed % 20) == 0) {
                double texty = tickLocation - fontHeight / 2;

                charSpd = tickSpeed;

                if (charSpd >= 100) {
                    // 100's
                    Font.display(glAutoDrawable, fontIndent, texty, String.valueOf(charSpd / 100));
                    charSpd = charSpd - 100 * (int) (charSpd / 100);
                }

                // 10's
                Font.display(glAutoDrawable, fontIndent + fontWidth, texty, String.valueOf(charSpd / 10));
                charSpd = charSpd - 10 * (int) (charSpd / 10);

                Font.display(glAutoDrawable, fontIndent + fontWidth * 2, texty, "0");
            }
        }

        // Draw ticks down from the center
        for (i = 1; i <= ((m_PhysicalSizey / 2) / tickSpacing) + 1; i += 1.0) {
            tickSpeed = (int) nextHighestIAS - (int) i * 10;

            // Only draw ticks if they correspond to an IAS of > 30
            if (tickSpeed >= 30) {
                tickLocation = (m_PhysicalSizey / 2) - ((i - 1) * tickSpacing) - (10 - vertOffset);

                gl.glBegin(GL.GL_LINES);
                gl.glVertex2f(indent_x - (float) tickWidth, (float) tickLocation);
                gl.glVertex2f(indent_x, (float) tickLocation);
                gl.glEnd();

                if ((tickSpeed % 20) == 0) {
                    double texty = tickLocation - fontHeight / 2;

                    charSpd = tickSpeed;

                    if (charSpd >= 100) {
                        // 100's
                        Font.display(glAutoDrawable, fontIndent, texty, String.valueOf(charSpd / 100));
                        charSpd = charSpd - 100 * (int) (charSpd / 100);
                    }

                    // 10's
                    Font.display(glAutoDrawable, fontIndent + fontWidth, texty, String.valueOf(charSpd / 10));
                    charSpd = charSpd - 10 * (int) (charSpd / 10);

                    Font.display(glAutoDrawable, fontIndent + fontWidth * 2, texty, "0");
                }
            }
        }

        gl.glPopMatrix();
    }
}