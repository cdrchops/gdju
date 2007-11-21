package com.gerbildrop.gc.gauges.pfd.a340;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.gauges.pfd.BaseGauge;

public class Door extends BaseGauge {
    public Door() {
        super(100, 0, 100, 100, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        gl.glScalef(100, 100, 0);
        // Paint page title
        gl.glColor3bv(BaseGauge.CLWhite);
        PFD.fnt_printf(glAutoDrawable, 0.400, 0.953, "DOOR/OXY"); /* TODO: underline */

        // Paint status area
        StatusArea.display(glAutoDrawable);//, m_GLEnv, this

        // Paint fuselage
        gl.glColor3ubv(BaseGauge.CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex3f(0.436f, 0.222f, 0);
        gl.glVertex3f(0.420f, 0.237f, 0);
        gl.glVertex3f(0.420f, 0.802f, 0);
        gl.glVertex3f(0.432f, 0.856f, 0);
        gl.glVertex3f(0.451f, 0.895f, 0);
        gl.glVertex3f(0.476f, 0.930f, 0);
        gl.glVertex3f(0.500f, 0.940f, 0);
        gl.glVertex3f(0.524f, 0.930f, 0);
        gl.glVertex3f(0.549f, 0.895f, 0);
        gl.glVertex3f(0.568f, 0.856f, 0);
        gl.glVertex3f(0.580f, 0.802f, 0);
        gl.glVertex3f(0.580f, 0.237f, 0);
        gl.glVertex3f(0.564f, 0.222f, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.214f, 0.518f, 0);
        gl.glVertex3f(0.420f, 0.580f, 0);

        gl.glVertex3f(0.580f, 0.580f, 0);
        gl.glVertex3f(0.782f, 0.518f, 0);
        gl.glEnd();

        gl.glPushMatrix();
        paintLeftCabinDoor(glAutoDrawable, lcabinDoor, true);  // 798
        paintRightCabinDoor(glAutoDrawable, rcabinDoor, true); // 798

        gl.glTranslatef(0, -0.156f, 0);
        paintLeftCabinDoor(glAutoDrawable, lcabinDoor2, true);  // 642
        paintRightCabinDoor(glAutoDrawable, rcabinDoor2, true); // 642

        gl.glTranslatef(0, -0.109f, 0);
        paintLeftCabinDoor(glAutoDrawable, lcabinDoor3, true);  // 533
        paintRightCabinDoor(glAutoDrawable, rcabinDoor3, true); // 533

        gl.glTranslatef(0, -0.233f, 0);
        paintLeftCabinDoor(glAutoDrawable, lcabinDoor4, true);  // 300
        paintRightCabinDoor(glAutoDrawable, rcabinDoor4, true); // 300
        gl.glPopMatrix();

        gl.glPushMatrix();
        paintCargoDoor(glAutoDrawable, cargoDoor1); // 720

        gl.glTranslatef(0, -0.265f, 0);
        paintCargoDoor(glAutoDrawable, cargoDoor2); // 455
        gl.glPopMatrix();

        paintBulkDoor(glAutoDrawable, bulkDoor);
        paintAvionicDoor(glAutoDrawable, avionicDoor);
    }


    void paintLeftCabinDoor(GLAutoDrawable glAutoDrawable, boolean bClosed, boolean bArmed) {
        GL gl = glAutoDrawable.getGL();
        if (bClosed) {
            gl.glColor3ubv(BaseGauge.CLGreen);
            gl.glBegin(GL.GL_LINE_LOOP);
        } else {
            gl.glColor3ubv(BaseGauge.CLAmber);
            gl.glBegin(GL.GL_QUADS);
        }
        gl.glVertex3f(0.420f, 0.798f, 0);
        gl.glVertex3f(0.451f, 0.798f, 0);

        gl.glVertex3f(0.451f, 0.751f, 0);
        gl.glVertex3f(0.420f, 0.751f, 0);
        gl.glEnd();

        if (bClosed) {
            if (bArmed) {
                gl.glColor3bv(BaseGauge.CLWhite);
                PFD.fnt_printf(glAutoDrawable, 0.290, 0.745, "SLIDE");
            }
        } else {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.200f, 0.770f, 0);
            gl.glVertex3f(0.410f, 0.770f, 0);
            gl.glEnd();
            PFD.fnt_printf(glAutoDrawable, 0.062, 0.745, "CABIN");
            if (!bArmed) {
                PFD.fnt_printf(glAutoDrawable, 0.290, 0.745, "SLIDE");
            }
        }
    }


    void paintRightCabinDoor(GLAutoDrawable glAutoDrawable, boolean bClosed, boolean bArmed) {
        GL gl = glAutoDrawable.getGL();
        if (bClosed) {
            gl.glColor3ubv(BaseGauge.CLGreen);
            gl.glBegin(GL.GL_LINE_LOOP);
        } else {
            gl.glColor3ubv(BaseGauge.CLAmber);
            gl.glBegin(GL.GL_QUADS);
        }
        gl.glVertex3f(0.580f, 0.798f, 0);
        gl.glVertex3f(0.549f, 0.798f, 0);

        gl.glVertex3f(0.549f, 0.751f, 0);
        gl.glVertex3f(0.580f, 0.751f, 0);
        gl.glEnd();

        if (bClosed) {
            if (bArmed) {
                gl.glColor3bv(BaseGauge.CLWhite);
                PFD.fnt_printf(glAutoDrawable, 0.587, 0.745, "SLIDE");
            }
        } else {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.800f, 0.770f, 0);
            gl.glVertex3f(0.590f, 0.770f, 0);
            gl.glEnd();
            PFD.fnt_printf(glAutoDrawable, 0.813, 0.745, "CABIN");
            if (!bArmed) {
                PFD.fnt_printf(glAutoDrawable, 0.587, 0.745, "SLIDE");
            }
        }
    }


