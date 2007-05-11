/**
 * from JOUVIE ... need to add comments and props for him.
 *
 * This class was setup instead of using his class b/c the code would have had to be rewritten
 * to handle GLAutoDrawable rather than just the GLDrawable
 */
package com.gerbildrop.gc.util;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class ProjectionUtil {
    /**
     * Switch to orthographic projection<BR> The current projection and modelview matrix are saved (push). You can loads
     * projection and modelview matrices used before this call with :<BR> Utils.loadMatrix
     *
     * @param glDrawable
     *
     * @see Utils#loadMatrix(javax.media.opengl.GLAutoDrawable)
     */
    public static void switchToOrthoProjection(GLAutoDrawable glDrawable) {
        final GL gl = glDrawable.getGL();

        /*
         * We save the current projection matrix and we define a viewing volume
         * in the orthographic mode.
         * Projection matrix stack defines how the scene is projected to the screen.
         */
        gl.glMatrixMode(GL.GL_PROJECTION);              //select the Projection matrix
        gl.glPushMatrix();                              //save the current projection matrix
        gl.glLoadIdentity();                            //reset the current projection matrix to creates a new Orthographic projection
        //Creates a new orthographic viewing volume

        gl.glOrtho(0, 500, 0, 300, -1, 1);

        /*
         * Select, save and reset the modelview matrix.
         * Modelview matrix stack store transformation like translation, rotation ...
         */
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
    }

    /**
     * Load projection and modelview matrices previously saved by the method :<BR> switchToOrthoProjection
     *
     * @see Utils#switchToOrthoProjection(GLAutoDrawable)
     */
    public static void loadMatrix(GLAutoDrawable glDrawable) {
        GL gl = glDrawable.getGL();

        //Select the Projection matrix stack
        gl.glMatrixMode(GL.GL_PROJECTION);
        //Load the previous Projection matrix (Generaly, it is a Perspective projection)
        gl.glPopMatrix();

        //Select the Modelview matrix stack
        gl.glMatrixMode(GL.GL_MODELVIEW);
        //Load the previous Modelview matrix
        gl.glPopMatrix();
    }
}
