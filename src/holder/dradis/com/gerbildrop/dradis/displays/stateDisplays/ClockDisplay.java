package com.gerbildrop.dradis.displays.stateDisplays;

import java.util.Calendar;

import com.jmex.game.StandardGame;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.state.LightState;
import com.jme.renderer.Renderer;
import com.jme.math.Vector3f;
import com.gerbildrop.dradis.displays.base.DradisDisplayBase;
import com.gerbildrop.dradis.displays.clock.Hours;
import com.gerbildrop.dradis.displays.clock.Minutes;
import com.gerbildrop.dradis.displays.clock.Seconds;
import com.gerbildrop.engine.displays.Displays;
import com.gerbildrop.dradis.resources.DradisResources;
import com.gerbildrop.engine.util.NodeTranslator;
import com.gerbildrop.engine.spatial.base.BaseModelObject;

public class ClockDisplay implements Displays {
    public void init(final StandardGame game, final Node rootNode) {
        rootNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        rootNode.setCullMode(SceneElement.CULL_NEVER);

        final DradisDisplayBase tg = new DradisDisplayBase();
        tg.init("clockNode", DradisResources.DradisBackground, .5f);
        rootNode.attachChild(tg.getHudQuad());

        final DradisDisplayBase do2 = new DradisDisplayBase();
        do2.init("dradisOverlay", DradisResources.dradisOverlay, .5f);
        rootNode.attachChild(do2.getHudQuad());
        do2.getHudQuad().setLocalTranslation(new Vector3f(327.25f, 226.0f, 0));

        final DradisDisplayBase dcc = new DradisDisplayBase();
        dcc.init("clockCenter", DradisResources.dradisClockCenter, .5f);
        rootNode.attachChild(dcc.getHudQuad());

        Calendar c = Calendar.getInstance();

        initHours(c.get(Calendar.HOUR), rootNode);
        initMinutes(c.get(Calendar.MINUTE), rootNode);
        initSeconds(c.get(Calendar.SECOND), rootNode);

        initRotatingHours(c.get(Calendar.HOUR), rootNode);
        initRotatingMinutes(c.get(Calendar.MINUTE), rootNode);
        initRotatingSeconds(c.get(Calendar.SECOND), rootNode);

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    public void update(final float time, final Node rootNode) {
        rootNode.detachChildNamed("hours");
        rootNode.detachChildNamed("minutes");
        rootNode.detachChildNamed("seconds");
        rootNode.detachChildNamed("hours1");
        rootNode.detachChildNamed("minutes1");
        rootNode.detachChildNamed("seconds1");
        rootNode.detachChildNamed("hours2");
        rootNode.detachChildNamed("minutes2");
        rootNode.detachChildNamed("seconds2");

        Calendar c = Calendar.getInstance();
        initRotatingHours(c.get(Calendar.HOUR), rootNode);
        initRotatingMinutes(c.get(Calendar.MINUTE), rootNode);
        initRotatingSeconds(c.get(Calendar.SECOND), rootNode);

        initHours(c.get(Calendar.HOUR), rootNode);
        initMinutes(c.get(Calendar.MINUTE), rootNode);
        initSeconds(c.get(Calendar.SECOND), rootNode);

        rootNode.setLightCombineMode(LightState.OFF);
        rootNode.updateRenderState();
    }

    private int y = 255;

    private void initHours(int hours, Node rootNode) {
        int hour[] = Hours.checkHours(hours);
        final DradisDisplayBase zero1 = new DradisDisplayBase();
        zero1.init("hour1", Hours.getHourOne(hour[0]), .5f);
        rootNode.attachChild(zero1.getHudQuad());

        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.PITCH, 185);
        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.YAW, y);

