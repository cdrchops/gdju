package com.gerbildrop.gc.gauges.cdu.pageSelections;

import com.gerbildrop.gc.gauges.cdu.CduManager;

import java.awt.event.KeyEvent;

public class InsUtil {
    public static void insSoftKeys(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            case '7':
                break;
            case '8':

                break;
            case KeyEvent.VK_ESCAPE:
                CduManager.softKeysEnabled = false;
                break;
        }
    }
}