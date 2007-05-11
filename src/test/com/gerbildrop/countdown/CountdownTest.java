package com.gerbildrop.countdown;

import com.jme.app.SimpleGame;

public class CountdownTest extends SimpleGame {
    protected void simpleInitGame() {
        TimerCountdown.getInstance().init(rootNode, 0, 0, 10);
        startTimeMillis = System.currentTimeMillis();
    }

    private static long startTimeMillis;

    protected void simpleUpdate() {
        super.simpleUpdate();
        TimerCountdown.getInstance().update(tpf);

        if (System.currentTimeMillis() - startTimeMillis == 5000) {
            TimerCountdown.getInstance().init(rootNode, 0, 0, 15);
        }
    }


    public static void main(String[] args) {
        new CountdownTest().start();
    }
}
