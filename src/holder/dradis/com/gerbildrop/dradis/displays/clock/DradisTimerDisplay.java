/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.gerbildrop.dradis.displays.clock;

import java.util.Calendar;

import com.gerbildrop.dradis.displays.base.BaseDradisDisplay;

public class DradisTimerDisplay extends BaseDradisDisplay {
    private static final DradisClockBackground dradisClockBackground = new DradisClockBackground();
    private static final DradisDigitalTimer dradisDigitalTimer = new DradisDigitalTimer();

    Hours hours = new Hours();
    Minutes minutes = new Minutes();
    Seconds seconds = new Seconds();

    private static int hour;
    private static int minute;
    private static int second;

    private static Calendar event;
    private static Calendar now;

    public void load() {
        setName("DradisTimer");
        super.load();
        updateTimer();

        //todo: be able to modify this value in a popup rather than hard coding it for now.
        event = Calendar.getInstance();
        event.set(Calendar.MINUTE, 1);
        event.set(Calendar.HOUR, 0);
        event.set(Calendar.SECOND, 0);

        //todo: put all clock pieces into their own clock item that we attach to the rootNode
        dradisClockBackground.load();

        dradisDigitalTimer.load();

        hours.load();
        minutes.load();
        seconds.load();

        attachChild(dradisClockBackground.getNode());
        attachChild(dradisDigitalTimer.getNode());
        attachChild(hours.getNode());
        attachChild(minutes.getNode());
        attachChild(seconds.getNode());
    }

    public void update(float timer) {
        super.update(timer);
        updateTimer();
        //todo: fix bug that keeps it going in the wrong positions
        event.add(Calendar.SECOND, -1);
        int tmpSeconds = (event.get(Calendar.SECOND));
        int tmpMinutes = (event.get(Calendar.MINUTE));
        int tmpHours = (event.get(Calendar.HOUR));

        dradisDigitalTimer.updateTimer(tmpHours, tmpMinutes, tmpSeconds);
        hours.updateTimer(tmpHours, this);
        minutes.updateTimer(tmpMinutes, this);
        seconds.updateTimer(tmpSeconds, this);

        updateRenderState();
    }

    private void updateTimer() {
        now = Calendar.getInstance();
        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        second = now.get(Calendar.SECOND);
    }
}