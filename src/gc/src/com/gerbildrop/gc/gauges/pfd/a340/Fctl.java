package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class Fctl extends BaseGauge {
    public Fctl() {
        super(100, 100, 1000, 1000, 4, 4, 0, 0, 1);
    }

    Spoilers m_spoilers = new Spoilers();
    SpeedBrake m_speedbrake = new SpeedBrake();
    Computers m_computers = new Computers();
    PitchTrim m_pitchtrim = new PitchTrim();
    Ailerons m_ailerons = new Ailerons();
    Elevators m_elevators = new Elevators();
    float x = 0;

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        gl.glScalef(100, 100, 0);
        paintPageBackground();

        m_spoilers.display(glAutoDrawable);
        m_speedbrake.display(glAutoDrawable);
        m_computers.display(glAutoDrawable);
        m_pitchtrim.display(glAutoDrawable);
        m_ailerons.display(glAutoDrawable);
        m_elevators.display(glAutoDrawable);

        // Paint page title
        gl.glColor3ubv(CLWhite);
        Font.display(glAutoDrawable, 0.023f, 0.911f, "F/CTL");
        // Paint status area
        StatusArea.display(glAutoDrawable);

        // Rudder indication
        gl.glColor3ubv(CLWhite);

        Font.display(glAutoDrawable, 0.461f, 0.365f, "RUD");
        gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.461f, 0.323f, 0);
        gl.glVertex3f(0.536f, 0.323f, 0);
        gl.glVertex3f(0.536f, 0.369f, 0);
        gl.glVertex3f(0.461f, 0.369f, 0);
        gl.glEnd();
        gl.glColor3ubv(CLGreen);

        Font.display(glAutoDrawable, 0.461f, 0.319f, "GBY");
        gl.glPushMatrix();
        gl.glTranslatef(0.500f, 0.245f, 0);
        gl.glScaled(0.27f, 0.27f, 1);
        paintRudderIndication();
        gl.glPopMatrix();
    }


    void paintPageBackground() {
        // The wind outline is painted in grey
        gl.glColor3ubv(CLGrey);

        /* Left wing outline */
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.086f, 0.840f, 0);
        gl.glVertex3f(0.086f, 0.848f, 0);
        gl.glVertex3f(0.366f, 0.911f, 0);
        gl.glVertex3f(0.366f, 0.902f, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.086f, 0.788f, 0);
        gl.glVertex3f(0.086f, 0.778f, 0);
        gl.glVertex3f(0.420f, 0.825f, 0);
        gl.glVertex3f(0.420f, 0.837f, 0);
        gl.glEnd();

        /* Right wing outline */
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.911f, 0.840f, 0);
        gl.glVertex3f(0.911f, 0.848f, 0);
        gl.glVertex3f(0.630f, 0.911f, 0);
        gl.glVertex3f(0.630f, 0.902f, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.911f, 0.788f, 0);
        gl.glVertex3f(0.911f, 0.778f, 0);
        gl.glVertex3f(0.576f, 0.825f, 0);
        gl.glVertex3f(0.576f, 0.837f, 0);
        gl.glEnd();

        /* Left tail wing outline */
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.366f, 0.451f, 0);
        gl.glVertex3f(0.304f, 0.420f, 0);
        gl.glVertex3f(0.304f, 0.374f, 0);
        gl.glVertex3f(0.428f, 0.389f, 0);
        gl.glEnd();

        /* Right tail wing outline */
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.630f, 0.451f, 0);
        gl.glVertex3f(0.693f, 0.420f, 0);
        gl.glVertex3f(0.693f, 0.374f, 0);
        gl.glVertex3f(0.568f, 0.389f, 0);
        gl.glEnd();
    }


    void paintRudderIndication() {
        int i;
        double a;

        // Rudder scale
        gl.glColor3ubv(CLWhite);
//        setPaintWidth(1);
//        setPaintWidth(3);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (i = 0; i <= 12; ++i) {
            a = 150.0f + i * 5.0f;
            gl.glVertex3f((float) Math.sin(a * 0.01745f), (float) Math.cos(a * 0.01745f) + 0.67f, 0);
            if (i == 1) {
                gl.glEnd();
//                    setPaintWidth(1);
                gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex3f((float) Math.sin(a * 0.01745f), (float) Math.cos(a * 0.01745f) + 0.67f, 0);
            }
            if (i == 11) {
                gl.glEnd();
//                    setPaintWidth(3);
                gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex3f((float) Math.sin(a * 0.01745), (float) Math.cos(a * 0.01745) + 0.67f, 0);
            }
        }
        gl.glEnd();
//        setPaintWidth(1);

        // Left rudder travel limiter
        if ((int) x % 800 < 400) gl.glColor3ubv(CLGreen);
        else gl.glColor3ubv(CLAmber);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(-0.300f, -0.295f, 0);
        gl.glVertex3f(-0.300f, -0.345f, 0);
        gl.glVertex3f(-0.255f, -0.345f, 0);
        gl.glEnd();

        // Rudder center indication
        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(-0.020f, -0.330f, 0);
        gl.glVertex3f(-0.020f, -0.370f, 0);
        gl.glVertex3f(+0.020f, -0.370f, 0);
        gl.glVertex3f(+0.020f, -0.330f, 0);
        gl.glEnd();

        // Right rudder travel limiter
        if ((int) x % 800 < 400) gl.glColor3ubv(CLGreen);
        else gl.glColor3ubv(CLAmber);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(+0.300f, -0.295f, 0);
        gl.glVertex3f(+0.300f, -0.345f, 0);
        gl.glVertex3f(+0.255f, -0.345f, 0);
        gl.glEnd();

        // Rudder position
        if ((int) x % 800 < 400) gl.glColor3ubv(CLGreen);
        else gl.glColor3ubv(CLAmber);
        gl.glPushMatrix();
        gl.glRotated(Math.sin(x / 40) * 30, 0, 0, 1);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0, -0.330f, 0);
        for (a = -120.0; a <= 120.0; a += 10) {
            gl.glVertex3f((float) Math.sin(a * 0.01745) / 15, (float) Math.cos(a * 0.01745) / 15, 0);
        }
        gl.glVertex3f(0.000f, -0.330f, 0);
        gl.glEnd();
        gl.glPopMatrix();

        if ((int) x % 800 < 400) gl.glColor3ubv(CLGreen);
        else gl.glColor3ubv(CLAmber);
        gl.glTranslatef(0, 0.600f, 0);
        gl.glRotated(Math.sin(x / 40) * 11, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, -0.950f, 0);
        gl.glVertex3f(0.000f, -1.050f, 0);
        gl.glEnd();
    }
}