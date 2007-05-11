package com.gerbildrop.engine.state.menu.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.jme.system.GameSettings;
import com.jmex.editors.swing.settings.GameSettingsPanel;
import com.gerbildrop.engine.state.MenuOptions;

public class OptionsMenu implements MenuOptions {
    private JPanel settingsPanel;
    private GameSettingsPanel gameSettingsPanel;

    private static final OptionsMenu _INSTANCE = new OptionsMenu();

    private OptionsMenu() {}

    public static OptionsMenu getInstance() {
        return _INSTANCE;
    }

    public void buildMenu(JDesktopPane desktop,
                                  GameSettings gameSettings,
                                  ActionListener actionListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(SETTINGS);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(BorderLayout.NORTH, label);

        gameSettingsPanel = new GameSettingsPanel(gameSettings);
        JScrollPane scroller = new JScrollPane(gameSettingsPanel);
        scroller.setBorder(null);
        panel.add(BorderLayout.CENTER, scroller);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        panel.add(BorderLayout.SOUTH, buttonsPanel);

        JButton backButton = new JButton(MAIN_MENU_LABEL);
        backButton.setName(MAIN_MENU);
        backButton.addActionListener(actionListener);
        buttonsPanel.add(backButton);

        JButton defaultsButton = new JButton(DEFAULTS_LABEL);
        defaultsButton.setName(DEFAULTS);
        defaultsButton.addActionListener(actionListener);
        buttonsPanel.add(defaultsButton);

        JButton saveButton = new JButton(APPLY_SETTINGS_LABEL);
        saveButton.setName(APPLY_SETTINGS);
        saveButton.addActionListener(actionListener);
        buttonsPanel.add(saveButton);

        panel.setSize(350, 400);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setLocation(Math.round(400 - ((float) panel.getSize().width / 2)), Math.round(300 - ((float) panel.getSize().height / 2)));
        panel.setOpaque(false);
        MenuUtil.makeAllOpaque(panel, false);
        panel.setVisible(false);
        desktop.add(panel);

        settingsPanel = panel;
    }

    public GameSettingsPanel getGameSettingsPanel() {
        return gameSettingsPanel;
    }

    public JPanel getMenu() {
        return settingsPanel;
    }
}
