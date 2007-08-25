package com.gerbildrop.gc.gauges.cdu.pageSelections;

import javax.media.opengl.GLAutoDrawable;

import com.gerbildrop.gc.fonts.Font;

public class StrDisplay {
    public static void strDisplay(GLAutoDrawable glAutoDrawable) {
        //19x8
        Font.display(glAutoDrawable, 30, 100, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 95, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 90, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 85, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 80, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 75, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 70, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 65, "|----------------------------|");
    }
}