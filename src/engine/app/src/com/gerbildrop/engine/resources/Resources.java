package com.gerbildrop.engine.resources;

import java.net.URL;

public class Resources {
    public static final String BASE_PATH = "/dradis/skybox/";
    public static final URL STARS_TEXTURE = Resources.class.getResource(BASE_PATH + "stars.jpg");
    public static final URL TITLE_ICON = Resources.class.getResource(BASE_PATH + "XBG.png");
    public static final URL TARGETING_HUD = Resources.class.getResource(BASE_PATH + "huds/Crosshair2.png");
}
