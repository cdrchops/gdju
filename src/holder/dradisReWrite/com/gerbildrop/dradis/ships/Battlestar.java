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

package com.gerbildrop.dradis.ships;

import com.gerbildrop.dradis.loaders.DradisModelStorage;
import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.util.NodeRotator;
import com.jme.scene.Node;

/**
 * @author vivaldi
 * @version $Id: Battlestar.java,v 1.3 2007/04/04 14:30:56 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class Battlestar {
    Model battlestarModel = null;

    public static enum BattlestarType {
        GALACTICA, PEGASUS}

    public void load() {
        load(BattlestarType.GALACTICA);
    }

    public void load(BattlestarType type) {
        if (type == BattlestarType.PEGASUS) {
            battlestarModel = DradisModelStorage.get("pegasus");
            battlestarModel.getNode().setName("pegasus");
        } else {
            battlestarModel = DradisModelStorage.get("galactica");
            battlestarModel.getNode().setName("galactica");
        }
//        battlestarModel.getNode().setCullMode(CullState.CS_BACK);
        battlestarModel.getNode().updateCollisionTree();
        NodeRotator.rotate(getNode(), Model.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(getNode(), Model.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(getNode(), Model.PITCH, (float) Math.toRadians(90));
    }

    public Node getNode() {
        return battlestarModel.getNode();
    }
}