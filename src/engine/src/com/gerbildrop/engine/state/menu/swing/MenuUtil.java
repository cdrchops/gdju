package com.gerbildrop.engine.state.menu.swing;

import javax.swing.JComponent;

public final class MenuUtil {
    public static void makeAllOpaque(JComponent c, boolean opaque) {
        c.setOpaque(opaque);
        for (int i = 0; i < c.getComponentCount(); i++) {
            if (c.getComponent(i) instanceof JComponent) {
                makeAllOpaque((JComponent) c.getComponent(i), opaque);
            }
        }
    }
}
