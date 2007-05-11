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

import com.gerbildrop.engine.input.controls.GameControls;
import com.gerbildrop.engine.state.MenuOptions;

import com.jme.system.GameSettings;
import com.jmex.editors.swing.controls.GameControlEditor;

public class ControlsMenu implements MenuOptions {
    private JPanel controlsPanel;
    private GameControlEditor controlEditor;

    private static final ControlsMenu _INSTANCE = new ControlsMenu();

    private ControlsMenu() {}

    public static ControlsMenu getInstance() {
        return _INSTANCE;
    }

    public void buildMenu(JDesktopPane desktop,
                          GameSettings settings,
                          ActionListener actionListener) {

        controlEditor = new GameControlEditor(GameControls.getInstance().getControlManager(), 2);
        controlEditor.setOpaque(false);

        JScrollPane controlsScroller = new JScrollPane(controlEditor);
        controlsScroller.setBorder(null);
        controlsScroller.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.CENTER, controlsScroller);

        JLabel label = new JLabel("Control Configuration");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(BorderLayout.NORTH, label);

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

        JButton saveButton = new JButton("Save");
        saveButton.setName("controlsSave");
        saveButton.addActionListener(actionListener);
        buttonsPanel.add(saveButton);

        panel.setSize(350, 400);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setLocation(Math.round(400 - ((float) panel.getSize().width / 2)), Math.round(300 - ((float) panel.getSize().height / 2)));
        panel.setOpaque(false);
        MenuUtil.makeAllOpaque(panel, false);
        panel.setVisible(false);
        desktop.add(panel);
        this.controlsPanel = panel;
    }

    public GameControlEditor getControlEditor() {
        return controlEditor;
    }

    public JPanel getMenu() {
        return controlsPanel;
    }
}
