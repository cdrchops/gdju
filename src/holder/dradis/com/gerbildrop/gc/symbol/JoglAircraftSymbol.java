package com.gerbildrop.gc.symbol;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL;

public class JoglAircraftSymbol {
    public static void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 0);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(-0.004f, +0.043f);
        gl.glVertex2f(+0.004f, +0.043f);
        gl.glVertex2f(+0.004f, -0.062f);
        gl.glVertex2f(-0.004f, -0.062f);

        gl.glVertex2f(-0.048f, +0.004f);
        gl.glVertex2f(+0.048f, +0.004f);
        gl.glVertex2f(+0.048f, -0.004f);
        gl.glVertex2f(-0.048f, -0.004f);

        gl.glVertex2f(-0.017f, -0.039f);
        gl.glVertex2f(+0.017f, -0.039f);
        gl.glVertex2f(+0.017f, -0.047f);
        gl.glVertex2f(-0.017f, -0.047f);
        gl.glEnd();
    }
}