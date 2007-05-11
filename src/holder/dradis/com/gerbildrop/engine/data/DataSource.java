package com.gerbildrop.engine.data;

import com.jme.scene.Geometry;

/**
 * @author timo
 * @version ${1.1}
 * @since Jul 7, 2006 9:30:51 PM
 */
public abstract class DataSource extends Geometry {
    private static int engineNumber = 1;
    private static int display = 5;

    private static float roll = 0;
    private static float pitch = 0;
    private static float trueHeading = 0;
    private static float magneticVariation = 0;
    private static float latitudeAircraftLow = 0;
    private static float latitudeAircraftHigh = 0;
    private static float longitudeAircraftLow = 0;
    private static float longitudeAircraftHigh = 0;
    private static float ias = 0;
    private static float tas = 0;
    private static float machSpeed = 0;
    private static float groundSpeedK = 0;
    private static float verticalSpeedMS = 0;
    private static float fracMeters = 0;
    private static float unitMeters = 0;
    private static float flapsDeflectionRadians = 0;
    private static float flapsTrueDeflectionRadians = 0;
    private static float noseGear = 0;
    private static float rightGear = 0;
    private static float leftGear = 0;
    private static float altimeterPressure = 0;
    private static float tat = 0;

    //------------------------Nav Radio--------------------------
    private static float nav1Freq = 0;
    private static float nav1Radial = 0;
    private static String nav1LocalizerNeedle = null;
    private static float nav1OBS = 0;
    private static boolean nav1GlideslopeAlive = false;
    private static String nav1GlideslopeNeedle = null;
    private static float nav2Freq = 0;
    private static float nav2Radial = 0;
    private static String nav2LocalizerNeedle = null;
    private static float nav2OBS = 0;
    private static float ADFHeading = 0;
    private static float rStatus = 0;

    //---------------------------ILS--------------------------------
    private static float ILSInverseRunwayHeading = 0;
    private static float ILSGlideslopeInclination = 0;

    //------------------------Fuel----------------------------------
    private static float fuelCentreLevel = 0;
    private static float fuelLeftLevel = 0;
    private static float fuelRightLevel = 0;
    private static float fuelCentreCapacity = 0;
    private static float fuelLeftCapacity = 0;
    private static float fuelRightCapacity = 0;

    //------------------------Autopilot-----------------------------
    private static float dirActive = 0;
    private static float dirPitch = 0;
    private static float dirBank = 0;
    private static float refAirspeedK = 0;

    //-------------------------------------------------------------
    private static float totalAirTemperature = 0;

    //---------------------- Fuel Tanks----------------------------
    private static float fuelTankCapacityCenter = 0;
    private static float fuelTankCapacityLeft = 0;
    private static float fuelTankCapacityRight = 0;
    private static float fuelTankLevelCenter = 0;
    private static float fuelTankLevelLeft = 0;
    private static float fuelTankLevelRight = 0;

    //-------------------------Engines-----------------------------

    //-------------------------Engine 1----------------------------
    private static float engine1N1 = 0;
    private static float engine1N2 = 0;
    private static float engine1FF_PPH = 0;
    private static float engine1EPR = 0;
    private static float engine1EGT = 0;

    // mdf
    private static String pp1 = null;
    private static String pp2 = null;
    private static float engine1OilPressure = 0;
    private static float engine1OilTemp = 0;
    private static float engine1OilQuantity = 0;
    private static float engine1Vibration = 0;
    private static float engine1HydraulicPressure = 0;
    private static float engine1HydraulicQuantity = 0;

    //-------------------------Engine 2----------------------------
    private static float engine2N1 = 0;
    private static float engine2N2 = 0;
    private static float engine2FFPPH = 0;
    private static float engine2EPR = 0;
    private static float engine2EGT = 0;

    // mdf
    private static String pp3 = null;
    private static String pp4 = null;
    private static float engine2OilPressure = 0;
    private static float engine2OilTemp = 0;
    private static float engine2OilQuantity = 0;
    private static float engine2Vibration = 0;
    private static float engine2HydraulicPressure = 0;
    private static float engine2HydraulicQuantity = 0;
    private static float autoPilotHeading = 0;
    private static float adf1Heading = 0;
    private static float magneticHeading = 0;
    private static float latitude = 0;
    private static float longitude = 0;
    private static float vertSpeedFPM = 0;
    private static float altitudeMeters = 0;
    private static float altitude = 0;

    //button set parameters
    private static boolean lcabinDoorClosed = false;
    private static boolean lcabinDoor2Closed = false;
    private static boolean lcabinDoor3Closed = false;
    private static boolean lcabinDoor4Closed = false;

    private static boolean rcabinDoorClosed = false;
    private static boolean rcabinDoor2Closed = false;
    private static boolean rcabinDoor3Closed = false;
    private static boolean rcabinDoor4Closed = false;

    private static boolean cargoDoor1Closed = false;
    private static boolean cargoDoor2Closed = false;

