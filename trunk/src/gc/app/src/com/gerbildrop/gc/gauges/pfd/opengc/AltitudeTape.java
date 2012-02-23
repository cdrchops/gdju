package com.gerbildrop.gc.gauges.pfd.opengc;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class AltitudeTape extends BaseGauge {
    public AltitudeTape() {
        super(150, 32, 24, 136, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // Save matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();

        // Draw in gray-blue
        gl.glColor3ub((byte) 51, (byte) 51, (byte) 76);

        // Draw the background rectangle
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(0.0f, m_PhysicalSizey);
        gl.glVertex2f(m_PhysicalSizex, m_PhysicalSizey);
        gl.glVertex2f(m_PhysicalSizex, 0.0f);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glEnd();

        // Tick marks are spaced every 100 ft. vertically
        // The tick spacing represents how far apart they are in physical
        // units
        double tickSpacing = 17.0;
        double tickWidth = 3.7;
        double fontHeight = 4;
        double fontWidth = 3.5;
        double fontIndent = 4.5;

        long nextHighestAlt = (alt / 100) * 100;

        if (nextHighestAlt < alt)
            nextHighestAlt += 100;

        // The vertical offset is how high in physical units the next highest 100's
        // altitude is above the arrow
        double vertOffset = ((double) nextHighestAlt - (double) alt) / 100 * tickSpacing;

        // Vertical location of the tick mark
        double tickLocation = 0;

        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(2.0f);

        double i = 0; // counter
        long tickAlt; // speed represented by tick mark
        long charAlt; // speed for computing text digits

        // Draw ticks up from the center
        for (i = 0; i <= ((m_PhysicalSizey / 2) / tickSpacing) + 1; i += 1.0) {
            tickAlt = nextHighestAlt + (int) (i * 100.0);
            tickLocation = (m_PhysicalSizey / 2) + i * tickSpacing + vertOffset;
            double texty = tickLocation - fontHeight / 2;

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0, (float) tickLocation);
            gl.glVertex2f((float) tickWidth, (float) tickLocation);
            gl.glEnd();

            if ((tickAlt % 200) == 0) {
                charAlt = tickAlt;
                boolean tenk = false;
                boolean onek = false;

                // 10000's
                if (charAlt >= 10000) {
                    Font.display(glAutoDrawable, fontIndent, texty, String.valueOf(charAlt / 10000));
                    charAlt = charAlt - 10000 * (int) (charAlt / 10000);

                    tenk = true;
                }

                // 1000's
                if (charAlt >= 1000) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth, texty, String.valueOf(charAlt / 1000));
                    charAlt = charAlt - 1000 * (int) (charAlt / 1000);

                    onek = true;
                } else if (tenk) {
                    Font.display(glAutoDrawable, fontIndent, texty, "0");
                }

                // 100's
                if (charAlt >= 100) {
                    Font.display(glAutoDrawable, fontIndent * 2, texty, String.valueOf(charAlt / 100));
                    charAlt = charAlt - 100 * (int) (charAlt / 100);
                } else if (tenk || onek) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth * 2, texty, "0");
                }

                Font.display(glAutoDrawable, fontIndent + fontWidth * 3, texty, "0");
                Font.display(glAutoDrawable, fontIndent + fontWidth * 4, texty, "0");
            }
        }

        // Draw ticks down from the center
        for (i = 1; i <= ((m_PhysicalSizey / 2) / tickSpacing) + 1; i += 1.0) {
            tickAlt = nextHighestAlt - (int) (i * 100);
            tickLocation = (m_PhysicalSizey / 2) - ((i - 1) * tickSpacing) - (tickSpacing - vertOffset);
            double texty = tickLocation - fontHeight / 2;

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0, (float) tickLocation);
            gl.glVertex2f((float) tickWidth, (float) tickLocation);
            gl.glEnd();

            if ((tickAlt % 200) == 0) {
                charAlt = tickAlt;
                boolean tenk = false;
                boolean onek = false;

                // 10000's
                if (charAlt >= 10000) {
                    Font.display(glAutoDrawable, fontIndent, texty, String.valueOf(charAlt / 10000));
                    charAlt = charAlt - 10000 * (int) (charAlt / 10000);

                    tenk = true;
                }

                // 1000's
                if (charAlt >= 1000) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth, texty, String.valueOf(charAlt / 1000));
                    charAlt = charAlt - 1000 * (int) (charAlt / 1000);

                    onek = true;
                } else if (tenk) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth, texty, "0");
                }

                // 100's
                if (charAlt >= 100) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth * 2, texty, String.valueOf(charAlt / 100));
                    charAlt = charAlt - 100 * (int) (charAlt / 100);
                } else if (tenk || onek) {
                    Font.display(glAutoDrawable, fontIndent + fontWidth * 2, texty, String.valueOf(charAlt / 100));
                }

                Font.display(glAutoDrawable, fontIndent + fontWidth * 3, texty, "0");
                Font.display(glAutoDrawable, fontIndent + fontWidth * 4, texty, "0");
            }
        }

        gl.glPopMatrix();
    }
}