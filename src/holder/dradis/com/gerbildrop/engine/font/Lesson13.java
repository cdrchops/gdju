package com.gerbildrop.engine.font;

/**
 * @author timo
 * @version ${1.1}
 * @since Apr 1, 2007 6:24:39 PM
 */

/*
 *      This Code Was Created By Jeff Molofee 2000
 *      Modified by Shawn T. to handle (%3.2f, num) parameters.
 *      A HUGE Thanks To Fredric Echols For Cleaning Up
 *      And Optimizing The Base Code, Making It More Flexible!
 *      If You've Found This Code Useful, Please Let Me Know.
 *      Visit My Site At nehe.gamedev.net
 */

import java.text.NumberFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.glu.GLU;

/**
 * @author Mark Bernard date:    26-May-2004
 *         <p/>
 *         Port of NeHe's Lesson 13 to LWJGL Title: Bitmap fonts Uses version 0.9alpha of LWJGL http://www.lwjgl.org/
 *         <p/>
 *         Be sure that the LWJGL libraries are in your classpath
 *         <p/>
 *         Ported directly from the C++ version
 *         <p/>
 *         The main point of this tutorial is to get fonts on the screen.  The original OpenGL did not port directly as
 *         it used Windows specific extensions and I could not get some OpenGL commands to work.  In the end, what you
 *         see on the screen is the same, but it is written somewhat differently.  I have noted the differences in the
 *         code with comments.
 *         <p/>
 *         2004-10-08: Updated to version 0.92alpha of LWJGL. 2004-12-19: Updated to version 0.94alpha of LWJGL
 */
public class Lesson13 {
    private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "NeHe's OpenGL Lesson 13 for LWJGL (Bitmap Fonts)";
    private boolean f1 = false;
    private DisplayMode displayMode;

    private NumberFormat numberFormat = NumberFormat.getInstance();

    private float cnt1;                     // 1st Counter Used To Move Text & For Coloring
    private float cnt2;                     // 2nd Counter Used To Move Text & For Coloring

    public static void main(String args[]) {
        boolean fullscreen = false;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }

        Lesson13 l13 = new Lesson13();
        l13.run(fullscreen);
    }

    public void run(boolean fullscreen) {
        this.fullscreen = fullscreen;
        try {
            init();
            while (!done) {
                mainloop();
                render();
                Display.update();
            }
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void mainloop() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            done = true;
        }
        if (Display.isCloseRequested()) {                     // Exit if window is closed
            done = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F1) && !f1) {    // Is F1 Being Pressed?
            f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if (!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            f1 = false;
        }
    }

    private void switchMode() {
        fullscreen = !fullscreen;
        try {
            Display.setFullscreen(fullscreen);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  // Clear Screen And Depth Buffer
        GL11.glLoadIdentity();                                              // Reset The Current Modelview Matrix

        // Position The Text On The Screen
        GL11.glTranslatef(-0.9f + 0.05f * ((float) Math.cos(cnt1)), 0.32f * ((float) Math.sin(cnt2)), -2.0f);                               // Move One Unit Into The Screen

        // Pulsing Colors Based On Text Position
        float red = 1.0f * ((float) Math.cos(cnt1));
        float green = 1.0f * ((float) Math.sin(cnt2));
        float blue = 1.0f - 0.5f * ((float) Math.cos(cnt1 + cnt2));
        GL11.glColor3f(red, green, blue);

        //format the floating point number to 2 decimal places
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        GLPrint.glPrint("Active OpenGL Text With NeHe - " + numberFormat.format(cnt1));     // Print GL Text To The Screen
        cnt1 += 0.051f;                                                     // Increase The First Counter
        cnt2 += 0.005f;                                                     // Increase The Second Counter

        return true;
    }

    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }

    private void init() throws Exception {
        createWindow();

        initGL();
//        new GLPrint();
    }

    private void initGL() {
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        //GL11.glEnable(GL11.GL_CULL_FACE);
        // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(45.0f,
                           (float) displayMode.getWidth() / (float) displayMode.getHeight(),
                           0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

    private void cleanup() {
        Display.destroy();
    }
}