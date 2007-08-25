package com.gerbildrop.gl.font;

import org.lwjgl.opengl.GL11;

/**
 * I didn't write this whole class... it came from a MessageBoard somewhere that I don't remember
 * where.  I've modified it severely to handle my needs
 *
 * @author timo
 * @version ${1.1}
 * @since Apr 1, 2007 6:24:23 PM
 */
public class GLPrint {
    GLFont font;

    public GLPrint(String fontName) {
        font = new GLFont();
        TTFBitmapUtil.getGLFont(font, fontName);
    }

    public GLPrint(GLFont _font) {
        font = _font;
    }

    /**
     * I didn't write this method either, but I did modify it from the previous version
     *
     * the previous author left the following note:
     * Some liberties had to be taken with this method.  I could not get the glCallLists() to work,
     * so it is done manually instead.
     *
     * @param x float
     * @param y float
     * @param msg String
     */
    public void print(float x, float y, String msg) {
        //no font, don't do anything
        if (font == null) {
            return;
        }

//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();

        // Set position and size
        GL11.glTranslated(x, y, 0);
        GL11.glScalef(0.5f, 0.5f, 1);

        // Draw using the triangulated font
        GL11.glRasterPos3d(x, y, 0);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTexture());

        for (int i = 0; i < msg.length(); i++) {
            GL11.glCallList(font.getBase() + msg.charAt(i));
            GL11.glTranslatef(0.05f, 0.0f, 0.0f);
        }

        GL11.glTranslated(-x, -y, 0);

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        // Restore modelview matrix
        GL11.glPopMatrix();
    }
}