package com.gerbildrop.gl.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ByteOrder;

import org.lwjgl.opengl.GL11;


public class TTFBitmapUtil {
    private static final Color OPAQUE_WHITE = new Color(0xFFFFFFFF, true);
    private static final Color TRANSPARENT_BLACK = new Color(0x00000000, true);

    public static BufferedImage getFont(String fontName) {
        return getFont(fontName, 512, 24);
    }

    public static BufferedImage getFont(String fontName, int bitmapSize, int fontSize) {
        return getFont(fontName, false, bitmapSize, fontSize, false, 0);
    }

    //todo: rewrite so that it can take a
//      URL
//      String
//      BufferedImage

    public static void getGLFont(GLFont print, String fontName) {
        getGLFont(print, fontName, 512, 24);
    }

    public static void getGLFont(GLFont print, String fontName, int bitmapSize, int fontSize) {
        getGLFont(print, getFont(fontName, true, bitmapSize, fontSize, false, 0));
    }

    public static void getGLFont(GLFont print, BufferedImage fontImage) {
        /* The following code is taken directly for the LWJGL example code.
         * It takes a Java Image and converts it into an OpenGL texture.
         * This is a very powerful feature as you can use this to generate textures on the fly out
         * of anything.
         */
        //      Flip Image
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -fontImage.getHeight(null));
        AffineTransformOp op =
                new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        fontImage = op.filter(fontImage, null);

        // Put Image In Memory
        ByteBuffer scratch =
                ByteBuffer.allocateDirect(
                        4 * fontImage.getWidth() * fontImage.getHeight());

        byte data[] =
                (byte[]) fontImage.getRaster().getDataElements(
                        0,
                        0,
                        fontImage.getWidth(),
                        fontImage.getHeight(),
                        null);
        scratch.clear();
        scratch.put(data);
        scratch.rewind();

        // Create A IntBuffer For Image Address In Memory
        IntBuffer buf =
                ByteBuffer
                        .allocateDirect(4)
                        .order(ByteOrder.nativeOrder())
                        .asIntBuffer();
        GL11.glGenTextures(buf); // Create Texture In OpenGL

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, buf.get(0));
        // Typical Texture Generation Using Data From The Image

        // Linear Filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        // Linear Filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        // Generate The Texture
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, fontImage.getWidth(), fontImage.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, scratch);


        print.setTexture(buf.get(0));                           // Return Image Address In Memory

        print.setBase(GL11.glGenLists(256));                    // Storage For 256 Characters

        /* Generate the display lists.  One for each character in the standard/extended ASCII chart.
         */
        float textureDelta = 1.0f / 16.0f;
        for (int i = 0; i < 256; i++) {
            float u = ((float) (i % 16)) / 16.0f;
            float v = 1.f - (((float) (i / 16)) / 16.0f);
            GL11.glNewList(print.getBase() + i, GL11.GL_COMPILE);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, print.getTexture());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(u, v);
            GL11.glVertex3f(-0.0450f, 0.0450f, 0.0f);
            GL11.glTexCoord2f((u + textureDelta), v);
            GL11.glVertex3f(0.0450f, 0.0450f, 0.0f);
            GL11.glTexCoord2f((u + textureDelta), v - textureDelta);
            GL11.glVertex3f(0.0450f, -0.0450f, 0.0f);
            GL11.glTexCoord2f(u, v - textureDelta);
            GL11.glVertex3f(-0.0450f, -0.0450f, 0.0f);
            GL11.glEnd();
            GL11.glEndList();
        }
    }

    private static BufferedImage getFont(String fontName, boolean forOpenGL,
                                         int bitmapSize, int fontSize,
                                         boolean directionSet, int delta) {
        boolean sizeFound = false;
        Font font;
        BufferedImage fontImage;
       /*
        * To find out how much space a Font takes, you need to use a the
        * FontMetrics class. To get the FontMetrics, you need to get it from a
        * Graphics context. A Graphics context is only available from a
        * displayable surface, ie any class that subclasses Component or any
        * Image. First the font is set on a Graphics object. Then get the
        * FontMetrics and find out the width and height of the widest character
        * (W). Then take the largest of the 2 values and find the maximum size
        * font that will fit in the size allocated.
        */
        while (!sizeFound) {
            font = new Font(fontName, Font.PLAIN, fontSize); // Font Name
            // use BufferedImage.TYPE_4BYTE_ABGR to allow alpha blending
            fontImage = new BufferedImage(bitmapSize, bitmapSize,
                                          BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = (Graphics2D) fontImage.getGraphics();
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int width = fm.stringWidth("W");
            int height = fm.getHeight();
            int lineWidth = (width > height) ? width * 16 : height * 16;
            if (!directionSet) {
                if (lineWidth > bitmapSize) {
                    delta = -2;
                } else {
                    delta = 2;
                }
                directionSet = true;
            }
            if (delta > 0) {
                if (lineWidth < bitmapSize) {
                    fontSize += delta;
                } else {
                    sizeFound = true;
                    fontSize -= delta;
                }
            } else if (delta < 0) {
                if (lineWidth > bitmapSize) {
                    fontSize += delta;
                } else {
                    sizeFound = true;
                    fontSize -= delta;
                }
            }
        }

        /*
        * Now that a font size has been determined, create the final image, set
        * the font and draw the standard/extended ASCII character set for that
        * font.
        */
        font = new Font(fontName, Font.BOLD, fontSize); // Font Name
        // use BufferedImage.TYPE_4BYTE_ABGR to allow alpha blending
        fontImage = new BufferedImage(bitmapSize, bitmapSize,
                                      BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) fontImage.getGraphics();
        g.setFont(font);
        if (!forOpenGL) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g.setColor(OPAQUE_WHITE);
        g.setBackground(TRANSPARENT_BLACK);

        FontMetrics fm = g.getFontMetrics();
        if (forOpenGL) {
            for (int i = 0; i < 256; i++) {
                int x = i % 16;
                int y = i / 16;
                char ch[] = {(char) i};
                String temp = new String(ch);
                g.drawString(temp, (x * 32) + 1, (y * 32) + fm.getAscent());
            }
        } else {
            for (int i = 0; i < 256; i++) {
                int x = i % 16;
                int y = i / 16;
                char ch[] = {(char) i};
                String temp = new String(ch);
                g.drawString(temp, (int) ((x * 32) + (16 - (fm.getStringBounds(
                        temp, g).getWidth() / 2))), (y * 32) + fm.getAscent() - 64);
            }
        }

        return fontImage;
    }
}