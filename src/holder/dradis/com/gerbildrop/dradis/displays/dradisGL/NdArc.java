package com.gerbildrop.dradis.displays.dradisGL;

import com.gerbildrop.engine.gui.BaseGauge;
import com.gerbildrop.engine.gl.CircleEvaluator;
import com.jme.intersection.CollisionResults;
import com.jme.scene.Spatial;
import org.lwjgl.opengl.GL11;

public class NdArc extends BaseGauge {
    public NdArc(int width, int height) {
        super(width, height);
    }

    public void findCollisions(Spatial spatial, CollisionResults collisionResults) {
    }

    public boolean hasCollision(Spatial spatial, boolean _active) {
        return false;
    }

     public void draw() {
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

//    TextureLoader loader = new TextureLoader();

    protected void draw3() {

//        Texture fontTexture = null;
//        try {
//            fontTexture = loader.getTexture("/dradis/fonts/font.png");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        font = new BitmapFont(fontTexture, 32, 32);
//
//        font.drawString(1, "GS     TAS", 0.025f, 0.900f);

        // Draw the ground and true airspeed
//        doColor(CLWhite);
//        GL11.glTranslatef(0, 0, 0);
//        GLPrint.glPrint("GS     TAS");
//        GL11.glTranslatef(-0.025f, -0.900f, 0);
//        GL11.glColor3ubv(CLWhite);
//        Font.display(glAutoDrawable, 0.025f, 0.900f, "GS     TAS");
//    m_GLEnv->font()->print(0.025f, 0.900f, 0.05f, 0.05f, false, "GS     TAS");
//        GL11.glColor3ubv(CLGreen);
//        Font.display(glAutoDrawable, 0.100f, 0.900f, String.valueOf(m_fGS) + "     " + String.valueOf(m_fTAS));
//    m_GLEnv->font()->print(0.100f, 0.900f, 0.05f, 0.05f, false, "%3.0f     %3.0f", m_fGS, m_fTAS);

        // Draw the wind direction and speed
//        GL11.glColor3ubv(CLWhite);
//        Font.display(glAutoDrawable, 0.100f, 0.850f, "/");
//    m_GLEnv->font()->print(0.100f, 0.850f, 0.05f, 0.05f, false, "/");
        GL11.glScalef(1f, 1f, 0);
        doColor(CLGreen);
        if (!m_bWindAvailable) {
//            Font.display(glAutoDrawable, 0.025f, 0.850f, "160 15");
//        m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "160 15");

            // Draw the wind direction arrow
            GL11.glPushMatrix();
            GL11.glTranslatef(0.068f, 0.813f, 0);
            GL11.glRotatef(m_fHeading - 160, 0, 0, 1.0f);

            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2f(0.000f, -0.031f);
            GL11.glVertex2f(0.000f, +0.031f);
            GL11. glEnd();
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GL11.glVertex2f(-0.015f, -0.016f);
            GL11.glVertex2f(0.000f, -0.031f);
            GL11.glVertex2f(+0.015f, -0.016f);
            GL11.glEnd();
            GL11.glPopMatrix();
        } else {}
//            Font.display(glAutoDrawable, 0.025f, 0.850f, "--- --");
//        m_GLEnv->font()->print(0.025f, 0.850f, 0.05f, 0.05f, false, "--- --");

        // Paint current heading needle
        doColor(CLYellow);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0.476f, 0.830f);
        GL11.glVertex2f(0.484f, 0.830f);
        GL11.glVertex2f(0.484f, 0.775f);
        GL11.glVertex2f(0.476f, 0.775f);
        GL11.glEnd();

        // Paint the heading scale
        doColor(CLWhite);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.480f, 0.167f, 0);
        GL11.glRotatef(m_fHeading, 0, 0, 1.0f);
        int i;
        for (i = 0; i < 36; ++i) {
            float fAngle = i * 10.0f - m_fHeading;
            while (fAngle < 0.0) fAngle += 360.0f;
            while (fAngle > 360.0) fAngle -= 360.0f;
            if (fAngle <= 40.0 || fAngle >= 320.0) {
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex2f(0.000f, 0.640f);
                GL11.glVertex2f(0.000f, 0.670f);
                GL11.glEnd();
//                Font.display(glAutoDrawable, i > 9 ? -0.03f : -0.015f, 0.665f, String.valueOf(i));
//                m_GLEnv->font()->print(i > 9 ? -0.03f : -0.015f, 0.665f, 0.06f, 0.06f, false, "%i", i);
            }
            GL11.glRotatef(-10.0f, 0, 0, 1.0f);
        }
        GL11.glPopMatrix();
        CircleEvaluator ce = new CircleEvaluator();

        // Draw the range arcs
        doColor(CLWhite);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.169);
        ce.SetArcStartEnd(-60, 60);
        ce.SetDegreesPerPoint(36);
        ce.Evaluate();
//        evalCircle(0.480, 0.167, 0.160, 36, -60, 60);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.320, 36, -60, 60);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.320);
        ce.SetArcStartEnd(-60, 60);
        ce.SetDegreesPerPoint(36);
        ce.Evaluate();
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.480, 12, -52, 52);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.480);
        ce.SetArcStartEnd(-52, 52);
        ce.SetDegreesPerPoint(12);
        ce.Evaluate();
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_STRIP);
//        evalCircle(0.480, 0.167, 0.640, 10, -45, 45);
        ce.SetOrigin(0.480, 0.167);
        ce.SetRadius(0.640);
        ce.SetArcStartEnd(-45, 45);
        ce.SetDegreesPerPoint(10);
        ce.Evaluate();
        GL11.glEnd();

        // Paint aircraft symbol to indicate heading and center
        GL11.glPushMatrix();
        GL11.glTranslatef(0.480f, 0.167f, 0);
//        paint_Aircraft_Symbol();
//        AircraftSymbol.display(glAutoDrawable);
        GL11.glPopMatrix();
    }
}
