package com.gerbildrop.gc.fonts;

/*
 * Created on Dec 7, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
/**
 * adapted for gdju and jogl from code provided at this forum post:
 * http://lwjgl.org/forum/viewtopic.php?t=1258
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;


/** @author Jeremy Adams (elias4444) */
public class FontTranslator {

    private float textureratio = 1.0f;
    private Texture[] characters;
    private HashMap charlist = new HashMap();
    private TextureLoader textureloader;
    private int kerneling = 2;
    private String fontname = "impact.ttf";
    private int fontsize = 14;
    private Font font;

    private class IntObject {
        public int charnum;

        IntObject(int charnumpass) {
            charnum = charnumpass;
        }
    }

    public FontTranslator(GL gl, float whatratio, String fontnamepass, String fontstylepass, int fontsizepass, int kernelpass, int widthadd, int heightadd) {
        textureratio = whatratio;
        textureloader = new TextureLoader(textureratio);
        kerneling = (int) (kernelpass * textureratio);
        fontname = fontnamepass;
        fontsize = fontsizepass;

        characters = new Texture[256];

        try {
            ClassLoader c = Thread.currentThread().getContextClassLoader();

            InputStream in = c.getResourceAsStream(fontname + ".ttf");

            Font tempfont = Font.createFont(Font.TRUETYPE_FONT, in);
            in.close();
            if (fontstylepass.equals("BOLD")) {
                font = tempfont.deriveFont(Font.BOLD, fontsize);
            } else if (fontstylepass.equals("ITALIC")) {
                font = tempfont.deriveFont(Font.ITALIC, fontsize);
            } else {
                font = tempfont.deriveFont(Font.PLAIN, fontsize);
            }
        } catch (FontFormatException e1) {
            e1.printStackTrace();
            System.out.println("ERROR -- FontFormat is invalid!");
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("ERROR -- Font not found!");
        }

        try {
            for (int i = 0; i < 256; i++) {
                char ch[] = {(char) i}; //which character are we dealing with
                String temp = new String(ch);

                // Create a temporary image to extract font size
                BufferedImage tempfontImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = (Graphics2D) tempfontImage.getGraphics();
                //// Add AntiAliasing /////
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                ///////////////////////////
                g.setFont(font);
                FontMetrics fm = g.getFontMetrics();
                int charwidth = fm.stringWidth(temp) + widthadd;
                if (charwidth <= 0) {
                    charwidth = 1;
                }
                int charheight = fm.getHeight() + heightadd;
                if (charheight <= 0) {
                    charheight = fontsize;
                }

                // Create another image for texture creation
                BufferedImage fontImage = new BufferedImage(charwidth, charheight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D gt = (Graphics2D) fontImage.getGraphics();
                //// Add AntiAliasing /////
                gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                ///////////////////////////
                gt.setFont(font);

                gt.setColor(Color.WHITE);
                int charx = 0;
                int chary = 0;
                gt.drawString(temp, (charx), (chary) + fm.getAscent());

                String temptexname = "Char." + i;
                characters[i] = textureloader.getTexture(gl, temptexname, fontImage);

                charlist.put(temp, new IntObject(i));

                fontImage = null;
                tempfontImage = null;
                g = null;
                gt = null;
            }
        } catch (IOException e) {
            System.out.println("FAILED!!!");
            e.printStackTrace();
        }


    }

    public void drawText(GLAutoDrawable glAutoDrawable, String whatchars, float x, float y, float z, float a, float rotxpass, float rotypass, float rotzpass) {
        GL gl = glAutoDrawable.getGL();
        float totalwidth = 0;
        int k = 0;
        for (int i = 0; i < whatchars.length(); i++) {
            String tempstr = whatchars.substring(i, i + 1);
            k = ((IntObject) (charlist.get(tempstr))).charnum;
            drawtexture(gl, characters[k], x + totalwidth, y, z, 1, 1, 1, a, rotxpass, rotypass, rotzpass);
            totalwidth += characters[k].getImageWidth() + kerneling;
        }

    }


    public void drawText(GL gl, String whatchars, float x, float y, float z, float red, float green, float blue, float a, float rotxpass, float rotypass, float rotzpass) {
        float totalwidth = 0;
        int k = 0;
        for (int i = 0; i < whatchars.length(); i++) {
            String tempstr = whatchars.substring(i, i + 1);
            k = ((IntObject) (charlist.get(tempstr))).charnum;
            drawtexture(gl, characters[k], x + totalwidth, y, z, red, green, blue, a, rotxpass, rotypass, rotzpass);
            totalwidth += characters[k].getImageWidth() + kerneling;
        }

    }


    public int getWidth(String whatchars) {
        float totalwidth = 0;
        int k = 0;
        for (int i = 0; i < whatchars.length(); i++) {
            String tempstr = whatchars.substring(i, i + 1);
            k = ((IntObject) (charlist.get(tempstr))).charnum;
            totalwidth += characters[k].getImageWidth() + kerneling;
        }
        return (int) totalwidth;

    }


    private void drawtexture(GL gl, Texture texture, float x, float y, float z, float red, float green, float blue, float a, float rotx, float roty, float rotz) {
//      boolean islightingon = gl.glIsEnabled(GL.GL_LIGHTING);

//      if (islightingon) {
//         gl.glDisable(GL.GL_LIGHTING);
//      }

        float imgwidth = texture.getImageWidth();
        float imgheight = -texture.getImageHeight();
        float texwidth = texture.getWidth();
        float texheight = texture.getHeight();

        // store the current model matrix
        gl.glPushMatrix();

        // bind to the appropriate texture for this sprite
        texture.bind();

        // translate to the right location and prepare to draw
        gl.glTranslatef(x, y, z);
//      gl.glRotatef(rotx,1,0,0);
//      gl.glRotatef(roty,0,1,0);
//      gl.glRotatef(rotz,0,0,1);
        gl.glColor3f(red, green, blue);//, a

        // draw a quad textured to match the sprite
        gl.glBegin(GL.GL_QUADS);
        {
            gl.glTexCoord2f(0, 0);
            gl.glVertex2f(0, 0);
            gl.glTexCoord2f(0, texheight);
            gl.glVertex2f(0, imgheight);
            gl.glTexCoord2f(texwidth, texheight);
            gl.glVertex2f(imgwidth, imgheight);
            gl.glTexCoord2f(texwidth, 0);
            gl.glVertex2f(imgwidth, 0);
        }
        gl.glEnd();

        // restore the model view matrix to prevent contamination
        gl.glPopMatrix();

//      if (islightingon) {
//         gl.glEnable(GL.GL_LIGHTING);
//      }
    }
}