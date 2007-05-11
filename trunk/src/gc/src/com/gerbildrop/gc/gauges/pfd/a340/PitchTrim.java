package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class PitchTrim {//extends BaseGauge {

    public PitchTrim() {
//        super(100, 100, 1000, 1000, 4, 4, 0, 0, 1);
//        m_vJam       = diom_loadVariable("aerosurface.pitchtrim.jam");
//        m_vAngle     = diom_loadVariable("aerosurface.pitchtrim.angle");
//        m_vLowBlue   = diom_loadVariable("aerosurface.pitchtrim.lowblue");
//        m_vLowYellow = diom_loadVariable("aerosurface.pitchtrim.lowyellow");
    }

    boolean m_vJam = false;
    double m_vAngle = 0;
    boolean m_vLowBlue = false;
    boolean m_vLowYellow = false;

    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        boolean bJam = m_vJam;
        double fAngle = m_vAngle;
        boolean bLowBlue = m_vLowBlue;
        boolean bLowYellow = m_vLowYellow;

        // Pitch trim
        if (bJam) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLWhite);
        Font.display(glAutoDrawable, 0.324f, 0.494f, "PITCH TRIM");


        if (bLowBlue && bLowYellow) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.409f, 0.447f, Math.abs(fAngle) + "\220");
        if (fAngle < 0.0)
            Font.display(glAutoDrawable, 0.509f, 0.447f, "DOWN");
        else
            Font.display(glAutoDrawable, 0.509f, 0.447f, " UP ");

        gl.glColor3ubv(BaseGauge.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.626f, 0.498f, 0);
        gl.glVertex3f(0.676f, 0.498f, 0);
        gl.glVertex3f(0.676f, 0.544f, 0);
        gl.glVertex3f(0.626f, 0.544f, 0);
        gl.glEnd();
        if (bLowBlue) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.626f, 0.494f, "B");
        if (bLowYellow) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.651f, 0.494f, "Y");
    }
}