package com.gerbildrop.gc.gauges.cdu;

import javax.media.opengl.GLAutoDrawable;

public class CDU {
    public void display(GLAutoDrawable glAutoDrawable) {
//        ArtificialHorizon ah = new ArtificialHorizon();
//        ah.display(glAutoDrawable);
        CduDisplay cd = new CduDisplay();
        cd.display(glAutoDrawable);
    }
}
