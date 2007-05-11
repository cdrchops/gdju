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

import com.gerbildrop.dradis.loaders.DradisNodeStorage;
import com.gerbildrop.dradis.util.DradisNode;
import com.jme.math.Vector3f;
import com.jme.scene.Node;

public class DradisDigitalTimer {
    private static DradisNode colon;
    private static DradisNode colon2;
    private static DradisNode space;
    private static DradisNode space2;
    private static DradisNode space3;
    private static Node clockNode = new Node("timerNode");

    private static DradisNode[] hours = new DradisNode[10];
    private static DradisNode[] hoursPart2 = new DradisNode[10];

    private static DradisNode[] minutes = new DradisNode[10];
    private static DradisNode[] minutesPart2 = new DradisNode[10];

    private static DradisNode[] seconds = new DradisNode[10];
    private static DradisNode[] secondsPart2 = new DradisNode[10];

    private static final int x = 250;
    private static final int y = 375;

    private static Vector3f hourOneVector;
    private static Vector3f hourTwoVector;

    private static Vector3f minuteOneVector;
    private static Vector3f minuteTwoVector;

    private static Vector3f secondOneVector;
    private static Vector3f secondTwoVector;

    private static Vector3f spaceOneVector;
    private static Vector3f spaceTwoVector;
    private static Vector3f spaceThreeVector;

    private static Vector3f colonOneVector;
    private static Vector3f colonTwoVector;

    private static boolean initialized = false;

    private static Node secondsNode = new Node("secondsNode");
    private static Node minutesNode = new Node("minutesNode");
    private static Node hoursNode = new Node("hoursNode");

    private static float hourStartTime;
    private static float minuteStartTime;
    private static float secondStartTime;

    private static int hourOne;
    private static int hourTwo;
    private static int minuteOne;
    private static int minuteTwo;
    private static int secondOne;
    private static int secondTwo;

    public void load() {
        colon = DradisNodeStorage.getCT("timerColon");
        colon2 = DradisNodeStorage.getCT("timerColon2");
        space = DradisNodeStorage.getCT("timerSpace");
        space2 = DradisNodeStorage.getCT("timerSpace2");
        space3 = DradisNodeStorage.getCT("timerSpace3");

        setArray(hours, "timerHours");
        setArray(hoursPart2, "timerHours2");
        setArray(minutes, "timerMinutes");
        setArray(minutesPart2, "timerMinutes2");
        setArray(seconds, "timerSeconds");
        setArray(secondsPart2, "timerSeconds2");

        initVectors();
        drawTimer();
    }

    private void initVectors() {
        Vector3f baseVector = new Vector3f(x, y, 0);
        hourOneVector = new Vector3f(x, y, 0);
        spaceOneVector = new Vector3f(baseVector.add(new Vector3f(58, 0, 0)));
        hourTwoVector = new Vector3f(baseVector.add(new Vector3f(15 + 58, 0, 0)));

        colonOneVector = new Vector3f(baseVector.add(15 + 58 * 2, 0, 0));

        baseVector = baseVector.add(15 + 58 * 2 + 48, 0, 0);

        minuteOneVector = new Vector3f(baseVector);
        spaceTwoVector = new Vector3f(baseVector.add(new Vector3f(58, 0, 0)));
        minuteTwoVector = new Vector3f(baseVector.add(new Vector3f(15 + 58, 0, 0)));

        colonTwoVector = new Vector3f(baseVector.add(15 + 58 * 2, 0, 0));

        baseVector = baseVector.add(15 + 58 * 2 + 48, 0, 0);

        secondOneVector = new Vector3f(baseVector);
        spaceThreeVector = new Vector3f(baseVector.add(new Vector3f(58, 0, 0)));
        secondTwoVector = new Vector3f(baseVector.add(new Vector3f(15 + 58, 0, 0)));
    }

