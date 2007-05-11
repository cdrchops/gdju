package com.gerbildrop.dradis.state.menu;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gerbildrop.engine.resources.Resources;
import com.gerbildrop.engine.state.menu.GameMenu;

public class DradisGameMenu extends GameMenu {
    private static final DradisGameMenu _INSTANCE = new DradisGameMenu();

    private DradisGameMenu() {}

    public static DradisGameMenu getInstance() {
        return _INSTANCE;
    }

    public void buildMainMenu(JDesktopPane desktop, ActionListener actionListener) {
        mainMenu = new JPanel();
        mainMenu.setSize(250, 375);
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
        menuPanel.setSize(250, 255);
        //menuPanel.setLocation(Math.round(400 - ((float)menuPanel.getSize().width / 2)), Math.round(300 - ((float)mainMenu.getSize().height / 2) + 50));
        menuPanel.setLocation(0, 110);
        menuPanel.setBorder(BorderFactory.createEtchedBorder());
        menuPanel.setOpaque(false);

        newGame = new JButton("New Game");
        newGame.setName("New Game");
        newGame.addActionListener(actionListener);
        menuPanel.add(newGame);
        newGame.setSize(new Dimension(200, 25));
        newGame.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) newGame.getSize().width / 2)), 25);

        JButton controls = new JButton("Controls");
        controls.setName("Controls");
        controls.addActionListener(actionListener);
        menuPanel.add(controls);
        controls.setSize(new Dimension(200, 25));
        controls.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) controls.getSize().width / 2)), 70);

        JButton options = new JButton("Settings");
        options.setName("Settings");
        options.addActionListener(actionListener);
        menuPanel.add(options);
        options.setSize(new Dimension(200, 25));
        options.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) options.getSize().width / 2)), 115);

        JButton credits = new JButton("Credits");
        credits.setName("Credits");
        credits.addActionListener(actionListener);
        menuPanel.add(credits);
        credits.setSize(new Dimension(200, 25));
        credits.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) credits.getSize().width / 2)), 160);

        JButton exit = new JButton("Quit");
        exit.setName("Quit");
        exit.addActionListener(actionListener);
        menuPanel.add(exit);
        exit.setSize(new Dimension(200, 25));
        exit.setLocation(Math.round((menuPanel.getSize().width / 2.0f) - ((float) exit.getSize().width / 2)), 205);
        mainMenu.add(menuPanel);

        desktop.add(mainMenu);
    }
}
