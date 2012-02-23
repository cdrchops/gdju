package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Computers {// extends BaseGauge {
    boolean m_vComputerPrim1 = true;
    boolean m_vComputerPrim2 = true;
    boolean m_vComputerPrim3 = true;
    boolean m_vComputerSec1 = true;
    boolean m_vComputerSec2 = true;

    public Computers() {
//        super(100, 100, 1000, 1000, 4, 4, 0, 0, 1);
//        m_vComputerPrim1 = diom_loadVariable("fmgc.primary[1].failure");
//        m_vComputerPrim2 = diom_loadVariable("fmgc.primary[2].failure");
//        m_vComputerPrim3 = diom_loadVariable("fmgc.primary[3].failure");
//        m_vComputerSec1  = diom_loadVariable("fmgc.secundary[1].failure");
//        m_vComputerSec2  = diom_loadVariable("fmgc.secundary[2].failure");
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        boolean bPrim1 = m_vComputerPrim1;
        boolean bPrim2 = m_vComputerPrim2;
        boolean bPrim3 = m_vComputerPrim3;
        boolean bSec1 = m_vComputerSec1;
        boolean bSec2 = m_vComputerSec2;
        GL2 gl = glAutoDrawable.getGL().getGL2();
        /* Paint PRIM SEC labels */
        gl.glColor3ubv(BaseGauge.CLWhite);
        Font.display(glAutoDrawable, 0.233f, 0.684f, "PRIM");
        Font.display(glAutoDrawable, 0.541f, 0.683f, "SEC");

        /* Paint primary computer 1 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.233f, 0.677f, 0);
        paintComputersIndication(glAutoDrawable, bPrim1, "1");
        gl.glPopMatrix();

        /* Paint primary computer 2 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.280f, 0.646f, 0);
        paintComputersIndication(glAutoDrawable, bPrim2, "2");
        gl.glPopMatrix();

        /* Paint primary computer 3 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.327f, 0.615f, 0);
        paintComputersIndication(glAutoDrawable, bPrim3, "3");
        gl.glPopMatrix();

        /* Paint secondary computer 1 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.514f, 0.677f, 0);
        paintComputersIndication(glAutoDrawable, bSec1, "1");
        gl.glPopMatrix();

        /* Paint secondary computer 2 indication */
        gl.glPushMatrix();
        gl.glTranslatef(0.560f, 0.646f, 0);
        paintComputersIndication(glAutoDrawable, bSec2, "2");
        gl.glPopMatrix();
    }


    void paintComputersIndication(GLAutoDrawable glAutoDrawable, boolean bFailure, final String sLabel) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        /* Paint the box around the computer indication */
        // Paint in amber for failed and in grey for working computer
        if (bFailure) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGrey);
        // Paint box around indication
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.141f, 0.063f, 0);
        gl.glVertex3f(0.156f, 0.063f, 0);
        gl.glVertex3f(0.156f, 0.000f, 0);
        gl.glVertex3f(0.000f, 0.000f, 0);
        gl.glEnd();

        /* Print the number of the computer */
        // Paint in green for working computer
        if (!bFailure) gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.119f, 0.006f, sLabel);
    }
}