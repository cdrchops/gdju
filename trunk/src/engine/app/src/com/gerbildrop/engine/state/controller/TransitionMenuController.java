package com.gerbildrop.engine.state.controller;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.jme.renderer.ColorRGBA;
import com.jmex.game.StandardGame;
import com.jmex.scene.TimedLifeController;

public class TransitionMenuController extends TimedLifeController {
    private static final long serialVersionUID = 1L;

    private ColorRGBA color;
    private JPanel oldPanel;
    private JPanel newPanel;
    private Runnable runnable;
    private boolean setNewPanel;
    private StandardGame game;

    public TransitionMenuController(float lifeInSeconds, ColorRGBA color) {
        super(lifeInSeconds);
        this.color = color;
    }

    public void reset(JPanel oldPanel, JPanel newPanel) {
        reset();
        if (oldPanel == null) setPercentage(0.5f);
        this.oldPanel = oldPanel;
        this.newPanel = newPanel;
        setNewPanel = true;
    }

    public void reset(JPanel oldPanel, Runnable runnable) {
        reset();
        this.oldPanel = oldPanel;
        newPanel = null;
        this.runnable = runnable;
    }

    public void setGame(StandardGame game) {
        this.game = game;
    }

    public void updatePercentage(float percentComplete) {
        if (percentComplete <= 0.5f) {
            color.a = 1.0f - (percentComplete * 2);
        } else if ((newPanel != null) && (setNewPanel)) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    newPanel.setVisible(true);
                    if (oldPanel != null) oldPanel.setVisible(false);
                }
            });
            setNewPanel = false;
        }
        if ((game == null) && (percentComplete > 0.5f)) {
            if (runnable != null) {
                new Thread(runnable).start();
                runnable = null;
                setActive(false);
            }
            color.a = (percentComplete * 2) - 1.0f;
        } else if (percentComplete == 1.0f) {
            game.finish();
        }
    }

    public static void updateComponentTreeUI(Component c) {
        com.gerbildrop.engine.state.controller.TransitionMenuController.updateComponentTreeUI0(c);
        c.invalidate();
        c.validate();
        c.repaint();
    }

    private static void updateComponentTreeUI0(Component c) {
        Component[] children = null;
        if (c instanceof JToolBar) {
            children = ((JToolBar) c).getComponents();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    com.gerbildrop.engine.state.controller.TransitionMenuController.updateComponentTreeUI0(children[i]);
                }
            }

            ((JComponent) c).updateUI();
        } else {
            if (c instanceof JComponent) {
                ((JComponent) c).updateUI();
            }

            if (c instanceof JMenu) {
                children = ((JMenu) c).getMenuComponents();
            } else if (c instanceof Container) {
                children = ((Container) c).getComponents();
            }

            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    com.gerbildrop.engine.state.controller.TransitionMenuController.updateComponentTreeUI0(children[i]);
                }
            }
        }
    }
}
