package com.gerbildrop.gc.gauges.pfd.opengc;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class VSI extends BaseGauge {
    double m_NeedleCenter = 54.0;
    double m_MaxNeedleDeflection = 42.0;

    public VSI() {
        super(182, 44, 16, 108, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // Save matrix
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();

        // Draw in gray-blue
        gl.glColor3ub((byte) 51, (byte) 51, (byte) 76);

        // Bottom angular part
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2f(0.0f, 10.0f);
        gl.glVertex2f(0.0f, 40.0f);
        gl.glVertex2f(4.5f, 44.0f);
        gl.glVertex2f(16.0f, 44.0f);
        gl.glVertex2f(16.0f, 27.0f);
        gl.glVertex2f(7.0f, 10.0f);
        gl.glEnd();

        // Center rectangle
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2f(4.5f, 44.0f);
        gl.glVertex2f(4.5f, 66.0f);
        gl.glVertex2f(16.0f, 66.0f);
        gl.glVertex2f(16.0f, 44.0f);
        gl.glEnd();

        // Top angular part
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2f(4.5f, 66.0f);
        gl.glVertex2f(0.0f, 70.0f);
        gl.glVertex2f(0.0f, 100.0f);
        gl.glVertex2f(7.0f, 100.0f);
        gl.glVertex2f(16.0f, 83.0f);
        gl.glVertex2f(16.0f, 66.0f);
        gl.glEnd();

        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);

        // if vertical +- 200 fpm display digital readout
//        if (Vertical_Speed_FPM < -199.0) {
//            Font.display(glAutoDrawable, 1, 3.5, String.valueOf(Vertical_Speed_FPM));
//        } else if (Vertical_Speed_FPM > 199.0) {
        Font.display(glAutoDrawable, 1, 103.5, String.valueOf(Vertical_Speed_FPM));
//        }

        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(6000.0), "6-");
        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(2000.0), "2-");
        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(1000.0), "1-");
        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(-1000.0), "1-");
        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(-2000.0), "2-");
        Font.display(glAutoDrawable, 2.0f, VSpeedToNeedle(-6000.0), "6-");

        gl.glLineWidth(3.5f);
        gl.glBegin(GL.GL_LINES);

        // Horizontal center detent
        gl.glVertex2f(5.0f, (float) VSpeedToNeedle(0.0) + 1.75f);
        gl.glVertex2f(10.0f, (float) VSpeedToNeedle(0.0) + 1.75f);

        // Next draw the angled line that indicates climb rate
        // We need to add 1.75 to the calculated needle position because of how
        // GL interprets the line width
        gl.glVertex2f(5.0f, (float) VSpeedToNeedle(Vertical_Speed_FPM) + 1.75f);
        gl.glVertex2f(30.0f, (float) m_NeedleCenter + 1.75f);
        gl.glEnd();

        gl.glPopMatrix();
    }

    public double VSpeedToNeedle(double vspd) {
        double needle;

        if (vspd >= 0) {
            if (vspd > 6000)
                vspd = 6000;

            needle = (1 - Math.exp(-3 * vspd / 6000)) * m_MaxNeedleDeflection + m_NeedleCenter;
        } else {
            vspd = Math.abs(vspd);

            if (vspd > 6000)
                vspd = 6000;

            needle = m_NeedleCenter - (1 - Math.exp(-3 * vspd / 6000)) * m_MaxNeedleDeflection;
        }

        return needle;
    }
}