    private static boolean bulkDoorClosed = false;
    private static boolean avionicDoorClosed = false;

    private static boolean resevoir1LowAirPress = false;
    private static boolean resevoir1Overheat = false;
    private static boolean resevoir1ValveOpen = false;
    private static boolean enginePump1On = false;
    private static boolean enginePump1LowPress = false;
    private static boolean electPump1commanded = false;
    private static boolean electPump1On = false;
    private static boolean electPump1LowPress = false;

    private static boolean resevoir2LowAirPress = false;
    private static boolean resevoir2Overheat = false;
    private static boolean resevoir2ValveOpen = false;
    private static boolean enginePump2On = false;
    private static boolean enginePump2LowPress = false;
    private static boolean electPump2commanded = false;
    private static boolean electPump2On = false;
    private static boolean electPump2LowPress = false;

    private static boolean resevoir3LowAirPress = false;
    private static boolean resevoir3Overheat = false;
    private static boolean resevoir3ValveOpen = false;
    private static boolean enginePump3On = false;
    private static boolean enginePump3LowPress = false;
    private static boolean electPump3commanded = false;
    private static boolean electPump3On = false;
    private static boolean electPump3LowPress = false;

    private static boolean resevoir4ValveOpen = false;
    private static boolean enginePump4On = false;
    private static boolean enginePump4LowPress = false;
    private static boolean ramAirTurbineStowed = false;
    private static boolean ramAirTurbineStowing = false;
    private static boolean ramAirTurbineOverspeed = false;
    /*
    private static int chronoTimer = 0;

    private static float VOR1Frequency = 0;

    private static float VOR1Bearing = 0;
    private static float VOR1Course = 0;
    private static float VOR1Deviation = 0;

    private static float ADF1Frequency = 0;

    private static float ADF1Bearing = 0;

    private static float DME1Distance = 0;

    private static float VOR2Frequency = 0;

    private static float VOR2Bearing = 0;
    private static float VOR2Course = 0;
    private static float VOR2Deviation = 0;

    private static float ADF2Frequency = 0;

    private static float ADF2Bearing = 0;

    private static float DME2Distance = 0;


    private static float velocity_V1 = 0;
    private static float velocity_VR = 0;
    private static float velocity_V2 = 0;
    private static float velocity_VSW = 0;
    private static float velocity_VLS = 0;
    private static float velocity_VMax = 0;
*/
    private static float range = 0;

    private static boolean windAvailable = false;

    private static int ndRange = (int) range;
    private static String navSelect1 = "";
    private static String navSelect2 = "";

    private static boolean chronoActve = false;
    private static boolean VOR1Tuned = false;
    private static boolean ADF1Tuned = false;
    private static boolean VOR2Tuned = false;
    private static boolean ADF2Tuned = false;
    private static boolean attitudeAvailable = true;
    private static boolean altitudeAvailable = true;
    private static boolean headingAvailable = true;
    private static boolean vertSpeedAvailable = true;

    private static boolean airSpeedAvailable = true;

    public static int getEngineNumber() {
        return engineNumber;
    }

    public static void setEngineNumber(final int engineNumber) {
        DataSource.engineNumber = engineNumber;
    }

    public static int getDisplay() {
        return display;
    }

    public static void setDisplay(final int display) {
        DataSource.display = display;
    }

    public static float getRoll() {
        return roll;
    }

    public static void setRoll(final float roll) {
        DataSource.roll = roll;
    }

    public static float getPitch() {
        return pitch;
    }

    public static void setPitch(final float pitch) {
        DataSource.pitch = pitch;
    }

    public static float getTrueHeading() {
        return trueHeading;
    }

    public static void setTrueHeading(final float trueHeading) {
        DataSource.trueHeading = trueHeading;
    }

    public static float getMagneticVariation() {
        return magneticVariation;
    }

    public static void setMagneticVariation(final float magneticVariation) {
        DataSource.magneticVariation = magneticVariation;
    }

    public static float getLatitudeAircraftLow() {
        return latitudeAircraftLow;
    }

    public static void setLatitudeAircraftLow(final float latitudeAircraftLow) {
        DataSource.latitudeAircraftLow = latitudeAircraftLow;
    }

    public static float getLatitudeAircraftHigh() {
        return latitudeAircraftHigh;
    }

    public static void setLatitudeAircraftHigh(final float latitudeAircraftHigh) {
        DataSource.latitudeAircraftHigh = latitudeAircraftHigh;
    }

    public static float getLongitudeAircraftLow() {
        return longitudeAircraftLow;
    }

    public static void setLongitudeAircraftLow(final float longitudeAircraftLow) {
        DataSource.longitudeAircraftLow = longitudeAircraftLow;
    }

    public static float getLongitudeAircraftHigh() {
        return longitudeAircraftHigh;
    }

    public static void setLongitudeAircraftHigh(final float longitudeAircraftHigh) {
        DataSource.longitudeAircraftHigh = longitudeAircraftHigh;
    }

