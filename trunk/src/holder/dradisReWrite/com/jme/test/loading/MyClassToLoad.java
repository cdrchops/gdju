package com.jme.test.loading;

/**
 * The class that we're going to store our objects into to load for our next GameState or store the objects here for the
 * entire scope of the game
 *
 * @author vivaldi
 * @version $Id: MyClassToLoad.java,v 1.3 2007/04/04 14:30:55 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class MyClassToLoad {
    public static class MyResourceLoaderThread implements Runnable {
        public void run() {
            loadResources();
        }
    }

    private static void loadResources() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //put resources to load here
        //I use a Map to store all of my nodes and textures, then call this class
        // once loaded to get those objects from the Map
        // you can do whatever you want
    }
}