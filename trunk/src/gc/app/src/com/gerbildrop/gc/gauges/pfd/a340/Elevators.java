package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Elevators {//extends BaseGauge{
    double m_vLeftPos = 0;
    boolean m_vLeftFail = false;
    boolean m_vLeftLowBlue = true;
    boolean m_vLeftLowGreen = true;

    double m_vRightPos = 0;
    boolean m_vRightFail = true;
    boolean m_vRightLowGreen = true;
    boolean m_vRightLowYellow = true;

    public Elevators() {
//        super(100, 100, 1000, 1000, 4, 4, 0, 0, 1);
//        m_vLeftPos        = diom_loadVariable("aerosurface.left.elevator.position");
//        m_vLeftFail       = diom_loadVariable("aerosurface.left.elevator.actuatorfail");
//        m_vLeftLowBlue    = diom_loadVariable("aerosurface.left.elevator.lowblue");
//        m_vLeftLowGreen   = diom_loadVariable("aerosurface.left.elevator.lowgreen");
//
//        m_vRightPos       = diom_loadVariable("aerosurface.right.elevator.position");
//        m_vRightFail      = diom_loadVariable("aerosurface.right.elevator.actuatorfail");
//        m_vRightLowGreen  = diom_loadVariable("aerosurface.right.elevator.lowgreen");
//        m_vRightLowYellow = diom_loadVariable("aerosurface.right.elevator.lowyellow");
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        paintLeft(glAutoDrawable);
        paintRight(glAutoDrawable);
    }


    void paintLeft(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        double fPos = m_vLeftPos;
        boolean bFail = m_vLeftFail;
        boolean bLowBlue = m_vLeftLowBlue;
        boolean bLowGreen = m_vLeftLowGreen;

        // Left elevator indication
        gl.glColor3ubv(BaseGauge.CLWhite);
        Font.display(glAutoDrawable, 0.131f, 0.444f, "L");
        Font.display(glAutoDrawable, 0.093f, 0.409f, "ELEV");


        gl.glColor3ubv(BaseGauge.CLGrey);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(0.117f, 0.296f, 0);
        gl.glVertex3f(0.167f, 0.296f, 0);
        gl.glVertex3f(0.167f, 0.342f, 0);
        gl.glVertex3f(0.117f, 0.342f, 0);
        gl.glEnd();
        if (bLowBlue) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);

        Font.display(glAutoDrawable, 0.117f, 0.292f, "B");
        if (bLowGreen) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.142f, 0.292f, "G");

        gl.glPushMatrix();
        gl.glTranslatef(0.249f, 0.350f, 0);
        gl.glScaled(0.2, 0.2, 1);
        paintElevatorIndication(glAutoDrawable, fPos, bFail);
        gl.glPopMatrix();
    }


    void paintRight(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        double fPos = m_vRightPos;
        boolean bFail = m_vRightFail;
        boolean bLowGreen = m_vRightLowGreen;
        boolean bLowYellow = m_vRightLowYellow;

        // Right elevator indication
        gl.glColor3ubv(BaseGauge.CLWhite);
        Font.display(glAutoDrawable, 0.845f, 0.444f, "R");
        Font.display(glAutoDrawable, 0.807f, 0.409f, "ELEV");

        gl.glColor3ubv(BaseGauge.CLGrey);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(0.825f, 0.296f, 0);
        gl.glVertex3f(0.875f, 0.296f, 0);
        gl.glVertex3f(0.875f, 0.342f, 0);
        gl.glVertex3f(0.825f, 0.342f, 0);
        gl.glEnd();
        if (bLowGreen) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.825f, 0.292f, "G");
        if (bLowYellow) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.850f, 0.292f, "Y");

        gl.glPushMatrix();
        gl.glTranslatef(0.749f, 0.350f, 0);
        gl.glScaled(0.2, 0.2, 1);
        gl.glRotated(180, 0, 1, 0);
        paintElevatorIndication(glAutoDrawable, fPos, bFail);
        gl.glPopMatrix();
    }


    void paintElevatorIndication(GLAutoDrawable glAutoDrawable, double fPos, boolean bFail) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        // Elevator scale
        gl.glColor3ubv(BaseGauge.CLWhite);
//        setPaintWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, +0.500f, 0);
        gl.glVertex3f(0.000f, +0.420f, 0);
        gl.glEnd();
//        setPaintWidth(1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, +0.420f, 0);
        gl.glVertex3f(0.000f, -0.420f, 0);
        gl.glEnd();
//        setPaintWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.000f, -0.420f, 0);
        gl.glVertex3f(0.000f, -0.500f, 0);
        gl.glEnd();
//        setPaintWidth(1);

        if (bFail) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        gl.glTranslatef(0f, (float) fPos / 200.0f, 0);
        // Elevator position
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(+0.042f, 0.000f, 0);
        gl.glVertex3f(+0.125f, +0.075f, 0);
        gl.glVertex3f(+0.125f, -0.075f, 0);
        gl.glEnd();
    }
}