    public static float getIas() {
        return ias;
    }

    public static void setIas(final float ias) {
        DataSource.ias = ias;
    }

    public static float getTas() {
        return tas;
    }

    public static void setTas(final float tas) {
        DataSource.tas = tas;
    }

    public static float getMachSpeed() {
        return machSpeed;
    }

    public static void setMachSpeed(final float machSpeed) {
        DataSource.machSpeed = machSpeed;
    }

    public static float getGroundSpeedK() {
        return groundSpeedK;
    }

    public static void setGroundSpeedK(final float groundSpeedK) {
        DataSource.groundSpeedK = groundSpeedK;
    }

    public static float getVerticalSpeedMS() {
        return verticalSpeedMS;
    }

    public static void setVerticalSpeedMS(final float verticalSpeedMS) {
        DataSource.verticalSpeedMS = verticalSpeedMS;
    }

    public static float getFracMeters() {
        return fracMeters;
    }

    public static void setFracMeters(final float fracMeters) {
        DataSource.fracMeters = fracMeters;
    }

    public static float getUnitMeters() {
        return unitMeters;
    }

    public static void setUnitMeters(final float unitMeters) {
        DataSource.unitMeters = unitMeters;
    }

    public static float getFlapsDeflectionRadians() {
        return flapsDeflectionRadians;
    }

    public static void setFlapsDeflectionRadians(final float flapsDeflectionRadians) {
        DataSource.flapsDeflectionRadians = flapsDeflectionRadians;
    }

    public static float getFlapsTrueDeflectionRadians() {
        return flapsTrueDeflectionRadians;
    }

    public static void setFlapsTrueDeflectionRadians(final float flapsTrueDeflectionRadians) {
        DataSource.flapsTrueDeflectionRadians = flapsTrueDeflectionRadians;
    }

    public static float getNoseGear() {
        return noseGear;
    }

    public static void setNoseGear(final float noseGear) {
        DataSource.noseGear = noseGear;
    }

    public static float getRightGear() {
        return rightGear;
    }

    public static void setRightGear(final float rightGear) {
        DataSource.rightGear = rightGear;
    }

    public static float getLeftGear() {
        return leftGear;
    }

    public static void setLeftGear(final float leftGear) {
        DataSource.leftGear = leftGear;
    }

    public static float getAltimeterPressure() {
        return altimeterPressure;
    }

    public static void setAltimeterPressure(final float altimeterPressure) {
        DataSource.altimeterPressure = altimeterPressure;
    }

    public static float getTat() {
        return tat;
    }

    public static void setTat(final float tat) {
        DataSource.tat = tat;
    }

    public static float getNav1Freq() {
        return nav1Freq;
    }

    public static void setNav1Freq(final float nav1Freq) {
        DataSource.nav1Freq = nav1Freq;
    }

    public static float getNav1Radial() {
        return nav1Radial;
    }

    public static void setNav1Radial(final float nav1Radial) {
        DataSource.nav1Radial = nav1Radial;
    }

    public static String getNav1LocalizerNeedle() {
        return nav1LocalizerNeedle;
    }

    public static void setNav1LocalizerNeedle(final String nav1LocalizerNeedle) {
        DataSource.nav1LocalizerNeedle = nav1LocalizerNeedle;
    }

    public static float getNav1OBS() {
        return nav1OBS;
    }

    public static void setNav1OBS(final float nav1OBS) {
        DataSource.nav1OBS = nav1OBS;
    }

    public static boolean isNav1GlideslopeAlive() {
        return nav1GlideslopeAlive;
    }

    public static void setNav1GlideslopeAlive(final boolean nav1GlideslopeAlive) {
        DataSource.nav1GlideslopeAlive = nav1GlideslopeAlive;
    }

    public static String getNav1GlideslopeNeedle() {
        return nav1GlideslopeNeedle;
    }

    public static void setNav1GlideslopeNeedle(final String nav1GlideslopeNeedle) {
        DataSource.nav1GlideslopeNeedle = nav1GlideslopeNeedle;
    }

    public static float getNav2Freq() {
        return nav2Freq;
    }

    public static void setNav2Freq(final float nav2Freq) {
        DataSource.nav2Freq = nav2Freq;
    }

    public static float getNav2Radial() {
        return nav2Radial;
    }

    public static void setNav2Radial(final float nav2Radial) {
        DataSource.nav2Radial = nav2Radial;
    }

    public static String getNav2LocalizerNeedle() {
        return nav2LocalizerNeedle;
    }

    public static void setNav2LocalizerNeedle(final String nav2LocalizerNeedle) {
        DataSource.nav2LocalizerNeedle = nav2LocalizerNeedle;
    }

    public static float getNav2OBS() {
        return nav2OBS;
    }

    public static void setNav2OBS(final float nav2OBS) {
        DataSource.nav2OBS = nav2OBS;
    }

    public static float getADFHeading() {
        return ADFHeading;
    }

