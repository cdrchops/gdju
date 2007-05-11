package com.gerbildrop.dradis.spatial.ships;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.gerbildrop.dradis.resources.DradisResources;
import com.gerbildrop.engine.spatial.base.BaseModelObject;
import com.gerbildrop.engine.spatial.base.ShipBase;
import com.gerbildrop.engine.util.NodeRotator;
import com.jme.scene.Node;
import com.jmex.pool.ByteArraySpatialGenerator;
import com.jmex.pool.SpatialPool;
import com.jmex.sound.openAL.SoundSystem;

public class Battlestar extends ShipBase {
    private static final long serialVersionUID = 2L;
    private static SpatialPool<BaseModelObject> pool;

    public Battlestar(Node rootNode, String name) {
        super(rootNode, name);
    }

    public URL getResource() {
        return DradisResources.galactica;
    }

    public void rotateAndTranslate(Node model) {
        NodeRotator.rotate(model, BaseModelObject.ROLL, (float) Math.toRadians(180));
        NodeRotator.rotate(model, BaseModelObject.YAW, (float) Math.toRadians(180));
        NodeRotator.rotate(model, BaseModelObject.PITCH, (float) Math.toRadians(90));
    }

    protected void initSound() {
        snode = SoundSystem.createSoundNode();
        firingSound = SoundSystem.create3DSample(Viper2.class.getClassLoader().getResource("jmetest/data/sound/explosion.ogg"));

        SoundSystem.bindEventToSample(firingSound, FIRING_SOUND);

        SoundSystem.setSampleMaxAudibleDistance(firingSound, 5000);
        SoundSystem.addSampleToNode(firingSound, snode);
    }

    public void initHull() throws Exception {
        if (pool == null) {

            InputStream in = getResource().openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[512];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
            out.close();
            in.close();
            byte[] bytes = out.toByteArray();
            ByteArraySpatialGenerator<BaseModelObject> generator = new ByteArraySpatialGenerator<BaseModelObject>(bytes);
            pool = new SpatialPool<BaseModelObject>(generator, 5, SpatialPool.Mode.GROWABLE, false);
        }

        Node model = pool.get();
        attachChild(model);

        rotateAndTranslate(model);

        setLocalScale(0.2f);
    }

}
