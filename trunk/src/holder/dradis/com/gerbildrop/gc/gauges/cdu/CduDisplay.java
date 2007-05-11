package com.gerbildrop.gc.gauges.cdu;

import com.gerbildrop.gc.gauges.pfd.BaseGauge;
import com.gerbildrop.gc.gauges.cdu.pageSelections.PosDisplay;
import com.gerbildrop.gc.gauges.cdu.pageSelections.TacanDisplay;
import com.gerbildrop.gc.gauges.cdu.pageSelections.StrDisplay;
import com.gerbildrop.gc.gauges.cdu.pageSelections.NavDisplay;
import com.gerbildrop.gc.gauges.cdu.pageSelections.InsDisplay;
import com.gerbildrop.gc.gauges.cdu.pageSelections.DestDisplay;
import com.gerbildrop.gc.fonts.Font;

import javax.media.opengl.GLAutoDrawable;

public class CduDisplay extends BaseGauge {
    public CduDisplay() {
        super(50, 50, 125, 125, 4, 4, 0, 0, 1);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        super.display(glAutoDrawable);
        Font.display(glAutoDrawable, 10, 100, "SOFTKEY 1");
        Font.display(glAutoDrawable, 10, 90, "SOFTKEY 3");
        Font.display(glAutoDrawable, 10, 80, "SOFTKEY 5");
        Font.display(glAutoDrawable, 10, 70, "SOFTKEY 7");

        switch (CduManager.displayType) {
            case CduManager.DEST:
                DestDisplay.destDisplay(glAutoDrawable);
                break;
            case CduManager.INS:
                InsDisplay.insDisplay(glAutoDrawable);
                break;
            case CduManager.NAV:
                NavDisplay.navDisplay(glAutoDrawable);
                break;
            case CduManager.STR:
                StrDisplay.strDisplay(glAutoDrawable);
                break;
            case CduManager.TACAN:
                TacanDisplay.tacanDisplay(glAutoDrawable);
                break;
            case CduManager.POS:
                PosDisplay.posDisplay(glAutoDrawable);
                break;
        }

        Font.display(glAutoDrawable, 90, 100, "SOFTKEY 2");
        Font.display(glAutoDrawable, 90, 90, "SOFTKEY 4");
        Font.display(glAutoDrawable, 90, 80, "SOFTKEY 6");
        Font.display(glAutoDrawable, 90, 70, "SOFTKEY 8");
    }
}