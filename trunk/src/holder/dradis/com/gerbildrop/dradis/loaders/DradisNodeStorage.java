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

package com.gerbildrop.dradis.loaders;

import java.util.HashMap;
import java.util.Map;

import com.gerbildrop.dradis.logging.Debug;
import com.gerbildrop.dradis.util.DradisNode;

/**
 * @author vivaldi
 * @version $Id: DradisNodeStorage.java,v 1.15 2007/04/04 14:29:07 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisNodeStorage {
    private static final Map<String, DradisNode> dradisNodeStorageMap = new HashMap<String, DradisNode>();

    public static class DradisNodeStorageThread implements Runnable {
        public void run() {
            loadMenu();
            Debug.debug("DradisNodeStorage nodeMap " + dradisNodeStorageMap.size());
        }
    }

    public static class DradisNodeStorageClockThread implements Runnable {
        public void run() {
            loadClock();
        }
    }

    public static class DradisNodeStorageTimerThread implements Runnable {
        public void run() {
            loadTimer();
        }
    }

    private static void loadMenu() {
        DradisNode background = new DradisNode();

        DradisNode option = new DradisNode();
        DradisNode optionLit = new DradisNode();

        DradisNode singleMission = new DradisNode();
        DradisNode singleMissionLit = new DradisNode();

        DradisNode campaign = new DradisNode();
        DradisNode campaignLit = new DradisNode();

        DradisNode exit = new DradisNode();
        DradisNode exitLit = new DradisNode();

        option.load("optionNode", "optionQuad", "dradis/displays/menu/options.png", 1.6f);
        optionLit.load("optionLitNode", "optionLitQuad", "dradis/displays/menu/optionsLit.png", 1.6f);
        background.load("backgroundNode", "backgroundQuad", "dradis/displays/menu/background.png", 1.6f);

        exit.load("exitNode", "exitQuad", "dradis/displays/menu/exit.png", 1.6f);
        exitLit.load("exitLitNode", "exitLitQuad", "dradis/displays/menu/exitLit.png", 1.6f);

        campaign.load("campaignNode", "campaignQuad", "dradis/displays/menu/campaign.png", 1.6f);
        campaignLit.load("campaignLitNode", "campaignLitQuad", "dradis/displays/menu/campaignLit.png", 1.6f);

        singleMission.load("singleMissionNode", "singleMissionQuad", "dradis/displays/menu/singleMission.png", 1.6f);
        singleMissionLit.load("singleMissionLitNode", "singleMissionLitQuad", "dradis/displays/menu/singleMissionLit.png", 1.6f);

        dradisNodeStorageMap.put(option.getName(), option);
        dradisNodeStorageMap.put(optionLit.getName(), optionLit);
        dradisNodeStorageMap.put(background.getName(), background);
        dradisNodeStorageMap.put(exit.getName(), exit);
        dradisNodeStorageMap.put(exitLit.getName(), exitLit);
        dradisNodeStorageMap.put(singleMission.getName(), singleMission);
        dradisNodeStorageMap.put(singleMissionLit.getName(), singleMissionLit);
        dradisNodeStorageMap.put(campaign.getName(), campaign);
        dradisNodeStorageMap.put(campaignLit.getName(), campaignLit);
    }

    public static DradisNode get(String name) {
        DradisNode tmp = null;
        try {
            tmp = (DradisNode) dradisNodeStorageMap.get(name).clone();
        } catch (CloneNotSupportedException e) {
            Debug.error(e);
        }
        return tmp;
    }

    /**
     * getClockTimer gets a clock or timer item which should never be cloned
     * @param name
     * @return
     */
    public static DradisNode getCT(String name) {
       return dradisNodeStorageMap.get(name);
    }

    public static void loadClock() {
        DradisNode colon = new DradisNode();
        DradisNode colon2 = new DradisNode();
        DradisNode space = new DradisNode();
        DradisNode space2 = new DradisNode();
        DradisNode space3 = new DradisNode();
        colon.load("colon", "colonQuad", "dradis/displays/clock/numbers/colon.png");//, 48f, 94f);
        colon2.load("colon2", "colon2Quad", "dradis/displays/clock/numbers/colon.png");//, 48f, 94f);
        space.load("space", "spaceQuad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);
        space2.load("space2", "space2Quad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);
        space3.load("space3", "space3Quad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);

        addHours();
        addMinutes();
        addSeconds();

        setArray("hours");
        setArray("hours2");
        setArray("minutes");
        setArray("minutes2");
        setArray("seconds");
        setArray("seconds2");

        dradisNodeStorageMap.put(colon.getName(), colon);
        dradisNodeStorageMap.put(colon2.getName(), colon2);
        dradisNodeStorageMap.put(space.getName(), space);
        dradisNodeStorageMap.put(space2.getName(), space2);
        dradisNodeStorageMap.put(space3.getName(), space3);
    }

    public static void loadTimer() {
        DradisNode timerColon = new DradisNode();
        DradisNode timerColon2 = new DradisNode();
        DradisNode timerSpace = new DradisNode();
        DradisNode timerSpace2 = new DradisNode();
        DradisNode timerSpace3 = new DradisNode();

        timerColon.load("timerColon", "timerColonQuad", "dradis/displays/clock/numbers/colon.png");//, 48f, 94f);
        timerColon2.load("timerColon2", "timerColon2Quad", "dradis/displays/clock/numbers/colon.png");//, 48f, 94f);
        timerSpace.load("timerSpace", "timerSpaceQuad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);
        timerSpace2.load("timerSpace2", "timerSpace2Quad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);
        timerSpace3.load("timerSpace3", "timerSpace3Quad", "dradis/displays/clock/numbers/spacer.png");//, 15f, 94f);

        setArray("timerHours");
        setArray("timerHours2");
        setArray("timerMinutes");
        setArray("timerMinutes2");
        setArray("timerSeconds");
        setArray("timerSeconds2");

        addHours();
        addMinutes();
        addSeconds();

        dradisNodeStorageMap.put(timerColon.getName(), timerColon);
        dradisNodeStorageMap.put(timerColon2.getName(), timerColon2);
        dradisNodeStorageMap.put(timerSpace.getName(), timerSpace);
        dradisNodeStorageMap.put(timerSpace2.getName(), timerSpace2);
        dradisNodeStorageMap.put(timerSpace3.getName(), timerSpace3);
    }

    private static void addHours() {
        for (int i = 0; i < 13; i++) {
            DradisNode hour = new DradisNode();
            hour.load("hour" + i + "Node", "hour" + i + "Quad", "dradis/displays/clock/hours/hours" + i + ".png");
            dradisNodeStorageMap.put(hour.getName(), hour);
        }
    }

    private static void addMinutes() {
        for (int i = 0; i < 61; i++) {
            DradisNode minute = new DradisNode();
            minute.load("minutes" + i + "Node", "minutes" + i + "Node", "dradis/displays/clock/minutes/" + i + ".png");           
            dradisNodeStorageMap.put(minute.getName(), minute);
        }
    }

    private static void addSeconds() {
        for (int i = 0; i < 61; i++) {
            DradisNode second = new DradisNode();
            second.load("seconds" + i + "Node", "seconds" + i + "Node", "dradis/displays/clock/seconds/" + i + ".png");
            dradisNodeStorageMap.put(second.getName(), second);
        }
    }

    private static void setArray(final String addtlName) {
        for (int i = 0; i < 10; i++) {
            DradisNode numbers = null;
            switch (i) {
                case 0:
                    numbers = new DradisNode();
                    numbers.load("zero" + addtlName + "Node", "zero" + addtlName + "Quad", "dradis/displays/clock/numbers/zero.png");
                    break;
                case 1:
                    numbers = new DradisNode();
                    numbers.load("one" + addtlName + "Node", "one" + addtlName + "Quad", "dradis/displays/clock/numbers/one.png");
                    break;
                case 2:
                    numbers = new DradisNode();
                    numbers.load("two" + addtlName + "Node", "two" + addtlName + "Quad", "dradis/displays/clock/numbers/two.png");
                    break;
                case 3:
                    numbers = new DradisNode();
                    numbers.load("three" + addtlName + "Node", "three" + addtlName + "Quad", "dradis/displays/clock/numbers/three.png");
                    break;
                case 4:
                    numbers = new DradisNode();
                    numbers.load("four" + addtlName + "Node", "four" + addtlName + "Quad", "dradis/displays/clock/numbers/four.png");
                    break;
                case 5:
                    numbers = new DradisNode();
                    numbers.load("five" + addtlName + "Node", "five" + addtlName + "Quad", "dradis/displays/clock/numbers/five.png");
                    break;
                case 6:
                    numbers = new DradisNode();
                    numbers.load("six" + addtlName + "Node", "six" + addtlName + "Quad", "dradis/displays/clock/numbers/six.png");
                    break;
                case 7:
                    numbers = new DradisNode();
                    numbers.load("seven" + addtlName + "Node", "seven" + addtlName + "Quad", "dradis/displays/clock/numbers/seven.png");
                    break;
                case 8:
                    numbers = new DradisNode();
                    numbers.load("eight" + addtlName + "Node", "eight" + addtlName + "Quad", "dradis/displays/clock/numbers/eight.png");
                    break;
                case 9:
                    numbers = new DradisNode();
                    numbers.load("nine" + addtlName + "Node", "nine" + addtlName + "Quad", "dradis/displays/clock/numbers/nine.png");
                    break;
            }

            dradisNodeStorageMap.put(numbers.getName(), numbers);
        }
    }
}
