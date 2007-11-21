package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.base.Configuration;
import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.util.GLUtil;

import javax.media.opengl.GL;

public class PFD extends BaseComponent {
    protected void drawLWJGL() {
    }
    //    public PFD() {
//        super(0, 100, 100, 100, 4, 4, 0, 0, 1);
//    }

    double h = 250;

    public void drawJOGL(GL gl) {
        gl.glScalef(100, 100, 0);
        paintAttitudeInd();
//        paintFMA();
//        paintSpeedInd(glAutoDrawable);
//        paintTrajectoryDevInd();
//        paintHeadingInd(glAutoDrawable);
//        paintVerticalSpeedInd();
//        paintAltitudeInd(glAutoDrawable);
    }

    void paintFMA() {
        GL gl = Configuration.getGL();
        gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.239f, 0.973f);
        gl.glVertex2f(0.239f, 0.820f);
        gl.glVertex2f(0.427f, 0.973f);
        gl.glVertex2f(0.428f, 0.820f);
        gl.glVertex2f(0.616f, 0.973f);
        gl.glVertex2f(0.616f, 0.820f);
        gl.glVertex2f(0.804f, 0.973f);
        gl.glVertex2f(0.804f, 0.820f);
        gl.glEnd();
    }

    public static void fnt_printf(double x, double y, String value) {
        GLUtil.fnt_printf(x, y, value);
    }

    void evalCircle(double x, double y, double r, int n, double start, double end) {
        GLUtil.evalCircle(x, y, r, n, start, end);
    }

    double transformPitch(double t) {
        return GLUtil.transformPitch(t);
    }

    void paintAttitudeInd() {
        GL gl = Configuration.getGL();
        boolean bAvail = attitudeAvailable;//diom_getValueBoolean(vAttitudeAvail);

        if (bAvail) {
            double fPitch = Pitch;
            double fRoll = Roll;

            gl.glPushMatrix();
            gl.glTranslatef(0.463f, 0.494f, 0.0f);
            gl.glRotated(fRoll, 0, 0, -1);
            gl.glTranslatef(-0.463f, -0.494f, 0.0f);
            gl.glPushMatrix();
            double fTranslation = transformPitch(fPitch / 10) * 0.110;
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
                y = transformPitch(i);
                gl.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                gl.glEnd();
                gl.glColor3ubv(CLWhite);
                fnt_printf(0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                fnt_printf(0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));

                gl.glBegin(GL.GL_LINES);
                y = transformPitch(-i);
                gl.glVertex2d(0.463 - 0.074, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.074, 0.494 + (y * 0.110));
                gl.glEnd();
                fnt_printf(0.320, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
                fnt_printf(0.556, 0.494 + (y * 0.110) - 0.027, String.valueOf(i * 10));
            }

            // Draw 5 degrees increment pitch scale
            for (int i = 0; i < 3; ++i) {
                double pos[] = {0.5, 1.5, 2.5};
                double y;
                gl.glBegin(GL.GL_LINES);
                y = transformPitch(pos[i]);
                gl.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));

                y = transformPitch(-pos[i]);
                gl.glVertex2d(0.463 - 0.040, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.040, 0.494 + (y * 0.110));
                gl.glEnd();
            }

            // Draw 2.5 degrees increment pitch scale
            for (int i = 0; i < 6; ++i) {
                double pos[] = {0.25, 0.75, 1.25, 1.75, 2.25, 2.75};
                double y;
                gl.glBegin(GL.GL_LINES);
                y = transformPitch(pos[i]);
                gl.glVertex2d(0.463 - 0.019, 0.494 + (y * 0.110));
                gl.glVertex2d(0.463 + 0.019, 0.494 + (y * 0.110));

                y = transformPitch(-pos[i]);
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
            fnt_printf(0.426, 0.297 - 0.062, String.valueOf(h));

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
        evalCircle(0.463, 0.494, 0.267, 8, -62, -30);
        gl.glEnd();
        if (bAvail) gl.glColor3ubv(CLWhite);
        gl.glBegin(GL.GL_LINE_STRIP);
        evalCircle(0.463, 0.494, 0.267, 8, -30, +30);
        gl.glEnd();
        if (bAvail) gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINE_STRIP);
        evalCircle(0.463, 0.494, 0.267, 8, +30, +62);
        evalCircle(0.463, 0.494, 0.267, 24, 118, 242);
        evalCircle(0.463, 0.494, 0.267, 1, -62, -30);
        gl.glEnd();

        if (bAvail) {
            gl.glColor3ubv(CLWhite);
            // Draw the -45 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.297, 1, -45, -45);
            evalCircle(0.463, 0.494, 0.267, 1, -45, -45);
            gl.glEnd();
            // Draw the -30 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, -31, -30);
            evalCircle(0.463, 0.494, 0.297, 1, -31, -30);
            evalCircle(0.463, 0.494, 0.297, 1, -29, -30);
            evalCircle(0.463, 0.494, 0.267, 1, -29, -30);
            gl.glEnd();
            // Draw the -20 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, -21, -21);
            evalCircle(0.463, 0.494, 0.290, 1, -21, -21);
            evalCircle(0.463, 0.494, 0.290, 1, -19, -19);
            evalCircle(0.463, 0.494, 0.267, 1, -19, -19);
            gl.glEnd();
            // Draw the -10 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, -11, -11);
            evalCircle(0.463, 0.494, 0.290, 1, -11, -11);
            evalCircle(0.463, 0.494, 0.290, 1, -9, -9);
            evalCircle(0.463, 0.494, 0.267, 1, -9, -9);
            gl.glEnd();
            // Drawe the 0 deg roll marker
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2f(0.463f, 0.761f);
            gl.glVertex2f(0.463f - 0.015f, 0.791f);
            gl.glVertex2f(0.463f + 0.015f, 0.791f);
            gl.glEnd();
            // Draw the +10 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, +11, +11);
            evalCircle(0.463, 0.494, 0.290, 1, +11, +11);
            evalCircle(0.463, 0.494, 0.290, 1, +9, +9);
            evalCircle(0.463, 0.494, 0.267, 1, +9, +9);
            gl.glEnd();
            // Draw the +20 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, +21, +21);
            evalCircle(0.463, 0.494, 0.290, 1, +21, +21);
            evalCircle(0.463, 0.494, 0.290, 1, +19, +19);
            evalCircle(0.463, 0.494, 0.267, 1, +19, +19);
            gl.glEnd();
            // Draw the +30 deg roll marker
            gl.glBegin(GL.GL_LINE_STRIP);
            evalCircle(0.463, 0.494, 0.267, 1, +31, +30);
            evalCircle(0.463, 0.494, 0.297, 1, +31, +30);
            evalCircle(0.463, 0.494, 0.297, 1, +29, +30);
            evalCircle(0.463, 0.494, 0.267, 1, +29, +30);
            gl.glEnd();
            // Draw the +45 deg roll marker
            gl.glBegin(GL.GL_LINES);
            evalCircle(0.463, 0.494, 0.267, 1, +45, +45);
            evalCircle(0.463, 0.494, 0.297, 1, +45, +45);
            gl.glEnd();

            // Draw the aircraft wings and center
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex2f(0.247f, 0.504f);
            gl.glVertex2f(0.247f, 0.484f);
            gl.glVertex2f(0.360f, 0.504f);
            gl.glVertex2f(0.340f, 0.484f);
            gl.glVertex2f(0.360f, 0.453f);
            gl.glVertex2f(0.340f, 0.453f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.453f, 0.504f);
            gl.glVertex2f(0.453f, 0.484f);
            gl.glVertex2f(0.473f, 0.484f);
            gl.glVertex2f(0.473f, 0.504f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUAD_STRIP);
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

    double speedToScreen(double fIAS, double fSpeed) {
        return 0.493 + (fSpeed - fIAS) * 0.0065;
    }

    void paintSpeedInd() {
        GL gl = getGL();
        boolean bAvail = airSpeedAvailable;

        if (bAvail) {
            double fIAS = ias;
            double fTAS = ias;
            double fV1 = velocity_V1;
            double fVR = velocity_VR;
            double fV2 = velocity_V2;
            double fVSW = velocity_VSW;
            double fVLS = velocity_VLS;
            double fVMax = velocity_VMax;

            // Paint the background
            gl.glColor3ubv(CLGrey);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.024f, 0.761f);
            gl.glVertex2f(0.165f, 0.761f);
            gl.glVertex2f(0.165f, 0.224f);
            gl.glVertex2f(0.024f, 0.224f);
            gl.glEnd();

            // Paint the scale
            gl.glColor3ubv(CLWhite);
            int i;
            int iD = ((int) fIAS) / 10;
            for (i = -4; i < 6; ++i) {
                double h = (i + iD) * 10.0;
                double y = speedToScreen(fIAS, h);

                if (h >= 0.0 && y >= 0.209 && y <= 0.776) {
                    gl.glBegin(GL.GL_LINES);
                    gl.glVertex2d(0.135, y);
                    gl.glVertex2d(0.165, y);
                    gl.glEnd();

                    if (((int) (h / 10.0) & 1) == 0) {
                        fnt_printf(0.042, y - 0.025, String.valueOf(h));
                    }
                }
            }

            gl.glBegin(GL.GL_QUADS);
            // Paint VMax bar
            double fY1 = speedToScreen(fIAS, fVMax);
            if (fY1 <= 0.761) {
                if (fY1 < 0.224) fY1 = 0.224;
                gl.glColor3ubv(CLRed);
                gl.glVertex2d(0.165, fY1);
                gl.glVertex2f(0.165f, 0.761f);
                gl.glVertex2f(0.195f, 0.761f);
                gl.glVertex2d(0.195, fY1);
            }
            // Paint VLS bar
            fY1 = speedToScreen(fIAS, fVSW);
            double fY2 = speedToScreen(fIAS, fVLS);
            if (fY2 >= 0.224 && fY1 <= 0.761) {
                if (fY1 < 0.224) fY1 = 0.224;
                if (fY2 > 0.761) fY2 = 0.761;
                gl.glColor3ubv(CLAmber);
                gl.glVertex2d(0.165, fY1);
                gl.glVertex2d(0.165, fY2);
                gl.glVertex2d(0.177, fY2);
                gl.glVertex2d(0.177, fY1);
            }
            // Paint VSW bar
            fY1 = speedToScreen(fIAS, 0.0);
            fY2 = speedToScreen(fIAS, fVSW);
            if (fY2 >= 0.224 && fY1 <= 0.761) {
                if (fY1 < 0.224) fY1 = 0.224;
                if (fY2 > 0.761) fY2 = 0.761;
                gl.glColor3ubv(CLRed);
                gl.glVertex2d(0.165, fY1);
                gl.glVertex2d(0.165, fY2);
                gl.glVertex2d(0.195, fY2);
                gl.glVertex2d(0.195, fY1);
            }
            gl.glEnd();

            // Paint V1 Dicision speed
            fY1 = speedToScreen(fIAS, fV1);
            if (fY1 >= 0.224 && fY1 <= 0.761) {
                gl.glColor3ubv(CLWhite);
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2d(0.165, fY1);
                gl.glVertex2d(0.195, fY1);
                gl.glEnd();

                fnt_printf(0.195, fY1 - 0.025, "1");
            }

            // Paint VR rotation speed
            fY1 = speedToScreen(fIAS, fVR);
            if (fY1 >= 0.239 && fY1 <= 0.746) {
                gl.glColor3ubv(CLWhite);
                gl.glBegin(GL.GL_LINE_LOOP);
                gl.glVertex2d(0.150, fY1);
                gl.glVertex2d(0.165, fY1 + 0.015);
                gl.glVertex2d(0.180, fY1);
                gl.glVertex2d(0.165, fY1 - 0.015);
                gl.glEnd();
            }

            // Paint V2 climb speed
            fY1 = speedToScreen(fIAS, fV2);
            if (fY1 >= 0.239 && fY1 <= 0.746) {
                gl.glColor3ubv(CLWhite);
                gl.glBegin(GL.GL_LINE_LOOP);
                gl.glVertex2d(0.165, fY1);
                gl.glVertex2d(0.196, fY1 + 0.015);
                gl.glVertex2d(0.196, fY1 - 0.015);
                gl.glEnd();
            }

            // Erase scale that is outside of indicator
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.024f, 0.761f);
            gl.glVertex2f(0.024f, 0.800f);
            gl.glVertex2f(0.165f, 0.800f);
            gl.glVertex2f(0.165f, 0.761f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.024f, 0.224f);
            gl.glVertex2f(0.024f, 0.195f);
            gl.glVertex2f(0.165f, 0.195f);
            gl.glVertex2f(0.165f, 0.224f);
            gl.glEnd();

            // Draw the mach number
            double fMach = fTAS / 661.7;
            if (fMach >= 0.5f) {
                gl.glColor3ubv(CLGreen);
                fnt_printf(0.030, 0.150, String.valueOf(fMach));
            }
        }

        // Draw the outline
        if (bAvail) gl.glColor3ubv(CLWhite);
        else gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.024f, 0.761f);
        gl.glVertex2f(0.196f, 0.761f);
        gl.glVertex2f(0.165f, 0.761f);
        gl.glVertex2f(0.165f, 0.224f);
        gl.glVertex2f(0.196f, 0.224f);
        gl.glVertex2f(0.024f, 0.224f);
        gl.glEnd();

        if (bAvail) {
            // Draw The Needle
            gl.glColor3ubv(CLYellow);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.024f, 0.493f);
            gl.glVertex2f(0.165f, 0.493f);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2f(0.165f, 0.493f);
            gl.glVertex2f(0.196f, 0.478f);
            gl.glVertex2f(0.196f, 0.508f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.024f, 0.486f);
            gl.glVertex2f(0.039f, 0.486f);
            gl.glVertex2f(0.039f, 0.500f);
            gl.glVertex2f(0.024f, 0.500f);

            gl.glVertex2f(0.165f, 0.486f);
            gl.glVertex2f(0.150f, 0.486f);
            gl.glVertex2f(0.150f, 0.500f);
            gl.glVertex2f(0.165f, 0.500f);
            gl.glEnd();
        }
    }

    void paintTrajectoryDevInd() {
    }

    void paintHeadingInd() {
        GL gl = getGL();
        boolean bAvail = headingAvail;

        if (bAvail) {
            double fHeading = heading;
            while (fHeading < 0.0f) fHeading += 360.0f;

            // Paint the background
            gl.glColor3ubv(CLGrey);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.231f, 0.043f);
            gl.glVertex2f(0.231f, 0.141f);
            gl.glVertex2f(0.678f, 0.141f);
            gl.glVertex2f(0.678f, 0.043f);
            gl.glEnd();

            // Paint the scale
            int i;
            gl.glColor3ubv(CLWhite);
            for (i = -6; i < 6; ++i) {
                double h = fHeading;
                while (h > 5.0) h -= 5.0;

                double x = 0.4545 + ((i - (h / 5.0)) * 0.046875);
                if (x >= 0.205 && x <= 0.700) {
                    gl.glBegin(GL.GL_LINES);
                    gl.glVertex2d(x, 0.141);
                    if ((int) (fHeading - h + (i * 5.0)) % 10 == 0) {
                        gl.glVertex2d(x, 0.090);
                        gl.glEnd();
                        h = (fHeading + (i * 5.0) - h) / 10.0;
                        if (h < 0.0) h += 36.0;
                        else if (h > 35.0) h -= 36.0;
                        if (h < 10.0) x += 0.0125;
                        fnt_printf(x - 0.025, 0.045, String.valueOf(h));
                    } else {
                        gl.glVertex2d(x, 0.110);
                        gl.glEnd();
                    }
                }
            }

            // Erase scale that is outside of indicator
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.180f, 0.043f);
            gl.glVertex2f(0.180f, 0.141f);
            gl.glVertex2f(0.231f, 0.141f);
            gl.glVertex2f(0.231f, 0.043f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.678f, 0.043f);
            gl.glVertex2f(0.678f, 0.141f);
            gl.glVertex2f(0.728f, 0.141f);
            gl.glVertex2f(0.728f, 0.043f);
            gl.glEnd();
        }

        // Draw box around indicator
        if (bAvail) gl.glColor3ubv(CLWhite);
        else gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2f(0.231f, 0.043f);
        gl.glVertex2f(0.231f, 0.141f);
        gl.glVertex2f(0.678f, 0.141f);
        gl.glVertex2f(0.678f, 0.043f);
        gl.glEnd();

        if (bAvail) {
            // Draw needle
            gl.glColor3ubv(CLYellow);
//            setPaintWidth(2);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.4545f, 0.130f);
            gl.glVertex2f(0.4545f, 0.160f);
            gl.glEnd();
//            setPaintWidth(1);
        }
    }

    void paintVerticalSpeedInd() {
        GL gl = getGL();
        boolean bAvail = vertSpeedAvail;

        if (bAvail) {
            // Paint the backgroun
            gl.glColor3ubv(CLGrey);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.929f, 0.761f);
            gl.glVertex2f(0.984f, 0.651f);
            gl.glVertex2f(0.984f, 0.337f);
            gl.glVertex2f(0.929f, 0.221f);
            gl.glEnd();
        }

        if (bAvail) gl.glColor3ubv(CLWhite);
        else gl.glColor3ubv(CLGrey);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(0.929f, 0.761f);
        gl.glVertex2f(0.984f, 0.651f);
        gl.glVertex2f(0.984f, 0.337f);
        gl.glVertex2f(0.929f, 0.221f);
        gl.glEnd();
    }

    double altitudeToScreen(double fAltitude, double fAlt) {
        return 0.492 + (fAlt - fAltitude) * 0.00045;
    }

    double altitudeFracToScreen(double fAltitude, double fAltFrac) {
        return 0.492 + (fAltFrac - fAltitude) * 0.0025;
    }

    void paintAltitudeInd() {
        GL gl = getGL();
        boolean bAvail = altitudeAvail;
        double fAltitude = alt;
        double fAutopilotAltitude = m_fAutopilotHeading;

        if (bAvail) {
            // Paint the background
            gl.glColor3ubv(CLGrey);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.757f, 0.761f);
            gl.glVertex2f(0.855f, 0.761f);
            gl.glVertex2f(0.855f, 0.227f);
            gl.glVertex2f(0.757f, 0.227f);
            gl.glEnd();

            // paint the scale for the altitude / 100
            gl.glColor3ubv(CLWhite);
            int i;
            int iD = ((int) fAltitude) / 100;
            for (i = -6; i < 8; ++i) {
                double h = (i + iD) * 100.0;
                double y = altitudeToScreen(fAltitude, h);

                if (y >= 0.209 && y <= 0.776) {
                    gl.glBegin(GL.GL_LINES);
                    gl.glVertex2d(0.835, y);
                    gl.glVertex2d(0.855, y);
                    gl.glEnd();

                    if (((int) h % 500) == 0) {
                        fnt_printf(0.757, y - 0.025, String.valueOf(h / 100.0));
                    }
                }
            }

            // Erase pixels outside of indicator
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.757f, 0.801f);
            gl.glVertex2f(0.855f, 0.801f);
            gl.glVertex2f(0.855f, 0.761f);
            gl.glVertex2f(0.757f, 0.761f);

            gl.glVertex2f(0.757f, 0.227f);
            gl.glVertex2f(0.855f, 0.227f);
            gl.glVertex2f(0.855f, 0.187f);
            gl.glVertex2f(0.757f, 0.187f);
            gl.glEnd();

            // Paint the selected autopilot altitude
            double y = altitudeToScreen(fAltitude, fAutopilotAltitude);
            gl.glColor3ubv(CLBlue);
            if (y >= 0.209 && y <= 0.776) {
                gl.glBegin(GL.GL_LINE_LOOP);
                gl.glVertex2d(0.734, y + 0.045);
                gl.glVertex2d(0.734, y + 0.015);
                gl.glVertex2d(0.755, y + 0.000);
                gl.glVertex2d(0.734, y - 0.015);
                gl.glVertex2d(0.734, y - 0.045);
                gl.glVertex2d(0.781, y - 0.045);
                gl.glVertex2d(0.781, y + 0.045);
                gl.glEnd();
            } else {
                // Altitude is outside of scale print the number
                // at the top of the indicator
                fnt_printf(0.757, 0.761, String.valueOf(fAutopilotAltitude));
            }

            // Paint the background for the index
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.855f, 0.529f);
            gl.glVertex2f(0.757f, 0.529f);

            gl.glVertex2f(0.757f, 0.455f);
            gl.glVertex2f(0.855f, 0.455f);
            gl.glEnd();

            // paint the index for the altitude / 100
            gl.glColor3ubv(CLWhite);
            //m_GLEnv->font()->print(0.753, 0.457, 0.063, 0.07, false, "%3u", abs((int)(fAltitude / 100.0)));
            // FIXME: Use correct font height
            fnt_printf(0.753, 0.457, String.valueOf(Math.abs((int) (fAltitude / 100.0))));

            // Paint the scale for the altitude % 100
            iD = ((int) fAltitude) / 20;
            for (i = -1; i < 3; ++i) {
                double h = (i + iD) * 20.0;
                double xy = altitudeFracToScreen(fAltitude, h);

                if (xy >= 0.414 && xy <= 0.554) {
                    fnt_printf(0.855, xy - 0.025, String.valueOf(Math.abs((int) h) % 100));
                }
            }

            // Erase pixels outside of indicator
            gl.glColor3ubv(CLBlack);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.855f, 0.545f);
            gl.glVertex2f(0.910f, 0.545f);
            gl.glVertex2f(0.910f, 0.575f);
            gl.glVertex2f(0.855f, 0.575f);

            gl.glVertex2f(0.855f, 0.439f);
            gl.glVertex2f(0.910f, 0.439f);
            gl.glVertex2f(0.910f, 0.400f);
            gl.glVertex2f(0.855f, 0.400f);
            gl.glEnd();
        }

        if (bAvail) {
            gl.glColor3ubv(CLWhite);
        } else {
            gl.glColor3ubv(CLGrey);
        }

        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.757f, 0.761f);
        gl.glVertex2f(0.882f, 0.761f);
        gl.glVertex2f(0.855f, 0.761f);
        gl.glVertex2f(0.855f, 0.529f);

        gl.glVertex2f(0.855f, 0.455f);
        gl.glVertex2f(0.855f, 0.227f);
        gl.glVertex2f(0.757f, 0.227f);
        gl.glVertex2f(0.882f, 0.227f);

        gl.glEnd();
        if (bAvail) {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glColor3ubv(CLYellow);
            gl.glVertex2f(0.757f, 0.529f);
            gl.glVertex2f(0.855f, 0.529f);
            gl.glVertex2f(0.855f, 0.545f);
            gl.glVertex2f(0.910f, 0.545f);

            gl.glVertex2f(0.910f, 0.439f);
            gl.glVertex2f(0.855f, 0.439f);
            gl.glVertex2f(0.855f, 0.455f);
            gl.glVertex2f(0.757f, 0.455f);
            gl.glEnd();
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex2f(0.910f, 0.496f);
            gl.glVertex2f(0.922f, 0.496f);
            gl.glVertex2f(0.922f, 0.488f);
            gl.glVertex2f(0.910f, 0.488f);
            gl.glEnd();
        }
    }
}