package com.jmex.bui;

import com.gerbildrop.engine.game.GameBase;
import com.jmex.game.state.GameStateManager;

public class BuiControlsMenu extends GameBase {

    public BuiControlsMenu() {
        try {
            super.run("DRADIS -- XBG Combat Simulator (MMORPG)", "/dradis/config/controls.xml", "GlobalGameState");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BuiGameTest bg = new BuiGameTest(game);
       GameStateManager.getInstance().attachChild(bg);
       bg.setActive(true);
    }

    public static void main(String[] args) {
        new BuiControlsMenu();
    }
}
