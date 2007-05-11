package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.util.CircleEvaluator;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class Hydraulics extends BaseGauge {
    public Hydraulics() {
        super(0, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        gl.glScalef(100, 100, 0);
        // Paint page title
        gl.glColor3bv(CLWhite);
        PFD.fnt_printf(glAutoDrawable, 0.463, 0.953, "HYD"); /* TODO: underline */

        // Paint the unit of pressure
        PFD.fnt_printf(glAutoDrawable, 0.463, 0.765, "PSI");

        // Paint status area
        StatusArea.display(glAutoDrawable);

        /* Paint Green hydraulic system */
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.243f, 0.792f, 0);
        gl.glVertex3f(0.278f, 0.792f, 0);

        gl.glVertex3f(0.270f, 0.784f, 0);
        gl.glVertex3f(0.286f, 0.800f, 0);

        gl.glVertex3f(0.169f, 0.456f, 0);
        gl.glVertex3f(0.169f, 0.518f, 0);

        gl.glVertex3f(0.169f, 0.629f, 0);
        gl.glVertex3f(0.169f, 0.761f, 0);

        gl.glVertex3f(0.169f, 0.345f, 0);
        gl.glVertex3f(0.278f, 0.345f, 0);

        gl.glVertex3f(0.270f, 0.336f, 0);
        gl.glVertex3f(0.286f, 0.354f, 0);
        gl.glEnd();
        gl.glPushMatrix();
        gl.glTranslatef(0.169f, 0.345f, 0);
        reservoir(glAutoDrawable, 0.4f, resevoir1LowAirPress, resevoir1Overheat);

        valve(resevoir1ValveOpen);

        gl.glColor3bv(CLWhite);
        PFD.fnt_printf(glAutoDrawable, 0.031, 0.075, "1");
        PFD.fnt_printf(glAutoDrawable, -0.063, 0.470, "GREEN");

        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(0.000f, 0.564f, 0);
        gl.glVertex3f(0.014f, 0.540f, 0);
        gl.glVertex3f(-0.014f, 0.540f, 0);
        gl.glEnd();

        gl.glColor3bv(CLGreen);
        PFD.fnt_printf(glAutoDrawable, -0.050, 0.420, "3000");

        gl.glTranslatef(0, 0.173f, 0);
        enginePump(glAutoDrawable, enginePump1On, enginePump1LowPress);

        gl.glTranslatef(0, 0.172f, 0);
        electricPump(glAutoDrawable, electPump1commanded, electPump1On, electPump1LowPress);
        gl.glPopMatrix();

        /* Paint Blue hydraulic system */
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.388f, 0.456f, 0);
        gl.glVertex3f(0.388f, 0.518f, 0);

        gl.glVertex3f(0.388f, 0.629f, 0);
        gl.glVertex3f(0.388f, 0.761f, 0);
        gl.glEnd();
        gl.glPushMatrix();
        gl.glTranslatef(0.388f, 0.345f, 0);
        reservoir(glAutoDrawable, 0.8f, resevoir2LowAirPress, resevoir2Overheat);

        valve(resevoir2ValveOpen);

        gl.glColor3bv(CLWhite);
        PFD.fnt_printf(glAutoDrawable, 0.031, 0.075, "2");
        PFD.fnt_printf(glAutoDrawable, -0.050, 0.470, "BLUE");
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(0.000f, 0.564f, 0);
        gl.glVertex3f(0.014f, 0.540f, 0);
        gl.glVertex3f(-0.014f, 0.540f, 0);
        gl.glEnd();
        gl.glColor3bv(CLGreen);
        PFD.fnt_printf(glAutoDrawable, -0.050, 0.420, "3000");

        gl.glTranslatef(0, 0.173f, 0);
        enginePump(glAutoDrawable, enginePump2On, enginePump2LowPress);

        gl.glTranslatef(0, 0.172f, 0);
        electricPump(glAutoDrawable, electPump2commanded, electPump2On, electPump2LowPress);
        gl.glPopMatrix();

        /* Paint Yellow hydraulic system */
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.607f, 0.456f, 0);
        gl.glVertex3f(0.607f, 0.518f, 0);

        gl.glVertex3f(0.607f, 0.629f, 0);
        gl.glVertex3f(0.607f, 0.761f, 0);
        gl.glEnd();
        gl.glPushMatrix();
        gl.glTranslatef(0.607f, 0.345f, 0);
        reservoir(glAutoDrawable, 0.9f, resevoir3LowAirPress, resevoir3Overheat);

        valve(resevoir3ValveOpen);

        gl.glColor3ubv(CLWhite);
        gl.glColor3bv(CLWhite);
        PFD.fnt_printf(glAutoDrawable, 0.031, 0.075, "3");
        PFD.fnt_printf(glAutoDrawable, -0.075, 0.470, "YELLOW");
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(0.000f, 0.564f, 0);
        gl.glVertex3f(0.014f, 0.540f, 0);
        gl.glVertex3f(-0.014f, 0.540f, 0);
        gl.glEnd();
        gl.glColor3bv(CLGreen);
        PFD.fnt_printf(glAutoDrawable, -0.050, 0.420, "3000");

        gl.glTranslatef(0, 0.173f, 0);
        enginePump(glAutoDrawable, enginePump3On, enginePump3LowPress);

        gl.glTranslatef(0, 0.172f, 0);
        electricPump(glAutoDrawable, electPump3commanded, electPump3On, electPump3LowPress);
        gl.glPopMatrix();

        /* Paint Green backup hydraulic system */
        gl.glColor3ubv(CLGreen);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.729f, 0.784f, 0);
        gl.glVertex3f(0.745f, 0.800f, 0);

        gl.glVertex3f(0.737f, 0.792f, 0);
        gl.glVertex3f(0.812f, 0.792f, 0);

        gl.glVertex3f(0.812f, 0.456f, 0);
        gl.glVertex3f(0.812f, 0.518f, 0);

        gl.glVertex3f(0.812f, 0.629f, 0);
        gl.glVertex3f(0.812f, 0.792f, 0);

        gl.glVertex3f(0.729f, 0.337f, 0);
        gl.glVertex3f(0.745f, 0.353f, 0);

        gl.glVertex3f(0.737f, 0.345f, 0);
        gl.glVertex3f(0.812f, 0.345f, 0);
        gl.glEnd();
        gl.glPushMatrix();
        gl.glTranslatef(0.812f, 0.345f, 0);
        valve(resevoir4ValveOpen);

        gl.glTranslatef(0, 0.173f, 0);
        enginePump(glAutoDrawable, enginePump4On, enginePump4LowPress);

        gl.glTranslatef(0, 0.172f, 0);
        ramAirTurbine(glAutoDrawable, ramAirTurbineStowed, ramAirTurbineStowing, ramAirTurbineOverspeed);

        gl.glColor3bv(CLGreen);
        PFD.fnt_printf(glAutoDrawable, 0.038, -0.075, "5600");
        PFD.fnt_printf(glAutoDrawable, 0.050, -0.125, "RPM");
        gl.glPopMatrix();
    }


    void reservoir(GLAutoDrawable glAutoDrawable, float fLevel, boolean bLowAirPress, boolean bOverheat) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3ubv(CLGreen);
        gl.glVertex3f(0.000f, -0.031f, 0);
        gl.glVertex3f(0.015f, -0.031f, 0);
        gl.glVertex3f(0.015f, -0.055f, 0);
        gl.glVertex3f(0.000f, -0.055f, 0);

        gl.glColor3ubv(CLAmber);
        gl.glVertex3f(0.000f, -0.157f, 0);
        gl.glVertex3f(0.021f, -0.157f, 0);
        gl.glVertex3f(0.021f, -0.188f, 0);
        gl.glVertex3f(0.000f, -0.188f, 0);
        gl.glEnd();

        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3ubv(CLGreen);
        gl.glVertex3f(-0.016f, -0.016f, 0);
        gl.glVertex3f(0.000f, -0.031f, 0);

        gl.glVertex3f(-0.016f, -0.031f, 0);
        gl.glVertex3f(-0.016f, -0.188f, 0);
        gl.glVertex3f(0.000f, -0.188f, 0);

        gl.glColor3ubv(CLWhite);
        gl.glVertex3f(0.000f, -0.031f, 0);

        gl.glColor3ubv(CLGreen);
        gl.glVertex3f(0.000f, 0.000f, 0);
        gl.glEnd();

        gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, -0.188f + (fLevel * 0.172f), 0);
        gl.glVertex3f(0.024f, -0.188f + (fLevel * 0.172f), 0);
        gl.glEnd();

        if (bLowAirPress) {
            gl.glColor3bv(CLWhite);
            PFD.fnt_printf(glAutoDrawable, 0.032, -0.075, "LO AIR");
            PFD.fnt_printf(glAutoDrawable, 0.032, -0.105, "PRESS");
        }

        if (bOverheat) {
            gl.glColor3bv(CLWhite);
            PFD.fnt_printf(glAutoDrawable, 0.032, -0.195, "OVHT");
        }
    }


    void valve(boolean bOpened) {
        if (bOpened) gl.glColor3ubv(CLGreen);
        else gl.glColor3ubv(CLAmber);

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, 0.000f, 0);
        gl.glVertex3f(0.000f, 0.032f, 0);

        if (bOpened) {
            gl.glVertex3f(0.000f, 0.032f, 0);
            gl.glVertex3f(0.000f, 0.079f, 0);
        } else {
            gl.glVertex3f(-0.024f, 0.056f, 0);
            gl.glVertex3f(0.024f, 0.056f, 0);
        }

        gl.glVertex3f(0.000f, 0.079f, 0);
        gl.glVertex3f(0.000f, 0.111f, 0);
        gl.glEnd();
        CircleEvaluator ce = new CircleEvaluator(gl);
        ce.SetOrigin(0.000, 0.056);
        ce.SetRadius(0.024);
        ce.SetDegreesPerPoint(18);
    }


    void enginePump(GLAutoDrawable glAutoDrawable, boolean bOn, boolean bLowPress) {
        if (bOn && !bLowPress) {
            gl.glColor3ubv(CLGreen);
            gl.glColor3bv(CLGreen);
        } else {
            gl.glColor3ubv(CLAmber);
            gl.glColor3bv(CLAmber);
        }

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, 0.000f, 0);
        gl.glVertex3f(0.000f, 0.032f, 0);

        if (bOn) {
            if (!bLowPress) {
                gl.glVertex3f(0.000f, 0.032f, 0);
                gl.glVertex3f(0.000f, 0.079f, 0);
            }
        } else {
            gl.glVertex3f(-0.024f, 0.056f, 0);
            gl.glVertex3f(0.024f, 0.056f, 0);
        }

        gl.glVertex3f(0.000f, 0.079f, 0);
        gl.glVertex3f(0.000f, 0.111f, 0);
        gl.glEnd();

        if (bOn && bLowPress) {
            PFD.fnt_printf(glAutoDrawable, -0.020, 0.036, "LO");
        }

        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(0.024f, 0.032f, 0);
        gl.glVertex3f(-0.024f, 0.032f, 0);
        gl.glVertex3f(-0.024f, 0.079f, 0);
        gl.glVertex3f(0.024f, 0.079f, 0);
        gl.glEnd();
    }


    void electricPump(GLAutoDrawable glAutoDrawable, boolean bCommanded, boolean bOn, boolean bLowPress) {
        if (!bCommanded) {
            gl.glColor3ubv(CLWhite);
        } else {
            if (!bOn) {
                gl.glColor3ubv(CLAmber);
            } else {
                if (bLowPress) gl.glColor3ubv(CLAmber);
                else gl.glColor3ubv(CLGreen);
            }
        }

        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(-0.024f, 0.000f, 0);
        gl.glVertex3f(-0.040f, -0.015f, 0);
        gl.glVertex3f(-0.040f, 0.015f, 0);
        gl.glEnd();

        if (bOn) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(-0.024f, 0.000f, 0);
            gl.glVertex3f(-0.040f, -0.015f, 0);
            gl.glVertex3f(-0.040f, 0.015f, 0);
            gl.glEnd();

            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(-0.024f, 0.000f, 0);
            gl.glVertex3f(0.000f, 0.000f, 0);
            gl.glEnd();
        }

        if (bCommanded && bLowPress) gl.glColor3bv(CLAmber);
        else gl.glColor3bv(CLWhite);
        PFD.fnt_printf(glAutoDrawable, -0.150, -0.025, "ELEC");
    }


    void ramAirTurbine(GLAutoDrawable glAutoDrawable, boolean bStowed, boolean bStowing, boolean bOverspeed) {
        if (bStowed) {
            gl.glColor3ubv(CLWhite);
        } else {
            if (bOverspeed) gl.glColor3ubv(CLAmber);
            else gl.glColor3ubv(CLGreen);
        }
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(0.024f, 0.000f, 0);
        gl.glVertex3f(0.040f, -0.015f, 0);
        gl.glVertex3f(0.040f, 0.015f, 0);
        gl.glEnd();
        if (!bStowed) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(0.024f, 0.000f, 0);
            gl.glVertex3f(0.040f, -0.015f, 0);
            gl.glVertex3f(0.040f, 0.015f, 0);
            gl.glEnd();

            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.024f, 0.000f, 0);
            gl.glVertex3f(0.000f, 0.000f, 0);
            gl.glEnd();
        }

        if (bStowed || bOverspeed) gl.glColor3bv(CLWhite);
        else gl.glColor3bv(CLAmber);
        PFD.fnt_printf(glAutoDrawable, 0.050, -0.025, "RAT");
    }
}