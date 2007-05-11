package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.symbol.AircraftSymbol;
import com.gerbildrop.gc.util.CircleEvaluator;
import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class NdArc extends BaseGauge {
    public NdArc() {
        super(0, 100, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        while (m_fHeading < 0.0f) m_fHeading += 360.0f;

        gl.glScalef(100, 100, 0);
        // Draw the ground and true airspeed
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.025f, 0.900f, "GS     TAS");
//    m_GLEnv->font()->print(0.025f, 0.900f, 0.05f, 0.05f, false, "GS     TAS");
        gl.glColor3ubv(CLGreen);
        Font.display(glAutoDrawable, 0.100f, 0.900f, String.valueOf(m_fGS) + "     " + String.valueOf(m_fTAS));
//    m_GLEnv->font()->print(0.100f, 0.900f, 0.05f, 0.05f, false, "%3.0f     %3.0f", m_fGS, m_fTAS);

        // Draw the wind direction and speed
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.100f, 0.850f, "/");
//    m_GLEnv->font()->print(0.100f, 0.850f, 0.05f, 0.05f, false, "/");
        gl.glColor3ubv(CLGreen);
        if (!m_bWindAvailable) {
            Font.display(glAutoDrawable, 0.025f, 0.850f, "160 15");
//        m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "160 15");

            // Draw the wind direction arrow
            gl.glPushMatrix();
            gl.glTranslatef(0.068f, 0.813f, 0);
            gl.glRotated(m_fHeading - 160, 0, 0, 1.0);

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.000f, -0.031f);
            gl.glVertex2f(0.000f, +0.031f);
            gl. glEnd();
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2f(-0.015f, -0.016f);
            gl.glVertex2f(0.000f, -0.031f);
            gl.glVertex2f(+0.015f, -0.016f);
            gl.glEnd();
            gl.glPopMatrix();
        } else
            Font.display(glAutoDrawable, 0.025f, 0.850f, "--- --");
//        m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "--- --");

        // Paint current heading needle
        gl.glColor3ubv(CLYellow);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(0.476f, 0.830f);
        gl.glVertex2f(0.484f, 0.830f);
        gl.glVertex2f(0.484f, 0.775f);
        gl.glVertex2f(0.476f, 0.775f);
        gl.glEnd();

        // Paint the heading scale
        gl.glColor3ubv(CLWhite);
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.167f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);
        int i;
        for (i = 0; i < 36; ++i) {
            float fAngle = i * 10.0f - m_fHeading;
            while (fAngle < 0.0) fAngle += 360.0f;
            while (fAngle > 360.0) fAngle -= 360.0f;
            if (fAngle <= 40.0 || fAngle >= 320.0) {
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2f(0.000f, 0.640f);
                gl.glVertex2f(0.000f, 0.670f);
                gl.glEnd();
                Font.display(glAutoDrawable, i > 9 ? -0.03f : -0.015f, 0.665f, String.valueOf(i));
//                m_GLEnv->font()->print(i > 9 ? -0.03f : -0.015f, 0.665f, 0.06f, 0.06f, false, "%i", i);
            }
            gl.glRotated(-10.0, 0, 0, 1.0);
        }
        gl.glPopMatrix();
        CircleEvaluator ce = new CircleEvaluator(gl);

        // Draw the range arcs
        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.169);
        ce.SetArcStartEnd(-60, 60);
        ce.SetDegreesPerPoint(36);
        ce.Evaluate();
//        evalCircle(0.480, 0.167, 0.160, 36, -60, 60);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.320, 36, -60, 60);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.320);
        ce.SetArcStartEnd(-60, 60);
        ce.SetDegreesPerPoint(36);
        ce.Evaluate();
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.480, 12, -52, 52);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.480);
        ce.SetArcStartEnd(-52, 52);
        ce.SetDegreesPerPoint(12);
        ce.Evaluate();
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.640, 10, -45, 45);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.640);
        ce.SetArcStartEnd(-45, 45);
        ce.SetDegreesPerPoint(10);
        ce.Evaluate();
        gl.glEnd();

        // Paint aircraft symbol to indicate heading and center
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.167f, 0);
//        paint_Aircraft_Symbol();
        AircraftSymbol.display(glAutoDrawable);
        gl.glPopMatrix();
    }
}
