/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.symbol;

import com.gerbildrop.riverhawk.base.Configuration;

import javax.media.opengl.GL;


/**
 * @author torr
 * @since Nov 20, 2007 - 2:58:06 PM
 */
public class AircraftSymbol {
    public static void display() {
        GL gl = Configuration.getGL();
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