    public static void setADFHeading(final float ADFHeading) {
        DataSource.ADFHeading = ADFHeading;
    }

    public static float getrStatus() {
        return rStatus;
    }

    public static void setrStatus(final float rStatus) {
        DataSource.rStatus = rStatus;
    }

    public static float getILSInverseRunwayHeading() {
        return ILSInverseRunwayHeading;
    }

    public static void setILSInverseRunwayHeading(final float ILSInverseRunwayHeading) {
        DataSource.ILSInverseRunwayHeading = ILSInverseRunwayHeading;
    }

    public static float getILSGlideslopeInclination() {
        return ILSGlideslopeInclination;
    }

    public static void setILSGlideslopeInclination(final float ILSGlideslopeInclination) {
        DataSource.ILSGlideslopeInclination = ILSGlideslopeInclination;
    }

    public static float getFuelCentreLevel() {
        return fuelCentreLevel;
    }

    public static void setFuelCentreLevel(final float fuelCentreLevel) {
        DataSource.fuelCentreLevel = fuelCentreLevel;
    }

    public static float getFuelLeftLevel() {
        return fuelLeftLevel;
    }

    public static void setFuelLeftLevel(final float fuelLeftLevel) {
        DataSource.fuelLeftLevel = fuelLeftLevel;
    }

    public static float getFuelRightLevel() {
        return fuelRightLevel;
    }

    public static void setFuelRightLevel(final float fuelRightLevel) {
        DataSource.fuelRightLevel = fuelRightLevel;
    }

    public static float getFuelCentreCapacity() {
        return fuelCentreCapacity;
    }

    public static void setFuelCentreCapacity(final float fuelCentreCapacity) {
        DataSource.fuelCentreCapacity = fuelCentreCapacity;
    }

    public static float getFuelLeftCapacity() {
        return fuelLeftCapacity;
    }

    public static void setFuelLeftCapacity(final float fuelLeftCapacity) {
        DataSource.fuelLeftCapacity = fuelLeftCapacity;
    }

    public static float getFuelRightCapacity() {
        return fuelRightCapacity;
    }

    public static void setFuelRightCapacity(final float fuelRightCapacity) {
        DataSource.fuelRightCapacity = fuelRightCapacity;
    }

    public static float getDirActive() {
        return dirActive;
    }

    public static void setDirActive(final float dirActive) {
        DataSource.dirActive = dirActive;
    }

    public static float getDirPitch() {
        return dirPitch;
    }

    public static void setDirPitch(final float dirPitch) {
        DataSource.dirPitch = dirPitch;
    }

    public static float getDirBank() {
        return dirBank;
    }

    public static void setDirBank(final float dirBank) {
        DataSource.dirBank = dirBank;
    }

    public static float getRefAirspeedK() {
        return refAirspeedK;
    }

    public static void setRefAirspeedK(final float refAirspeedK) {
        DataSource.refAirspeedK = refAirspeedK;
    }

    public static float getTotalAirTemperature() {
        return totalAirTemperature;
    }

    public static void setTotalAirTemperature(final float totalAirTemperature) {
        DataSource.totalAirTemperature = totalAirTemperature;
    }

    public static float getFuelTankCapacityCenter() {
        return fuelTankCapacityCenter;
    }

    public static void setFuelTankCapacityCenter(final float fuelTankCapacityCenter) {
        DataSource.fuelTankCapacityCenter = fuelTankCapacityCenter;
    }

    public static float getFuelTankCapacityLeft() {
        return fuelTankCapacityLeft;
    }

    public static void setFuelTankCapacityLeft(final float fuelTankCapacityLeft) {
        DataSource.fuelTankCapacityLeft = fuelTankCapacityLeft;
    }

    public static float getFuelTankCapacityRight() {
        return fuelTankCapacityRight;
    }

    public static void setFuelTankCapacityRight(final float fuelTankCapacityRight) {
        DataSource.fuelTankCapacityRight = fuelTankCapacityRight;
    }

    public static float getFuelTankLevelCenter() {
        return fuelTankLevelCenter;
    }

    public static void setFuelTankLevelCenter(final float fuelTankLevelCenter) {
        DataSource.fuelTankLevelCenter = fuelTankLevelCenter;
    }

    public static float getFuelTankLevelLeft() {
        return fuelTankLevelLeft;
    }

    public static void setFuelTankLevelLeft(final float fuelTankLevelLeft) {
        DataSource.fuelTankLevelLeft = fuelTankLevelLeft;
    }

    public static float getFuelTankLevelRight() {
        return fuelTankLevelRight;
    }

    public static void setFuelTankLevelRight(final float fuelTankLevelRight) {
        DataSource.fuelTankLevelRight = fuelTankLevelRight;
    }

    public static float getEngine1N1() {
        return engine1N1;
    }

    public static void setEngine1N1(final float engine1N1) {
        DataSource.engine1N1 = engine1N1;
    }

    public static float getEngine1N2() {
        return engine1N2;
    }

