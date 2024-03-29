package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;

public class PitchTrim {
    boolean m_vJam = false;
    double m_vAngle = 0;
    boolean m_vLowBlue = false;
    boolean m_vLowYellow = false;

    public void display() {
        GL gl = Configuration.getGL();
//        boolean bJam = ;
//        double fAngle = m_vAngle;
//        boolean bLowBlue = m_vLowBlue;
//        boolean bLowYellow = m_vLowYellow;

        // Pitch trim
        if (m_vJam) {
            gl.glColor3ubv(BaseComponent.CLAmber);
        } else {
            gl.glColor3ubv(BaseComponent.CLWhite);
        }

        Font.display(0.324f, 0.494f, "PITCH TRIM");
        if (m_vLowBlue && m_vLowYellow) {
            gl.glColor3ubv(BaseComponent.CLAmber);
        } else {
            gl.glColor3ubv(BaseComponent.CLGreen);
        }

        Font.display(0.409f, 0.447f, Math.abs(m_vAngle) + "\220");
        if (m_vAngle < 0.0) {
            Font.display(0.509f, 0.447f, "DOWN");
        } else {
            Font.display(0.509f, 0.447f, " UP ");
        }

        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.626f, 0.498f, 0);
        gl.glVertex3f(0.676f, 0.498f, 0);
        gl.glVertex3f(0.676f, 0.544f, 0);
        gl.glVertex3f(0.626f, 0.544f, 0);
        gl.glEnd();

        if (m_vLowBlue) {
            gl.glColor3ubv(BaseComponent.CLAmber);
        } else {
            gl.glColor3ubv(BaseComponent.CLGreen);
        }

        Font.display(0.626f, 0.494f, "B");
        if (m_vLowYellow) {
            gl.glColor3ubv(BaseComponent.CLAmber);
        } else {
            gl.glColor3ubv(BaseComponent.CLGreen);
        }

        Font.display(0.651f, 0.494f, "Y");
    }
}