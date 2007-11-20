/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.test;

import com.gerbildrop.riverhawk.base.JoglView;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author torr
 * @since Nov 20, 2007 - 12:00:39 PM
 */
public class JoglTest extends JFrame {

    static GLCanvas glcanvas = null;

    public static void main(String[] args) {
        final JoglTest app = new JoglTest(new ComponentsView());

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

    public JoglTest(JoglView fpv) {
        //set the JFrame title
        super("JOGL Components test");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //only three JOGL lines of code ... and here they are
        GLCapabilities glcaps = new GLCapabilities();
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

    public void centerWindow(Component frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        if (frameSize.width > screenSize.width) frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;

        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }
}