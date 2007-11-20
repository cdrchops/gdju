package com.gerbildrop.riverhawk.gc;

import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.riverhawk.base.Configuration;

import javax.media.opengl.GL;

public class Spoilers {
//    public Spoilers() {
//        super(100, 100, 1000, 1000, 4, 4, 0, 0, 1);
    //    }
    String sVarName;
    boolean[] m_vSpoilerFaultL = new boolean[6];
    boolean[] m_vSpoilerStateL = new boolean[6];
    boolean[] m_vSpoilerFaultR = new boolean[6];
    boolean[] m_vSpoilerStateR = new boolean[6];

    public Spoilers() {
        for (int iSpoilerNumber = 0; iSpoilerNumber <= 5; ++iSpoilerNumber) {
//            snprintf(sVarName, sizeof(sVarName), "aerosurface.left.spooler[%i].state", iSpoilerNumber);
            m_vSpoilerStateL[iSpoilerNumber] = false;
//            snprintf(sVarName, sizeof(sVarName), "aerosurface.left.spooler[%i].state", iSpoilerNumber);
            m_vSpoilerStateR[iSpoilerNumber] = false;
//            snprintf(sVarName, sizeof(sVarName), "aerosurface.right.spooler[%i].fault", iSpoilerNumber);
            m_vSpoilerFaultL[iSpoilerNumber] = false;
//            snprintf(sVarName, sizeof(sVarName), "aerosurface.right.spooler[%i].fault", iSpoilerNumber);
            m_vSpoilerFaultR[iSpoilerNumber] = false;
        }
    }

    public void display() {
        GL gl = Configuration.getGL();

        int iSpoilerNumber;

        /* Paint the spoilers on the left wing */
        gl.glPushMatrix();
        // move the the most left end of the wing to start painting
        gl.glTranslatef(0.130f, 0.810f, 0);
        // Set the size of the spoiler symbol
        gl.glScaled(0.03, 0.03, 1);
        // Draw all left wing spoiler symbols
        for (iSpoilerNumber = 0; iSpoilerNumber <= 5; ++iSpoilerNumber) {
            boolean bState = m_vSpoilerStateL[iSpoilerNumber];
            boolean bFault = m_vSpoilerFaultL[iSpoilerNumber];

            paintSpoilerIndication(gl, bState, bFault);
            // Move to the right for the next spoiler symbol
            gl.glTranslatef(1.5f, 0.25f, 0);
        }
        gl.glPopMatrix();

        /* Paint the spoilers on the right wing */
        gl.glPushMatrix();
        // move the the most right end of the wing to start painting
        gl.glTranslatef(0.870f, 0.810f, 0);
        // Set the size of the spoiler symbol
        gl.glScaled(0.03, 0.03, 1);
        // Draw all right wing spoiler symbols
        for (iSpoilerNumber = 0; iSpoilerNumber <= 5; ++iSpoilerNumber) {
            boolean bState = m_vSpoilerStateR[iSpoilerNumber];
            boolean bFault = m_vSpoilerFaultR[iSpoilerNumber];

            paintSpoilerIndication(gl, bState, bFault);
            // Move to the left for the next spoiler symbol
            gl.glTranslatef(-1.5f, 0.25f, 0f);
        }
        gl.glPopMatrix();
    }

    void paintSpoilerIndication(GL gl, boolean bDeflected, boolean bFault) {
        if (bFault) {
            // Paint in amber when spoiler is faulty
            gl.glColor3ubv(BaseGauge.CLAmber);
            if (bDeflected) {
                // Paint the Deflected (triangle) symbol
                gl.glBegin(GL.GL_LINE_LOOP);
                gl.glVertex3d(-0.500, -0.500, 0);
                gl.glVertex3d(+0.500, -0.500, 0);
                gl.glVertex3d(0.000, +0.500, 0);
                gl.glEnd();
            } else {
                // Paint the Retracted (Z) sumbol
                gl.glBegin(GL.GL_LINES);
                gl.glVertex3f(-0.500f, -0.500f, 0);
                gl.glVertex3f(+0.500f, -0.500f, 0);
                gl.glEnd();

                gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex3f(+0.400f, -0.400f, 0);
                gl.glVertex3f(-0.400f, -0.400f, 0);
                gl.glVertex3f(+0.400f, +0.400f, 0);
                gl.glVertex3f(-0.400f, +0.400f, 0);
                gl.glEnd();
            }
        } else {
            // Paint in green when spoiler is working fine
            gl.glColor3ubv(BaseGauge.CLGreen);
            if (bDeflected) {
                // Paint the Deflected (triangle) symbol
                gl.glBegin(GL.GL_LINE_LOOP);
                gl.glVertex3d(-0.500, -0.500, 0);
                gl.glVertex3d(+0.500, -0.500, 0);
                gl.glVertex3d(0.000, +0.500, 0);
                gl.glEnd();
            } else {
                // Paint the Retracted (dash) symbol
                gl.glBegin(GL.GL_LINES);
                gl.glVertex3d(-0.500, -0.500, 0);
                gl.glVertex3d(+0.500, -0.500, 0);
                gl.glEnd();
            }
        }
    }
}