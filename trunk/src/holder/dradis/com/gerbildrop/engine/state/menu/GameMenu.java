package com.gerbildrop.engine.state.menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

public abstract class GameMenu {
    protected JPanel mainMenu;
    protected JButton newGame;

    public abstract void buildMainMenu(JDesktopPane desktop, ActionListener actionListener);

    public JPanel getMainMenu() {
        return mainMenu;
    }

    public JButton getNewGameButton() {
        return newGame;
    }

}
