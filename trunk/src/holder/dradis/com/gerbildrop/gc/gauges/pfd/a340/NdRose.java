package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.engine.util.StringUtil;
import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.gc.symbol.AircraftSymbol;
import com.gerbildrop.gc.util.CircleEvaluator;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class NdRose extends BaseGauge {
    public NdRose() {
        super(0, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        gl.glScalef(100, 100, 0);
        while (m_fHeading < 0.0f) m_fHeading += 360.0f;

        // Draw the ground and true airspeed
        gl.glColor3bv(CLWhite);
        Font.display(glAutoDrawable, 0.025, 0.900, "GS     TAS");
        gl.glColor3bv(CLGreen);
        Font.display(glAutoDrawable, 0.1, 0.900, m_fGS + "     " + m_fTAS);
        // Draw the wind direction and speed
        gl.glColor3bv(CLWhite);
        Font.display(glAutoDrawable, 0.1, 0.850, "/");
        gl.glColor3bv(CLGreen);
        if (!m_bWindAvailable) {
            Font.display(glAutoDrawable, 0.025, 0.850, "160 15");
            // Draw the wind direction arrow
            gl.glColor3ubv(CLGreen);
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
            Font.display(glAutoDrawable, 0.025, 0.850, "--- --");

        // Draw the true north reference message
        gl.glColor3bv(CLBlue);
        Font.display(glAutoDrawable, 0.4295, 0.900, "TRUE");

        // Draw the range indicators
        gl.glColor3bv(CLWhite);
        Font.display(glAutoDrawable, 0.360, 0.335, m_iND_Range / 2 + "");
        Font.display(glAutoDrawable, 0.240, 0.215, m_iND_Range + "");

        // Draw inner circle composed of 32 segments
        gl.glColor3ubv(CLWhite);
        double fStep = 6.28318530717958647692528676655901 / (float) 64;
        double fAngle = 0;
        int i;
        for (i = 0; i < 32; ++i) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(0.179 * Math.sin(fAngle) + 0.480, 0.179 * Math.cos(fAngle) + 0.467);
            fAngle += fStep;
            gl.glVertex2d(0.179 * Math.sin(fAngle) + 0.480, 0.179 * Math.cos(fAngle) + 0.467);
            fAngle += fStep;
            gl.glEnd();
        }

        // Paint the outer scale
        CircleEvaluator ce = new CircleEvaluator(gl);
        ce.SetOrigin(0.480, 0.467);
        ce.SetRadius(0.350);
        ce.SetDegreesPerPoint(36);
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);
        for (i = 0; i < 72; ++i) {
            if (i % 9 == 6) {
                gl.glBegin(GL.GL_TRIANGLES);
                gl.glVertex2f(-0.015f, 0.380f);
                gl.glVertex2f(0.000f, 0.350f);
                gl.glVertex2f(0.015f, 0.380f);
                gl.glEnd();
            } else {
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2f(0.000f, 0.350f);
                gl.glVertex2f(0.000f, /*(i & 1) ? */0.380f /*: 0.365f*/);
                gl.glEnd();
            }

            // Paint the labels in 30 degrees increments
            if (i % 6 == 0) {
                int iAngle = i / 2;
                /* TODO Use font that is 20% larger */
                Font.display(glAutoDrawable, iAngle > 9 ? -0.03 : -0.015, 0.375, iAngle + "");
            }
            gl.glRotated(-5.0, 0, 0, 1.0);
        }
        gl.glPopMatrix();

        // Rotate rose for current heading
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);

        // Paint the selected heading / track
        gl.glColor3ubv(CLBlue);
        gl.glPushMatrix();
        gl.glRotated(m_fAutopilotHeading, 0, 0, -1.0);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.000f, 0.350f);
        gl.glVertex2f(-0.022f, 0.384f);
        gl.glVertex2f(0.022f, 0.384f);
        gl.glEnd();
        gl.glPopMatrix();

        // paint either the VOR1 or ADF1 bearing needle
        if (!StringUtil.isEqual(m_sNavSelect1, "ADF")) {
            if (m_bADF1_tuned) paintADFNeedle(glAutoDrawable, m_fADF1_bearing);
        } else if (!StringUtil.isEqual(m_sNavSelect1, "VOR")) {
            if (m_bVOR1_tuned) paintVORNeedle(glAutoDrawable, m_fVOR1_bearing);
        }

        // paint either the VOR2 or ADF2 bearing needle
        if (!StringUtil.isEqual(m_sNavSelect2, "ADF")) {
            if (m_bADF2_tuned) paintADFNeedle(glAutoDrawable, m_fADF2_bearing);
        } else if (!StringUtil.isEqual(m_sNavSelect2, "VOR")) {
            if (m_bVOR2_tuned) paintVORNeedle(glAutoDrawable, m_fVOR2_bearing);
        }
        gl.glPopMatrix();

        // Paint the chronometer if active
        if (m_bChronoActve) {
            gl.glColor3ubv(CLGrey);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.000f, 0.210f);
            gl.glVertex2f(0.178f, 0.210f);
            gl.glVertex2f(0.178f, 0.155f);
            gl.glVertex2f(0.000f, 0.155f);
            gl.glEnd();
            gl.glColor3ubv(CLWhite);
            Font.display(glAutoDrawable, 0.000, 0.153, String.valueOf(m_iChronoTimer / 60 + "\'" + m_iChronoTimer % 60 + "\""));
        }

        // Paint the information about either VOR1 or ADF1
        if (!StringUtil.isEqual(m_sNavSelect1, "ADF")) {
            paint_info_ADF1(glAutoDrawable, m_fADF1_frequency);
        } else if (!StringUtil.isEqual(m_sNavSelect1, "VOR")) {
            paint_info_VOR1(glAutoDrawable, m_fVOR1_frequency, m_fDME1_distance);
        }

        // Paint the information about either VOR2 or ADF2
        if (!StringUtil.isEqual(m_sNavSelect2, "ADF")) {
            paint_info_ADF2(glAutoDrawable, m_fADF2_frequency);
        } else if (!StringUtil.isEqual(m_sNavSelect2, "VOR")) {
            paint_info_VOR2(glAutoDrawable, m_fVOR2_frequency, m_fDME2_distance);
        }

        // Paint current heading needle
        gl.glColor3ubv(CLYellow);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(0.476f, 0.840f);
        gl.glVertex2f(0.484f, 0.840f);
        gl.glVertex2f(0.484f, 0.785f);
        gl.glVertex2f(0.476f, 0.785f);
        gl.glEnd();

        // Paint aircraft symbol to indicate heading and center
        gl.glPushMatrix();
        gl.glTranslatef(0.480f, 0.467f, 0);
        gl.glRotated(m_fHeading, 0, 0, 1.0);
        AircraftSymbol.display(glAutoDrawable);

        gl.glPopMatrix();
    }

    private static void paintVORNeedle(GLAutoDrawable glAutoDrawable, double fHeading) {
        // Paint the VOR navaid needle
        gl.glColor3ubv(CLWhite);
        gl.glPushMatrix();
        gl.glRotated(fHeading, 0, 0, -1.0);
        // Paint the line
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.000f, 0.350f);
        gl.glVertex2f(0.000f, 0.277f);
        gl.glVertex2f(0.000f, 0.233f);
        gl.glVertex2f(0.000f, 0.160f);

        gl.glVertex2f(0.000f, -0.160f);
        gl.glVertex2f(0.000f, -0.233f);
        gl.glVertex2f(0.000f, -0.277f);
        gl.glVertex2f(0.000f, -0.350f);
        gl.glEnd();

        // Paint upper arrow
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(+0.000f, 0.299f);
        gl.glVertex2f(-0.022f, 0.255f);
        gl.glVertex2f(+0.022f, 0.255f);
        gl.glEnd();

        // Paint lower arrow
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(+0.000f, -0.233f);
        gl.glVertex2f(-0.022f, -0.277f);
        gl.glVertex2f(+0.022f, -0.277f);
        gl.glEnd();
        gl.glPopMatrix();
    }


    private static void paintADFNeedle(GLAutoDrawable glAutoDrawable, double fHeading) {
        // Paint the ADF navaid needle
        gl.glColor3ubv(CLGreen);
        gl.glPushMatrix();
        gl.glRotated(fHeading, 0, 0, -1.0);
        // Paint the right side of the arrow
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.000f, 0.335f);
        gl.glVertex2f(0.000f, 0.248f);
        gl.glVertex2f(0.030f, 0.226f);
        gl.glVertex2f(0.030f, 0.160f);
        gl.glEnd();
        // Paint the left side of the arrow
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.000f, 0.248f);
        gl.glVertex2f(-0.030f, 0.226f);
        gl.glVertex2f(-0.030f, 0.160f);
        gl.glEnd();

        // Paint the right side of the feather
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.030f, -0.160f);
        gl.glVertex2f(0.030f, -0.233f);
        gl.glVertex2f(0.000f, -0.211f);
        gl.glVertex2f(0.000f, -0.350f);
        gl.glEnd();
        // Paint the left side of the feather
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(-0.030f, -0.160f);
        gl.glVertex2f(-0.030f, -0.233f);
        gl.glVertex2f(0.000f, -0.211f);
        gl.glEnd();
        gl.glPopMatrix();
    }


    private static void paint_info_VOR1(GLAutoDrawable glAutoDrawable, double fVOR_frequency, double fDME_distance) {
        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.039f, 0.121f);
        gl.glVertex2f(0.039f, 0.106f);

        gl.glVertex2f(0.039f, 0.091f);
        gl.glVertex2f(0.039f, 0.055f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.039f, 0.106f);
        gl.glVertex2f(0.054f, 0.091f);
        gl.glVertex2f(0.024f, 0.091f);
        gl.glEnd();

        Font.display(glAutoDrawable, 0.748, 0.082, "VOR1");
        Font.display(glAutoDrawable, 0.748, 0.042, String.valueOf(fVOR_frequency));
        Font.display(glAutoDrawable, 0.748, 0.002, String.valueOf(fDME_distance));
    }


    private static void paint_info_ADF1(GLAutoDrawable glAutoDrawable, double fADF_frequency) {
        gl.glColor3ubv(CLGreen);

        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.039f, 0.082f);
        gl.glVertex2f(0.039f, 0.055f);

        gl.glVertex2f(0.054f, 0.040f);
        gl.glVertex2f(0.054f, 0.012f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.039f, 0.055f);
        gl.glVertex2f(0.024f, 0.040f);
        gl.glVertex2f(0.024f, 0.012f);
        gl.glEnd();

        Font.display(glAutoDrawable, 0.748, 0.042, "ADF1");
        Font.display(glAutoDrawable, 0.748, 0.002, String.valueOf(fADF_frequency));
    }


    private static void paint_info_VOR2(GLAutoDrawable glAutoDrawable, double fVOR_frequency, double fDME_distance) {
        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.926f, 0.136f);
        gl.glVertex2f(0.926f, 0.121f);

        gl.glVertex2f(0.926f, 0.106f);
        gl.glVertex2f(0.926f, 0.070f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.926f, 0.121f);
        gl.glVertex2f(0.941f, 0.106f);
        gl.glVertex2f(0.911f, 0.106f);
        gl.glEnd();

        Font.display(glAutoDrawable, 0.748, 0.082, "VOR2");
        Font.display(glAutoDrawable, 0.748, 0.042, String.valueOf(fVOR_frequency));
        Font.display(glAutoDrawable, 0.748, 0.002, String.valueOf(fDME_distance));
    }


    private static void paint_info_ADF2(GLAutoDrawable glAutoDrawable, double fADF_frequency) {
        gl.glColor3ubv(CLGreen);

        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.926f, 0.082f);
        gl.glVertex2f(0.926f, 0.055f);

        gl.glVertex2f(0.941f, 0.040f);
        gl.glVertex2f(0.941f, 0.012f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.926f, 0.055f);
        gl.glVertex2f(0.911f, 0.040f);
        gl.glVertex2f(0.911f, 0.012f);
        gl.glEnd();
        Font.display(glAutoDrawable, 0.748, 0.042, "ADF2");
        Font.display(glAutoDrawable, 0.748, 0.002, String.valueOf(fADF_frequency));
    }
}