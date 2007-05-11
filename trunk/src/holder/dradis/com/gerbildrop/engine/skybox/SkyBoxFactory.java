package com.gerbildrop.engine.skybox;

import java.net.URL;

import com.jme.image.Texture;
import com.jme.scene.Skybox;
import com.jme.util.TextureManager;

/** @author Matthew D. Hicks */
public class SkyBoxFactory {
    public static Skybox createSkybox(String name, URL url, float extent, boolean preload) {
        return createSkybox(name, url, url, url, url, url, url, extent, preload);
    }

    public static Skybox createSkybox(String name, URL up, URL down, URL north, URL south, URL east, URL west, float extent, boolean preload) {
        Skybox skybox = new Skybox(name, extent, extent, extent);
        Texture tUp = TextureManager.loadTexture(
                up,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tDown = TextureManager.loadTexture(
                down,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tNorth = TextureManager.loadTexture(
                north,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tSouth = TextureManager.loadTexture(
                south,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tEast = TextureManager.loadTexture(
                east,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tWest = TextureManager.loadTexture(
                west,
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        skybox.setTexture(Skybox.UP, tUp);
        skybox.setTexture(Skybox.DOWN, tDown);
        skybox.setTexture(Skybox.NORTH, tNorth);
        skybox.setTexture(Skybox.SOUTH, tSouth);
        skybox.setTexture(Skybox.EAST, tEast);
        skybox.setTexture(Skybox.WEST, tWest);
        //if (preload) skybox.preloadTextures();
        return skybox;
    }
}
