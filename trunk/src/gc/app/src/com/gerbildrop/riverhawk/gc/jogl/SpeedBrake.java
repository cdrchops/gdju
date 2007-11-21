package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;

public class SpeedBrake {
    boolean m_vLowGreen = true;
    boolean m_vLowBlue = true;
    boolean m_vLowYellow = true;

    public void display() {
        GL gl = Configuration.getGL();
//        super.display(glAutoDrawable);
        boolean bGreen = m_vLowGreen;
        boolean bBlue = m_vLowBlue;
        boolean bYellow = m_vLowYellow;

        /* Paint the Speed Brake label */
        gl.glColor3ubv(BaseComponent.CLWhite);

        Font.display(0.410f, 0.755f, "SPD BRK");

        /* Paint the speedbrake indication */
        paintSpeedbrakeIndication(bGreen, bBlue, bYellow);
    }


    void paintSpeedbrakeIndication(boolean bLowGreenHyd, boolean bLowBlueHyd, boolean bLowYellowHyd) {
        GL gl = Configuration.getGL();
        /* Paint the speedbrake indication */
        // Paint the grey box behind the indication
        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.461f, 0.903f, 0);
        gl.glVertex3f(0.536f, 0.903f, 0);
        gl.glVertex3f(0.536f, 0.949f, 0);
        gl.glVertex3f(0.461f, 0.949f, 0);
        gl.glEnd();

        // Paint the speedbrake green hydrolic indicator
        if (bLowGreenHyd) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.461f, 0.899f, "G");

        // Paint the speedbrake blue hydrolic indicator
        if (bLowBlueHyd) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.486f, 0.899f, "B");

        // Paint the speedbrake yellow hydrolic indicator
        if (bLowYellowHyd) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.511f, 0.899f, "Y");

    }

}