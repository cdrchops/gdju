package com.gerbildrop.gc;

import com.gerbildrop.gc.display.MFD;
import com.gerbildrop.gc.display.MfdKeys;
import com.gerbildrop.gc.gauges.cdu.CDU;
import com.gerbildrop.gc.gauges.cdu.CduKeys;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** For our purposes only two of the GLEventListeners matter. Those would be init() and display(). */
public class GlassCockpitView implements GLEventListener, KeyListener {
    int xPosition = 0;
    int zPosition = 0;

    float red = 0.0f;
    float green = 0.0f;
    float blue = 1.0f;

    GLCanvas glc;
    private static final MFD mfd = new MFD();
    private static final CDU cdu = new CDU();

    /** Take care of drawing here. */
    public void display(final GLAutoDrawable glAutoDrawable) {
        initView(glAutoDrawable);

        if (MFD) {
            mfd.display(glAutoDrawable);
        } else {
            cdu.display(glAutoDrawable);
        }
//        FontTranslator font = new FontTranslator(gl, 1.0f, "impact", "", 12, 1, 1, 1);
//        font.drawText(gl, "T", 100, 100, 0, 255, 255, 0, 0, 0, 0, 0);
//        Font.display(glAutoDrawable, 75, 75, "This is the other String");
    }

    private void initView(final GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        final GLU glu = new GLU();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
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
    }

    /** Take care of initialization here. */
    public void init(final GLAutoDrawable glAutoDrawable) {
        //The mode is GL.GL_MODELVIEW by default
        //We will also use the default ViewPort
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        final GLU glu = new GLU();

        //We're changing the mode to GL.GL_PROJECTION
        //This is where we set up the camera
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Aspect is width/height
        final double w = (glAutoDrawable).getWidth();
        final double h = (glAutoDrawable).getHeight();
        final double aspect = w / h;
        //When using gluPerspective near and far need
        //to be positive.
        //The arguments are:
        //fovy, aspect, near, far
        glu.gluPerspective(60.0, aspect, 2.0, 20.0);
    }

    public void setGLCanvas(final GLCanvas glc) {
        this.glc = glc;
    }

    public static boolean MFD = true;

    public void keyTyped(final KeyEvent e) {

        if (e.getKeyChar() == '`') {
            MFD = !MFD;
        }

        if (MFD) {
            MfdKeys.checkKeys(e);
        } else {
            CduKeys.checkKeys(e);
        }

        glc.repaint();
    }

    public void displayChanged(final GLAutoDrawable glAutoDrawable,
                               final boolean b,
                               final boolean b1) {
    }

    public void keyPressed(final KeyEvent e) {
    }

    public void keyReleased(final KeyEvent e) {
    }

    public void reshape(final GLAutoDrawable glAutoDrawable,
                        final int i,
                        final int i1,
                        final int i2,
                        final int i3) {
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
    }
}