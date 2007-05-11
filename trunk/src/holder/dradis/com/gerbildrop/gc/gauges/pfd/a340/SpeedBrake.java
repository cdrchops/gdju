package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class SpeedBrake {// extends BaseGauge {
    boolean m_vLowGreen = true;
    boolean m_vLowBlue = true;
    boolean m_vLowYellow = true;

    public SpeedBrake() {
//        super(100, 100, 100, 100, 4, 4, 0, 0, 1);
//        m_vLowGreen  = diom_loadVariable("aerosurface.speedbrake.lowgreen");
//        m_vLowBlue   = diom_loadVariable("aerosurface.speedbrake.lowblue");
//        m_vLowYellow = diom_loadVariable("aerosurface.speedbrake.lowyellow");
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
//        super.display(glAutoDrawable);
        boolean bGreen = m_vLowGreen;
        boolean bBlue = m_vLowBlue;
        boolean bYellow = m_vLowYellow;

        /* Paint the Speed Brake label */
        gl.glColor3ubv(BaseGauge.CLWhite);

        Font.display(glAutoDrawable, 0.410f, 0.755f, "SPD BRK");

        /* Paint the speedbrake indication */
        paintSpeedbrakeIndication(glAutoDrawable, bGreen, bBlue, bYellow);
    }


    void paintSpeedbrakeIndication(GLAutoDrawable glAutoDrawable, boolean bLowGreenHyd, boolean bLowBlueHyd, boolean bLowYellowHyd) {
        GL gl = glAutoDrawable.getGL();
        /* Paint the speedbrake indication */
        // Paint the grey box behind the indication
        gl.glColor3ubv(BaseGauge.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.461f, 0.903f, 0);
        gl.glVertex3f(0.536f, 0.903f, 0);
        gl.glVertex3f(0.536f, 0.949f, 0);
        gl.glVertex3f(0.461f, 0.949f, 0);
        gl.glEnd();

        // Paint the speedbrake green hydrolic indicator
        if (bLowGreenHyd) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.461f, 0.899f, "G");

        // Paint the speedbrake blue hydrolic indicator
        if (bLowBlueHyd) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.486f, 0.899f, "B");

        // Paint the speedbrake yellow hydrolic indicator
        if (bLowYellowHyd) gl.glColor3ubv(BaseGauge.CLAmber);
        else gl.glColor3ubv(BaseGauge.CLGreen);
        Font.display(glAutoDrawable, 0.511f, 0.899f, "Y");

    }

}