    private void setArray(final DradisNode[] numbers, final String addtlName) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    numbers[0] = DradisNodeStorage.getCT("zero" + addtlName + "Node");
                    break;
                case 1:
                    numbers[1] = DradisNodeStorage.getCT("one" + addtlName + "Node");
                    break;
                case 2:
                    numbers[2] = DradisNodeStorage.getCT("two" + addtlName + "Node");
                    break;
                case 3:
                    numbers[3] = DradisNodeStorage.getCT("three" + addtlName + "Node");
                    break;
                case 4:
                    numbers[4] = DradisNodeStorage.getCT("four" + addtlName + "Node");
                    break;
                case 5:
                    numbers[5] = DradisNodeStorage.getCT("five" + addtlName + "Node");
                    break;
                case 6:
                    numbers[6] = DradisNodeStorage.getCT("six" + addtlName + "Node");
                    break;
                case 7:
                    numbers[7] = DradisNodeStorage.getCT("seven" + addtlName + "Node");
                    break;
                case 8:
                    numbers[8] = DradisNodeStorage.getCT("eight" + addtlName + "Node");
                    break;
                case 9:
                    numbers[9] = DradisNodeStorage.getCT("nine" + addtlName + "Node");
                    break;
            }
        }
    }

    private void drawTimer() {
        if (!initialized) {
            initialized = true;

            final Node spaceOneNode = space.getNode();
            spaceOneNode.setLocalTranslation(spaceOneVector);
            clockNode.attachChild(spaceOneNode);

            final Node spaceTwoNode = space2.getNode();
            spaceTwoNode.setLocalTranslation(spaceTwoVector);
            clockNode.attachChild(spaceTwoNode);

            final Node spaceThreeNode = space3.getNode();
            spaceThreeNode.setLocalTranslation(spaceThreeVector);
            clockNode.attachChild(spaceThreeNode);

            final Node colonNode = colon.getNode();
            colonNode.setLocalTranslation(colonOneVector);
            clockNode.attachChild(colonNode);

            final Node colonTwoNode = colon2.getNode();
            colonTwoNode.setLocalTranslation(colonTwoVector);
            clockNode.attachChild(colonTwoNode);
        }
    }

    public Node getNode() {
        return clockNode;
    }

    public void updateTimer(float hour, float minute, float second) {
        drawTimerSeconds(second);
        drawTimerMinutes(minute);
        drawTimerHours(hour);
    }

    private void drawTimerHours(float hour) {
        if (hour != hourStartTime) {
            hourStartTime = hour;
            checkTimerHours((int) hour);

            Node hourOneNode = hours[hourOne].getNode();
            Node hourTwoNode = hoursPart2[hourTwo].getNode();

            hourOneNode.setLocalTranslation(hourOneVector);
            hourTwoNode.setLocalTranslation(hourTwoVector);

            clockNode.detachChildNamed("hoursNode");
            hoursNode.detachAllChildren();
            hoursNode.attachChild(hourOneNode);
            hoursNode.attachChild(hourTwoNode);

            clockNode.attachChild(hoursNode);
        }
    }

    private void drawTimerMinutes(float minute) {
        if (minute != minuteStartTime) {
            checkTimerMinutes((int) minute);
            Node minuteOneNode = minutes[minuteOne].getNode();
            Node minuteTwoNode = minutesPart2[minuteTwo].getNode();

            minuteOneNode.setLocalTranslation(minuteOneVector);
            minuteTwoNode.setLocalTranslation(minuteTwoVector);

            clockNode.detachChildNamed("minutesNode");
            minutesNode.detachAllChildren();
            minutesNode.attachChild(minuteOneNode);
            minutesNode.attachChild(minuteTwoNode);

            clockNode.attachChild(minutesNode);
            minuteStartTime = minute;
        }
    }

    private void drawTimerSeconds(float second) {
        if (second != secondStartTime) {
            checkTimerSeconds((int) second);

            final Node secondOneNode = seconds[secondOne].getNode();
            final Node secondTwoNode = secondsPart2[secondTwo].getNode();

            secondOneNode.setLocalTranslation(secondOneVector);
            secondTwoNode.setLocalTranslation(secondTwoVector);

            clockNode.detachChildNamed("secondsNode");
            secondsNode.detachAllChildren();
            secondsNode.attachChild(secondOneNode);
            secondsNode.attachChild(secondTwoNode);
            clockNode.attachChild(secondsNode);

            secondStartTime = second;
        }
    }

    private static void checkTimerHours(int hour) {
        if (hour > 9 && hour < 20) {
            hourOne = 1;
            hourTwo = hour - 10;
        } else if (hour > 19) {
            hourOne = 2;
            hourTwo = hour - 20;
        } else {
            hourOne = 0;
            hourTwo = hour;
        }

        if (hourOne < 0) {
            hourOne = 0;
        }

        if (hourTwo < 0) {
            hourTwo = 0;
        }
    }

    private static void checkTimerMinutes(int minute) {
        if (minute > 9) {
            if (minute > 49) {
                minuteOne = 5;
                minuteTwo = minute - 50;
            } else if (minute > 39) {
                minuteOne = 4;
                minuteTwo = minute - 40;
            } else if (minute > 29) {
                minuteOne = 3;
                minuteTwo = minute - 30;
            } else if (minute > 19) {
                minuteOne = 2;
                minuteTwo = minute - 20;
            } else {
                minuteOne = 1;
                minuteTwo = minute - 10;
            }
        } else {
            minuteOne = 0;
            minuteTwo = minute;
        }

        if (minuteOne < 0) {
            minuteOne = 0;
        }

        if (minuteTwo < 0) {
            minuteTwo = 0;
        }

//        System.out.println("minuteOne = " + minuteOne);
//        System.out.println("minuteTwo = " + minuteTwo);
    }

    private static void checkTimerSeconds(int second) {
        if (second > 9) {
            if (second > 49) {
                secondOne = 5;
                secondTwo = second - 50;
            } else if (second > 39) {
                secondOne = 4;
                secondTwo = second - 40;
            } else if (second > 29) {
                secondOne = 3;
                secondTwo = second - 30;
            } else if (second > 19) {
                secondOne = 2;
                secondTwo = second - 20;
            } else {
                secondOne = 1;
                secondTwo = second - 10;
            }
        } else {
            secondOne = 0;
            secondTwo = second;
        }

        if (secondOne < 0) {
            secondOne = 0;
        }
        if (secondTwo < 0) {
            secondTwo = 0;
        }
    }
}
