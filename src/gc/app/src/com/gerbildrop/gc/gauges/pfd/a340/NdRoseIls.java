package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class NdRoseIls extends BaseGauge {
    public NdRoseIls() {
        super(0, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        // Draw the ILS information block
        paint_info_ILSMode(glAutoDrawable, m_fVOR1_frequency, m_fVOR1_course);

        gl.glScalef(100, 100, 0);

        // Draw the VOR Approach message
        gl.glColor3ubv(CLGreen);

        Font.display(glAutoDrawable, 0.392f, 0.943f, "ILS APP");

        // Draw the ILS course and deviation
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);

        paintILSCource(glAutoDrawable, m_fVOR1_course, m_fVOR1_deviation);
        gl.glPopMatrix();
    }

    public static void paintILSCource(GLAutoDrawable glAutoDrawable, double fHeading, double fDeviation) {
        // Paint the ILS course dagger
        gl.glColor3ubv(CLMagenta);
        gl.glPushMatrix();
        gl.glRotated(fHeading, 0, 0, -1.0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.000f, 0.350f);
        gl.glVertex2f(0.000f, 0.080f);

        gl.glVertex2f(-0.060f, 0.210f);
        gl.glVertex2f(+0.060f, 0.210f);

        gl.glVertex2f(0.000f, -0.080f);
        gl.glVertex2f(0.000f, -0.350f);
        gl.glEnd();

        Font.display(glAutoDrawable, -8.0 / 4.0 * 0.0895, 0, "6");
        Font.display(glAutoDrawable, -2.0 / 4.0 * 0.0895, 0, "6");
        Font.display(glAutoDrawable, +2.0 / 4.0 * 0.0895, 0, "6");
        Font.display(glAutoDrawable, +8.0 / 4.0 * 0.0895, 0, "6");

        double fDev = fDeviation < -10.0f ? -10.0f
                      : fDeviation > 10.0f ? 10.0f
                        : fDeviation;
        gl.glTranslated(fDev / 4.0 * 0.0895, 0, 0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.000f, 0.080f);
        gl.glVertex2f(0.000f, -0.080f);
        gl.glEnd();
        gl.glPopMatrix();
    }


    public static void paint_info_ILSMode(GLAutoDrawable glAutoDrawable, double fFrequency, double fCourse) {
        // Draw the ILS information block
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.700f, 0.900f, "ILS1 " + fFrequency);
        Font.display(glAutoDrawable, 0.775f, 0.850f, "CRS " + fCourse);
        Font.display(glAutoDrawable, 0.900f, 0.800f, "");
    }
}