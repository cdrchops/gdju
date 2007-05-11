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

package com.gerbildrop.dradis.displays.dradis;

import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.util.DradisNode;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.shape.Quad;

public class DradisLocator extends Model {
    protected static final float MAXIMUM = 100f;

    protected Quad gauge;
    protected Quad hudQuad;
    DradisNode dradisBars = new DradisNode();

    public void load() {
        rootNode = new Node("DradisLocator");
        dradisBars.load("dradisDisplay", "dradisQuad", "dradis/displays/dradis/overlay.png");
        rootNode.attachChild(dradisBars.getNode());
        createNubbin(NubbinType.VIPER, "myViper", new Vector3f());
    }

    public void update(String name, Vector3f updatedLocation) {
        Node tmpNode = (Node) rootNode.getChild(name);
        if (tmpNode != null) {
            System.out.println("tmpNode not null");
            final Vector3f loca = new Vector3f(1280 / 2, 0, 974 / 2);
            Vector3f newLoc = new Vector3f(loca.add(updatedLocation));
            Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);
            tmpNode.setLocalTranslation(displayLocation);
        }
    }

    public static enum NubbinType {
        RAPTOR, BATTLESTAR, RAIDER, UNKNOWN, CIVVIE, VIPER, BASESTAR}

    /**
     * brown = raptor grey = galactica or pegasus red = raider, basestar or heavy raider yellow or white = civillian
     * ships blue = player
     */
    public void createNubbin(NubbinType nt, String name, Vector3f location) {
        DradisNode nubbinNode = new DradisNode();

        switch (nt) {
            case VIPER:
                nubbinNode.load("viperIconNode", "viperIconQuad", "dradis/displays/dradis/viperIcon.png");
                break;
            case RAPTOR:

                break;
            case BATTLESTAR:

                break;
            case RAIDER:
                nubbinNode.load("raiderIconNode", "raiderIconQuad", "dradis/displays/dradis/raiderIcon.png");
                break;
            case CIVVIE:

                break;
            case BASESTAR:

                break;
            case UNKNOWN:
            default:


        }

        final Vector3f loca = new Vector3f(nubbinNode.getTextureWidth() / 2, 0, nubbinNode.getTextureWidth() / 2);
        Vector3f newLoc = new Vector3f(loca.add(location));
        Vector3f displayLocation = new Vector3f(newLoc.x, newLoc.z, 0);

        nubbinNode.getNode().setLocalTranslation(displayLocation);

        nubbinNode.getNode().updateRenderState();

        rootNode.attachChild(nubbinNode.getNode());
    }

    private void setColor(Quad hudQuad, NubbinType nt) {
        ColorRGBA cr;

        switch (nt) {
            case VIPER:
                cr = ColorRGBA.blue;
                break;
            case RAPTOR:
                cr = ColorRGBA.brown;
                break;
            case BATTLESTAR:
                cr = ColorRGBA.gray;
                break;
            case RAIDER:
                cr = ColorRGBA.red;
                break;
            case CIVVIE:
                cr = ColorRGBA.white;
                break;
            case BASESTAR:
                cr = ColorRGBA.white;
                break;
            case UNKNOWN:
            default:
                cr = ColorRGBA.yellow;

        }

        hudQuad.setDefaultColor(cr);
    }
}
