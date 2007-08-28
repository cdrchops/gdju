package com.gerbildrop.groovy.test

import javax.swing.JFrame;
import javax.swing.JButton;
import com.gerbildrop.groovy.test.GButton;
import com.gerbildrop.groovy.test.GAction;

JFrame f = new JFrame();
JButton b = new GButton();

b.setText("press me");

b.addActionListener{ println 'hello world' } // <-- cool!

f.getContentPane().add(b);
f.pack()
f.show()
f.setVisible(true);
