package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.gc.symbol.AircraftSymbol;
import com.gerbildrop.gc.util.CircleEvaluator;

public class NdPlan extends BaseGauge {
    public NdPlan() {
        super(100, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        gl.glScalef(100, 100, 0);
        // Draw the ground and true airspeed
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.025f, 0.900f, "GS     TAS");
//        m_GLEnv->font()->print(0.025f, 0.900f, 0.05f, 0.05f, false, "GS     TAS");
        gl.glColor3ubv(CLGreen);
//        m_GLEnv->font()->print(0.100f, 0.900f, 0.05f, 0.05f, false, "%3.0f     %3.0f", m_fGS, m_fTAS);
        Font.display(glAutoDrawable, 0.100f, 0.900f, String.valueOf(m_fGS) + "     " + String.valueOf(m_fTAS));

        // Draw the wind direction and speed
        gl.glColor3ubv(CLWhite);
//        m_GLEnv->font()->print(0.100f, 0.850f, 0.05f, 0.05f, false, "/");
        Font.display(glAutoDrawable, 0.100f, 0.850f, "/");
        gl.glColor3ubv(CLGreen);
        if (!m_bWindAvailable) {
//            m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "160 15");
            Font.display(glAutoDrawable, 0.025f, 0.850f, "160 15");
            // Draw the wind direction arrow
            gl.glPushMatrix();
            gl.glTranslatef(0.068f, 0.813f, 0);
            gl.glRotated(160, 0, 0, 1.0);

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
//            m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "--- --");
            Font.display(glAutoDrawable, 0.025f, 0.850f, "--- --");

        CircleEvaluator ce = new CircleEvaluator(gl);
        // Draw the range indicators
        gl.glColor3ubv(CLWhite);
//        m_GLEnv->font()->print(0.360f, 0.335f, 0.05f, 0.05f, false, "80");
//        m_GLEnv->font()->print(0.240f, 0.215f, 0.05f, 0.05f, false, "160");
        Font.display(glAutoDrawable, 0.360f, 0.335f, "80");
        Font.display(glAutoDrawable, 0.240f, 0.215f, "160");
        // Draw inner circle
        gl.glColor3ubv(CLWhite);
//        m_GLEnv->paintCircle(0.480, 0.467, 0.179, 32);
        ce.setOrigin(0.480, 0.467);
        ce.setRadius(0.179);
//        ce.setArcStartEnd(-60, 60);
        ce.setDegreesPerPoint(32);
        ce.evaluate();
        // Paint the outer scale
//        m_GLEnv->paintCircle(0.480, 0.467, 0.350, 36);
        ce.setOrigin(0.480, 0.467);
        ce.setRadius(0.350);
//        ce.setArcStartEnd(-60, 60);
        ce.setDegreesPerPoint(36);
        ce.evaluate();
        int i;
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
//        m_GLEnv->font()->print(-0.0125f, 0.269f, 0.05f, 0.05f, false, "N");
        Font.display(glAutoDrawable, -0.0125f, 0.269f, "N");
//        m_GLEnv->font()->print(-0.0125f,-0.320f, 0.05f, 0.05f, false, "S");
        Font.display(glAutoDrawable, -0.0125f, -0.320f, "S");
//        m_GLEnv->font()->print(-0.313f, -0.028f, 0.05f, 0.05f, false, "W");
        Font.display(glAutoDrawable, -0.313f, -0.028f, "W");
//        m_GLEnv->font()->print( 0.288f, -0.028f, 0.05f, 0.05f, false, "E");
        Font.display(glAutoDrawable, 0.288f, -0.028f, "E");

        for (i = 0; i < 4; ++i) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2f(-0.015f, 0.320f);
            gl.glVertex2f(0.000f, 0.350f);
            gl.glVertex2f(+0.015f, 0.320f);
            gl.glEnd();

            gl.glRotated(-90.0, 0, 0, 1.0);
        }
        gl.glPopMatrix();

        // Paint aircraft symbol to indicate heading and center
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);
        AircraftSymbol.display(glAutoDrawable);
        gl.glPopMatrix();
    }
}
