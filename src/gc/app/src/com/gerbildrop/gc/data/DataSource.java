package com.gerbildrop.gc.data;

/**
 * @author timo
 * @version ${1.1}
 * @since Jul 7, 2006 9:30:51 PM
 */
public class DataSource {
    private static int engineNumber = 1;
    private static int display = 5;

    private static double roll;
    private static double pitch;
    private static double trueHeading;
    private static double magneticVariation;
    private static double latitudeAircraftLow;
    private static double latitudeAircraftHigh;
    private static double longitudeAircraftLow;
    private static double longitudeAircraftHigh;
    private static double ias;
    private static double tas;
    private static double machSpeed;
    private static double groundSpeedK;
    private static double verticalSpeedMS;
    private static double fracMeters;
    private static double unitMeters;
    private static double flapsDeflectionRadians;
    private static double flapsTrueDeflectionRadians;
    private static double noseGear;
    private static double rightGear;
    private static double leftGear;
    private static double altimeterPressure;
    private static double tat;

    //------------------------Nav Radio--------------------------
    private static double nav1Freq;
    private static double nav1Radial;
    private static String nav1LocalizerNeedle;
    private static double nav1OBS;
    private static boolean nav1GlideslopeAlive;
    private static String nav1GlideslopeNeedle;
    private static double nav2Freq;
    private static double nav2Radial;
    private static String nav2LocalizerNeedle;
    private static double nav2OBS;
    private static double ADFHeading;
    private static double rStatus;

    //---------------------------ILS--------------------------------
    private static double ILSInverseRunwayHeading;
    private static double ILSGlideslopeInclination;

    //------------------------Fuel----------------------------------
    private static double fuelCentreLevel;
    private static double fuelLeftLevel;
    private static double fuelRightLevel;
    private static double fuelCentreCapacity;
    private static double fuelLeftCapacity;
    private static double fuelRightCapacity;

    //------------------------Autopilot-----------------------------
    private static double dirActive;
    private static double dirPitch;
    private static double dirBank;
    private static double refAirspeedK;

    //-------------------------------------------------------------
    private static double totalAirTemperature;

    //---------------------- Fuel Tanks----------------------------
    private static double fuelTankCapacityCenter;
    private static double fuelTankCapacityLeft;
    private static double fuelTankCapacityRight;
    private static double fuelTankLevelCenter;
    private static double fuelTankLevelLeft;
    private static double fuelTankLevelRight;

    //-------------------------Engines-----------------------------

    //-------------------------Engine 1----------------------------
    private static double engine1N1;
    private static double engine1N2;
    private static double engine1FF_PPH;
    private static double engine1EPR;
    private static double engine1EGT;

    // mdf
    private static String pp1;
    private static String pp2;
    private static double engine1OilPressure;
    private static double engine1OilTemp;
    private static double engine1OilQuantity;
    private static double engine1Vibration;
    private static double engine1HydraulicPressure;
    private static double engine1HydraulicQuantity;

    //-------------------------Engine 2----------------------------
    private static double engine2N1;
    private static double engine2N2;
    private static double engine2FFPPH;
    private static double engine2EPR;
    private static double engine2EGT;

    // mdf
    private static String pp3;
    private static String pp4;
    private static double engine2OilPressure;
    private static double engine2OilTemp;
    private static double engine2OilQuantity;
    private static double engine2Vibration;
    private static double engine2HydraulicPressure;
    private static double engine2HydraulicQuantity;
    private static double autoPilotHeading;
    private static double adf1Heading;
    private static double magneticHeading;
    private static double latitude;
    private static double longitude;
    private static double vertSpeedFPM;
    private static double altitudeMeters;
    private static double altitude;

    //button set parameters
    private static boolean lcabinDoorClosed;
    private static boolean lcabinDoor2Closed;
    private static boolean lcabinDoor3Closed;
    private static boolean lcabinDoor4Closed;

