package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;

public class Elevators {
    double m_vLeftPos = 0;
    boolean m_vLeftFail = false;
    boolean m_vLeftLowBlue = true;
    boolean m_vLeftLowGreen = true;

    double m_vRightPos = 0;
    boolean m_vRightFail = true;
    boolean m_vRightLowGreen = true;
    boolean m_vRightLowYellow = true;

    public void display() {
        paintLeft();
        paintRight();
    }


    void paintLeft() {
        GL gl = Configuration.getGL();
        double fPos = m_vLeftPos;
        boolean bFail = m_vLeftFail;
        boolean bLowBlue = m_vLeftLowBlue;
        boolean bLowGreen = m_vLeftLowGreen;

        // Left elevator indication
        gl.glColor3ubv(BaseComponent.CLWhite);
        Font.display(0.131f, 0.444f, "L");
        Font.display(0.093f, 0.409f, "ELEV");


        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.117f, 0.296f, 0);
        gl.glVertex3f(0.167f, 0.296f, 0);
        gl.glVertex3f(0.167f, 0.342f, 0);
        gl.glVertex3f(0.117f, 0.342f, 0);
        gl.glEnd();
        if (bLowBlue) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);

        Font.display(0.117f, 0.292f, "B");
        if (bLowGreen) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.142f, 0.292f, "G");

        gl.glPushMatrix();
        gl.glTranslatef(0.249f, 0.350f, 0);
        gl.glScaled(0.2, 0.2, 1);
        paintElevatorIndication(fPos, bFail);
        gl.glPopMatrix();
    }


    void paintRight() {
        GL gl = Configuration.getGL();

        double fPos = m_vRightPos;
        boolean bFail = m_vRightFail;
        boolean bLowGreen = m_vRightLowGreen;
        boolean bLowYellow = m_vRightLowYellow;

        // Right elevator indication
        gl.glColor3ubv(BaseComponent.CLWhite);
        Font.display(0.845f, 0.444f, "R");
        Font.display(0.807f, 0.409f, "ELEV");

        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.825f, 0.296f, 0);
        gl.glVertex3f(0.875f, 0.296f, 0);
        gl.glVertex3f(0.875f, 0.342f, 0);
        gl.glVertex3f(0.825f, 0.342f, 0);
        gl.glEnd();
        if (bLowGreen) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.825f, 0.292f, "G");
        if (bLowYellow) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.850f, 0.292f, "Y");

        gl.glPushMatrix();
        gl.glTranslatef(0.749f, 0.350f, 0);
        gl.glScaled(0.2, 0.2, 1);
        gl.glRotated(180, 0, 1, 0);
        paintElevatorIndication(fPos, bFail);
        gl.glPopMatrix();
    }


    void paintElevatorIndication(double fPos, boolean bFail) {
        GL gl = Configuration.getGL();
        // Elevator scale
        gl.glColor3ubv(BaseComponent.CLWhite);
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

        if (bFail) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        gl.glTranslatef(0f, (float) fPos / 200.0f, 0);
        // Elevator position
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(+0.042f, 0.000f, 0);
        gl.glVertex3f(+0.125f, +0.075f, 0);
        gl.glVertex3f(+0.125f, -0.075f, 0);
        gl.glEnd();
    }
}