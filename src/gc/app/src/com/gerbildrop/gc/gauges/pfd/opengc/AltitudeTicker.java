package com.gerbildrop.gc.gauges.pfd.opengc;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class AltitudeTicker extends BaseGauge {
    public AltitudeTicker() {
        super(157, 90, 28, 18, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // Draw black background
        gl.glColor3f(0, 0, 0);
        // Rectangular part
        gl.glRectd(3.5, 0.0, 28.0, 18.0);
        // Triangular part
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2f(0.0f, 9.0f);
        gl.glVertex2f(3.5f, 6.0f);
        gl.glVertex2f(3.5f, 12.0f);
        gl.glEnd();

        // White border around background
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(1.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.0f, 9.0f);
        gl.glVertex2f(3.5f, 12.0f);
        gl.glVertex2f(3.5f, 18.0f);
        gl.glVertex2f(28.0f, 18.0f);
        gl.glVertex2f(28.0f, 0.0f);
        gl.glVertex2f(3.5f, 0.0f);
        gl.glVertex2f(3.5f, 6.0f);
        gl.glEnd();

        // y position of the text (for easy changes)
        double bigFontHeight = 8.0;
        double littleFontHeight = 6.5;
        double texty = m_PhysicalSizey / 2 - bigFontHeight / 2;

        // Draw text in white
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);

        // 10000's
        if (alt >= 10000) {
            Font.display(glAutoDrawable, 5.0, texty, String.valueOf(alt / 10000));
            alt = alt - 10000 * (int) (alt / 10000);
        } else { // draw a green square
            gl.glColor3f(0, (byte) 179, 0);
            gl.glBegin(GL.GL_POLYGON);
            gl.glVertex2f(4.0f, (float) texty);
            gl.glVertex2f(4.0f, (float) texty + (float) bigFontHeight);
            gl.glVertex2f(8.0f, (float) texty + (float) bigFontHeight);
            gl.glVertex2f(8.0f, (float) texty);
            gl.glVertex2f(4.0f, (float) texty);
            gl.glEnd();
            gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        }

        // 1000's
        Font.display(glAutoDrawable, 9.5, texty, String.valueOf(alt / 1000));
        alt = alt - 1000 * (int) (alt / 1000);

        // The 100's, 10's, and 1's are drawn in a smaller size
        texty = m_PhysicalSizey / 2 - littleFontHeight / 2;

        // 100's
        Font.display(glAutoDrawable, 15.0, texty, String.valueOf(alt / 100));
        alt = alt - 100 * (int) (alt / 100);

        // The 10's and 1's position (which is always 0) scroll based on altitude
        // We use three digits for the tens position, high, middle, and low
        // Note that the tens digit is always a multiple of 2
        int middle_ten = (int) alt / 10;

        boolean roundupnine = false;

        switch (middle_ten) {
            case 1:
                middle_ten = 2;
                break;
            case 3:
                middle_ten = 4;
                break;
            case 5:
                middle_ten = 6;
                break;
            case 7:
                middle_ten = 8;
                break;
            case 9:
                middle_ten = 0;
                roundupnine = true;
                break;
        }

        // Figure out the translation for the tens and ones position
        double vertTranslation;

        if (middle_ten != 0)
            vertTranslation = (middle_ten * 10 - (double) alt) / 20 * littleFontHeight;
        else {
            if (roundupnine)
                vertTranslation = (100 - (double) alt) / 20 * littleFontHeight;
            else
                vertTranslation = (0 - (double) alt) / 20 * littleFontHeight;
        }

        gl.glTranslated(0, vertTranslation, 0);

        // Now figure out the digits above and below
        int top_ten = (middle_ten + 2) % 10;
        int bottom_ten = (middle_ten - 2 + 10) % 10;

        // Display all of the digits
        Font.display(glAutoDrawable, 19.0, texty + littleFontHeight + littleFontHeight / 10, String.valueOf(top_ten));
        Font.display(glAutoDrawable, 23.0, texty + littleFontHeight + littleFontHeight / 10, "0");
        Font.display(glAutoDrawable, 19.0, texty, String.valueOf(middle_ten));
        Font.display(glAutoDrawable, 23.0, texty, "0");
        Font.display(glAutoDrawable, 19.0, texty + -1 * littleFontHeight - littleFontHeight / 10, String.valueOf(bottom_ten));
        Font.display(glAutoDrawable, 23.0, texty + -1 * littleFontHeight - littleFontHeight / 10, "0");
    }
}