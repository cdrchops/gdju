package com.gerbildrop.groovy.test

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import groovy.lang.Closure;

public class GAction extends AbstractAction {
    Closure code;

    GAction(Closure c)  { code= c; }

    public void actionPerformed(ActionEvent e)  { code(); }
}