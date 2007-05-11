package com.gerbildrop.dradis.displays.dradisGL;


import com.gerbildrop.engine.gl.GLUtil;
import com.gerbildrop.engine.gui.BaseGauge;
import com.jme.intersection.CollisionResults;
import com.jme.scene.Spatial;
import org.lwjgl.opengl.GL11;

public class ArtificialHorizon extends BaseGauge {
    public ArtificialHorizon(int width, int height) {
        super(width, height);
    }

    public void findCollisions(Spatial spatial, CollisionResults collisionResults) {
    }

    public boolean hasCollision(Spatial spatial, boolean _active) {
        return false;
    }

    public void draw() {

        boolean bAvail = attitudeAvailable;
        double h = 250;
        GL11.glScalef(100, 100, 0);
        if (bAvail) {
            float fPitch = Pitch;
            float fRoll = Roll;

            GL11.glPushMatrix();
            GL11.glTranslatef(0.463f, 0.494f, 0.0f);
            GL11.glRotatef(fRoll, 0, 0, -1);
            GL11.glTranslatef(-0.463f, -0.494f, 0.0f);
            GL11.glPushMatrix();
            double fTranslation = GLUtil.transformPitch(fPitch / 10) * 0.110;
            GL11.glTranslated(0, fTranslation, 0);
            GL11.glColor3ub((byte) 0, (byte) 130, (byte) 255);
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            GL11.glVertex2d(0.180, 0.770 - fTranslation);
            GL11.glVertex2f(0.180f, 0.500f);
            GL11.glVertex2d(0.740, 0.770 - fTranslation);
            GL11.glVertex2f(0.740f, 0.500f);
            GL11.glEnd();

            GL11.glColor3ub((byte) 127, (byte) 80, (byte) 30);
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            GL11.glVertex2d(0.180, 0.20f - fTranslation);
            GL11.glVertex2f(0.180f, 0.500f);
            GL11.glVertex2d(0.740, 0.200 - fTranslation);
            GL11.glVertex2f(0.740f, 0.500f);
            GL11.glEnd();

            // Draw the horizon
            doColor(CLWhite);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2f(0.180f, 0.494f);
            GL11.glVertex2f(0.740f, 0.494f);
            GL11.glEnd();

            // Draw the heading ticks
            for (int i = -3; i < 4; ++i) {
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex2f(0.463f + (i * 0.110f), 0.494f);
                GL11.glVertex2f(0.463f + (i * 0.110f), 0.475f);
                GL11.glEnd();
            }

            // Draw pitch angle main scale
            for (int i = 1; i < 9; ++i) {
                if (i == 4 || i == 6 || i == 7) continue;
                double y;
                GL11.glBegin(GL11.GL_LINES);
                y = GLUtil.transformPitch(i);
                GL11.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                GL11.glEnd();
                doColor(CLWhite);
                GLUtil.fnt_printf(0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                GLUtil.fnt_printf(0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));

                GL11.glBegin(GL11.GL_LINES);
                y = GLUtil.transformPitch(-i);
                GL11.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                GL11.glEnd();
                GLUtil.fnt_printf(0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                GLUtil.fnt_printf(0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
            }

            // Draw 5 degrees increment pitch scale
            for (int i = 0; i < 3; ++i) {
                double pos[] = {0.5, 1.5, 2.5};
                double y;
                GL11.glBegin(GL11.GL_LINES);
                y = GLUtil.transformPitch(pos[i]);
                GL11.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));

                y = GLUtil.transformPitch(-pos[i]);
                GL11.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));
                GL11.glEnd();
            }

