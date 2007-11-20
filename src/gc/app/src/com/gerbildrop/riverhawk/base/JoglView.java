package com.gerbildrop.riverhawk.base;

import com.gerbildrop.riverhawk.base.Configuration;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * For our purposes only two of the GLEventListeners matter. Those would be init() and display().
 *
 */
public abstract class JoglView implements GLEventListener, KeyListener {
    int xPosition = 0;
    int zPosition = 0;

    GLCanvas glc;

    /** Take care of drawing here. */
    public void display(GLAutoDrawable glAutoDrawable) {
        initView(glAutoDrawable);
        Configuration.setGL(glAutoDrawable.getGL());
        display();
    }

    public abstract void display();

    private void initView(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        GLU glu = new GLU();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        //Define points for eye, at and up.
        //This is your camera. It ALWAYS goes
        //in the GL_MODELVIEW matrix.
        glu.gluLookAt(
                xPosition, 0, zPosition,
                xPosition, 0, (zPosition + 20),
                0, 1, 0
        );

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        initView();
    }

    public abstract void initView();

    /** Take care of initialization here. */
    public void init(GLAutoDrawable glAutoDrawable) {
        //The mode is GL.GL_MODELVIEW by default
        //We will also use the default ViewPort
        GL gl = glAutoDrawable.getGL();
        GLU glu = new GLU();

        //We're changing the mode to GL.GL_PROJECTION
        //This is where we set up the camera
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Aspect is width/height
        double w = (glAutoDrawable).getWidth();
        double h = (glAutoDrawable).getHeight();
        double aspect = w / h;
        //When using gluPerspective near and far need
        //to be positive.
        //The arguments are:
        //fovy, aspect, near, far
        glu.gluPerspective(60.0, aspect, 2.0, 20.0);
    }

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }

    public void keyTyped(KeyEvent e) {
        glc.repaint();
    }

    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {}
}