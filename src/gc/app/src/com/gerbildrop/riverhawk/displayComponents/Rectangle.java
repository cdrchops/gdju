package com.gerbildrop.riverhawk.displayComponents;

import org.lwjgl.opengl.GL11;

import javax.media.opengl.GL;

import com.gerbildrop.riverhawk.displayComponents.BaseComponent;

/**
 * $ID:$
 * $COPYRIGHT:$
 *
 * @author torr
 * @since Nov 20, 2007 - 11:48:11 AM
 */
public class Rectangle extends BaseComponent {
    public void drawLWJGL() {
        // Draw the background rectangle
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, m_PhysicalSizey);
        GL11.glVertex2f(m_PhysicalSizex, m_PhysicalSizey);
        GL11.glVertex2f(m_PhysicalSizex, 0.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glEnd();
    }

    public void drawJOGL(GL gl) {
        // Draw the background rectangle
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(0.0f, m_PhysicalSizey);
        gl.glVertex2f(m_PhysicalSizex, m_PhysicalSizey);
        gl.glVertex2f(m_PhysicalSizex, 0.0f);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glEnd();
    }
}
