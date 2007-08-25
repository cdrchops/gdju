package com.gerbildrop.gc.gauges.pfd.opengc;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class SpeedTicker extends BaseGauge {
    public SpeedTicker() {
        super(7, 90, 21, 18, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // Draw black background
        gl.glColor3f(0, 0, 0);

        // Rectangular part
        gl.glRectd(0.0, 0.0, 18.0, 18.0);

        // Triangular part
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2f(18.0f, 7.0f);
        gl.glVertex2f(21.0f, 9.0f);
        gl.glVertex2f(18.0f, 11.0f);
        gl.glEnd();

        // White border around background
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(1.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(0.0f, 18.0f);
        gl.glVertex2f(18.0f, 18.0f);
        gl.glVertex2f(18.0f, 11.0f);
        gl.glVertex2f(21.0f, 9.0f);
        gl.glVertex2f(18.0f, 7.0f);
        gl.glVertex2f(18.0f, 0.0f);
        gl.glEnd();

        // The speed ticker doesn't display speeds < 30
        if (ias < 30) {
            ias = 30.0;
            spd = 30;
        }

        // y position of the text (for easy changes)
        double fontHeight = 7.0;
        double texty = m_PhysicalSizey / 2 - fontHeight / 2;

        // Draw text in white
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);

        if (ias >= 100.0) {
            // 100's
            Font.display(glAutoDrawable, 2.0, texty, String.valueOf(spd / 100));
            spd = spd - 100 * (int) (spd / 100);
        }

        if (ias > 10.0) {
            // 10's
            Font.display(glAutoDrawable, 6.5, texty, String.valueOf(spd / 10));
            spd = spd - 10 * (int) (spd / 10);
        }

        // 1's - the most complicated, since they scroll
        // Middle digit
        int three_one = (int) spd;
        // Now figure out the digits above and below
        int five_one = (three_one + 2) % 10;
        int four_one = (three_one + 1) % 10;
        int two_one = (three_one - 1 + 10) % 10;
        int one_one = (three_one - 2 + 10) % 10;

        // Figure out the Speed translation factor for the one's place
        gl.glTranslated(0, -1 * (ias - (int) ias) * fontHeight, 0);

        // Display all of the digits
        Font.display(glAutoDrawable, 11.0, texty + fontHeight * 2 + fontHeight / 5, String.valueOf(five_one));
        Font.display(glAutoDrawable, 11.0, texty + fontHeight + fontHeight / 10, String.valueOf(four_one));
        Font.display(glAutoDrawable, 11.0, texty, String.valueOf(three_one));
        Font.display(glAutoDrawable, 11.0, texty - fontHeight - fontHeight / 10, String.valueOf(two_one));
        Font.display(glAutoDrawable, 11.0, texty - fontHeight * 2 - fontHeight / 5, String.valueOf(one_one));
    }
}