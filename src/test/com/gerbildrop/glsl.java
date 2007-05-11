package com.gerbildrop;

import java.net.URL;
import java.nio.FloatBuffer;

import com.jme.app.SimpleGame;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.batch.TriangleBatch;
import com.jme.scene.shape.Box;
import com.jme.scene.state.GLSLShaderObjectsState;
import com.jme.util.geom.BufferUtils;

public class glsl extends SimpleGame {

    public static void main(String[] args) {
        glsl app = new glsl();
        app.start();
    }

    protected void simpleInitGame() {
        display.getRenderer().setBackgroundColor(ColorRGBA.gray);

        Box box = new Box("box", new Vector3f(-10, -10, -10), new Vector3f(10, 10, 10));
        rootNode.attachChild(box);

        TriangleBatch batch = box.getBatch(0);
        int vertexCount = batch.getVertexCount();

        FloatBuffer colors = BufferUtils.createFloatBuffer(4 * vertexCount);
        for (int i = 0; i < vertexCount; ++i) {
            colors.put((float) Math.random()).put((float) Math.random()).put((float) Math.random()).put(1.0f);
        }

        GLSLShaderObjectsState glslState = display.getRenderer().createGLSLShaderObjectsState();
        URL vert = glsl.class.getResource("glsl/vertex.glsl");
        URL frag = glsl.class.getResource("glsl/fragment.glsl");
        glslState.load(frag, vert);
//        glslState.setAttributePointer("attColor", 4, false, 0, colors);
        glslState.relinkProgram();
        glslState.setEnabled(true);

//        box.setRenderState(glslState);
    }
}