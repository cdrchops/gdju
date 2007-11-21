/**
 * $ID$
 * $COPYRIGHT$
 */
package com.gerbildrop.riverhawk.gc.jogl;

import com.gerbildrop.riverhawk.displayComponents.BaseComponent;
import com.gerbildrop.riverhawk.displayComponents.Rectangle;
import com.gerbildrop.riverhawk.fonts.Font;

import javax.media.opengl.GL;


/**
 * @author torr
 * @since Nov 21, 2007 - 12:27:16 PM
 */
public class HMFD extends BaseComponent {
    protected void drawJOGL(final GL gl) {

        Font.display(1, 1, "#1 FUEL");
        Font.display(1, 4, "#1 FUEL");


        Font.display(50, 5, "#1 GEN");
        Font.display(50, 1, "#1 GEN BRG");

        Font.display(150, 1, "#2 FUEL");
        Font.display(150, 4, "#2 FUEL");


        Font.display(100, 5, "#2 GEN");
        Font.display(100, 1, "#2 GEN BRG");
    }

    protected void drawLWJGL() {
    }
}