        final DradisDisplayBase zero2 = new DradisDisplayBase();
        zero2.init("hour2", Hours.getHourTwo(hour[1]), .5f);
        rootNode.attachChild(zero2.getHudQuad());

        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.PITCH, 225);
        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.YAW, y);

        final DradisDisplayBase colon1 = new DradisDisplayBase();
        colon1.init("colon1", ClockDisplay.class.getResource("/dradis/displays/clock/numbers/colon.png"), .5f);
        rootNode.attachChild(colon1.getHudQuad());

        NodeTranslator.translate(colon1.getHudQuad(), BaseModelObject.PITCH, 255);
        NodeTranslator.translate(colon1.getHudQuad(), BaseModelObject.YAW, y);
    }

    private void initMinutes(int minutes, Node rootNode) {
        int minute[] = Minutes.checkMinutes(minutes);

        final DradisDisplayBase zero1 = new DradisDisplayBase();
        zero1.init("minute1", Minutes.getMinuteOne(minute[0]), .5f);
        rootNode.attachChild(zero1.getHudQuad());

        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.PITCH, 285);
        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.YAW, y);

        final DradisDisplayBase zero2 = new DradisDisplayBase();
        zero2.init("minute2", Minutes.getMinuteTwo(minute[1]), .5f);
        rootNode.attachChild(zero2.getHudQuad());

        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.PITCH, 325);
        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.YAW, y);

        final DradisDisplayBase colon2 = new DradisDisplayBase();
        colon2.init("colon2", ClockDisplay.class.getResource("/dradis/displays/clock/numbers/colon.png"), .5f);
        rootNode.attachChild(colon2.getHudQuad());

        NodeTranslator.translate(colon2.getHudQuad(), BaseModelObject.PITCH, 355);
        NodeTranslator.translate(colon2.getHudQuad(), BaseModelObject.YAW, y);
    }

    private void initSeconds(int seconds, Node rootNode) {
        int second[] = Seconds.checkSeconds(seconds);
        final DradisDisplayBase zero1 = new DradisDisplayBase();
        zero1.init("second1", Seconds.getSecondOne(second[0]), .5f);
        rootNode.attachChild(zero1.getHudQuad());

        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.PITCH, 385);
        NodeTranslator.translate(zero1.getHudQuad(), BaseModelObject.YAW, y);

        final DradisDisplayBase zero2 = new DradisDisplayBase();
        zero2.init("second2", Seconds.getSecondTwo(second[1]), .5f);
        rootNode.attachChild(zero2.getHudQuad());

        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.PITCH, 425);
        NodeTranslator.translate(zero2.getHudQuad(), BaseModelObject.YAW, y);
    }

    private void initRotatingHours(int hour, Node rootNode) {
        final DradisDisplayBase hours = new DradisDisplayBase();
        hours.init("hours", Hours.getHour(hour), .5f);
        rootNode.attachChild(hours.getHudQuad());

        NodeTranslator.translate(hours.getHudQuad(), BaseModelObject.PITCH, 295);
        NodeTranslator.translate(hours.getHudQuad(), BaseModelObject.YAW, 139);
    }

    private void initRotatingMinutes(int minute, Node rootNode) {
        final DradisDisplayBase minutes = new DradisDisplayBase();
        minutes.init("minutes", Minutes.getMinutes(minute), .5f);
        rootNode.attachChild(minutes.getHudQuad());

        NodeTranslator.translate(minutes.getHudQuad(), BaseModelObject.PITCH, 272);
        NodeTranslator.translate(minutes.getHudQuad(), BaseModelObject.YAW, 116);
    }

    private void initRotatingSeconds(int second, Node rootNode) {
        final DradisDisplayBase seconds = new DradisDisplayBase();
        seconds.init("seconds", Seconds.getSeconds(second), .5f);
        rootNode.attachChild(seconds.getHudQuad());

        NodeTranslator.translate(seconds.getHudQuad(), BaseModelObject.PITCH, 257);
        NodeTranslator.translate(seconds.getHudQuad(), BaseModelObject.YAW, 100);
    }
}