    public static void setEngine1N2(final float engine1N2) {
        DataSource.engine1N2 = engine1N2;
    }

    public static float getEngine1FF_PPH() {
        return engine1FF_PPH;
    }

    public static void setEngine1FF_PPH(final float engine1FF_PPH) {
        DataSource.engine1FF_PPH = engine1FF_PPH;
    }

    public static float getEngine1EPR() {
        return engine1EPR;
    }

    public static void setEngine1EPR(final float engine1EPR) {
        DataSource.engine1EPR = engine1EPR;
    }

    public static float getEngine1EGT() {
        return engine1EGT;
    }

    public static void setEngine1EGT(final float engine1EGT) {
        DataSource.engine1EGT = engine1EGT;
    }

    public static String getPp1() {
        return pp1;
    }

    public static void setPp1(final String pp1) {
        DataSource.pp1 = pp1;
    }

    public static String getPp2() {
        return pp2;
    }

    public static void setPp2(final String pp2) {
        DataSource.pp2 = pp2;
    }

    public static float getEngine1OilPressure() {
        return engine1OilPressure;
    }

    public static void setEngine1OilPressure(final float engine1OilPressure) {
        DataSource.engine1OilPressure = engine1OilPressure;
    }

    public static float getEngine1OilTemp() {
        return engine1OilTemp;
    }

    public static void setEngine1OilTemp(final float engine1OilTemp) {
        DataSource.engine1OilTemp = engine1OilTemp;
    }

    public static float getEngine1OilQuantity() {
        return engine1OilQuantity;
    }

    public static void setEngine1OilQuantity(final float engine1OilQuantity) {
        DataSource.engine1OilQuantity = engine1OilQuantity;
    }

    public static float getEngine1Vibration() {
        return engine1Vibration;
    }

    public static void setEngine1Vibration(final float engine1Vibration) {
        DataSource.engine1Vibration = engine1Vibration;
    }

    public static float getEngine1HydraulicPressure() {
        return engine1HydraulicPressure;
    }

    public static void setEngine1HydraulicPressure(final float engine1HydraulicPressure) {
        DataSource.engine1HydraulicPressure = engine1HydraulicPressure;
    }

    public static float getEngine1HydraulicQuantity() {
        return engine1HydraulicQuantity;
    }

    public static void setEngine1HydraulicQuantity(final float engine1HydraulicQuantity) {
        DataSource.engine1HydraulicQuantity = engine1HydraulicQuantity;
    }

    public static float getEngine2N1() {
        return engine2N1;
    }

    public static void setEngine2N1(final float engine2N1) {
        DataSource.engine2N1 = engine2N1;
    }

    public static float getEngine2N2() {
        return engine2N2;
    }

    public static void setEngine2N2(final float engine2N2) {
        DataSource.engine2N2 = engine2N2;
    }

    public static float getEngine2FFPPH() {
        return engine2FFPPH;
    }

    public static void setEngine2FFPPH(final float engine2FFPPH) {
        DataSource.engine2FFPPH = engine2FFPPH;
    }

    public static float getEngine2EPR() {
        return engine2EPR;
    }

    public static void setEngine2EPR(final float engine2EPR) {
        DataSource.engine2EPR = engine2EPR;
    }

    public static float getEngine2EGT() {
        return engine2EGT;
    }

    public static void setEngine2EGT(final float engine2EGT) {
        DataSource.engine2EGT = engine2EGT;
    }

    public static String getPp3() {
        return pp3;
    }

    public static void setPp3(final String pp3) {
        DataSource.pp3 = pp3;
    }

    public static String getPp4() {
        return pp4;
    }

    public static void setPp4(final String pp4) {
        DataSource.pp4 = pp4;
    }

    public static float getEngine2OilPressure() {
        return engine2OilPressure;
    }

    public static void setEngine2OilPressure(final float engine2OilPressure) {
        DataSource.engine2OilPressure = engine2OilPressure;
    }

    public static float getEngine2OilTemp() {
        return engine2OilTemp;
    }

    public static void setEngine2OilTemp(final float engine2OilTemp) {
        DataSource.engine2OilTemp = engine2OilTemp;
    }

    public static float getEngine2OilQuantity() {
        return engine2OilQuantity;
    }

    public static void setEngine2OilQuantity(final float engine2OilQuantity) {
        DataSource.engine2OilQuantity = engine2OilQuantity;
    }

    public static float getEngine2Vibration() {
        return engine2Vibration;
    }

    public static void setEngine2Vibration(final float engine2Vibration) {
        DataSource.engine2Vibration = engine2Vibration;
    }

    public static float getEngine2HydraulicPressure() {
        return engine2HydraulicPressure;
    }

    public static void setEngine2HydraulicPressure(final float engine2HydraulicPressure) {
        DataSource.engine2HydraulicPressure = engine2HydraulicPressure;
    }

    public static float getEngine2HydraulicQuantity() {
        return engine2HydraulicQuantity;
    }