            // Draw 2.5 degrees increment pitch scale
            for (int i = 0; i < 6; ++i) {
                double pos[] = {0.25, 0.75, 1.25, 1.75, 2.25, 2.75};
                double y;
                GL11.glBegin(GL11.GL_LINES);
                y = GLUtil.transformPitch(pos[i]);
                GL11.glVertex2d(0.463 - 0.019, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.019, 0.494 + (y * 0.110));

                y = GLUtil.transformPitch(-pos[i]);
                GL11.glVertex2d(0.463 - 0.019, 0.494 + (y * 0.110));
                GL11.glVertex2d(0.463 + 0.019, 0.494 + (y * 0.110));
                GL11.glEnd();
            }

            // Draw the extreme pitch attitude arrows
            GL11.glColor3ub((byte) 200, (byte) 0, (byte) 0);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f + 0.215f);
            GL11.glVertex2f(0.463f - 0.020f, 0.494f + 0.245f);
            GL11.glVertex2f(0.463f + 0.020f, 0.494f + 0.245f);
            GL11.glEnd();

            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f + 0.305f);
            GL11.glVertex2f(0.463f - 0.040f, 0.494f + 0.355f);
            GL11.glVertex2f(0.463f - 0.010f, 0.494f + 0.355f);
            GL11.glVertex2f(0.463f, 0.494f + 0.340f);
            GL11.glVertex2f(0.463f + 0.010f, 0.494f + 0.355f);
            GL11.glVertex2f(0.463f + 0.040f, 0.494f + 0.355f);
            GL11.glEnd();

            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f + 0.405f);
            GL11.glVertex2f(0.463f - 0.060f, 0.494f + 0.475f);
            GL11.glVertex2f(0.463f - 0.005f, 0.494f + 0.475f);
            GL11.glVertex2f(0.463f - 0.005f, 0.494f + 0.460f);
            GL11.glVertex2f(0.463f - 0.015f, 0.494f + 0.460f);
            GL11.glVertex2f(0.463f, 0.494f + 0.440f);
            GL11.glVertex2f(0.463f + 0.015f, 0.494f + 0.460f);
            GL11.glVertex2f(0.463f + 0.005f, 0.494f + 0.460f);
            GL11.glVertex2f(0.463f + 0.005f, 0.494f + 0.475f);
            GL11.glVertex2f(0.463f + 0.060f, 0.494f + 0.475f);
            GL11.glEnd();

            GL11.glColor3ub((byte) 255, (byte) 100, (byte) 100);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f - 0.215f + 0.025f);
            GL11.glVertex2f(0.463f - 0.020f, 0.494f - 0.245f + 0.025f);
            GL11.glVertex2f(0.463f + 0.020f, 0.494f - 0.245f + 0.025f);
            GL11.glEnd();

            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f - 0.305f + 0.030f);
            GL11.glVertex2f(0.463f - 0.040f, 0.494f - 0.355f + 0.030f);
            GL11.glVertex2f(0.463f - 0.010f, 0.494f - 0.355f + 0.030f);
            GL11.glVertex2f(0.463f, 0.494f - 0.340f + 0.030f);
            GL11.glVertex2f(0.463f + 0.010f, 0.494f - 0.355f + 0.030f);
            GL11.glVertex2f(0.463f + 0.040f, 0.494f - 0.355f + 0.030f);
            GL11.glEnd();

            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.494f - 0.405f + 0.030f);
            GL11.glVertex2f(0.463f - 0.060f, 0.494f - 0.475f + 0.030f);
            GL11.glVertex2f(0.463f - 0.005f, 0.494f - 0.475f + 0.030f);
            GL11.glVertex2f(0.463f - 0.005f, 0.494f - 0.460f + 0.030f);
            GL11.glVertex2f(0.463f - 0.015f, 0.494f - 0.460f + 0.030f);
            GL11.glVertex2f(0.463f, 0.494f - 0.440f + 0.030f);
            GL11.glVertex2f(0.463f + 0.015f, 0.494f - 0.460f + 0.030f);
            GL11.glVertex2f(0.463f + 0.005f, 0.494f - 0.460f + 0.030f);
            GL11.glVertex2f(0.463f + 0.005f, 0.494f - 0.475f + 0.030f);
            GL11.glVertex2f(0.463f + 0.060f, 0.494f - 0.475f + 0.030f);
            GL11.glEnd();

            GL11.glPopMatrix();

            // Draw Radio height indicator
            GL11.glColor3ub((byte) 127, (byte) 80, (byte) 30);
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            GL11.glVertex2f(0.180f, 0.220f);
            GL11.glVertex2f(0.180f, 0.297f);
            GL11.glVertex2f(0.740f, 0.220f);
            GL11.glVertex2f(0.740f, 0.297f);
            GL11.glEnd();

            doColor(CLWhite);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2f(0.180f, 0.297f);
            GL11.glVertex2f(0.740f, 0.297f);
            GL11.glEnd();

            // Draw the radio height
            GLUtil.fnt_printf(0.426, 0.297 - 0.062, String.valueOf(h));

            // Draw side slip indicator area
            GL11.glColor3ub((byte) 0, (byte) 130, (byte) 255);
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            GL11.glVertex2f(0.260f, 0.770f);
            GL11.glVertex2f(0.260f, 0.691f);
            GL11.glVertex2f(0.670f, 0.770f);
            GL11.glVertex2f(0.670f, 0.691f);
            GL11.glEnd();

            doColor(CLWhite);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2f(0.180f, 0.691f);
            GL11.glVertex2f(0.740f, 0.691f);
            GL11.glEnd();

            // Draw the roll angle needle
            doColor(CLYellow);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.749f);
            GL11.glVertex2f(0.478f, 0.725f);
            GL11.glVertex2f(0.448f, 0.725f);
            GL11.glEnd();

            // Draw the side slip indicator
            doColor(CLYellow);
            GL11.glPushMatrix();
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.445f, 0.717f);
            GL11.glVertex2f(0.481f, 0.717f);
            GL11.glVertex2f(0.488f, 0.707f);
            GL11.glVertex2f(0.438f, 0.707f);
            GL11.glEnd();

            GL11.glPopMatrix();
            GL11.glPopMatrix();

            // Erase outside the outline of the attitude indicator
            doColor(CLBlack);
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

            {
                final int N = 24;
                double angle = -62.0 / 57.29578;
                double step = 124.0 / (N - 1) / 57.29578;
                int i;

                GL11.glVertex2f(0.000f, 0.484f);
                GL11.glVertex2f(0.227f, 0.484f);

                for (i = 0; i < N; ++i) {
                    GL11.glVertex2d(((double) i) / (N - 1), 1.0);
                    GL11.glVertex2d(0.267 * Math.sin(angle) + 0.463, 0.267 * Math.cos(angle) + 0.494);
                    angle += step;
                }

                GL11.glVertex2f(1.000f, 0.484f);
                GL11.glVertex2f(0.698f, 0.484f);
            }

            GL11.glEnd();
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            {
                final int N = 24;
                double angle = 118.0 / 57.29578;
                double step = 124.0 / (N - 1) / 57.29578;
                int i;

                GL11.glVertex2f(1.000f, 0.484f);
                GL11.glVertex2f(0.698f, 0.484f);

                for (i = 0; i < N; ++i) {
                    GL11.glVertex2d(((double) ((N - 1) - i)) / (N - 1), 0.0);
                    GL11.glVertex2d(0.267 * Math.sin(angle) + 0.463, 0.267 * Math.cos(angle) + 0.494);
                    angle += step;
                }
                GL11.glVertex2f(0.000f, 0.484f);
                GL11.glVertex2f(0.227f, 0.484f);
            }
            GL11.glEnd();
        }

        // Draw the outline of the attitude indicator
        doColor(CLGrey);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GLUtil.evalCircle(0.463, 0.494, 0.267, 8, -62, -30);
        GL11.glEnd();
        if (bAvail) doColor(CLWhite);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GLUtil.evalCircle(0.463, 0.494, 0.267, 8, -30, +30);
        GL11.glEnd();
        if (bAvail) doColor(CLGrey);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GLUtil.evalCircle(0.463, 0.494, 0.267, 8, +30, +62);
        GLUtil.evalCircle(0.463, 0.494, 0.267, 24, 118, 242);
        GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -62, -30);
        GL11.glEnd();

        if (bAvail) {
            doColor(CLWhite);
            // Draw the -45 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, -45, -45);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -45, -45);
            GL11.glEnd();
            // Draw the -30 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -31, -30);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, -31, -30);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, -29, -30);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -29, -30);
            GL11.glEnd();
            // Draw the -20 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -21, -21);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, -21, -21);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, -19, -19);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -19, -19);
            GL11.glEnd();
            // Draw the -10 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -11, -11);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, -11, -11);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, -9, -9);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, -9, -9);
            GL11.glEnd();
            // Drawe the 0 deg roll marker
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.463f, 0.761f);
            GL11.glVertex2f(0.463f - 0.015f, 0.791f);
            GL11.glVertex2f(0.463f + 0.015f, 0.791f);
            GL11.glEnd();
            // Draw the +10 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +11, +11);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, +11, +11);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, +9, +9);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +9, +9);
            GL11.glEnd();
            // Draw the +20 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +21, +21);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, +21, +21);
            GLUtil.evalCircle(0.463, 0.494, 0.290, 1, +19, +19);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +19, +19);
            GL11.glEnd();
            // Draw the +30 deg roll marker
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +31, +30);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, +31, +30);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, +29, +30);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +29, +30);
            GL11.glEnd();
            // Draw the +45 deg roll marker
            GL11.glBegin(GL11.GL_LINES);
            GLUtil.evalCircle(0.463, 0.494, 0.267, 1, +45, +45);
            GLUtil.evalCircle(0.463, 0.494, 0.297, 1, +45, +45);
            GL11.glEnd();

            // Draw the aircraft wings and center
            doColor(CLBlack);
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            GL11.glVertex2f(0.247f, 0.504f);
            GL11.glVertex2f(0.247f, 0.484f);
            GL11.glVertex2f(0.360f, 0.504f);
            GL11.glVertex2f(0.340f, 0.484f);
            GL11.glVertex2f(0.360f, 0.453f);
            GL11.glVertex2f(0.340f, 0.453f);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(0.453f, 0.504f);
            GL11.glVertex2f(0.453f, 0.484f);
            GL11.glVertex2f(0.473f, 0.484f);
            GL11.glVertex2f(0.473f, 0.504f);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            GL11.glVertex2f(0.679f, 0.504f);
            GL11.glVertex2f(0.679f, 0.484f);
            GL11.glVertex2f(0.566f, 0.504f);
            GL11.glVertex2f(0.585f, 0.484f);

            GL11.glVertex2f(0.566f, 0.453f);
            GL11.glVertex2f(0.585f, 0.453f);
            GL11.glEnd();
            doColor(CLYellow);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.247f, 0.504f);
            GL11.glVertex2f(0.360f, 0.504f);
            GL11.glVertex2f(0.360f, 0.453f);
            GL11.glVertex2f(0.340f, 0.453f);
            GL11.glVertex2f(0.340f, 0.484f);
            GL11.glVertex2f(0.247f, 0.484f);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.453f, 0.484f);
            GL11.glVertex2f(0.473f, 0.484f);
            GL11.glVertex2f(0.473f, 0.504f);
            GL11.glVertex2f(0.453f, 0.504f);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(0.679f, 0.504f);
            GL11.glVertex2f(0.566f, 0.504f);
            GL11.glVertex2f(0.566f, 0.453f);
            GL11.glVertex2f(0.585f, 0.453f);
            GL11.glVertex2f(0.585f, 0.484f);
            GL11.glVertex2f(0.679f, 0.484f);
            GL11.glEnd();
        }
    }
}