package com.gerbildrop.countdown;

import java.util.Calendar;
import java.text.NumberFormat;

import com.jme.scene.Text;
import com.jme.scene.Node;

public class TimerCountdown {
    private static final TimerCountdown _INSTANCE = new TimerCountdown();

    private TimerCountdown() {}

    public static TimerCountdown getInstance() {
        return _INSTANCE;
    }

    private Calendar event;

    private int second;
    private int hour;
    private int minute;

    private int startSecond;
    private int startMinute;
    private int startHour;
    private static final NumberFormat twoDecimals = NumberFormat.getInstance();
    private static Text completionText;
    private boolean continueUpdating;

    private Node rootNode;

    static {
        twoDecimals.setMinimumIntegerDigits(2);
    }

    public void init(Node _rootNode, int _startHour, int _startMinute, int _startSecond) {
        startSecond = _startSecond;
        startMinute = _startMinute;
        startHour = _startHour;

        continueUpdating = true;

        rootNode = _rootNode;
        event = Calendar.getInstance();

        second = event.get(Calendar.SECOND);
        minute = event.get(Calendar.MINUTE);
        hour = event.get(Calendar.HOUR);

        event.set(Calendar.MINUTE, startMinute);
        event.set(Calendar.HOUR, startHour);
        event.set(Calendar.SECOND, startSecond);

        //sorry Go Diego Go reference:)
        completionText = new Text("textNode", "Mission Completa");
        completionText.setLocalTranslation(100, 100, 0);

        drawTimer();
    }

    public void update(float tpf) {
        if (continueUpdating) {
            Calendar current = Calendar.getInstance();
            int tmpSec = current.get(Calendar.SECOND);
            int tmpMinute = current.get(Calendar.MINUTE);
            int tmpHour = current.get(Calendar.HOUR);
            boolean reDrawTimer = false;

            if (tmpSec == second + 1) {
                second++;
                startSecond--;
                event.add(Calendar.SECOND, -1);
                reDrawTimer = true;
            }

            if (tmpMinute == minute + 1) {
                minute++;
                startMinute--;
                event.add(Calendar.MINUTE, -1);
                reDrawTimer = true;
            }

            if (tmpHour == hour + 1) {
                hour++;
                startHour--;
                event.add(Calendar.HOUR, -1);
                reDrawTimer = true;
            }

            if (startSecond <= 0 && startMinute <= 0 && startHour <= 0) {
                reDrawTimer = false;
                rootNode.detachChildNamed("textNode");
                rootNode.attachChild(completionText);
                continueUpdating = false;
            }

            if (reDrawTimer) {
                drawTimer();
            }
        }
    }

    private void drawTimer() {
        StringBuilder sb = new StringBuilder();

        sb.append(twoDecimals.format(event.get(Calendar.HOUR))).append(":");
        sb.append(twoDecimals.format(event.get(Calendar.MINUTE))).append(":");
        sb.append(twoDecimals.format(event.get(Calendar.SECOND)));

        final Text timerText = new Text("textNode", sb.toString());
        timerText.setLocalTranslation(100, 100, 0);
        rootNode.detachChildNamed("textNode");
        rootNode.attachChild(timerText);
    }
}
