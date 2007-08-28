package com.gerbildrop.groovy.test

import javax.swing.JButton

public class GButton extends JButton {
  /**
   * Construct a new 'anonymous' action
   * and register it with the button.
   */
  public void addActionListener(Closure code) {
    this.addActionListener(new GAction(code));
  }
}