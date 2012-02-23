package com.gerbildrop.gc.display;

import com.gerbildrop.gc.fonts.Font;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class MasterCaution {
    public void display(GLAutoDrawable glAutoDrawable) {
        drawMasterCautionAdvisoryGrid(glAutoDrawable);
    }

    private void drawMasterCautionAdvisoryGrid(GLAutoDrawable glAutoDrawable) {
        drawWarning(glAutoDrawable, 100, 100, "FPs Counter= test");
        drawWarning(glAutoDrawable, 100, 200, "FPS Counter = test");
    }

    private void drawWarning(GLAutoDrawable glAutoDrawable, int x, int y, String display) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        //draw box with position x & y
        // then draw font with offsets that are needed
        // adjust font display x & y for offsets
//        gl.glTranslated(0, 0, 2);

//        float red = 0.0f;
//        float green = 0.9f;
//        float blue = 0.1f;
//        gl.glColor3f(red, green, blue);
//
//        gl.glBegin(GL.GL_QUADS);
//        gl.glVertex3f(-1.0f, 1.0f, 0.0f);
//        gl.glVertex3f(1.0f, 1.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, 0.0f);
//        gl.glEnd();

        Font.display(glAutoDrawable, x, y, display);
    }

    private static void drawOneLine(GL2 gl, float x1, float y1, float x2, float y2) {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f((x1), (y1));
        gl.glVertex2f((x2), (y2));
        gl.glEnd();
    }
}