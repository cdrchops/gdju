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

package com.gerbildrop.dradis.displays.lso;

import com.gerbildrop.dradis.displays.base.BaseDradis;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.util.DradisNode;
import com.gerbildrop.dradis.util.NodeTranslator;

public class DradisLSO extends BaseDradis {
    private DradisNode lsoOverLayBottom = new DradisNode();
    private DradisNode viperSideView = new DradisNode();
    private DradisNode viperTopView = new DradisNode();
    private DradisNode dradisLSOGrid = new DradisNode();
    private DradisNode dradisLSOViperIconSide = new DradisNode();
    private DradisNode dradisLSOViperIconTop = new DradisNode();

    public void load() {
        setName("Dradis LSO");
        super.load();

        lsoOverLayBottom.load("lsoOverlayBottom", "lsoOverlayBottomQuad", "dradis/displays/LSO/dradisLSOBottom.png");
        attachChild(lsoOverLayBottom.getNode());

        NodeTranslator.translate(lsoOverLayBottom.getNode(), Model.YAW, 78);
        NodeTranslator.translate(lsoOverLayBottom.getNode(), Model.PITCH, 49);

        viperSideView.load("viperSideView", "viperSideViewQuad", "dradis/displays/LSO/lsoSide.png", 1.4f);
        attachChild(viperSideView.getNode());

        NodeTranslator.translate(viperSideView.getNode(), Model.YAW, 78);
        NodeTranslator.translate(viperSideView.getNode(), Model.PITCH, 150);

        viperTopView.load("viperTopView", "viperTopViewQuad", "dradis/displays/LSO/lsoTop.png", 1.1f);
        attachChild(viperTopView.getNode());

        NodeTranslator.translate(viperTopView.getNode(), Model.YAW, 78);
        NodeTranslator.translate(viperTopView.getNode(), Model.PITCH, 700);

        dradisLSOGrid.load("dradisLSOGrid", "dradisLSOGridQuad", "dradis/displays/LSO/dradisLSOGrid.png");
        attachChild(dradisLSOGrid.getNode());

        NodeTranslator.translate(dradisLSOGrid.getNode(), Model.YAW, 173);
        NodeTranslator.translate(dradisLSOGrid.getNode(), Model.PITCH, 52);

        dradisLSOViperIconSide.load("dradisLSOViperIconSideGrid", "dradisLSOViperIconSideQuad", "dradis/displays/LSO/viperGlideSlopeIcon.png");
        attachChild(dradisLSOViperIconSide.getNode());

        NodeTranslator.translate(dradisLSOViperIconSide.getNode(), Model.YAW, 550);
        NodeTranslator.translate(dradisLSOViperIconSide.getNode(), Model.PITCH, 200);

        dradisLSOViperIconTop.load("dradisLSOViperIconTopGrid", "dradisLSOViperIconTopQuad", "dradis/displays/LSO/viperGlideSlopeIcon2.png");
        attachChild(dradisLSOViperIconTop.getNode());

        NodeTranslator.translate(dradisLSOViperIconTop.getNode(), Model.YAW, 240);
        NodeTranslator.translate(dradisLSOViperIconTop.getNode(), Model.PITCH, 200);
    }

    public void update(float timer) {
        super.update(timer);
    }
}