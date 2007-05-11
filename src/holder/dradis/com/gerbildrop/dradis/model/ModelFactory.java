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

package com.gerbildrop.dradis.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import com.gerbildrop.dradis.logging.Debug;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.state.CullState;
import com.jme.system.DisplaySystem;
import com.jme.util.export.binary.BinaryImporter;
import com.jmex.model.XMLparser.Converters.FormatConverter;
import com.jmex.model.XMLparser.Converters.MaxToJme;

/**
 * takes a texture path and a model name and loads them as a Model then passes that model on to the caller
 *
 * @author vivaldi
 * @version $Id: ModelFactory.java,v 1.19 2007/04/04 14:29:03 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class ModelFactory {
    private static final ModelFactory _INSTANCE = new ModelFactory();

    public static ModelFactory getInstance() {
        return _INSTANCE;
    }

    private ModelFactory() {}

    public Node createModelTest(String texture, String model) {
        Node rootNode;

        URL _texturePath = ClassLoader.getSystemResource(texture);
        URL _modelPath = ClassLoader.getSystemResource(model);

        assert _modelPath != null;
        assert _texturePath != null;

        rootNode = loadModelWithBinaryImporter(_modelPath);
//        rootNode = loadModelWithConverter(_modelPath, _texturePath);
        assert rootNode != null;

        rootNode.setLocalTranslation(new Vector3f(0.0F, 0.0F, 0F));
        rootNode.setLocalScale(0.1F);
        rootNode.updateGeometricState(0.0F, true);

        if (rootNode.getChild(0).getControllers().size() != 0) {
            rootNode.getChild(0).getController(0).setSpeed(20);
        }

        CullState cullState = DisplaySystem.getDisplaySystem().getRenderer().createCullState();
        cullState.setCullMode(CullState.CS_BACK);

        return rootNode;
    }

    public Model createModel(String texture, String model) {
        Model m = new Model();
        Node rootNode;

        URL _texturePath = ClassLoader.getSystemResource(texture);
        URL _modelPath = ClassLoader.getSystemResource(model);

        assert _modelPath != null;
        assert _texturePath != null;

        rootNode = loadModelWithBinaryImporter(_modelPath);
//        rootNode = loadModelWithConverter(_modelPath, _texturePath);
        assert rootNode != null;

        rootNode.setLocalTranslation(new Vector3f(0.0F, 0.0F, 0F));
        rootNode.setLocalScale(0.1F);
        rootNode.updateGeometricState(0.0F, true);

        if (rootNode.getChild(0).getControllers().size() != 0) {
            rootNode.getChild(0).getController(0).setSpeed(20);
        }

        CullState cullState = DisplaySystem.getDisplaySystem().getRenderer().createCullState();
        cullState.setCullMode(CullState.CS_BACK);

        m.setModelPath(_modelPath);
        m.setTexturePath(_texturePath);
        m.setNode(rootNode);

        return m;
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

    private Node loadModelWithConverter(URL _modelPath, URL _texturePath) {
        Node r1 = null;
        try {
            FormatConverter maxConverter = new MaxToJme();
            ByteArrayOutputStream BO = new ByteArrayOutputStream();

            if (_texturePath != null) {
                maxConverter.setProperty("texurl", _texturePath);
            }

            maxConverter.convert(new BufferedInputStream(_modelPath.openStream()), BO);
            r1 = (Node) BinaryImporter.getInstance().load(new ByteArrayInputStream(BO.toByteArray()));

        } catch (IOException e) {
            System.out.println("Damn exceptions:" + e);
            e.printStackTrace();
        }

        return r1;
    }
}
