package com.gerbildrop.dradis.model;

import java.io.IOException;
import java.net.URL;

import com.gerbildrop.dradis.logging.Debug;
import com.gerbildrop.dradis.ships.BaseModelObject;
import com.jme.scene.Node;
import com.jme.util.export.binary.BinaryImporter;

public class BModelFactory {

    private static final BModelFactory _INSTANCE = new BModelFactory();

    public static BModelFactory getInstance() {
        return _INSTANCE;
    }

    private BModelFactory() {}

    public BaseModelObject createModelTest(String texture, String model) {
        BaseModelObject returnable = new BaseModelObject();
        returnable.setTexturePath(ClassLoader.getSystemResource(texture));
        returnable.setModelPath(ClassLoader.getSystemResource(model));

        returnable.setRootNode(loadModelWithBinaryImporter(returnable.getModelPath()));

        returnable.setLocalValues();

        return returnable;
    }

    private Node loadModelWithBinaryImporter(URL _modelPath) {
        Node rootNode = null;

        BinaryImporter jbr = BinaryImporter.getInstance();
//        JmeBinaryReader jbr = new JmeBinaryReader();

        try {
//            long time = System.currentTimeMillis();
            rootNode = (Node) jbr.load(_modelPath.openStream());
        } catch (IOException e) {
            Debug.error(e);
        }

        return rootNode;
    }
}