    private static boolean rcabinDoorClosed;
    private static boolean rcabinDoor2Closed;
    private static boolean rcabinDoor3Closed;
    private static boolean rcabinDoor4Closed;

    private static boolean cargoDoor1Closed;
    private static boolean cargoDoor2Closed;

    private static boolean bulkDoorClosed;
    private static boolean avionicDoorClosed;

    private static boolean resevoir1LowAirPress;
    private static boolean resevoir1Overheat;
    private static boolean resevoir1ValveOpen;
    private static boolean enginePump1On;
    private static boolean enginePump1LowPress;
    private static boolean electPump1commanded;
    private static boolean electPump1On;
    private static boolean electPump1LowPress;

    private static boolean resevoir2LowAirPress;
    private static boolean resevoir2Overheat;
    private static boolean resevoir2ValveOpen;
    private static boolean enginePump2On;
    private static boolean enginePump2LowPress;
    private static boolean electPump2commanded;
    private static boolean electPump2On;
    private static boolean electPump2LowPress;

    private static boolean resevoir3LowAirPress;
    private static boolean resevoir3Overheat;
    private static boolean resevoir3ValveOpen;
    private static boolean enginePump3On;
    private static boolean enginePump3LowPress;
    private static boolean electPump3commanded;
    private static boolean electPump3On;
    private static boolean electPump3LowPress;

    private static boolean resevoir4ValveOpen;
    private static boolean enginePump4On;
    private static boolean enginePump4LowPress;
    private static boolean ramAirTurbineStowed;
    private static boolean ramAirTurbineStowing;
    private static boolean ramAirTurbineOverspeed;
    /*
    private static int chronoTimer;

    private static double VOR1Frequency;

    private static double VOR1Bearing;
    private static double VOR1Course;
    private static double VOR1Deviation;

    private static double ADF1Frequency;

    private static double ADF1Bearing;

    private static double DME1Distance;

    private static double VOR2Frequency;

    private static double VOR2Bearing;
    private static double VOR2Course;
    private static double VOR2Deviation;

    private static double ADF2Frequency;

    private static double ADF2Bearing;

    private static double DME2Distance;


    private static double velocity_V1;
    private static double velocity_VR;
    private static double velocity_V2;
    private static double velocity_VSW;
    private static double velocity_VLS;
    private static double velocity_VMax;
*/
    private static double range;

    private static boolean windAvailable;

    private static int ndRange = (int) range;
    private static String navSelect1 = "";
    private static String navSelect2 = "";

    private static boolean chronoActve;
    private static boolean VOR1Tuned;
    private static boolean ADF1Tuned;
    private static boolean VOR2Tuned;
    private static boolean ADF2Tuned;
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

    public static double getRoll() {
        return roll;
    }

    public static void setRoll(final double roll) {
        DataSource.roll = roll;
    }

    public static double getPitch() {
        return pitch;
    }

    public static void setPitch(final double pitch) {
        DataSource.pitch = pitch;
    }

    public static double getTrueHeading() {
        return trueHeading;
    }

    public static void setTrueHeading(final double trueHeading) {
        DataSource.trueHeading = trueHeading;
    }

    public static double getMagneticVariation() {
        return magneticVariation;
    }

    public static void setMagneticVariation(final double magneticVariation) {
        DataSource.magneticVariation = magneticVariation;
    }

    public static double getLatitudeAircraftLow() {
        return latitudeAircraftLow;
    }

    public static void setLatitudeAircraftLow(final double latitudeAircraftLow) {
        DataSource.latitudeAircraftLow = latitudeAircraftLow;
    }

    public static double getLatitudeAircraftHigh() {
        return latitudeAircraftHigh;
    }

    public static void setLatitudeAircraftHigh(final double latitudeAircraftHigh) {
        DataSource.latitudeAircraftHigh = latitudeAircraftHigh;
    }

