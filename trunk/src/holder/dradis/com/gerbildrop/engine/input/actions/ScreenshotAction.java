package com.gerbildrop.engine.input.actions;

import java.io.File;

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
        if (number) {
            int n = 1;
            while (new File(filename + n + ".png").exists()) {
                n++;
            }
            DisplaySystem.getDisplaySystem().getRenderer().takeScreenShot(filename + n);
        } else {
            DisplaySystem.getDisplaySystem().getRenderer().takeScreenShot(filename);
        }
    }
}