    void paintCargoDoor(GLAutoDrawable glAutoDrawable, boolean bClosed) {
        GL gl = glAutoDrawable.getGL();
        if (bClosed) {
            gl.glColor3ubv(BaseGauge.CLGreen);
            gl.glBegin(GL.GL_LINE_LOOP);
        } else {
            gl.glColor3ubv(BaseGauge.CLAmber);
            gl.glBegin(GL.GL_QUADS);
        }
        gl.glVertex3f(0.580f, 0.720f, 0);
        gl.glVertex3f(0.580f, 0.673f, 0);

        gl.glVertex3f(0.529f, 0.673f, 0);
        gl.glVertex3f(0.529f, 0.720f, 0);
        gl.glEnd();

        if (!bClosed) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.800f, 0.697f, 0);
            gl.glVertex3f(0.590f, 0.697f, 0);
            gl.glEnd();

            PFD.fnt_printf(glAutoDrawable, 0.813f, 0.672f, "CARGO");
        }
    }


    void paintBulkDoor(GLAutoDrawable glAutoDrawable, boolean bClosed) {
        GL gl = glAutoDrawable.getGL();
        if (bClosed) {
            gl.glColor3ubv(BaseGauge.CLGreen);
            gl.glBegin(GL.GL_LINE_LOOP);
        } else {
            gl.glColor3ubv(BaseGauge.CLAmber);
            gl.glBegin(GL.GL_QUADS);
        }
        gl.glVertex3f(0.553f, 0.377f, 0);
        gl.glVertex3f(0.553f, 0.331f, 0);

        gl.glVertex3f(0.529f, 0.331f, 0);
        gl.glVertex3f(0.529f, 0.377f, 0);
        gl.glEnd();

        if (!bClosed) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.800f, 0.354f, 0);
            gl.glVertex3f(0.590f, 0.354f, 0);
            gl.glEnd();
            PFD.fnt_printf(glAutoDrawable, 0.813f, 0.329f, "BULK");
        }
    }


    void paintAvionicDoor(GLAutoDrawable glAutoDrawable, boolean bClosed) {
        GL gl = glAutoDrawable.getGL();
        if (bClosed) {
            gl.glColor3ubv(BaseGauge.CLGreen);
            gl.glBegin(GL.GL_LINE_LOOP);
        } else {
            gl.glColor3ubv(BaseGauge.CLAmber);
            gl.glBegin(GL.GL_QUADS);
        }
        gl.glVertex3f(0.486f, 0.887f, 0);
        gl.glVertex3f(0.486f, 0.829f, 0);

        gl.glVertex3f(0.514f, 0.829f, 0);
        gl.glVertex3f(0.514f, 0.887f, 0);
        gl.glEnd();

        if (!bClosed) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(0.245f, 0.858f, 0);
            gl.glVertex3f(0.480f, 0.858f, 0);
            gl.glEnd();
            PFD.fnt_printf(glAutoDrawable, 0.062f, 0.833f, "AVIONIC");
        }
    }

}