    public static double getLongitudeAircraftLow() {
        return longitudeAircraftLow;
    }

    public static void setLongitudeAircraftLow(final double longitudeAircraftLow) {
        DataSource.longitudeAircraftLow = longitudeAircraftLow;
    }

    public static double getLongitudeAircraftHigh() {
        return longitudeAircraftHigh;
    }

    public static void setLongitudeAircraftHigh(final double longitudeAircraftHigh) {
        DataSource.longitudeAircraftHigh = longitudeAircraftHigh;
    }

    public static double getIas() {
        return ias;
    }

    public static void setIas(final double ias) {
        DataSource.ias = ias;
    }

    public static double getTas() {
        return tas;
    }

    public static void setTas(final double tas) {
        DataSource.tas = tas;
    }

    public static double getMachSpeed() {
        return machSpeed;
    }

    public static void setMachSpeed(final double machSpeed) {
        DataSource.machSpeed = machSpeed;
    }

    public static double getGroundSpeedK() {
        return groundSpeedK;
    }

    public static void setGroundSpeedK(final double groundSpeedK) {
        DataSource.groundSpeedK = groundSpeedK;
    }

    public static double getVerticalSpeedMS() {
        return verticalSpeedMS;
    }

    public static void setVerticalSpeedMS(final double verticalSpeedMS) {
        DataSource.verticalSpeedMS = verticalSpeedMS;
    }

    public static double getFracMeters() {
        return fracMeters;
    }

    public static void setFracMeters(final double fracMeters) {
        DataSource.fracMeters = fracMeters;
    }

    public static double getUnitMeters() {
        return unitMeters;
    }

    public static void setUnitMeters(final double unitMeters) {
        DataSource.unitMeters = unitMeters;
    }

    public static double getFlapsDeflectionRadians() {
        return flapsDeflectionRadians;
    }

    public static void setFlapsDeflectionRadians(final double flapsDeflectionRadians) {
        DataSource.flapsDeflectionRadians = flapsDeflectionRadians;
    }

    public static double getFlapsTrueDeflectionRadians() {
        return flapsTrueDeflectionRadians;
    }

    public static void setFlapsTrueDeflectionRadians(final double flapsTrueDeflectionRadians) {
        DataSource.flapsTrueDeflectionRadians = flapsTrueDeflectionRadians;
    }

    public static double getNoseGear() {
        return noseGear;
    }

    public static void setNoseGear(final double noseGear) {
        DataSource.noseGear = noseGear;
    }

    public static double getRightGear() {
        return rightGear;
    }

    public static void setRightGear(final double rightGear) {
        DataSource.rightGear = rightGear;
    }

    public static double getLeftGear() {
        return leftGear;
    }

    public static void setLeftGear(final double leftGear) {
        DataSource.leftGear = leftGear;
    }

    public static double getAltimeterPressure() {
        return altimeterPressure;
    }

    public static void setAltimeterPressure(final double altimeterPressure) {
        DataSource.altimeterPressure = altimeterPressure;
    }

    public static double getTat() {
        return tat;
    }

    public static void setTat(final double tat) {
        DataSource.tat = tat;
    }

    public static double getNav1Freq() {
        return nav1Freq;
    }

    public static void setNav1Freq(final double nav1Freq) {
        DataSource.nav1Freq = nav1Freq;
    }

    public static double getNav1Radial() {
        return nav1Radial;
    }

    public static void setNav1Radial(final double nav1Radial) {
        DataSource.nav1Radial = nav1Radial;
    }

    public static String getNav1LocalizerNeedle() {
        return nav1LocalizerNeedle;
    }

    public static void setNav1LocalizerNeedle(final String nav1LocalizerNeedle) {
        DataSource.nav1LocalizerNeedle = nav1LocalizerNeedle;
    }

    public static double getNav1OBS() {
        return nav1OBS;
    }

