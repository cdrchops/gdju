/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;
import com.gerbildrop.riverhawk.gc.jogl.symbol.AircraftSymbol;
import com.gerbildrop.riverhawk.util.CircleEvaluator;

import javax.media.opengl.GL;

/**
 * @author torr
 * @since Nov 20, 2007 - 2:56:33 PM
 */
public class NdArc extends BaseComponent {
    protected void drawLWJGL() {}

    protected void drawJOGL(final GL gl) {
        while (m_fHeading < 0.0f) m_fHeading += 360.0f;

        gl.glScalef(100, 100, 0);
        // Draw the ground and true airspeed
        gl.glColor3ubv(CLWhite);
        Font.display(0.025f, 0.900f, "GS     TAS");
//    m_GLEnv->font()->print(0.025f, 0.900f, 0.05f, 0.05f, false, "GS     TAS");
        gl.glColor3ubv(CLGreen);
        Font.display(0.100f, 0.900f, String.valueOf(m_fGS) + "     " + String.valueOf(m_fTAS));
//    m_GLEnv->font()->print(0.100f, 0.900f, 0.05f, 0.05f, false, "%3.0f     %3.0f", m_fGS, m_fTAS);

        // Draw the wind direction and speed
        gl.glColor3ubv(CLWhite);
        Font.display(0.100f, 0.850f, "/");
//    m_GLEnv->font()->print(0.100f, 0.850f, 0.05f, 0.05f, false, "/");
        gl.glColor3ubv(CLGreen);
        if (!m_bWindAvailable) {
            Font.display(0.025f, 0.850f, "160 15");
//        m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "160 15");

            // Draw the wind direction arrow
            gl.glPushMatrix();
            gl.glTranslatef(0.068f, 0.813f, 0);
            gl.glRotated(m_fHeading - 160, 0, 0, 1.0);

            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.000f, -0.031f);
            gl.glVertex2f(0.000f, +0.031f);
            gl.glEnd();
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2f(-0.015f, -0.016f);
            gl.glVertex2f(0.000f, -0.031f);
            gl.glVertex2f(+0.015f, -0.016f);
            gl.glEnd();
            gl.glPopMatrix();
        } else
            Font.display(0.025f, 0.850f, "--- --");
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
                Font.display(i > 9 ? -0.03f : -0.015f, 0.665f, String.valueOf(i));
//                m_GLEnv->font()->print(i > 9 ? -0.03f : -0.015f, 0.665f, 0.06f, 0.06f, false, "%i", i);
            }
            gl.glRotated(-10.0, 0, 0, 1.0);
        }
        gl.glPopMatrix();


        // Draw the range arcs
        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        evaluateCircle(0.169, 60, 36);
//        evalCircle(0.480, 0.167, 0.160, 36, -60, 60);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.320, 36, -60, 60);
        evaluateCircle(0.320, 60, 36);

        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.480, 12, -52, 52);
        evaluateCircle(0.480, 52, 12);

        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.640, 10, -45, 45);
        evaluateCircle(0.640, 45, 10);

        gl.glEnd();

        // Paint aircraft symbol to indicate heading and center
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.167f, 0);
//        paint_Aircraft_Symbol();
        AircraftSymbol.display();
        gl.glPopMatrix();
    }

    private void evaluateCircle(double radius, int arcSE, int degreesPerPoint) {
        CircleEvaluator ce = new CircleEvaluator();
        ce.setOrigin(0.480, 0.167);
        ce.setRadius(radius);
        ce.setArcStartEnd(-arcSE, arcSE);
        ce.setDegreesPerPoint(degreesPerPoint);
        ce.evaluate();
    }
}