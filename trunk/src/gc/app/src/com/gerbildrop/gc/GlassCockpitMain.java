package com.gerbildrop.gc;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

/** This is a basic JOGL app. Feel free to reuse this code or modify it. */
public class GlassCockpitMain extends JFrame {

    static GLCanvas glcanvas = null;

    public static void main(final String[] args) {
        final GlassCockpitMain app = new GlassCockpitMain();

        // show what we've done
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                        glcanvas.requestFocusInWindow();
                    }
                }
        );
    }

    public GlassCockpitMain() {
        //set the JFrame title
        super("Glass Cockpit test");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create our FirstPersonView which serves two purposes
        // 1) it is our GLEventListener, and
        // 2) it is our KeyListener
        final GlassCockpitView fpv = new GlassCockpitView();

        //only three JOGL lines of code ... and here they are
        final GLCapabilities glcaps = new GLCapabilities(GLProfile.getDefault());
        glcanvas = new GLCanvas(glcaps);
        glcanvas.addGLEventListener(fpv);
        glcanvas.addKeyListener(fpv);

        //we'll want this for our repaint requests
        fpv.setGLCanvas(glcanvas);

        //add the GLCanvas just like we would any Component
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(1000, 900);

        //center the JFrame on the screen
        centerWindow(this);
    }

    public void centerWindow(final Component frame) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = frame.getSize();

        if (frameSize.width > screenSize.width) frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;

        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }
}