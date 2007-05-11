package com.gerbildrop.dradis.test.gl;

import com.jme.intersection.CollisionResults;
import com.jme.renderer.Renderer;
import com.jme.renderer.RenderContext;
import com.jme.scene.Geometry;
import com.jme.scene.Spatial;
import com.jme.system.DisplaySystem;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.glu.GLU;

public class Arc2 extends Geometry {

    public void findCollisions(Spatial spatial, CollisionResults collisionResults) {
    }

    public boolean hasCollision(Spatial spatial, boolean _active) {
        return false;
    }

    public void draw(Renderer renderer) {
        super.draw(renderer);
        applyDefaultStates();
        initTest();
        meat2();
    }

    private void applyDefaultStates ()
    {
        RenderContext ctx = DisplaySystem.getDisplaySystem().getCurrentContext();
        for (int ii = 0; ii < Renderer.defaultStateList.length; ii++) {
            if (Renderer.defaultStateList[ii] != null &&
                Renderer.defaultStateList[ii] != ctx.getCurrentState(ii)) {
                Renderer.defaultStateList[ii].apply();
            }
        }
        ctx.clearCurrentStates();
    }

    private boolean finished = false;
    private int width = 640;
    private int height = 480;

    private void initTest() {

        GL11.glViewport(0, 0, width, height);
        GL11.glClearDepth(1.0f); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do
        GL11.glPolygonMode(GL11.GL_BACK, GL11.GL_LINE);
//           Display.setVSyncEnabled(false);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);        // Really Nice Perspective Calculations
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glScissor(0, 0, width, height);
        GL11.glViewport(0, 0, width, height);
        GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
        GL11.glPolygonMode(GL11.GL_BACK, GL11.GL_LINE);
        GL11.glMatrixMode(GL11.GL_PROJECTION);// Select The Projection Matrix
        GL11.glLoadIdentity();// Reset The Projection Matrix
        GLU.gluPerspective(40.0f, 1.0f, 1.0f, 100.0f);
        GLU.gluLookAt(0, 0, 10,
                      0, 0, -10,
                      0, 1, 0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    private void meat2() {
        GL11.glScalef(.4f, .4f, 0);
        GL11.glTranslatef(-1.5f, 0.0f, 0.0f);                // Move Left 1.5 Units And Into The Screen 6.0
        GL11.glBegin(GL11.GL_TRIANGLES);                    // Drawing Using Triangles
        GL11.glColor3f(1.0f, 0.0f, 0.0f);             // Set The Color To Red
        GL11.glVertex3f(0.0f, 1.0f, 0.0f);         // Move Up One Unit From Center (Top Point)
        GL11.glColor3f(0.0f, 1.0f, 0.0f);             // Set The Color To Green
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f);         // Left And Down One Unit (Bottom Left)
        GL11.glColor3f(0.0f, 0.0f, 1.0f);             // Set The Color To Blue
        GL11.glVertex3f(1.0f, -1.0f, 0.0f);         // Right And Down One Unit (Bottom Right)
        GL11.glEnd();                                       // Finished Drawing The Triangle
//        GL11.glScalef(.4f, .4f, 0);
        GL11.glTranslatef(2.5f, 0.0f, 0.0f);              // Move Right 3 Units
        GL11.glColor3f(0.5f, 0.5f, 1.0f);                 // Set The Color To Blue One Time Only
        GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
        GL11.glVertex3f(-1.0f, 1.0f, 0.0f);         // Top Left
        GL11.glVertex3f(1.0f, 1.0f, 0.0f);         // Top Right
        GL11.glVertex3f(1.0f, -1.0f, 0.0f);         // Bottom Right
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f);         // Bottom Left
        GL11.glEnd();                                       // Done Drawing The Quad
    }
}