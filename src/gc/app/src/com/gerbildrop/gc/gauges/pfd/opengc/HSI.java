package com.gerbildrop.gc.gauges.pfd.opengc;

import com.gerbildrop.gc.fonts.Font;
import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.gc.util.CircleEvaluator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class HSI extends BaseGauge {
    public HSI() {
        super(42, 52, 94, 98, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);

        // For drawing circles
        CircleEvaluator aCircle = new CircleEvaluator(gl);

        // First, store the "root" position of the gauge component
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();

        // Move to the center of the window
        gl.glTranslated(47, 49, 0);
        // Rotate based on the bank
        gl.glRotated(Roll, 0, 0, 1);

        // Translate in the direction of the rotation based
        // on the pitch. On the 777, a pitch of 1 degree = 2 mm
        gl.glTranslated(0, Pitch * -2.0, 0);

        //-------------------Gauge Background------------------
        // It's drawn oversize to allow for pitch and bank

        // The "ground" rectangle
        // Remember, the coordinate system is now centered in the gauge component
        gl.glColor3ub((byte) 179, (byte) 102, (byte) 0);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-300, -300);
        gl.glVertex2f(-300, 0);
        gl.glVertex2f(300, 0);
        gl.glVertex2f(300, -300);
        gl.glVertex2f(-300, -300);
        gl.glEnd();

        // The "sky" rectangle
        // Remember, the coordinate system is now centered in the gauge component
        gl.glColor3ub((byte) 0, (byte) 153, (byte) 204);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-300, 0);
        gl.glVertex2f(-300, 300);
        gl.glVertex2f(300, 300);
        gl.glVertex2f(300, 0);
        gl.glVertex2f(-300, 0);
        gl.glEnd();

        //------------Draw the pitch markings--------------

        // Draw in white
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        // Specify line width
        gl.glLineWidth(1.0f);
        // The size for all pitch text

        gl.glBegin(GL.GL_LINES);

        // The dividing line between sky and ground
        gl.glVertex2f(-100, 0);
        gl.glVertex2f(100, 0);

        // +2.5 degrees
        gl.glVertex2f(-5, 5);
        gl.glVertex2f(5, 5);

        // +5.0 degrees
        gl.glVertex2f(-10, 10);
        gl.glVertex2f(10, 10);

        // +7.5 degrees
        gl.glVertex2f(-5, 15);
        gl.glVertex2f(5, 15);

        // +10.0 degrees
        gl.glVertex2f(-20, 20);
        gl.glVertex2f(20, 20);

        // +12.5 degrees
        gl.glVertex2f(-5, 25);
        gl.glVertex2f(5, 25);

        // +15.0 degrees
        gl.glVertex2f(-10, 30);
        gl.glVertex2f(10, 30);

        // +17.5 degrees
        gl.glVertex2f(-5, 35);
        gl.glVertex2f(5, 35);

        // +20.0 degrees
        gl.glVertex2f(-20, 40);
        gl.glVertex2f(20, 40);

        // -2.5 degrees
        gl.glVertex2f(-5, -5);
        gl.glVertex2f(5, -5);

        // -5.0 degrees
        gl.glVertex2f(-10, -10);
        gl.glVertex2f(10, -10);

        // -7.5 degrees
        gl.glVertex2f(-5, -15);
        gl.glVertex2f(5, -15);

        // -10.0 degrees
        gl.glVertex2f(-20, -20);
        gl.glVertex2f(20, -20);

        // -12.5 degrees
        gl.glVertex2f(-5, -25);
        gl.glVertex2f(5, -25);

        // -15.0 degrees
        gl.glVertex2f(-10, -30);
        gl.glVertex2f(10, -30);

        // -17.5 degrees
        gl.glVertex2f(-5, -35);
        gl.glVertex2f(5, -35);

        // -20.0 degrees
        gl.glVertex2f(-20, -40);
        gl.glVertex2f(20, -40);

        gl.glEnd();

        // +10
        Font.display(glAutoDrawable, -27.5f, 18.0f, "10");
        Font.display(glAutoDrawable, 21.0f, 18.0f, "10");
        // -10
        Font.display(glAutoDrawable, -27.5f, -22.0f, "10");
        Font.display(glAutoDrawable, 21.0f, -22.0f, "10");

        // +20
        Font.display(glAutoDrawable, -27.5f, 38.0f, "20");
        Font.display(glAutoDrawable, 21.0f, 38.0f, "20");

        // -20
        Font.display(glAutoDrawable, -27.5f, -42.0f, "20");
        Font.display(glAutoDrawable, 21.0f, -42.0f, "20");

        //-----The background behind the bank angle markings-------
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();

        // Draw in the sky color
        gl.glColor3ub((byte) 0, (byte) 153, (byte) 204);

        aCircle.setOrigin(47, 49);
        aCircle.setRadius(46);
        aCircle.setDegreesPerPoint(5);
        aCircle.setArcStartEnd(300.0, 360.0);

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(0, 98);
        gl.glVertex2f(0, 72);
        aCircle.evaluate();
        gl.glVertex2f(47, 98);
        gl.glEnd();

        aCircle.setArcStartEnd(0.0, 60.0);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(94, 98);
        gl.glVertex2f(47, 98);
        aCircle.evaluate();
        gl.glVertex2f(94, 72);
        gl.glEnd();

        //----------------The bank angle markings----------------

        // Left side bank markings
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();

        // Draw in white
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);

        // Move to the center of the window
        gl.glTranslated(47, 49, 0);

        // Draw the center detent
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(0.0f, 46.0f);
        gl.glVertex2f(-2.3f, 49.0f);
        gl.glVertex2f(2.3f, 49.0f);
        gl.glVertex2f(0.0f, 46.0f);
        gl.glEnd();

        gl.glRotated(10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 53);
        gl.glEnd();

        gl.glRotated(15.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(15.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 51);
        gl.glEnd();

        // Right side bank markings
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();
        // Move to the center of the window
        gl.glTranslated(47, 49, 0);

        gl.glRotated(-10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(-10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(-10.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 53);
        gl.glEnd();

        gl.glRotated(-15.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 49);
        gl.glEnd();

        gl.glRotated(-15.0, 0, 0, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(0, 51);
        gl.glEnd();

        //----------------End Draw Bank Markings----------------

        //----------------Bank Indicator----------------
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();
        // Move to the center of the window
        gl.glTranslated(47, 49, 0);
        // Rotate based on the bank
        gl.glRotated(Roll, 0, 0, 1);

        // Draw in white
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        // Specify line width
        gl.glLineWidth(2.0f);

        gl.glBegin(GL.GL_LINE_LOOP); // the bottom rectangle
        gl.glVertex2f(-4.5f, 39.5f);
        gl.glVertex2f(4.5f, 39.5f);
        gl.glVertex2f(4.5f, 41.5f);
        gl.glVertex2f(-4.5f, 41.5f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINE_STRIP); // the top triangle
        gl.glVertex2f(-4.5f, 41.5f);
        gl.glVertex2f(0, 46);
        gl.glVertex2f(4.5f, 41.5f);
        gl.glEnd();

        //--------------End draw bank indicator------------

        //----------------Attitude Indicator----------------
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();
        // Move to the center of the window
        gl.glTranslated(47, 49, 0);

        // The center axis indicator
        // Black background
        gl.glColor3f((byte) 0, (byte) 0, (byte) 0);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(1.25f, 1.25f);
        gl.glVertex2f(1.25f, -1.25f);
        gl.glVertex2f(-1.25f, -1.25f);
        gl.glVertex2f(-1.25f, 1.25f);
        gl.glVertex2f(1.25f, 1.25f);
        gl.glEnd();

        // White lines
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(1.25f, 1.25f);
        gl.glVertex2f(1.25f, -1.25f);
        gl.glVertex2f(-1.25f, -1.25f);
        gl.glVertex2f(-1.25f, 1.25f);
        gl.glEnd();

        // The left part
        // Black background
        gl.glColor3f((byte) 0, (byte) 0, (byte) 0);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-39, 1.25f);
        gl.glVertex2f(-19, 1.25f);
        gl.glVertex2f(-19, -1.25f);
        gl.glVertex2f(-39, -1.25f);
        gl.glVertex2f(-39, 1.25f);
        gl.glEnd();
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-19, 1.25f);
        gl.glVertex2f(-19, -5.75f);
        gl.glVertex2f(-22, -5.75f);
        gl.glVertex2f(-22, 1.25f);
        gl.glVertex2f(-19, 1.25f);
        gl.glEnd();

        // White lines
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(-39, 1.25f);
        gl.glVertex2f(-19, 1.25f);
        gl.glVertex2f(-19, -5.75f);
        gl.glVertex2f(-22, -5.75f);
        gl.glVertex2f(-22, -1.25f);
        gl.glVertex2f(-39, -1.25f);
        gl.glEnd();

        // The right part
        // Black background
        gl.glColor3f((byte) 0, (byte) 0, (byte) 0);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(39, 1.25f);
        gl.glVertex2f(19, 1.25f);
        gl.glVertex2f(19, -1.25f);
        gl.glVertex2f(39, -1.25f);
        gl.glVertex2f(39, 1.25f);
        gl.glEnd();
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(19, 1.25f);
        gl.glVertex2f(19, -5.75f);
        gl.glVertex2f(22, -5.75f);
        gl.glVertex2f(22, 1.25f);
        gl.glVertex2f(19, 1.25f);
        gl.glEnd();

        // White lines
        gl.glColor3ub((byte) 255, (byte) 255, (byte) 255);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(39, 1.25f);
        gl.glVertex2f(19, 1.25f);
        gl.glVertex2f(19, -5.75f);
        gl.glVertex2f(22, -5.75f);
        gl.glVertex2f(22, -1.25f);
        gl.glVertex2f(39, -1.25f);
        gl.glEnd();

        //--------------End draw attitude indicator------------
        // Reset the modelview matrix
        gl.glPopMatrix();
        gl.glPushMatrix();

        aCircle.setRadius(3.77);

        //-------------------Rounded corners------------------
        // The corners of the artificial horizon are rounded off by
        // drawing over them in black. The overlays are essentially the
        // remainder of a circle subtracted from a square, and are formed
        // by fanning out triangles from a point just off each corner
        // to an arc descrbing the curved portion of the art. horiz.

        gl.glColor3f((byte) 0, (byte) 0, (byte) 0);
        // Lower left
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(-1.0f, -1.0f);
        aCircle.setOrigin(3.77, 3.77);
        aCircle.setArcStartEnd(180, 270);
        aCircle.setDegreesPerPoint(15);
        aCircle.evaluate();
        gl.glEnd();
        // Upper left
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(-1.0f, 99.0f);
        aCircle.setOrigin(3.77, 94.23);
        aCircle.setArcStartEnd(270, 360);
        aCircle.setDegreesPerPoint(15);
        aCircle.evaluate();
        gl.glEnd();
        // Upper right
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(95.0f, 99.0f);
        aCircle.setOrigin(90.23, 94.23);
        aCircle.setArcStartEnd(0, 90);
        aCircle.setDegreesPerPoint(15);
        aCircle.evaluate();
        gl.glEnd();
        //Lower right
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(95.0f, -1);
        aCircle.setOrigin(90.23, 3.77);
        aCircle.setArcStartEnd(90, 180);
        aCircle.setDegreesPerPoint(15);
        aCircle.evaluate();
        gl.glEnd();

        // Finally, restore the modelview matrix to what we received
        gl.glPopMatrix();
    }
}