package com.gerbildrop.riverhawk.base;

import javax.media.opengl.GL;

/**
 * $ID:$
 * $COPYRIGHT:$
 *
 * @author torr
 * @since Nov 20, 2007 - 11:52:34 AM
 */
public class Configuration {
    public static final DisplayRenderer DISPLAY_RENDERER = DisplayRenderer.JOGL;

    private static GL gl;

    public static GL getGL() {
        return gl;
    }

    public static void setGL(GL _gl) {
        gl = _gl;
    }
}
