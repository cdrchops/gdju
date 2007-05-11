package com.gerbildrop.gl.color;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

public class GLColorFactory {
    //todo: add JOGL support to the factories
    //todo: everything should work with both LWJGL in JME and
    //todo:  JOGL
    public static void doColor(ByteBuffer bb) {

        GL11.glColor3ub(bb.get(0), bb.get(1), bb.get(2));

        //if jogl then
        // gl.glColor3ubv(bb);
    }

    public static void doColor(GLColors.COLORS bb) {

        doColor(GLColors.getColor(bb));

        //if jogl then
        // gl.glColor3ubv(bb);
    }
}
