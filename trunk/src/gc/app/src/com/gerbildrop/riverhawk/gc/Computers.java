package com.gerbildrop.riverhawk.gc;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;

public class Computers {
    boolean m_vComputerPrim1 = true;
    boolean m_vComputerPrim2 = true;
    boolean m_vComputerPrim3 = true;
    boolean m_vComputerSec1 = true;
    boolean m_vComputerSec2 = true;

    public void display() {
        boolean bPrim1 = m_vComputerPrim1;
        boolean bPrim2 = m_vComputerPrim2;
        boolean bPrim3 = m_vComputerPrim3;
        boolean bSec1 = m_vComputerSec1;
        boolean bSec2 = m_vComputerSec2;
        GL gl = Configuration.getGL();
        /* Paint PRIM SEC labels */
        gl.glColor3ubv(BaseComponent.CLWhite);
        Font.display(0.233f, 0.684f, "PRIM");
        Font.display(0.541f, 0.683f, "SEC");

        /* Paint primary computer 1 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.233f, 0.677f, 0);
        paintComputersIndication(bPrim1, "1");
        gl.glPopMatrix();

        /* Paint primary computer 2 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.280f, 0.646f, 0);
        paintComputersIndication(bPrim2, "2");
        gl.glPopMatrix();

        /* Paint primary computer 3 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.327f, 0.615f, 0);
        paintComputersIndication(bPrim3, "3");
        gl.glPopMatrix();

        /* Paint secondary computer 1 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.514f, 0.677f, 0);
        paintComputersIndication(bSec1, "1");
        gl.glPopMatrix();

        /* Paint secondary computer 2 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.560f, 0.646f, 0);
        paintComputersIndication(bSec2, "2");
        gl.glPopMatrix();
    }


    void paintComputersIndication(boolean bFailure, final String sLabel) {
        GL gl = Configuration.getGL();
        /* Paint the box around the computer indication */
        // Paint in amber for failed and in grey for working computer
        if (bFailure) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGrey);
        // Paint box around indication
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.141f, 0.063f, 0);
        gl.glVertex3f(0.156f, 0.063f, 0);
        gl.glVertex3f(0.156f, 0.000f, 0);
        gl.glVertex3f(0.000f, 0.000f, 0);
        gl.glEnd();

        /* Print the number of the computer */
        // Paint in green for working computer
        if (!bFailure) gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.119f, 0.006f, sLabel);
    }
}