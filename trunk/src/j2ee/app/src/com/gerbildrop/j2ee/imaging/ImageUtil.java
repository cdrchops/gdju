/*
 * Copyright (c) 2003-2006, GerbilDrop Java Utilities
 * http://gerbildrop.com
 * http://sourceforge.net/projects/gerbildrop
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Gerbildrop, GDJU, Gerbildrop Game Engine, Austin, StandTrooper, nor the
 * names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot;
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Copyright (c) 2006, Your Corporation. All Rights Reserved.
 */

package com.gerbildrop.j2ee.imaging;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Set;
import java.util.HashSet;

public class ImageUtil {
    private GraphicsConfiguration gc;

    public Image getMirrorImage(Image image) {
        return getScaledImage(image, -1, 1);
    }

    public Image getFlippedImage(Image image) {
        return getScaledImage(image, 1, -1);
    }

    private Image getScaledImage(Image image, float x, float y) {
        // set up the transform
        AffineTransform transform = new AffineTransform();
        transform.scale(x, y);
        transform.translate(
                (x - 1) * image.getWidth(null) / 2,
                (y - 1) * image.getHeight(null) / 2);

        // create a transparent (not translucent) image
        Image newImage = gc.createCompatibleImage(
                image.getWidth(null),
                image.getHeight(null),
                Transparency.BITMASK);

        // draw the transformed image
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(image, transform, null);
        g.dispose();

        return newImage;
    }

    public static boolean endsWithValidImageExtension(String strToEvaluate) {
        int endDot = strToEvaluate.lastIndexOf(".");
        return contains(strToEvaluate.substring(endDot + 1));
    }

    private static final Set<String> validImageExtensions;

    static {
        validImageExtensions = new HashSet<String>();
        validImageExtensions.add("jpg");
        validImageExtensions.add("jpeg");
        validImageExtensions.add("JPG");
        validImageExtensions.add("JPEG");
        validImageExtensions.add("gif");
        validImageExtensions.add("GIF");
        validImageExtensions.add("tiff");
        validImageExtensions.add("TIFF");
        validImageExtensions.add("tif");
        validImageExtensions.add("TIF");
        validImageExtensions.add("PNG");
        validImageExtensions.add("png");
    }

    public static boolean contains(String extension) {
        return validImageExtensions.contains(extension);
    }
}