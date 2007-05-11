package com.jme.test.loading;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles loading screens for your game when loading is done and your loadingObject is done then it passes you on to
 * your next GameState
 *
 * @author vivaldi
 * @version $Id: LoadingManager.java,v 1.3 2007/04/04 14:30:55 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class LoadingManager {

    private LoadingManager() {
    }

    private static final LoadingManager _INSTANCE = new LoadingManager();

    public static LoadingManager getInstance() {
        return _INSTANCE;
    }

    private static Map<String, LoadingObject> loaderMap = new HashMap<String, LoadingObject>();

    public void runLoader(String name) {
        runLoader(name, null, null);
    }

    public void runLoader(String name, URL imagePath) {
        loaderMap.get(name).runLoader(imagePath, null);
    }

    public void runLoader(String name, URL imagePath, URL img2) {
        loaderMap.get(name).runLoader(imagePath, img2);
    }

    public void runLoader(String name, TextureDefinition background, TextureDefinition border, TextureDefinition overlay) {
        loaderMap.get(name).runLoader(background, border, overlay);
    }

    public void checkState(String name) {
        loaderMap.get(name).checkState();
    }

    public void addLoader(LoadingObject lo) {
        loaderMap.put(lo.getName(), lo);
    }

    public boolean isFinishedLoading(String name) {
        return loaderMap.get(name).isFinishedLoading();
    }
}