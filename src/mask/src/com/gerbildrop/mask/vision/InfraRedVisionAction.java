package com.gerbildrop.mask.vision;

import com.gerbildrop.mask.BasicLevelGame;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;

public class InfraRedVisionAction extends InputAction {
    public void performAction(InputActionEvent inputActionEvent) {
        BasicLevelGame.irg = !BasicLevelGame.irg;
    }
}