    public static void setNav1OBS(final double nav1OBS) {
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

    public static double getNav2Freq() {
        return nav2Freq;
    }

    public static void setNav2Freq(final double nav2Freq) {
        DataSource.nav2Freq = nav2Freq;
    }

    public static double getNav2Radial() {
        return nav2Radial;
    }

    public static void setNav2Radial(final double nav2Radial) {
        DataSource.nav2Radial = nav2Radial;
    }

    public static String getNav2LocalizerNeedle() {
        return nav2LocalizerNeedle;
    }

    public static void setNav2LocalizerNeedle(final String nav2LocalizerNeedle) {
        DataSource.nav2LocalizerNeedle = nav2LocalizerNeedle;
    }

    public static double getNav2OBS() {
        return nav2OBS;
    }

    public static void setNav2OBS(final double nav2OBS) {
        DataSource.nav2OBS = nav2OBS;
    }

    public static double getADFHeading() {
        return ADFHeading;
    }

    public static void setADFHeading(final double ADFHeading) {
        DataSource.ADFHeading = ADFHeading;
    }

    public static double getrStatus() {
        return rStatus;
    }

    public static void setrStatus(final double rStatus) {
        DataSource.rStatus = rStatus;
    }

    public static double getILSInverseRunwayHeading() {
        return ILSInverseRunwayHeading;
    }

    public static void setILSInverseRunwayHeading(final double ILSInverseRunwayHeading) {
        DataSource.ILSInverseRunwayHeading = ILSInverseRunwayHeading;
    }

    public static double getILSGlideslopeInclination() {
        return ILSGlideslopeInclination;
    }

    public static void setILSGlideslopeInclination(final double ILSGlideslopeInclination) {
        DataSource.ILSGlideslopeInclination = ILSGlideslopeInclination;
    }

    public static double getFuelCentreLevel() {
        return fuelCentreLevel;
    }

    public static void setFuelCentreLevel(final double fuelCentreLevel) {
        DataSource.fuelCentreLevel = fuelCentreLevel;
    }

    public static double getFuelLeftLevel() {
        return fuelLeftLevel;
    }

    public static void setFuelLeftLevel(final double fuelLeftLevel) {
        DataSource.fuelLeftLevel = fuelLeftLevel;
    }

    public static double getFuelRightLevel() {
        return fuelRightLevel;
    }

    public static void setFuelRightLevel(final double fuelRightLevel) {
        DataSource.fuelRightLevel = fuelRightLevel;
    }

    public static double getFuelCentreCapacity() {
        return fuelCentreCapacity;
    }

    public static void setFuelCentreCapacity(final double fuelCentreCapacity) {
        DataSource.fuelCentreCapacity = fuelCentreCapacity;
    }

    public static double getFuelLeftCapacity() {
        return fuelLeftCapacity;
    }

    public static void setFuelLeftCapacity(final double fuelLeftCapacity) {
        DataSource.fuelLeftCapacity = fuelLeftCapacity;
    }

    public static double getFuelRightCapacity() {
        return fuelRightCapacity;
    }

    public static void setFuelRightCapacity(final double fuelRightCapacity) {
        DataSource.fuelRightCapacity = fuelRightCapacity;
    }

    public static double getDirActive() {
        return dirActive;
    }

    public static void setDirActive(final double dirActive) {
        DataSource.dirActive = dirActive;
    }

    public static double getDirPitch() {
        return dirPitch;
    }

    public static void setDirPitch(final double dirPitch) {
        DataSource.dirPitch = dirPitch;
    }

    public static double getDirBank() {
        return dirBank;
    }

    public static void setDirBank(final double dirBank) {
        DataSource.dirBank = dirBank;
    }

    public static double getRefAirspeedK() {
        return refAirspeedK;
    }

    public static void setRefAirspeedK(final double refAirspeedK) {
        DataSource.refAirspeedK = refAirspeedK;
    }

    public static double getTotalAirTemperature() {
        return totalAirTemperature;
    }

    public static void setTotalAirTemperature(final double totalAirTemperature) {
        DataSource.totalAirTemperature = totalAirTemperature;
    }

    public static double getFuelTankCapacityCenter() {
        return fuelTankCapacityCenter;
    }

    public static void setFuelTankCapacityCenter(final double fuelTankCapacityCenter) {
        DataSource.fuelTankCapacityCenter = fuelTankCapacityCenter;
    }

    public static double getFuelTankCapacityLeft() {
        return fuelTankCapacityLeft;
    }

    public static void setFuelTankCapacityLeft(final double fuelTankCapacityLeft) {
        DataSource.fuelTankCapacityLeft = fuelTankCapacityLeft;
    }

    public static double getFuelTankCapacityRight() {
        return fuelTankCapacityRight;
    }

    public static void setFuelTankCapacityRight(final double fuelTankCapacityRight) {
        DataSource.fuelTankCapacityRight = fuelTankCapacityRight;
    }

    public static double getFuelTankLevelCenter() {
        return fuelTankLevelCenter;
    }

    public static void setFuelTankLevelCenter(final double fuelTankLevelCenter) {
        DataSource.fuelTankLevelCenter = fuelTankLevelCenter;
    }

    public static double getFuelTankLevelLeft() {
        return fuelTankLevelLeft;
    }

    public static void setFuelTankLevelLeft(final double fuelTankLevelLeft) {
        DataSource.fuelTankLevelLeft = fuelTankLevelLeft;
    }

    public static double getFuelTankLevelRight() {
        return fuelTankLevelRight;
    }

    public static void setFuelTankLevelRight(final double fuelTankLevelRight) {
        DataSource.fuelTankLevelRight = fuelTankLevelRight;
    }

    public static double getEngine1N1() {
        return engine1N1;
    }

    public static void setEngine1N1(final double engine1N1) {
        DataSource.engine1N1 = engine1N1;
    }

    public static double getEngine1N2() {
        return engine1N2;
    }

    public static void setEngine1N2(final double engine1N2) {
        DataSource.engine1N2 = engine1N2;
    }

    public static double getEngine1FF_PPH() {
        return engine1FF_PPH;
    }

    public static void setEngine1FF_PPH(final double engine1FF_PPH) {
        DataSource.engine1FF_PPH = engine1FF_PPH;
    }

    public static double getEngine1EPR() {
        return engine1EPR;
    }

    public static void setEngine1EPR(final double engine1EPR) {
        DataSource.engine1EPR = engine1EPR;
    }

    public static double getEngine1EGT() {
        return engine1EGT;
    }

    public static void setEngine1EGT(final double engine1EGT) {
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

    public static double getEngine1OilPressure() {
        return engine1OilPressure;
    }

    public static void setEngine1OilPressure(final double engine1OilPressure) {
        DataSource.engine1OilPressure = engine1OilPressure;
    }

    public static double getEngine1OilTemp() {
        return engine1OilTemp;
    }

    public static void setEngine1OilTemp(final double engine1OilTemp) {
        DataSource.engine1OilTemp = engine1OilTemp;
    }

    public static double getEngine1OilQuantity() {
        return engine1OilQuantity;
    }

    public static void setEngine1OilQuantity(final double engine1OilQuantity) {
        DataSource.engine1OilQuantity = engine1OilQuantity;
    }

    public static double getEngine1Vibration() {
        return engine1Vibration;
    }

    public static void setEngine1Vibration(final double engine1Vibration) {
        DataSource.engine1Vibration = engine1Vibration;
    }

    public static double getEngine1HydraulicPressure() {
        return engine1HydraulicPressure;
    }

    public static void setEngine1HydraulicPressure(final double engine1HydraulicPressure) {
        DataSource.engine1HydraulicPressure = engine1HydraulicPressure;
    }

    public static double getEngine1HydraulicQuantity() {
        return engine1HydraulicQuantity;
    }

    public static void setEngine1HydraulicQuantity(final double engine1HydraulicQuantity) {
        DataSource.engine1HydraulicQuantity = engine1HydraulicQuantity;
    }

    public static double getEngine2N1() {
        return engine2N1;
    }

    public static void setEngine2N1(final double engine2N1) {
        DataSource.engine2N1 = engine2N1;
    }

    public static double getEngine2N2() {
        return engine2N2;
    }

    public static void setEngine2N2(final double engine2N2) {
        DataSource.engine2N2 = engine2N2;
    }

    public static double getEngine2FFPPH() {
        return engine2FFPPH;
    }

    public static void setEngine2FFPPH(final double engine2FFPPH) {
        DataSource.engine2FFPPH = engine2FFPPH;
    }

    public static double getEngine2EPR() {
        return engine2EPR;
    }

    public static void setEngine2EPR(final double engine2EPR) {
        DataSource.engine2EPR = engine2EPR;
    }

    public static double getEngine2EGT() {
        return engine2EGT;
    }

    public static void setEngine2EGT(final double engine2EGT) {
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

    public static double getEngine2OilPressure() {
        return engine2OilPressure;
    }

    public static void setEngine2OilPressure(final double engine2OilPressure) {
        DataSource.engine2OilPressure = engine2OilPressure;
    }

    public static double getEngine2OilTemp() {
        return engine2OilTemp;
    }

    public static void setEngine2OilTemp(final double engine2OilTemp) {
        DataSource.engine2OilTemp = engine2OilTemp;
    }

    public static double getEngine2OilQuantity() {
        return engine2OilQuantity;
    }

    public static void setEngine2OilQuantity(final double engine2OilQuantity) {
        DataSource.engine2OilQuantity = engine2OilQuantity;
    }

    public static double getEngine2Vibration() {
        return engine2Vibration;
    }

    public static void setEngine2Vibration(final double engine2Vibration) {
        DataSource.engine2Vibration = engine2Vibration;
    }

    public static double getEngine2HydraulicPressure() {
        return engine2HydraulicPressure;
    }

    public static void setEngine2HydraulicPressure(final double engine2HydraulicPressure) {
        DataSource.engine2HydraulicPressure = engine2HydraulicPressure;
    }

    public static double getEngine2HydraulicQuantity() {
        return engine2HydraulicQuantity;
    }

    public static void setEngine2HydraulicQuantity(final double engine2HydraulicQuantity) {
        DataSource.engine2HydraulicQuantity = engine2HydraulicQuantity;
    }

    public static double getAutoPilotHeading() {
        return autoPilotHeading;
    }

    public static void setAutoPilotHeading(final double autoPilotHeading) {
        DataSource.autoPilotHeading = autoPilotHeading;
    }

    public static double getAdf1Heading() {
        return adf1Heading;
    }

    public static void setAdf1Heading(final double adf1Heading) {
        DataSource.adf1Heading = adf1Heading;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        DataSource.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        DataSource.longitude = longitude;
    }

    public static double getMagneticHeading() {
        return magneticHeading;
    }

    public static void setMagneticHeading(double magneticHeading) {
        DataSource.magneticHeading = magneticHeading;
    }

    public static double getVertSpeedFPM() {
        return vertSpeedFPM;
    }

    public static void setVertSpeedFPM(double vertSpeedFPM) {
        DataSource.vertSpeedFPM = vertSpeedFPM;
    }

    public static double getAltitude() {
        return altitude;
    }

    public static void setAltitude(double altitude) {
        DataSource.altitude = altitude;
    }

    public static double getAltitudeMeters() {
        return altitudeMeters;
    }

    public static void setAltitudeMeters(double altitudeMeters) {
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

    public static double getRange() {
        return range;
    }

    public static void setRange(double range) {
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