package com.gerbildrop.mask.powerUps;

import java.net.URL;

import com.jme.image.Texture;
import com.jme.light.LightNode;
import com.jme.light.PointLight;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class PowerUpBox {
    Box powerEnclosure;
    Sphere powerUp;

    public void create(Node rootNode, Vector3f location) {
        DisplaySystem display = DisplaySystem.getDisplaySystem();

        // Define the enclosures box dimensions.
        Vector3f min = new Vector3f(-5, -5, -5);
        Vector3f max = new Vector3f(5, 5, 5);

        powerEnclosure = new Box("Power Up Enclosure", min, max);

        URL enclosureTextureLoc = PowerUpBox.class.getResource("/asTest/as_ex_edgealpha2.png");

        TextureState enclosureTS = display.getRenderer().createTextureState();
        Texture enclosureT = TextureManager.loadTexture(enclosureTextureLoc, Texture.MM_LINEAR_LINEAR, Texture.FM_LINEAR, 20f, false);
//        enclosureT.setCorrection(Texture.CM_PERSPECTIVE);
        enclosureT.setApply(Texture.AM_ADD);
        enclosureT.setCombineFuncRGB(Texture.ACF_ADD_SIGNED);
        enclosureT.setCombineSrc0RGB(Texture.ACS_TEXTURE);
        enclosureT.setCombineOp0RGB(Texture.ACO_SRC_COLOR);
        enclosureT.setCombineSrc1RGB(Texture.ACS_PREVIOUS);
        enclosureT.setCombineOp1RGB(Texture.ACO_SRC_COLOR);
        enclosureT.setCombineScaleRGB(1.0f);
        enclosureT.setWrap(Texture.WM_BCLAMP_S_BCLAMP_T);
        enclosureT.setRotation(new Quaternion(10f, 10f, 10f, 10f));
        enclosureTS.setTexture(enclosureT);
        powerEnclosure.setRenderState(enclosureTS);

        // Create an alpha state.
        AlphaState as = display.getRenderer().createAlphaState();
        // Blend between the source and destination functions.
        as.setBlendEnabled(true);
        // Set the source function setting.
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        // Set the destination function setting.
        as.setDstFunction(AlphaState.SB_ONE_MINUS_SRC_ALPHA);
        // Enable the test (?  yea, I don't know ?)
        as.setTestEnabled(true);
        // Set the test function to TF_GREATER.
        as.setTestFunction(AlphaState.TF_GREATER);
        // Assign the alpha state to the powerEnclosure's render state.
        powerEnclosure.setRenderState(as);

        // Detailed in the Sphere, TextureState, Texture wiki page.
        powerUp = new Sphere("The WOW factor", 25, 25, 4);
        URL colorTextureLoc = PowerUpBox.class.getResource("/asTest/as_ex_colors.png");
        TextureState colorTextureTS = display.getRenderer().createTextureState();
        Texture colorTextureT = TextureManager.loadTexture(colorTextureLoc, Texture.MM_LINEAR, Texture.FM_LINEAR, 1f, false);
        colorTextureT.setWrap(Texture.WM_WRAP_S_WRAP_T);
        colorTextureTS.setTexture(colorTextureT);
        powerUp.setRenderState(colorTextureTS);

        // Detailed in the LightState and LightNode wiki page.
        LightState ls = display.getRenderer().createLightState();
        LightNode ln = new LightNode("Power-Up Light", ls);
        PointLight pt = new PointLight();
        pt.setAmbient(ColorRGBA.yellow);
        pt.setSpecular(ColorRGBA.blue);
        pt.setLocation(new Vector3f(30f, 30f, 30f));
        pt.setEnabled(true);
        ln.setLight(pt);
        ln.setTarget(powerEnclosure);
        powerEnclosure.setRenderState(ls);
        powerEnclosure.updateRenderState();
//        rootNode.updateRenderState();
        powerEnclosure.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        powerUp.setRenderQueueMode(Renderer.QUEUE_OPAQUE);

        // Create a node to hold he power-up objects.
        Node n = new Node("PowerUp Pellet");
        n.setRenderQueueMode(Renderer.QUEUE_OPAQUE);
        // Attach the center power-up to the light node
        ln.attachChild(powerUp);
        // Attach the enclosure to the light node
        ln.attachChild(powerEnclosure);
        // Attach the light node to the PowerUp Pellet node.
        n.attachChild(ln);
        n.setLocalTranslation(location);//new Vector3f(20, 0, 0)
        n.setLocalScale(.1f);
        //  Attache the PowerUp Pellet node to the scenegraphs rootNode.
        rootNode.attachChild(n);
        //  Update the render state for lighting
//        rootNode.updateRenderState();
    }
}
