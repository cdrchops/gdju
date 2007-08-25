package com.gerbildrop.mask.vision;

import com.gerbildrop.mask.BasicLevelGame;
import com.jme.input.action.InputAction;
import com.jme.input.action.InputActionEvent;

public class NightVisionAction extends InputAction {
    public void performAction(InputActionEvent inputActionEvent) {
        BasicLevelGame.nvg = !BasicLevelGame.nvg;
    }
}
