package com.jmex.bui;

import com.gerbildrop.engine.input.controls.GameControls;
import com.gerbildrop.engine.menu.StarsMenuBackdrop;
import com.gerbildrop.engine.state.BuiMenuGameState;
import com.gerbildrop.engine.state.MenuOptions;
import com.gerbildrop.engine.state.menu.bui.GameControlEditor;
import com.gerbildrop.dradis.state.menu.bui.BuiControlMenu;
import com.jme.input.controls.GameControlManager;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.game.StandardGame;

public class BuiGameTest extends BuiMenuGameState implements MenuOptions {
    public BuiGameTest(StandardGame game) {
        super(game, false, StarsMenuBackdrop.getInstance());
        BuiControlMenu.getInstance().buildMenu(game.getSettings(), listener);
    }

    private GameControlEditor controlEditor;
    protected void buildMenu() {

       BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

       window.add(BuiControlMenu.getInstance().getMenu());

        window.setSize(500, 500);

        BuiSystem.addWindow(window);

        window.center();
    }

    public void handleEvent(final ActionEvent evt) {
        String buttonName = evt.getAction();
        if (buttonName.equals("")) {
//            ControlsMenu.getInstance().getMenu(), MainGameMenu.getInstance().getMenu();
        } else if (buttonName.equals(DEFAULTS)) {
            GameControls.getInstance().assignDefaultControls();
            BuiControlMenu.getInstance().getControlEditor().reset();
        } else if (buttonName.equals("controlsSave")) {
            BuiControlMenu.getInstance().getControlEditor().apply();
            GameControlManager.save(GameControls.getInstance().getControlManager(), _game.getSettings());
        }
    }

    protected void setMenuInstance() {
    }
}
