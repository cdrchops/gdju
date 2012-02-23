package com.gerbildrop.gc.display;

import com.gerbildrop.gc.data.DataSource;
import com.gerbildrop.gc.gauges.pfd.ArtificialHorizon;
import com.gerbildrop.gc.gauges.pfd.a340.Door;
import com.gerbildrop.gc.gauges.pfd.a340.Fctl;
import com.gerbildrop.gc.gauges.pfd.a340.Hydraulics;
import com.gerbildrop.gc.gauges.pfd.a340.NdArc;
import com.gerbildrop.gc.gauges.pfd.a340.NdPlan;
import com.gerbildrop.gc.gauges.pfd.a340.NdRose;
import com.gerbildrop.gc.gauges.pfd.a340.NdRoseIls;
import com.gerbildrop.gc.gauges.pfd.a340.NdRoseVor;
import com.gerbildrop.gc.gauges.pfd.a340.PFD;
import com.gerbildrop.gc.gauges.pfd.opengc.AltitudeTape;
import com.gerbildrop.gc.gauges.pfd.opengc.AltitudeTicker;
import com.gerbildrop.gc.gauges.pfd.opengc.HSI;
import com.gerbildrop.gc.gauges.pfd.opengc.HeadingIndicator;
import com.gerbildrop.gc.gauges.pfd.opengc.SpeedTape;
import com.gerbildrop.gc.gauges.pfd.opengc.SpeedTicker;
import com.gerbildrop.gc.gauges.pfd.opengc.VSI;

import javax.media.opengl.GLAutoDrawable;

public class MFD {
    public MFD() {
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        //todo: extend classes so that I can position them via a conf file
        //or from the MFD directly
//        CircleGauge cg = new CircleGauge();

        switch (DataSource.getDisplay()) {
            case 5:
                //this is where we try to recreate the Blackhawk display
                ArtificialHorizon ah = new ArtificialHorizon();
                ah.display(glAutoDrawable);
                break;
            case 8:
                Fctl fctl = new Fctl();
                Door d = new Door();
                Hydraulics h = new Hydraulics();
                PFD pfd = new PFD();
                //FCTL, Hydraulics, PFD and Door notice from A340 air bus project
                fctl.display(glAutoDrawable);
                h.display(glAutoDrawable);
                pfd.display(glAutoDrawable);
                d.display(glAutoDrawable);
                break;
            case 9:
                //nav/nd classes from A340 AirBus project
                new NdRoseVor().display(glAutoDrawable);
                new NdRoseIls().display(glAutoDrawable);
                new NdRose().display(glAutoDrawable);
                new NdPlan().display(glAutoDrawable);
                new NdArc().display(glAutoDrawable);
                break;
                //circle gauge test
//        cg.display(glAutoDrawable);

            case 0:
                HSI hsi = new HSI();
                VSI vsi = new VSI();
                AltitudeTape alt = new AltitudeTape();
                AltitudeTicker at = new AltitudeTicker();
                HeadingIndicator hi = new HeadingIndicator();
                SpeedTape st = new SpeedTape();
                SpeedTicker sti = new SpeedTicker();
                //from OpenGC, ported to Java
                hsi.display(glAutoDrawable);
                alt.display(glAutoDrawable);
                at.display(glAutoDrawable);
                hi.display(glAutoDrawable);
                vsi.display(glAutoDrawable);
                st.display(glAutoDrawable);
                sti.display(glAutoDrawable);
                break;

        }
    }
}