    public static void setEngine2HydraulicQuantity(final float engine2HydraulicQuantity) {
        DataSource.engine2HydraulicQuantity = engine2HydraulicQuantity;
    }

    public static float getAutoPilotHeading() {
        return autoPilotHeading;
    }

    public static void setAutoPilotHeading(final float autoPilotHeading) {
        DataSource.autoPilotHeading = autoPilotHeading;
    }

    public static float getAdf1Heading() {
        return adf1Heading;
    }

    public static void setAdf1Heading(final float adf1Heading) {
        DataSource.adf1Heading = adf1Heading;
    }

    public static float getLatitude() {
        return latitude;
    }

    public static void setLatitude(float latitude) {
        DataSource.latitude = latitude;
    }

    public static float getLongitude() {
        return longitude;
    }

    public static void setLongitude(float longitude) {
        DataSource.longitude = longitude;
    }

    public static float getMagneticHeading() {
        return magneticHeading;
    }

    public static void setMagneticHeading(float magneticHeading) {
        DataSource.magneticHeading = magneticHeading;
    }

    public static float getVertSpeedFPM() {
        return vertSpeedFPM;
    }

    public static void setVertSpeedFPM(float vertSpeedFPM) {
        DataSource.vertSpeedFPM = vertSpeedFPM;
    }

    public static float getAltitude() {
        return altitude;
    }

    public static void setAltitude(float altitude) {
        DataSource.altitude = altitude;
    }

    public static float getAltitudeMeters() {
        return altitudeMeters;
    }

    public static void setAltitudeMeters(float altitudeMeters) {
        DataSource.altitudeMeters = altitudeMeters;
    }

    public static boolean isAvionicDoorClosed() {
        return avionicDoorClosed;
    }

    public static void setAvionicDoorClosed(boolean avionicDoorClosed) {
        DataSource.avionicDoorClosed = avionicDoorClosed;
    }

    public static boolean isBulkDoorClosed() {
        return bulkDoorClosed;
    }

    public static void setBulkDoorClosed(boolean bulkDoorClosed) {
        DataSource.bulkDoorClosed = bulkDoorClosed;
    }

    public static boolean isCargoDoor1Closed() {
        return cargoDoor1Closed;
    }

    public static void setCargoDoor1Closed(boolean cargoDoor1Closed) {
        DataSource.cargoDoor1Closed = cargoDoor1Closed;
    }

    public static boolean isCargoDoor2Closed() {
        return cargoDoor2Closed;
    }

    public static void setCargoDoor2Closed(boolean cargoDoor2Closed) {
        DataSource.cargoDoor2Closed = cargoDoor2Closed;
    }

    public static boolean isElectPump1commanded() {
        return electPump1commanded;
    }

    public static void setElectPump1commanded(boolean electPump1commanded) {
        DataSource.electPump1commanded = electPump1commanded;
    }

    public static boolean isElectPump1LowPress() {
        return electPump1LowPress;
    }

    public static void setElectPump1LowPress(boolean electPump1LowPress) {
        DataSource.electPump1LowPress = electPump1LowPress;
    }

    public static boolean isElectPump1On() {
        return electPump1On;
    }

    public static void setElectPump1On(boolean electPump1On) {
        DataSource.electPump1On = electPump1On;
    }

    public static boolean isElectPump2commanded() {
        return electPump2commanded;
    }

    public static void setElectPump2commanded(boolean electPump2commanded) {
        DataSource.electPump2commanded = electPump2commanded;
    }

    public static boolean isElectPump2LowPress() {
        return electPump2LowPress;
    }

    public static void setElectPump2LowPress(boolean electPump2LowPress) {
        DataSource.electPump2LowPress = electPump2LowPress;
    }

    public static boolean isElectPump2On() {
        return electPump2On;
    }

    public static void setElectPump2On(boolean electPump2On) {
        DataSource.electPump2On = electPump2On;
    }

    public static boolean isElectPump3commanded() {
        return electPump3commanded;
    }

    public static void setElectPump3commanded(boolean electPump3commanded) {
        DataSource.electPump3commanded = electPump3commanded;
    }

    public static boolean isElectPump3LowPress() {
        return electPump3LowPress;
    }

    public static void setElectPump3LowPress(boolean electPump3LowPress) {
        DataSource.electPump3LowPress = electPump3LowPress;
    }

    public static boolean isElectPump3On() {
        return electPump3On;
    }

    public static void setElectPump3On(boolean electPump3On) {
        DataSource.electPump3On = electPump3On;
    }

    public static boolean isEnginePump1LowPress() {
        return enginePump1LowPress;
    }

    public static void setEnginePump1LowPress(boolean enginePump1LowPress) {
        DataSource.enginePump1LowPress = enginePump1LowPress;
    }

    public static boolean isEnginePump1On() {
        return enginePump1On;
    }

    public static void setEnginePump1On(boolean enginePump1On) {
        DataSource.enginePump1On = enginePump1On;
    }

