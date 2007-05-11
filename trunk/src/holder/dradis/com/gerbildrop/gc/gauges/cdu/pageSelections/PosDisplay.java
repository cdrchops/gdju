package com.gerbildrop.gc.gauges.cdu.pageSelections;

import com.gerbildrop.gc.fonts.Font;

import javax.media.opengl.GLAutoDrawable;

public class PosDisplay {
     public static String displayPos = "UTM";

     public static void posDisplay(GLAutoDrawable glAutoDrawable) {
        //19x8
        Font.display(glAutoDrawable, 30, 100, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 95, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 90, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 85, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 80, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 75, "|----------------------------|");
        Font.display(glAutoDrawable, 30, 70, "|------------------------" + displayPos + "-|");
        Font.display(glAutoDrawable, 30, 65, "|----------------------------|");
    }
}