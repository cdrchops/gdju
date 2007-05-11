package com.gerbildrop.gc.gauges.cdu;

import java.awt.event.KeyEvent;

import com.gerbildrop.gc.gauges.cdu.pageSelections.DestUtil;
import com.gerbildrop.gc.gauges.cdu.pageSelections.InsUtil;
import com.gerbildrop.gc.gauges.cdu.pageSelections.NavUtil;
import com.gerbildrop.gc.gauges.cdu.pageSelections.PosUtil;
import com.gerbildrop.gc.gauges.cdu.pageSelections.StrUtil;
import com.gerbildrop.gc.gauges.cdu.pageSelections.TacanUtil;
import com.gerbildrop.gc.window.WindowUtil;

public class CduKeys {
    public static void checkKeys(KeyEvent e) {
        if (!CduManager.softKeysEnabled) {
            noSoftKeys(e);
        } else {
            softKeys(e);
        }
    }

    private static void noSoftKeys(KeyEvent e) {
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
            case 'd':
                CduManager.displayType = CduManager.DEST;
                CduManager.softKeysEnabled = true;
                break;
            case 'i':
                CduManager.displayType = CduManager.INS;
                CduManager.softKeysEnabled = true;
                break;
            case 'n':
                CduManager.displayType = CduManager.NAV;
                CduManager.softKeysEnabled = true;
                break;
            case 's':
                CduManager.displayType = CduManager.STR;
                CduManager.softKeysEnabled = true;
                break;
            case 't':
                CduManager.displayType = CduManager.TACAN;
                CduManager.softKeysEnabled = true;
                break;
            case 'p':
                CduManager.displayType = CduManager.POS;
                CduManager.softKeysEnabled = true;
                break;
            case 'l':
                /**
                 * when pressed first, allows letters to be entered
                 * pressed second time, letters entered are put into the position from
                 * the scratchpad.
                 */
                //CduManager.LTR_USE = true;
                break;
            case KeyEvent.VK_ESCAPE:
                WindowUtil.lazilyExit();
                break;
        }
    }

    private static void softKeys(KeyEvent e) {
        switch (CduManager.displayType) {
            case CduManager.INS:
                InsUtil.insSoftKeys(e);
                break;
            case CduManager.NAV:
                NavUtil.navSoftKeys(e);
                break;
            case CduManager.STR:
                StrUtil.strSoftKeys(e);
                break;
            case CduManager.TACAN:
                TacanUtil.tacanSoftKeys(e);
                break;
            case CduManager.POS:
                PosUtil.posSoftKeys(e);
                break;
            case CduManager.DEST:
                DestUtil.destSoftKeys(e);
        }
    }
}