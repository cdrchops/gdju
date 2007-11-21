/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.test;

import com.gerbildrop.riverhawk.base.JoglView;
import com.gerbildrop.riverhawk.gc.jogl.ArtificialHorizon;
import com.gerbildrop.riverhawk.gc.jogl.CircleGauge;
import com.gerbildrop.riverhawk.gc.jogl.Fctl;
import com.gerbildrop.riverhawk.gc.jogl.HMFD;

/**
 * @author torr
 * @since Nov 20, 2007 - 12:34:03 PM
 */
public class ComponentsView extends JoglView {
//    Rectangle r = new Rectangle();
    ArtificialHorizon ah = new ArtificialHorizon();
    CircleGauge cg = new CircleGauge();
    Fctl fctl = new Fctl();
    HMFD hmfd = new HMFD();

    public void display() {
//        r.draw();

        hmfd.draw();
//        fctl.draw();
//        ah.draw();
//        cg.draw();
    }

    public void initView() {
//        r.setTranslation(50, 50);
//        r.setDimension(10, 20);

//        ah.setTranslation(42, 52);
//        ah.setDimension(94, 98);

//        cg.setTranslation(100, 100);
//        cg.setDimension(180, 180);

//        fctl.setTranslation(100, 100);
//        fctl.setDimension(1000, 1000);

        hmfd.setTranslation(10, 10);
        hmfd.setDimension(1000, 1000);
    }
}
