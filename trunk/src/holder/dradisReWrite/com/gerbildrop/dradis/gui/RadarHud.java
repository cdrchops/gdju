/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.gui;

import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.shape.Disk;

/**
 * @author vivaldi
 * @version $Id: RadarHud.java,v 1.4 2007/04/04 14:30:54 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class RadarHud extends BaseHud {
    private final Vector3f mOrigin = new Vector3f();
    private final Vector3f mDirection = new Vector3f();
    private Vector3f[] mContacts = new Vector3f[0];

    private float mRange = 500;
    private int mMaxLog = 4;
    private final static String[] mRangeDecals = {"10", "100", "1000", "10000"};
    private final static double LOG10 = Math.log(10.0);
//    private static final Dimension PREFERRED_SIZE = new Dimension(128, 128);

    public void update(float time) {
//        Rectangle bds = null;//this.getBounds();
//        Graphics2D g = (Graphics2D) graphics;

        int diameter = (275 / 2) - 6;
        int radius = diameter >> 1;
        int centerX = 275 / 2 >> 1;
        int centerY = 266 / 2 >> 1;
        int bdsX = centerX - radius;
        int bdsY = centerY - radius;
        Disk dsk = new Disk("radarHud", 100, 100, radius);
        dsk.setRenderQueueMode(Renderer.QUEUE_ORTHO);

//        g.setPaint(BASE_COLOR);
//        g.fillOval(bdsX, bdsY, diameter, diameter);

//        // the circles stuff looks better when antialiased

//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g.setPaint(DARK_BORDER_COLOR);
//        g.drawArc(bdsX, bdsY, diameter, diameter, 45 + 180, 180);
//
//        g.setPaint(BRIGHT_BORDER_COLOR);
//        g.drawArc(bdsX, bdsY, diameter, diameter, 45, 180);
//

//        // draw the range helpers

//        g.setPaint(Color.DARK_GRAY);
//        g.setFont(mFont);
        int helperincr = radius / (mMaxLog - 1);
        int helperradius = helperincr;
        for (int i = 1; i < mMaxLog - 1; i++) {
            int opening = 50 - i * 10;
//            drawCircle(g, centerX, centerY, helperradius, 90, 360 - opening);
//            g.drawString(mRangeDecals[i], centerX, centerY - helperradius + 4);
            helperradius += helperincr;
        }

//        // Heading indicator

//        g.setPaint(HEADING_COLOR);
//        g.drawLine(centerX, centerY, centerX + (int) (radius * mDirection.x), centerY + (int) (radius * mDirection.z));
//        // the raw dots don't need to be antialiased

//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
        int squareradius = (diameter * diameter >> 2) - 4;
//        g.setPaint(POINT_COLOR);
        for (Vector3f mContact : mContacts) {
            float distX = mContact.x - mOrigin.x;
            float distY = mContact.z - mOrigin.z;
            double dist = Math.sqrt((double) (distX * distX + distY * distY));

            double log = (Math.log(dist) / LOG10) - 1.0;

            distX *= (1.0 / dist * log / (mMaxLog - 1) * radius);
            distY *= (1.0 / dist * log / (mMaxLog - 1) * radius);

            int x = (int) distX;
            int y = (int) distY;

            if ((x * x + y * y) < squareradius) {
//                g.fillRect(x + centerX, y + centerY, 2, 2);
            }
        }
    }

    public void setOrigin(Vector3f position, double direction) {
        mOrigin.set(position);
        mDirection.x = (float) -Math.sin(direction);
        mDirection.z = (float) -Math.cos(direction);
        mDirection.y = 0;
    }

    public void setOrigin(Vector3f position, Vector3f direction) {
        mOrigin.set(position);
        mDirection.add(direction).normalize();
    }

    public void setContacts(Vector3f[] contacts) {
        mContacts = contacts;
    }
}