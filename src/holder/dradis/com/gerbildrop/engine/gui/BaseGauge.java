package com.gerbildrop.engine.gui;

import java.nio.ByteBuffer;

import com.jme.renderer.Renderer;
import com.jme.scene.Geometry;
import com.gerbildrop.engine.data.DataSource;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.glu.GLU;

public abstract class BaseGauge extends Geometry {
    private int width;
    private int height;

    public BaseGauge(int _width, int _height) {
        this.width = _width;
        this.height = _height;
    }

    public float m_PhysicalPositionx;
    public float m_PhysicalPositiony;

    public float m_PhysicalSizex;
    public float m_PhysicalSizey;

    public float m_Scalex;
    public float m_Scaley;
    public float parentPhysicalPositionx;
    public float parentPhysicalPositiony;
    /** Set by the render window to describe pixel-realspace conversions */
    public double m_UnitsPerPixel;

    // gauge component and the offset of the parent guage
    public int m_PixelPositionx;
    public int m_PixelPositiony;

    // The size in pixels of the gauge is the physical size / mm per pixel
    public int m_PixelSizex;
    public int m_PixelSizey;

    public static final ByteBuffer CLBlack = ByteBuffer.wrap(new byte[]{(byte) 0, (byte) 0, (byte) 0});
    public static final ByteBuffer CLWhite = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 255, (byte) 255});
    public static final ByteBuffer CLYellow = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 255, (byte) 0});
    public static final ByteBuffer CLGrey = ByteBuffer.wrap(new byte[]{(byte) 95, (byte) 95, (byte) 95});
    public static final ByteBuffer CLGreen = ByteBuffer.wrap(new byte[]{(byte) 0, (byte) 255, (byte) 0});
    public static final ByteBuffer CLAmber = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 96, (byte) 64});
    public static final ByteBuffer CLRed = ByteBuffer.wrap(new byte[]{(byte) 240, (byte) 0, (byte) 0});
    public static final ByteBuffer CLBlue = ByteBuffer.wrap(new byte[]{(byte) 140, (byte) 156, (byte) 255});
    public static final ByteBuffer CLMagenta = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 0, (byte) 200});

    public float Roll = DataSource.getRoll();
    public float Pitch = DataSource.getPitch();

    // Get the altitude
    public long alt = (long) DataSource.getAltitude();

    public float heading = DataSource.getMagneticHeading();

    public float m_fHeading = (float) DataSource.getTrueHeading();

    public float m_fTAS = (float) DataSource.getTas();
    public float m_fGS = (float) DataSource.getIas();

    public float m_vHeading = DataSource.getMagneticHeading();//diom_loadVariable("orientation.heading");

    public float m_vND_Range = DataSource.getRange();//diom_loadVariable("controls.efis.nd_range");

    // Speed for integer calculations
    public long spd = (long) DataSource.getIas();

    // Speed for floating point calculations
    public float ias = DataSource.getIas();

    public float Vertical_Speed_FPM = DataSource.getVertSpeedFPM();

    public float m_fAutopilotHeading = DataSource.getAutoPilotHeading();

    public boolean m_bWindAvailable = DataSource.isWindAvailable();

    public int m_iND_Range = (int) m_vND_Range;
    public String m_sNavSelect1 = DataSource.getNavSelect1();
    public String m_sNavSelect2 = DataSource.getNavSelect2();

    public boolean m_bChronoActve = DataSource.isChronoActve();
    public int m_iChronoTimer = 0;//DataSource.getChronoTimer();

    public float m_fVOR1_frequency = DataSource.getNav1Freq();
    public boolean m_bVOR1_tuned = DataSource.isVOR1Tuned();
    public float m_fVOR1_bearing = 0;//DataSource.getNav1Bearing();
    public float m_fVOR1_course = 0;//DataSource.getVOR1Course();
    public float m_fVOR1_deviation = 0;//DataSource.getVOR1Deviation();

    public float m_fADF1_frequency = 0;//DataSource.getADF1Frequency();
    public boolean m_bADF1_tuned = DataSource.isADF1Tuned();
    public float m_fADF1_bearing = 0;//DataSource.getADF1Bearing();

    public float m_fDME1_distance = 0;//DataSource.getDME1Distance();

    public float m_fVOR2_frequency = 0;//DataSource.getVOR2Frequency();
    public boolean m_bVOR2_tuned = DataSource.isVOR2Tuned();
    public float m_fVOR2_bearing = 0;//DataSource.getVOR2Bearing();
    public float m_fVOR2_course = 0;//DataSource.getVOR2Course();
    public float m_fVOR2_deviation = 0;//DataSource.getVOR2Deviation();

    public float m_fADF2_frequency = 0;//DataSource.getADF2Frequency();
    public boolean m_bADF2_tuned = DataSource.isADF2Tuned();
    public float m_fADF2_bearing = 0;//DataSource.getADF2Bearing();

    public float m_fDME2_distance = 0;//DataSource.getDME2Distance();

    public boolean attitudeAvailable = DataSource.isAttitudeAvailable();
    public boolean altitudeAvail = DataSource.isAltitudeAvailable();
    public boolean headingAvail = DataSource.isHeadingAvailable();
    public boolean vertSpeedAvail = DataSource.isVertSpeedAvailable();
    public boolean airSpeedAvailable = DataSource.isAirSpeedAvailable();

    public float velocity_V1 = 0;//DataSource.getVelocity_V1();
    public float velocity_VR = 0;//DataSource.getVelocity_VR();
    public float velocity_V2 = 0;//DataSource.getVelocity_V2();
    public float velocity_VSW = 0;//DataSource.getVelocity_VSW();
    public float velocity_VLS = 0;//DataSource.getVelocity_VLS();
    public float velocity_VMax = 0;//DataSource.getVelocity_VMax();
    public boolean lcabinDoor = DataSource.isLcabinDoorClosed();
    public boolean lcabinDoor2 = DataSource.isLcabinDoor2Closed();
    public boolean lcabinDoor3 = DataSource.isLcabinDoor3Closed();
    public boolean lcabinDoor4 = DataSource.isLcabinDoor4Closed();

    public boolean rcabinDoor = DataSource.isRcabinDoorClosed();
    public boolean rcabinDoor2 = DataSource.isRcabinDoor2Closed();
    public boolean rcabinDoor3 = DataSource.isRcabinDoor3Closed();
    public boolean rcabinDoor4 = DataSource.isRcabinDoor4Closed();

    public boolean cargoDoor1 = DataSource.isCargoDoor1Closed();
    public boolean cargoDoor2 = DataSource.isCargoDoor2Closed();

    public boolean bulkDoor = DataSource.isBulkDoorClosed();
    public boolean avionicDoor = DataSource.isAvionicDoorClosed();

    public boolean resevoir1LowAirPress = DataSource.isResevoir1LowAirPress();
    public boolean resevoir1Overheat = DataSource.isResevoir1Overheat();
    public boolean resevoir1ValveOpen = DataSource.isResevoir1ValveOpen();
    public boolean enginePump1On = DataSource.isEnginePump1On();
    public boolean enginePump1LowPress = DataSource.isEnginePump1LowPress();
    public boolean electPump1commanded = DataSource.isElectPump1commanded();
    public boolean electPump1On = DataSource.isElectPump1On();
    public boolean electPump1LowPress = DataSource.isElectPump1LowPress();

    public boolean resevoir2LowAirPress = DataSource.isResevoir2LowAirPress();
    public boolean resevoir2Overheat = DataSource.isResevoir2Overheat();
    public boolean resevoir2ValveOpen = DataSource.isResevoir2ValveOpen();
    public boolean enginePump2On = DataSource.isEnginePump2On();
    public boolean enginePump2LowPress = DataSource.isEnginePump2LowPress();
    public boolean electPump2commanded = DataSource.isElectPump2commanded();
    public boolean electPump2On = DataSource.isElectPump2On();
    public boolean electPump2LowPress = DataSource.isElectPump2LowPress();

    public boolean resevoir3LowAirPress = DataSource.isResevoir3LowAirPress();
    public boolean resevoir3Overheat = DataSource.isResevoir3Overheat();
    public boolean resevoir3ValveOpen = DataSource.isResevoir3ValveOpen();
    public boolean enginePump3On = DataSource.isEnginePump3On();
    public boolean enginePump3LowPress = DataSource.isEnginePump3LowPress();
    public boolean electPump3commanded = DataSource.isElectPump3commanded();
    public boolean electPump3On = DataSource.isElectPump3On();
    public boolean electPump3LowPress = DataSource.isElectPump3LowPress();

    public boolean resevoir4ValveOpen = DataSource.isResevoir4ValveOpen();
    public boolean enginePump4On = DataSource.isEnginePump4On();
    public boolean enginePump4LowPress = DataSource.isEnginePump4LowPress();
    public boolean ramAirTurbineStowed = DataSource.isRamAirTurbineStowed();
    public boolean ramAirTurbineStowing = DataSource.isRamAirTurbineStowing();
    public boolean ramAirTurbineOverspeed = DataSource.isRamAirTurbineOverspeed();

    public static boolean bInited = false;


    protected void initWindow() {
        GL11.glViewport(0, 0, width, height);
//        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
//        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(255.0f, 255.0f, 255.0f, 0.0f); // Black Background
//        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

//        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
                45.0f,
                width / height,
                0.1f,
                100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
//        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }

    protected void meatStart() {
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    protected void meatEnd() {
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    protected void doColor(ByteBuffer bb) {
        GL11.glColor3ub(bb.get(0), bb.get(1), bb.get(2));
    }

    public void draw(Renderer renderer) {
        super.draw(renderer);
        initWindow();
        meatStart();
        draw();
        meatEnd();
    }

    /**
     * Prepares an orthographic projection matrix. Saves the current projection matrix on the stack.
     * <p/>
     * <B>Remember to call <code>endOrtographicProjection</code> when done in orthographic mode</B>
     *
     * @param width  of the projection screen
     * @param height of the projection screen
     */
    protected void beginOrthographicProjection() {
        GL11.glMatrixMode(GL11.GL_PROJECTION); // switch to projection stack
        GL11.glPushMatrix(); // store projection matrix
        GL11.glLoadIdentity(); // reset matrix
        GLU.gluOrtho2D(0, width, 0, height); // set a 2D orthographic projection
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // switch to projection stack
        GL11.glPushMatrix(); // store current matrix
        GL11.glLoadIdentity(); // need identity matrix for translation in orthographic perspective
    } // method

    /** Restores the previous projection from the stack. */
    protected void endOrthographicProjection() {
        GL11.glMatrixMode(GL11.GL_PROJECTION); // switch to projection stack
        GL11.glPopMatrix(); // restore perspective matrix
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // switch to projection stack
        GL11.glPopMatrix();
    } // method

    protected abstract void draw();
}
