/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.test;

import com.gerbildrop.riverhawk.base.JoglView;
import com.gerbildrop.riverhawk.gc.ArtificialHorizon;
import com.gerbildrop.riverhawk.gc.CircleGauge;
import com.gerbildrop.riverhawk.gc.Fctl;

/**
 * @author torr
 * @since Nov 20, 2007 - 12:34:03 PM
 */
public class ComponentsView extends JoglView {
//    Rectangle r = new Rectangle();
    ArtificialHorizon ah = new ArtificialHorizon();
    CircleGauge cg = new CircleGauge();
    Fctl fctl = new Fctl();

    public void display() {
//        r.draw();

        fctl.draw();
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

        fctl.setTranslation(100, 100);
        fctl.setDimension(1000, 1000);
    }
}
