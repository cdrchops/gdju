package com.gerbildrop.gc.gauges.pfd;

import com.gerbildrop.gc.util.GLUtil;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class ArtificialHorizon extends BaseGauge {
    public ArtificialHorizon() {
        super(42, 52, 94, 98, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        boolean bAvail = attitudeAvailable;
        double h = 250;
        gl.glScalef(100, 100, 0);
        if (bAvail) {
            double fPitch = Pitch;
            double fRoll = Roll;

            gl.glPushMatrix();
            gl.glTranslatef(0.463f, 0.494f, 0.0f);
            gl.glRotated(fRoll, 0, 0, -1);
            gl.glTranslatef(-0.463f, -0.494f, 0.0f);
            gl.glPushMatrix();
            double fTranslation = GLUtil.transformPitch(fPitch / 10) * 0.110;
            gl.glTranslated(0, fTranslation, 0);
            gl.glColor3ub((byte) 0, (byte) 130, (byte) 255);
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glVertex2d(0.180, 0.770 - fTranslation);
            gl.glVertex2f(0.180f, 0.500f);
            gl.glVertex2d(0.740, 0.770 - fTranslation);
            gl.glVertex2f(0.740f, 0.500f);
            gl.glEnd();

            gl.glColor3ub((byte) 127, (byte) 80, (byte) 30);
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glVertex2d(0.180, 0.20f - fTranslation);
            gl.glVertex2f(0.180f, 0.500f);
            gl.glVertex2d(0.740, 0.200 - fTranslation);
            gl.glVertex2f(0.740f, 0.500f);
            gl.glEnd();

            // Draw the horizon
            gl.glColor3ubv(CLWhite);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.180f, 0.494f);
            gl.glVertex2f(0.740f, 0.494f);
            gl.glEnd();

            // Draw the heading ticks
            for (int i = -3; i < 4; ++i) {
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2f(0.463f + (i * 0.110f), 0.494f);
                gl.glVertex2f(0.463f + (i * 0.110f), 0.475f);
                gl.glEnd();
            }

            // Draw pitch angle main scale
            for (int i = 1; i < 9; ++i) {
                if (i == 4 || i == 6 || i == 7) continue;
                double y;
                gl.glBegin(GL.GL_LINES);
                y = GLUtil.transformPitch(i);
                gl.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                gl.glEnd();
                gl.glColor3ubv(CLWhite);
                GLUtil.fnt_printf(glAutoDrawable, 0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                GLUtil.fnt_printf(glAutoDrawable, 0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));

                gl.glBegin(GL.GL_LINES);
                y = GLUtil.transformPitch(-i);
                gl.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                gl.glEnd();
                GLUtil.fnt_printf(glAutoDrawable, 0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                GLUtil.fnt_printf(glAutoDrawable, 0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
            }

            // Draw 5 degrees increment pitch scale
            for (int i = 0; i < 3; ++i) {
                double pos[] = {0.5, 1.5, 2.5};
                double y;
                gl.glBegin(GL.GL_LINES);
                y = GLUtil.transformPitch(pos[i]);
                gl.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));

                y = GLUtil.transformPitch(-pos[i]);
                gl.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));
                gl.glEnd();
            }

            // Draw 2.5 degrees increment pitch scale
            for (int i = 0; i < 6; ++i) {
                double pos[] = {0.25, 0.75, 1.25, 1.75, 2.25, 2.75};
                double y;
                gl.glBegin(GL.GL_LINES);
                y = GLUtil.transformPitch(pos[i]);
                gl.glVertex2d(0.463 - 0.019, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.019, 0.494 + (y * 0.110));

                y = GLUtil.transformPitch(-pos[i]);
                gl.glVertex2d(0.463 - 0.019, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.019, 0.494 + (y * 0.110));
                gl.glEnd();
            }

            // Draw the extreme pitch attitude arrows
            gl.glColor3ub((byte) 200, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f + 0.215f);
            gl.glVertex2f(0.463f - 0.020f, 0.494f + 0.245f);
            gl.glVertex2f(0.463f + 0.020f, 0.494f + 0.245f);
            gl.glEnd();

            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f + 0.305f);
            gl.glVertex2f(0.463f - 0.040f, 0.494f + 0.355f);
            gl.glVertex2f(0.463f - 0.010f, 0.494f + 0.355f);
            gl.glVertex2f(0.463f, 0.494f + 0.340f);
            gl.glVertex2f(0.463f + 0.010f, 0.494f + 0.355f);
            gl.glVertex2f(0.463f + 0.040f, 0.494f + 0.355f);
            gl.glEnd();

            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f + 0.405f);
            gl.glVertex2f(0.463f - 0.060f, 0.494f + 0.475f);
            gl.glVertex2f(0.463f - 0.005f, 0.494f + 0.475f);
            gl.glVertex2f(0.463f - 0.005f, 0.494f + 0.460f);
            gl.glVertex2f(0.463f - 0.015f, 0.494f + 0.460f);
            gl.glVertex2f(0.463f, 0.494f + 0.440f);
            gl.glVertex2f(0.463f + 0.015f, 0.494f + 0.460f);
            gl.glVertex2f(0.463f + 0.005f, 0.494f + 0.460f);
            gl.glVertex2f(0.463f + 0.005f, 0.494f + 0.475f);
            gl.glVertex2f(0.463f + 0.060f, 0.494f + 0.475f);
            gl.glEnd();

            gl.glColor3ub((byte) 255, (byte) 100, (byte) 100);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f - 0.215f + 0.025f);
            gl.glVertex2f(0.463f - 0.020f, 0.494f - 0.245f + 0.025f);
            gl.glVertex2f(0.463f + 0.020f, 0.494f - 0.245f + 0.025f);
            gl.glEnd();

            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f - 0.305f + 0.030f);
            gl.glVertex2f(0.463f - 0.040f, 0.494f - 0.355f + 0.030f);
            gl.glVertex2f(0.463f - 0.010f, 0.494f - 0.355f + 0.030f);
            gl.glVertex2f(0.463f, 0.494f - 0.340f + 0.030f);
            gl.glVertex2f(0.463f + 0.010f, 0.494f - 0.355f + 0.030f);
            gl.glVertex2f(0.463f + 0.040f, 0.494f - 0.355f + 0.030f);
            gl.glEnd();

            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.494f - 0.405f + 0.030f);
            gl.glVertex2f(0.463f - 0.060f, 0.494f - 0.475f + 0.030f);
            gl.glVertex2f(0.463f - 0.005f, 0.494f - 0.475f + 0.030f);
            gl.glVertex2f(0.463f - 0.005f, 0.494f - 0.460f + 0.030f);
            gl.glVertex2f(0.463f - 0.015f, 0.494f - 0.460f + 0.030f);
            gl.glVertex2f(0.463f, 0.494f - 0.440f + 0.030f);
            gl.glVertex2f(0.463f + 0.015f, 0.494f - 0.460f + 0.030f);
            gl.glVertex2f(0.463f + 0.005f, 0.494f - 0.460f + 0.030f);
            gl.glVertex2f(0.463f + 0.005f, 0.494f - 0.475f + 0.030f);
            gl.glVertex2f(0.463f + 0.060f, 0.494f - 0.475f + 0.030f);
            gl.glEnd();

            gl.glPopMatrix();

            // Draw Radio height indicator
            gl.glColor3ub((byte) 127, (byte) 80, (byte) 30);
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glVertex2f(0.180f, 0.220f);
            gl.glVertex2f(0.180f, 0.297f);
            gl.glVertex2f(0.740f, 0.220f);
            gl.glVertex2f(0.740f, 0.297f);
            gl.glEnd();

            gl.glColor3ubv(CLWhite);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.180f, 0.297f);
            gl.glVertex2f(0.740f, 0.297f);
            gl.glEnd();

            // Draw the radio height
            GLUtil.fnt_printf(glAutoDrawable, 0.426, 0.297 - 0.062, String.valueOf(h));

            // Draw side slip indicator area
            gl.glColor3ub((byte) 0, (byte) 130, (byte) 255);
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glVertex2f(0.260f, 0.770f);
            gl.glVertex2f(0.260f, 0.691f);
            gl.glVertex2f(0.670f, 0.770f);
            gl.glVertex2f(0.670f, 0.691f);
            gl.glEnd();

            gl.glColor3ubv(CLWhite);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.180f, 0.691f);
            gl.glVertex2f(0.740f, 0.691f);
            gl.glEnd();

            // Draw the roll angle needle
            gl.glColor3ubv(CLYellow);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.749f);
            gl.glVertex2f(0.478f, 0.725f);
            gl.glVertex2f(0.448f, 0.725f);
            gl.glEnd();

            // Draw the side slip indicator
            gl.glColor3ubv(CLYellow);
            gl.glPushMatrix();
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.445f, 0.717f);
            gl.glVertex2f(0.481f, 0.717f);
            gl.glVertex2f(0.488f, 0.707f);
            gl.glVertex2f(0.438f, 0.707f);
            gl.glEnd();

            gl.glPopMatrix();
            gl.glPopMatrix();

            // Erase outside the outline of the attitude indicator
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_TRIANGLE_STRIP);

            {
                final int N = 24;
                double angle = -62.0 / 57.29578;
                double step = 124.0 / (N - 1) / 57.29578;
                int i;

                gl.glVertex2f(0.000f, 0.484f);
                gl.glVertex2f(0.227f, 0.484f);

                for (i = 0; i < N; ++i) {
                    gl.glVertex2d(((double) i) / (N - 1), 1.0);
                    gl.glVertex2d(0.267 * Math.sin(angle) + 0.463, 0.267 * Math.cos(angle) + 0.494);
                    angle += step;
                }

                gl.glVertex2f(1.000f, 0.484f);
                gl.glVertex2f(0.698f, 0.484f);
            }

            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            {
                final int N = 24;
                double angle = 118.0 / 57.29578;
                double step = 124.0 / (N - 1) / 57.29578;
                int i;

                gl.glVertex2f(1.000f, 0.484f);
                gl.glVertex2f(0.698f, 0.484f);

                for (i = 0; i < N; ++i) {
                    gl.glVertex2d(((double) ((N - 1) - i)) / (N - 1), 0.0);
                    gl.glVertex2d(0.267 * Math.sin(angle) + 0.463, 0.267 * Math.cos(angle) + 0.494);
                    angle += step;
                }
                gl.glVertex2f(0.000f, 0.484f);
                gl.glVertex2f(0.227f, 0.484f);
            }
            gl.glEnd();
        }

        // Draw the outline of the attitude indicator
        gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINE_STRIP);
        GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 8, -62, -30);
        gl.glEnd();
        if (bAvail) gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 8, -30, +30);
        gl.glEnd();
        if (bAvail) gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINE_STRIP);
        GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 8, +30, +62);
        GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 24, 118, 242);
        GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -62, -30);
        gl.glEnd();

        if (bAvail) {
            gl.glColor3ubv(CLWhite);
            // Draw the -45 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, -45, -45);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -45, -45);
            gl.glEnd();
            // Draw the -30 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -31, -30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, -31, -30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, -29, -30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -29, -30);
            gl.glEnd();
            // Draw the -20 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -21, -21);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, -21, -21);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, -19, -19);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -19, -19);
            gl.glEnd();
            // Draw the -10 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -11, -11);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, -11, -11);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, -9, -9);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, -9, -9);
            gl.glEnd();
            // Drawe the 0 deg roll marker
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.761f);
            gl.glVertex2f(0.463f - 0.015f, 0.791f);
            gl.glVertex2f(0.463f + 0.015f, 0.791f);
            gl.glEnd();
            // Draw the +10 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +11, +11);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, +11, +11);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, +9, +9);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +9, +9);
            gl.glEnd();
            // Draw the +20 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +21, +21);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, +21, +21);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.290, 1, +19, +19);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +19, +19);
            gl.glEnd();
            // Draw the +30 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +31, +30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, +31, +30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, +29, +30);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +29, +30);
            gl.glEnd();
            // Draw the +45 deg roll marker
            gl.glBegin(GL.GL_LINES);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.267, 1, +45, +45);
            GLUtil.evalCircle(gl, 0.463, 0.494, 0.297, 1, +45, +45);
            gl.glEnd();

            // Draw the aircraft wings and center
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL2.GL_QUAD_STRIP);
            gl.glVertex2f(0.247f, 0.504f);
            gl.glVertex2f(0.247f, 0.484f);
            gl.glVertex2f(0.360f, 0.504f);
            gl.glVertex2f(0.340f, 0.484f);
            gl.glVertex2f(0.360f, 0.453f);
            gl.glVertex2f(0.340f, 0.453f);
            gl.glEnd();
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(0.453f, 0.504f);
            gl.glVertex2f(0.453f, 0.484f);
            gl.glVertex2f(0.473f, 0.484f);
            gl.glVertex2f(0.473f, 0.504f);
            gl.glEnd();
            gl.glBegin(GL2.GL_QUAD_STRIP);
            gl.glVertex2f(0.679f, 0.504f);
            gl.glVertex2f(0.679f, 0.484f);
            gl.glVertex2f(0.566f, 0.504f);
            gl.glVertex2f(0.585f, 0.484f);

            gl.glVertex2f(0.566f, 0.453f);
            gl.glVertex2f(0.585f, 0.453f);
            gl.glEnd();
            gl.glColor3ubv(CLYellow);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.247f, 0.504f);
            gl.glVertex2f(0.360f, 0.504f);
            gl.glVertex2f(0.360f, 0.453f);
            gl.glVertex2f(0.340f, 0.453f);
            gl.glVertex2f(0.340f, 0.484f);
            gl.glVertex2f(0.247f, 0.484f);
            gl.glEnd();
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.453f, 0.484f);
            gl.glVertex2f(0.473f, 0.484f);
            gl.glVertex2f(0.473f, 0.504f);
            gl.glVertex2f(0.453f, 0.504f);
            gl.glEnd();
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.679f, 0.504f);
            gl.glVertex2f(0.566f, 0.504f);
            gl.glVertex2f(0.566f, 0.453f);
            gl.glVertex2f(0.585f, 0.453f);
            gl.glVertex2f(0.585f, 0.484f);
            gl.glVertex2f(0.679f, 0.484f);
            gl.glEnd();
        }
    }
}