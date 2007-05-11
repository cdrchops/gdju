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

import com.gerbildrop.dradis.model.Model;
import com.gerbildrop.dradis.model.ModelFactory;
import com.gerbildrop.dradis.ships.basic.Vehicle;
import com.jme.util.CloneConfiguration;

/**
 * @author vivaldi
 * @version $Id: DradisModelStorage.java,v 1.3 2007/04/04 14:31:00 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class DradisModelStorage {
    private static final Map<String, Model> hm = new HashMap<String, Model>();
    private static final CloneImportExport ie = new CloneImportExport();
    private static final String TEXTURE_PATH = "dradis/models/";

    public static class DradisModelStorageThread implements Runnable {
        public void run() {
            loadDradisModels();
        }
    }

    private static void loadDradisModels() {
        final CloneConfiguration SHARED_GEOM_BUFFER_CLONE = new CloneConfiguration(new String[] {}, new String[] {"vertBuf","colorBuf", "texBuf","normBuf"});
        ie.applyConfiguration(SHARED_GEOM_BUFFER_CLONE);

        hm.put("raptor", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/raptor.jme"));
        hm.put("raider", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/raider.jme"));
        hm.put("viper2", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/viper2.jme"));
        hm.put("viper7", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/viper7.jme"));
        hm.put("heavyRaider", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/heavyRaider.jme"));
        hm.put("galactica", ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/galactica.jme"));

//        try {
//            ie.save(hm.get("raptor"), new File("raptor.txt"));
//            ie.save(hm.get("raider"), new File("raider.txt"));
//            ie.save(hm.get("viper2"), new File("viper2.txt"));
//            ie.save(hm.get("heavyRaider"), new File("heavyRaider.txt"));
//            ie.save(hm.get("galactica"), new File("galactica.txt"));
//        } catch (IOException e) {
//            Debug.error(e);
//        }
    }

    public static Vehicle getVehicle(String name) {
        Model tmp = ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/" + name + ".jme");

        Vehicle tmpV = new Vehicle();
        tmpV.setRootNode(tmp.getRootNode());
        tmpV.setTexturePath(tmp.getTexturePath());
        tmpV.setModelPath(tmp.getModelPath());
        tmpV.setTextureWidth(tmp.getTextureWidth());
        tmpV.setTextureHeight(tmp.getTextureHeight());
        tmpV.setModelQuad(tmp.getModelQuad());

        return tmpV;
    }

    public static Model get(String name) {
//        ie.saveClone(hm.get(name));

        Model tmp = ModelFactory.getInstance().createModel(TEXTURE_PATH, "dradis/models/" + name + ".jme");
        //null;//
//        try {
//            tmp = (Model) hm.get(name).clone();
//        } catch (CloneNotSupportedException e) {
//            Debug.error(e);
//        }

        return tmp;//(Node) ie.loadClone();
    }
}
///** A configuration that specifies that all geometry buffers should be shared between copies */
//public static final CloneConfiguration SHARED_GEOM_BUFFER_CLONE =
//               new CloneConfiguration(new String[] {}, new String[] {"vertBuf","colorBuf", "texBuf","normBuf"});
///** A configuration that specifies that color and texture buffers should be shared between copies */
//public static final CloneConfiguration SHARED_COLOR_AND_TEXTURE_BUFFER_CLONE =
//               new CloneConfiguration(new String[] {},  new String[] {"colorBuf", "texBuf"});