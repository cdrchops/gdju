package com.gerbildrop.engine.input.actions;

import java.io.File;

import com.jme.renderer.Renderer;
import com.jme.system.DisplaySystem;

/** @author Matthew D. Hicks */
public class ScreenshotAction implements Runnable {
    private String filename;
    private boolean number;

    public ScreenshotAction(String filename, boolean number) {
        this.filename = filename;
        this.number = number;
    }

    public void run() {
        Renderer renderer = DisplaySystem.getDisplaySystem().getRenderer();
        if (number) {
            int n = 1;

            while (new File(filename + n + ".png").exists()) {
                n++;
            }

            renderer.takeScreenShot(filename + n);
        } else {
            renderer.takeScreenShot(filename);
        }
    }
}
