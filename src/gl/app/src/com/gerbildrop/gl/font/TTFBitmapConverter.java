package com.gerbildrop.gl.font;

import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gerbildrop.gl.font.TTFBitmapUtil;

public class TTFBitmapConverter {
    public static void main(String[] args) {
        new TTFBitmapConverter().run(args);
    }

    public void run(String[] args) {
        try {
            init(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void init(String[] fontNames) throws Exception {
        if (fontNames == null) {
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();

            fontNames = ge.getAvailableFontFamilyNames();
        }

        for (int names = 0; names < fontNames.length; names++) {
            buildFont(fontNames[names]);
            System.out.println("font " + names + " done: " + fontNames[names]);
        }

    }

    private void buildFont(String fontName) {
        BufferedImage fontImage = TTFBitmapUtil.getFont(fontName);

        // write png file
        File outputFile = new File(fontName + ".png");
        try {
            ImageIO.write(fontImage, "PNG", outputFile);
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.gc();

    }


}