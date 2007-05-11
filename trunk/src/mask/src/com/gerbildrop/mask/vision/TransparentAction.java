package com.gerbildrop.mask.vision;

import com.gerbildrop.mask.BasicLevelGame;
import com.gerbildrop.mask.powerUps.House;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;

public class TransparentAction extends InputAction {
    public void performAction(InputActionEvent inputActionEvent) {
        House.transparent = !House.transparent;
        BasicLevelGame.irg = !BasicLevelGame.irg;
    }
}