    public static boolean isEnginePump2LowPress() {
        return enginePump2LowPress;
    }

    public static void setEnginePump2LowPress(boolean enginePump2LowPress) {
        DataSource.enginePump2LowPress = enginePump2LowPress;
    }

    public static boolean isEnginePump2On() {
        return enginePump2On;
    }

    public static void setEnginePump2On(boolean enginePump2On) {
        DataSource.enginePump2On = enginePump2On;
    }

    public static boolean isEnginePump3LowPress() {
        return enginePump3LowPress;
    }

    public static void setEnginePump3LowPress(boolean enginePump3LowPress) {
        DataSource.enginePump3LowPress = enginePump3LowPress;
    }

    public static boolean isEnginePump3On() {
        return enginePump3On;
    }

    public static void setEnginePump3On(boolean enginePump3On) {
        DataSource.enginePump3On = enginePump3On;
    }

    public static boolean isEnginePump4LowPress() {
        return enginePump4LowPress;
    }

    public static void setEnginePump4LowPress(boolean enginePump4LowPress) {
        DataSource.enginePump4LowPress = enginePump4LowPress;
    }

    public static boolean isEnginePump4On() {
        return enginePump4On;
    }

    public static void setEnginePump4On(boolean enginePump4On) {
        DataSource.enginePump4On = enginePump4On;
    }

    public static boolean isLcabinDoor2Closed() {
        return lcabinDoor2Closed;
    }

    public static void setLcabinDoor2Closed(boolean lcabinDoor2Closed) {
        DataSource.lcabinDoor2Closed = lcabinDoor2Closed;
    }

    public static boolean isLcabinDoor3Closed() {
        return lcabinDoor3Closed;
    }

    public static void setLcabinDoor3Closed(boolean lcabinDoor3Closed) {
        DataSource.lcabinDoor3Closed = lcabinDoor3Closed;
    }

    public static boolean isLcabinDoor4Closed() {
        return lcabinDoor4Closed;
    }

    public static void setLcabinDoor4Closed(boolean lcabinDoor4Closed) {
        DataSource.lcabinDoor4Closed = lcabinDoor4Closed;
    }

    public static boolean isLcabinDoorClosed() {
        return lcabinDoorClosed;
    }

    public static void setLcabinDoorClosed(boolean lcabinDoorClosed) {
        DataSource.lcabinDoorClosed = lcabinDoorClosed;
    }

    public static boolean isRamAirTurbineOverspeed() {
        return ramAirTurbineOverspeed;
    }

    public static void setRamAirTurbineOverspeed(boolean ramAirTurbineOverspeed) {
        DataSource.ramAirTurbineOverspeed = ramAirTurbineOverspeed;
    }

    public static boolean isRamAirTurbineStowed() {
        return ramAirTurbineStowed;
    }

    public static void setRamAirTurbineStowed(boolean ramAirTurbineStowed) {
        DataSource.ramAirTurbineStowed = ramAirTurbineStowed;
    }

    public static boolean isRamAirTurbineStowing() {
        return ramAirTurbineStowing;
    }

    public static void setRamAirTurbineStowing(boolean ramAirTurbineStowing) {
        DataSource.ramAirTurbineStowing = ramAirTurbineStowing;
    }

    public static boolean isRcabinDoor2Closed() {
        return rcabinDoor2Closed;
    }

    public static void setRcabinDoor2Closed(boolean rcabinDoor2Closed) {
        DataSource.rcabinDoor2Closed = rcabinDoor2Closed;
    }

    public static boolean isRcabinDoor3Closed() {
        return rcabinDoor3Closed;
    }

    public static void setRcabinDoor3Closed(boolean rcabinDoor3Closed) {
        DataSource.rcabinDoor3Closed = rcabinDoor3Closed;
    }

    public static boolean isRcabinDoor4Closed() {
        return rcabinDoor4Closed;
    }

    public static void setRcabinDoor4Closed(boolean rcabinDoor4Closed) {
        DataSource.rcabinDoor4Closed = rcabinDoor4Closed;
    }

    public static boolean isRcabinDoorClosed() {
        return rcabinDoorClosed;
    }

    public static void setRcabinDoorClosed(boolean rcabinDoorClosed) {
        DataSource.rcabinDoorClosed = rcabinDoorClosed;
    }

    public static boolean isResevoir1LowAirPress() {
        return resevoir1LowAirPress;
    }

    public static void setResevoir1LowAirPress(boolean resevoir1LowAirPress) {
        DataSource.resevoir1LowAirPress = resevoir1LowAirPress;
    }

    public static boolean isResevoir1Overheat() {
        return resevoir1Overheat;
    }

    public static void setResevoir1Overheat(boolean resevoir1Overheat) {
        DataSource.resevoir1Overheat = resevoir1Overheat;
    }

    public static boolean isResevoir1ValveOpen() {
        return resevoir1ValveOpen;
    }

