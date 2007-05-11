package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.gc.util.CircleEvaluator;

public class NdRoseVor extends BaseGauge {
    public NdRoseVor() {
        super(0, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        gl.glScalef(100, 100, 0);
        // Draw the VOR information block
        paint_info_VORMode(glAutoDrawable, m_fVOR1_frequency, m_fVOR1_course);

        // Draw the VOR Approach message
        gl.glColor3ubv(CLGreen);

        Font.display(glAutoDrawable, 0.392f, 0.943f, "VOR APP");

        // Draw the VOR course and deviation
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0.0f);
        gl.glRotated(m_fHeading, 0, 0, 1.0);

        paintVORCource(glAutoDrawable, m_fVOR1_course, m_fVOR1_deviation);
        gl.glPopMatrix();
    }


    void paintVORCource(GLAutoDrawable glAutoDrawable, double fHeading, double fDeviation) {
        // Paint the VOR course dagger
        gl.glColor3ubv(CLBlue);
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

        CircleEvaluator ce = new CircleEvaluator(gl);
        for (int i = -2; i < 3; ++i) {
            ce.SetOrigin(i * 0.0895, 0);
            ce.SetRadius(0.007);
            ce.SetDegreesPerPoint(6);
            ce.Evaluate();
        }

        double fDev = fDeviation < -10.0f ? -10.0f
                : fDeviation > 10.0f ? 10.0f
                : fDeviation;
        gl.glTranslated(fDev / 5.0f * 0.0895f, 0, 0);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(-0.031f, 0.049f, 0);
        gl.glVertex3f(0.000f, 0.080f, 0);
        gl.glVertex3f(+0.031f, 0.049f, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, 0.080f, 0);
        gl.glVertex3f(0.000f, -0.080f, 0);
        gl.glEnd();
        gl.glPopMatrix();
    }


    void paint_info_VORMode(GLAutoDrawable glAutoDrawable, double fVOR_frequency, double fVOR_course) {
        // Draw the VOR information block
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.700f, 0.900f, "VOR1 " + fVOR_frequency);
        Font.display(glAutoDrawable, 0.775f, 0.850f, "CRS " + fVOR_course + "\220");
        Font.display(glAutoDrawable, 0.900f, 0.800f, "");
    }
}
