package com.gerbildrop.gc.fonts;

//import com.sun.opengl.util.GLUT;

import com.jogamp.opengl.util.gl2.GLUT;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

//todo: fix to use the freetype library
public class Font {
    private static int font = GLUT.BITMAP_HELVETICA_12;

    public static void display(GLAutoDrawable glAutoDrawable, double x, double y, String display) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLUT glut = new GLUT();

        float m_Sizex = 1.0f;
        float m_Sizey = 1.0f;

        // Save the modelview matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();

        // Set position and size
        gl.glTranslated(x, y, 0);
        gl.glScaled(0.0135 * m_Sizex, 0.0135 * m_Sizey, 1);

        // Draw using the triangulated font
        gl.glRasterPos3d(x, y, 0);

        glut.glutBitmapString(font, display);

        // Restore modelview matrix
        gl.glPopMatrix();
    }

    public static void setSize(int size) {
        switch (size) {
            case 1:
                font = GLUT.BITMAP_HELVETICA_10;
                break;
            case 2:
                font = GLUT.BITMAP_HELVETICA_12;
                break;
            case 3:
                font = GLUT.BITMAP_HELVETICA_18;
                break;
        }
    }
}