    public static void setResevoir1ValveOpen(boolean resevoir1ValveOpen) {
        DataSource.resevoir1ValveOpen = resevoir1ValveOpen;
    }

    public static boolean isResevoir2LowAirPress() {
        return resevoir2LowAirPress;
    }

    public static void setResevoir2LowAirPress(boolean resevoir2LowAirPress) {
        DataSource.resevoir2LowAirPress = resevoir2LowAirPress;
    }

    public static boolean isResevoir2Overheat() {
        return resevoir2Overheat;
    }

    public static void setResevoir2Overheat(boolean resevoir2Overheat) {
        DataSource.resevoir2Overheat = resevoir2Overheat;
    }

    public static boolean isResevoir2ValveOpen() {
        return resevoir2ValveOpen;
    }

    public static void setResevoir2ValveOpen(boolean resevoir2ValveOpen) {
        DataSource.resevoir2ValveOpen = resevoir2ValveOpen;
    }

    public static boolean isResevoir3LowAirPress() {
        return resevoir3LowAirPress;
    }

    public static void setResevoir3LowAirPress(boolean resevoir3LowAirPress) {
        DataSource.resevoir3LowAirPress = resevoir3LowAirPress;
    }

    public static boolean isResevoir3Overheat() {
        return resevoir3Overheat;
    }

    public static void setResevoir3Overheat(boolean resevoir3Overheat) {
        DataSource.resevoir3Overheat = resevoir3Overheat;
    }

    public static boolean isResevoir3ValveOpen() {
        return resevoir3ValveOpen;
    }

    public static void setResevoir3ValveOpen(boolean resevoir3ValveOpen) {
        DataSource.resevoir3ValveOpen = resevoir3ValveOpen;
    }

    public static boolean isResevoir4ValveOpen() {
        return resevoir4ValveOpen;
    }

    public static void setResevoir4ValveOpen(boolean resevoir4ValveOpen) {
        DataSource.resevoir4ValveOpen = resevoir4ValveOpen;
    }

    public static boolean isADF1Tuned() {
        return ADF1Tuned;
    }

    public static void setADF1Tuned(boolean ADF1Tuned) {
        DataSource.ADF1Tuned = ADF1Tuned;
    }

    public static boolean isADF2Tuned() {
        return ADF2Tuned;
    }

    public static void setADF2Tuned(boolean ADF2Tuned) {
        DataSource.ADF2Tuned = ADF2Tuned;
    }

    public static boolean isAirSpeedAvailable() {
        return airSpeedAvailable;
    }

    public static void setAirSpeedAvailable(boolean airSpeedAvailable) {
        DataSource.airSpeedAvailable = airSpeedAvailable;
    }

    public static boolean isAltitudeAvailable() {
        return altitudeAvailable;
    }

    public static void setAltitudeAvailable(boolean altitudeAvailable) {
        DataSource.altitudeAvailable = altitudeAvailable;
    }

    public static boolean isAttitudeAvailable() {
        return attitudeAvailable;
    }

    public static void setAttitudeAvailable(boolean attitudeAvailable) {
        DataSource.attitudeAvailable = attitudeAvailable;
    }

    public static boolean isChronoActve() {
        return chronoActve;
    }

    public static void setChronoActve(boolean chronoActve) {
        DataSource.chronoActve = chronoActve;
    }

    public static boolean isHeadingAvailable() {
        return headingAvailable;
    }

    public static void setHeadingAvailable(boolean headingAvailable) {
        DataSource.headingAvailable = headingAvailable;
    }

    public static String getNavSelect1() {
        return navSelect1;
    }

    public static void setNavSelect1(String navSelect1) {
        DataSource.navSelect1 = navSelect1;
    }

    public static String getNavSelect2() {
        return navSelect2;
    }

    public static void setNavSelect2(String navSelect2) {
        DataSource.navSelect2 = navSelect2;
    }

    public static int getNdRange() {
        return ndRange;
    }

    public static void setNdRange(int ndRange) {
        DataSource.ndRange = ndRange;
    }

    public static float getRange() {
        return range;
    }

    public static void setRange(float range) {
        DataSource.range = range;
    }

    public static boolean isVertSpeedAvailable() {
        return vertSpeedAvailable;
    }

//    public static void setVertSpeedAvailable(boolean vertSpeedAvailable) {
//        DataSource.vertSpeedAvailable = vertSpeedAvailable;
//    }

    public static boolean isVOR1Tuned() {
        return VOR1Tuned;
    }

    public static void setVOR1Tuned(boolean VOR1Tuned) {
        DataSource.VOR1Tuned = VOR1Tuned;
    }

    public static boolean isVOR2Tuned() {
        return VOR2Tuned;
    }

    public static void setVOR2Tuned(boolean VOR2Tuned) {
        DataSource.VOR2Tuned = VOR2Tuned;
    }

    public static boolean isWindAvailable() {
        return windAvailable;
    }

    public static void setWindAvailable(boolean windAvailable) {
        DataSource.windAvailable = windAvailable;
    }
}