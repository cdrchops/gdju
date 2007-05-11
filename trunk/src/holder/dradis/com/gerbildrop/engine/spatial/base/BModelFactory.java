package com.gerbildrop.engine.spatial.base;

import java.io.IOException;
import java.net.URL;

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
        TextureObject to = new TextureObject();
        to.setTexturePath(ClassLoader.getSystemResource(texture));
        returnable.setTextureObject(to);
        returnable.setModelPath(ClassLoader.getSystemResource(model));

        Node n = loadModelWithBinaryImporter(returnable.getModelPath());

        returnable = (BaseModelObject) n;
        returnable.setTextureObject(to);
        returnable.setModelPath(ClassLoader.getSystemResource(model));

        returnable.setLocalValues();

        return returnable;
    }

    private Node loadModelWithBinaryImporter(URL _modelPath) {
        Node rootNode = null;

        BinaryImporter jbr = BinaryImporter.getInstance();

        try {
            rootNode = (Node) jbr.load(_modelPath.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootNode;
    }
}
