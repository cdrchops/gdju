package com.gerbildrop.engine.state.menu.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.state.MenuOptions;
import com.jme.system.GameSettings;

public class MainGameMenu implements MenuOptions {
    private static final MainGameMenu _INSTANCE = new MainGameMenu();
    protected JPanel mainMenu;
    protected JButton newGame;
    private MainGameMenu() {}

    public static MainGameMenu getInstance() {
        return _INSTANCE;
    }

    public void buildMenu(JDesktopPane desktop, GameSettings settings, ActionListener actionListener) {
        mainMenu = new JPanel();
        mainMenu.setSize(250, 425);
        mainMenu.setLocation(Math.round(400 - ((float) mainMenu.getSize().width / 2)), Math.round(300 - ((float) mainMenu.getSize().height / 2)));
        mainMenu.setLayout(null);
        mainMenu.setOpaque(false);

        ImageIcon icon = new ImageIcon(Resources.TITLE_ICON);
        JLabel label = new JLabel(icon);
        label.setSize(250, 100);
        label.setLocation(0, 0);
        mainMenu.add(label);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setSize(250, 340);
        menuPanel.setLocation(0, 85);
        menuPanel.setBorder(BorderFactory.createEtchedBorder());
        menuPanel.setOpaque(false);

        newGame = new JButton(NEW_GAME_LABEL);
        newGame.setName(NEW_GAME);
        newGame.addActionListener(actionListener);
        newGame.setSize(new Dimension(200, 25));
        newGame.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) newGame.getSize().width / 2)), 25);
        menuPanel.add(newGame);

        JButton singlePlayer = new JButton(SINGLE_PLAYER_LABEL);
        singlePlayer.setName(SINGLE_PLAYER);
        singlePlayer.addActionListener(actionListener);
        singlePlayer.setSize(new Dimension(200, 25));
        singlePlayer.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) singlePlayer.getSize().width / 2)), 70);
        menuPanel.add(singlePlayer);

        JButton campaign = new JButton(CAMPAIGN_LABEL);
        campaign.setName(CAMPAIGN);
        campaign.addActionListener(actionListener);
        campaign.setSize(new Dimension(200, 25));
        campaign.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) campaign.getSize().width / 2)), 115);
        menuPanel.add(campaign);

        JButton controls = new JButton(CONTROLS_LABEL);
        controls.setName(CONTROLS);
        controls.addActionListener(actionListener);
        controls.setSize(new Dimension(200, 25));
        controls.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) controls.getSize().width / 2)), 160);
        menuPanel.add(controls);

        JButton options = new JButton(OPTIONS_LABEL);
        options.setName(OPTIONS);
        options.addActionListener(actionListener);
        options.setSize(new Dimension(200, 25));
        options.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) options.getSize().width / 2)), 205);
        menuPanel.add(options);

        JButton credits = new JButton(CREDITS_LABEL);
        credits.setName(CREDITS);
        credits.addActionListener(actionListener);
        credits.setSize(new Dimension(200, 25));
        credits.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) credits.getSize().width / 2)), 250);
        menuPanel.add(credits);

        JButton exit = new JButton(EXIT_LABEL);
        exit.setName(EXIT);
        exit.addActionListener(actionListener);
        exit.setSize(new Dimension(200, 25));
        exit.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) exit.getSize().width / 2)), 295);
        menuPanel.add(exit);

        mainMenu.add(menuPanel);

        desktop.add(mainMenu);
    }

    public JPanel getMenu() {
        return mainMenu;
    }

    public JButton getNewGameButton() {
        return newGame;
    }
}