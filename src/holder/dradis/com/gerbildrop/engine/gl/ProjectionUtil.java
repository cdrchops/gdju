package com.gerbildrop.engine.gl;

import javax.media.opengl.GL;

import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class ProjectionUtil {
    /**
     * Switch to orthographic projection<BR> The current projection and modelview matrix are saved (push). You can loads
     * projection and modelview matrices used before this call with :<BR> Utils.loadMatrix
     *
     * @param glDrawable
     *
     * @see Utils#loadMatrix(javax.media.opengl.GLAutoDrawable)
     */
    public static void switchToOrthoProjection() {
        /*
         * We save the current projection matrix and we define a viewing volume
         * in the orthographic mode.
         * Projection matrix stack defines how the scene is projected to the screen.
         */
        glMatrixMode(GL.GL_PROJECTION);              //select the Projection matrix
        glPushMatrix();                              //save the current projection matrix
        glLoadIdentity();                            //reset the current projection matrix to creates a new Orthographic projection
        //Creates a new orthographic viewing volume

        glOrtho(0, 500, 0, 300, -1, 1);

        /*
         * Select, save and reset the modelview matrix.
         * Modelview matrix stack store transformation like translation, rotation ...
         */
        glMatrixMode(GL.GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
    }

    /**
     * Load projection and modelview matrices previously saved by the method :<BR> switchToOrthoProjection
     *
     * @see Utils#switchToOrthoProjection(javax.media.opengl.GLAutoDrawable)
     */
    public static void loadMatrix() {
        //Select the Projection matrix stack
        glMatrixMode(GL.GL_PROJECTION);

        //Load the previous Projection matrix (Generaly, it is a Perspective projection)
        glPopMatrix();

        //Select the Modelview matrix stack
        glMatrixMode(GL.GL_MODELVIEW);

        //Load the previous Modelview matrix
        glPopMatrix();
    }
}
