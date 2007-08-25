package com.gerbildrop.mask;

public class MaskMain extends BasicLevelGame {//CameraMonitorGame {//

    public static void main(String[] args) {
        MaskMain app = new MaskMain();
        app.setDialogBehaviour(FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }
}