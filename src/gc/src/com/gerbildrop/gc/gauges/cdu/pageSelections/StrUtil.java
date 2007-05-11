package com.gerbildrop.gc.gauges.cdu.pageSelections;

import java.awt.event.KeyEvent;

import com.gerbildrop.gc.gauges.cdu.CduManager;

public class StrUtil {
    public static void strSoftKeys(KeyEvent e) {
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