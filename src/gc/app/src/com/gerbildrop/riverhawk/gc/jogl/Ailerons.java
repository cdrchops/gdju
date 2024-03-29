package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;

public class Ailerons {
    double m_vLeftPos1 = 0;
    double m_vLeftPos2 = 0;
    boolean m_vLeftFail1 = false;
    boolean m_vLeftFail2 = false;
    double m_vLeftNeutral = 0;
    double m_vLeftDrooped = 0;

    double m_vRightPos1 = 0;
    double m_vRightPos2 = 0;
    boolean m_vRightFail1 = false;
    boolean m_vRightFail2 = false;
    double m_vRightNeutral = 0;
    double m_vRightDrooped = 0;

    public void display() {
//        super.display(glAutoDrawable);
        paintLeft(Configuration.getGL());
        paintRight(Configuration.getGL());
    }

    void paintLeft(GL gl) {
        double fPos1 = m_vLeftPos1;
        double fPos2 = m_vLeftPos2;
        boolean bFail1 = m_vLeftFail1;
        boolean bFail2 = m_vLeftFail2;
        double fNeutral = m_vLeftNeutral;
        double fDrooped = m_vLeftDrooped;

        // Left aileron indication
        gl.glColor3ubv(BaseComponent.CLWhite);

        Font.display(0.011f, 0.724f, " L ");

        Font.display(0.011f, 0.689f, "AIL");
        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.035f, 0.557f, 0);
        gl.glVertex3f(0.085f, 0.557f, 0);
        gl.glVertex3f(0.085f, 0.603f, 0);
        gl.glVertex3f(0.035f, 0.603f, 0);
        gl.glEnd();
        gl.glColor3ubv(BaseComponent.CLGreen);

        Font.display(0.035f, 0.553f, "YG");
        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.163f, 0.557f, 0);
        gl.glVertex3f(0.213f, 0.557f, 0);
        gl.glVertex3f(0.213f, 0.603f, 0);
        gl.glVertex3f(0.163f, 0.603f, 0);
        gl.glEnd();
        gl.glColor3ubv(BaseComponent.CLGreen);

        Font.display(0.163f, 0.553f, "GB");
        gl.glPushMatrix();
        gl.glTranslatef(0.125f, 0.654f, 0);
        gl.glScaled(0.2, 0.2, 1);
        paintAileronIndication(fPos1, fPos2, bFail1, bFail2, fNeutral, fDrooped);
        gl.glPopMatrix();
    }


    void paintRight(GL gl) {

        double fPos1 = m_vRightPos1;
        double fPos2 = m_vRightPos2;
        boolean bFail1 = m_vRightFail1;
        boolean bFail2 = m_vRightFail2;
        double fNeutral = m_vRightNeutral;
        double fDrooped = m_vRightDrooped;

        // Right Aileron indication
        gl.glColor3ubv(BaseComponent.CLWhite);
        Font.display(0.914f, 0.724f, " R ");
        Font.display(0.914f, 0.689f, "AIL");
        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.782f, 0.557f, 0);
        gl.glVertex3f(0.832f, 0.557f, 0);
        gl.glVertex3f(0.832f, 0.603f, 0);
        gl.glVertex3f(0.782f, 0.603f, 0);
        gl.glEnd();
        gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.782f, 0.553f, "GB");
        gl.glColor3ubv(BaseComponent.CLGrey);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.911f, 0.557f, 0);
        gl.glVertex3f(0.961f, 0.557f, 0);
        gl.glVertex3f(0.961f, 0.603f, 0);
        gl.glVertex3f(0.911f, 0.603f, 0);
        gl.glEnd();
        gl.glColor3ubv(BaseComponent.CLGreen);
        Font.display(0.911f, 0.553f, "YG");
        gl.glPushMatrix();
        gl.glTranslatef(0.872f, 0.654f, 0);
        gl.glScaled(0.2, 0.2, 1);
        gl.glRotated(180, 0, 1, 0);
        paintAileronIndication(fPos1, fPos2, bFail1, bFail2, fNeutral, fDrooped);
        gl.glPopMatrix();
    }


    void paintAileronIndication(double fPos1, double fPos2, boolean bFail1, boolean bFail2, double fNeutral, double fDrooped) {
        GL gl = Configuration.getGL();
        // Aileron scale
        gl.glColor3ubv(BaseComponent.CLWhite);
//            setPaintWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, +0.500f, 0);
        gl.glVertex3f(0, +0.420f, 0);
        gl.glEnd();
//            setPaintWidth(1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, +0.420f, 0);
        gl.glVertex3f(0, -0.420f, 0);
        gl.glEnd();
//            setPaintWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0, -0.420f, 0);
        gl.glVertex3f(0, -0.500f, 0);
        gl.glEnd();
//            setPaintWidth(1);

        // Aileron 1 position
        if (bFail1) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        gl.glPushMatrix();
        gl.glTranslatef(0, (float) fPos1 / 200.0f, 0);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(+0.042f, 0.000f, 0);
        gl.glVertex3f(+0.125f, +0.075f, 0);
        gl.glVertex3f(+0.125f, -0.075f, 0);
        gl.glEnd();
        gl.glPopMatrix();

        // Aileron 2 position
        if (bFail2) gl.glColor3ubv(BaseComponent.CLAmber);
        else gl.glColor3ubv(BaseComponent.CLGreen);
        gl.glPushMatrix();
        gl.glTranslatef(0, (float) fPos2 / 200.0f, 0);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex3f(-0.042f, 0.000f, 0);
        gl.glVertex3f(-0.125f, +0.075f, 0);
        gl.glVertex3f(-0.125f, -0.075f, 0);
        gl.glEnd();
        gl.glPopMatrix();

        // Neutral position
        gl.glColor3ubv(BaseComponent.CLWhite);
        gl.glPushMatrix();
        gl.glTranslatef(0, (float) fNeutral / 200.0f, 0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(+0.030f, -0.025f, 0);
        gl.glVertex3f(-0.030f, -0.025f, 0);

        gl.glVertex3f(+0.030f, +0.025f, 0);
        gl.glVertex3f(-0.030f, +0.025f, 0);
        gl.glEnd();
        gl.glPopMatrix();

        // Drooped position
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, (float) fDrooped / 200.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-0.030f, +0.030f, 0.0f);
        gl.glVertex3f(+0.030f, +0.030f, 0.0f);
        gl.glVertex3f(+0.030f, -0.030f, 0.0f);
        gl.glVertex3f(-0.030f, -0.030f, 0.0f);
        gl.glEnd();
        gl.glPopMatrix();
    }
}