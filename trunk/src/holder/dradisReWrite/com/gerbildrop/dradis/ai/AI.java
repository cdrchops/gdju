package com.gerbildrop.dradis.ai;

import com.gerbildrop.dradis.ai.basics.InterceptController;
import com.gerbildrop.dradis.ai.basics.AlreadyGotcha;
import com.gerbildrop.dradis.ships.BaseModelObject;
import com.jme.scene.Controller;
import com.jme.scene.Node;

public abstract class AI extends Controller {
    protected BaseModelObject raider;
    protected Node rootNode;
    protected InterceptController ai = null;
    protected BaseModelObject v2;

    protected static boolean showName = true;

    protected AI(final BaseModelObject raider, final Node rootNode, final BaseModelObject v2) {
        this.raider = raider;
        this.rootNode = rootNode;
        this.v2 = v2;
    }

    public void update(float time) {
        checkCollision();
        if (ai != null) {
            try {
                ai.updateCycle(time);
            } catch (AlreadyGotcha alreadyGotcha) {
                alreadyGotcha.printStackTrace();
            }
        }
    }

    public abstract void checkCollision();
}
