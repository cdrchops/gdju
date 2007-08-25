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

package com.gerbildrop.loader.gui;

import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.jme.math.Vector3f;

/**
 * HoverButton has two states:
 *  Normal - what the display should look like all the time
 *  Hover - what the display shows for the image when the cursor is over it
 *
 * @author vivaldi
 * @version $Id: HoverButton.java,v 1.4 2007/04/29 17:48:35 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class HoverButton extends Button {
    private DradisDisplayBase normal = new DradisDisplayBase();
    private DradisDisplayBase hover = new DradisDisplayBase();
    private boolean selected = false;

    public HoverButton(String name) {
        super();
        setName(name);
    }

    public HoverButton(String name, Vector3f position) {
        this(name);
        setLocalTranslation(position);
    }

    public void initialize(DradisDisplayBase normal, DradisDisplayBase hover, Vector3f position) {
        this.normal = normal;
        this.hover = hover;
        setPosition(position);
        attachChild(hover.getHudQuad());
        attachChild(normal.getHudQuad());
    }


    public void setPosition(Vector3f position) {
        normal.getHudQuad().setLocalTranslation(position);
        hover.getHudQuad().setLocalTranslation(position);
    }

    public DradisDisplayBase getHover() {
        return hover;
    }

    public void setHover(DradisDisplayBase hover) {
        this.hover = hover;
    }

    public DradisDisplayBase getNormal() {
        return normal;
    }

    public void setNormal(DradisDisplayBase normal) {
        this.normal = normal;
    }

    public void selected(float x, float y) {
        Vector3f optionV = normal.getHudQuad().getWorldTranslation();
        if (x >= optionV.x
            && x <= normal.getTextureWidth()) {
            if (y >= optionV.y
                && y <= optionV.y + normal.getTextureHeight()) {
                if (!selected) {
                    selected = true;
                    normal.getHudQuad().setLocalTranslation(normal.getHudQuad().getLocalTranslation().subtract(0, 0, 2));
                    hover.getHudQuad().setLocalTranslation(hover.getHudQuad().getLocalTranslation().add(0, 0, 1));
                    updateRenderState();
                }
            } else {
                if (selected) {
                    selected = false;
                    normal.getHudQuad().setLocalTranslation(normal.getHudQuad().getLocalTranslation().add(0, 0, 2));
                    hover.getHudQuad().setLocalTranslation(hover.getHudQuad().getLocalTranslation().subtract(0, 0, 1));
                    updateRenderState();
                }
            }
        } else {
            if (selected) {
                selected = false;
                normal.getHudQuad().setLocalTranslation(normal.getHudQuad().getLocalTranslation().add(0, 0, 2));
                hover.getHudQuad().setLocalTranslation(hover.getHudQuad().getLocalTranslation().subtract(0, 0, 1));
                updateRenderState();
            }
        }
    }
}
