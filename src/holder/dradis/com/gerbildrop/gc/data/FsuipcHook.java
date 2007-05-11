package com.gerbildrop.gc.data;

/**
 * this is the class that contains all of the variables needed with hooks into the native FSUIPC java file to retrieve
 * values from the FSUIPC native interface.
 * <p/>
 * the data source will connect to this to retrieve the info that it needs
 */
public class FsuipcHook extends FSUIPC {

    public void load() {
        //-------------Airframe data--------------
        DataSource.setRoll(getLong(0x57c));
        DataSource.setPitch(getLong(0x578));
        DataSource.setTrueHeading(getLong(0x580));
        DataSource.setMagneticVariation(getShort(0x2A0));
        DataSource.setLatitudeAircraftLow(getLong(0x0560));
        DataSource.setLatitudeAircraftHigh(getLong(0x0564));
        DataSource.setLongitudeAircraftLow(getLong(0x0568));
        DataSource.setLongitudeAircraftHigh(getLong(0x056C));
        DataSource.setIas(getLong(0x2bc));
        DataSource.setTas(getLong(0x2b8));
        DataSource.setMachSpeed(getShort(0x11C6));
        DataSource.setGroundSpeedK(getLong(0x2b4));
        DataSource.setVerticalSpeedMS(getLong(0x2c8));
        DataSource.setFracMeters(getLong(0x570));
        DataSource.setUnitMeters(getLong(0x574));
        DataSource.setFlapsDeflectionRadians(getLong(0x0bdc));
        DataSource.setFlapsTrueDeflectionRadians(getLong(0x0BE0));
        DataSource.setNoseGear(getLong(0x0bec));
        DataSource.setRightGear(getLong(0x0bf0));
        DataSource.setLeftGear(getLong(0x0bf4));
        DataSource.setAltimeterPressure(getShort(0x0F48));
        DataSource.setTat(getShort(0x11D0));

        //------------------------Nav Radio--------------------------
        DataSource.setNav1Freq(getShort(0x0350));
        DataSource.setNav1Radial(getShort(0x0c50));
        DataSource.setNav1LocalizerNeedle(getString(0xc48, 1));
        DataSource.setNav1OBS(getShort(0x0c4e));
        DataSource.setNav1GlideslopeAlive(getBoolean(0xc4c));
        DataSource.setNav1GlideslopeNeedle(getString(0xc49, 1));
        DataSource.setNav2Freq(getShort(0x0352));
        DataSource.setNav2Radial(getShort(0x0c60));
        DataSource.setNav2LocalizerNeedle(getString(0xc59, 1));
        DataSource.setNav2OBS(getShort(0x0c4e));
        DataSource.setADFHeading(getShort(0x0c5e));
        DataSource.setrStatus(getShort(0x3300));

        //---------------------------ILS--------------------------------
        DataSource.setILSInverseRunwayHeading(getShort(0x0870));
        DataSource.setILSGlideslopeInclination(getShort(0x0872));

        //------------------------Fuel----------------------------------
        DataSource.setFuelCentreLevel(getLong(0x0B74));
        DataSource.setFuelLeftLevel(getLong(0x0B7C));
        DataSource.setFuelRightLevel(getLong(0x0B94));
        DataSource.setFuelCentreCapacity(getLong(0x0B78));
        DataSource.setFuelLeftCapacity(getLong(0x0B80));
        DataSource.setFuelRightCapacity(getLong(0x0B98));

        //------------------------Autopilot-----------------------------
        DataSource.setDirActive(getShort(0x2ee0));
        DataSource.setDirPitch(getDouble(0x2ee8));
        DataSource.setDirBank(getDouble(0x2ef0));
        DataSource.setRefAirspeedK(getShort(0x07E2));

        //-------------------------------------------------------------
        DataSource.setTotalAirTemperature(getShort(0x11D0));

        //---------------------- Fuel Tanks----------------------------
        DataSource.setFuelTankCapacityCenter(getLong(0x0B78));
        DataSource.setFuelTankCapacityLeft(getLong(0x0B80));
        DataSource.setFuelTankCapacityRight(getLong(0x0B98));
        DataSource.setFuelTankLevelCenter(getLong(0x0B74));
        DataSource.setFuelTankLevelLeft(getLong(0x0B7C));
        DataSource.setFuelTankLevelRight(getLong(0x0B94));

        //-------------------------Engines-----------------------------

        //-------------------------Engine 1----------------------------
        DataSource.setEngine1N1(getDouble(0x2010));
        DataSource.setEngine1N2(getDouble(0x2018));
        DataSource.setEngine1FF_PPH(getDouble(0x2020));
        DataSource.setEngine1EPR(getDouble(0x2030));
        DataSource.setEngine1EGT(getShort(0x8be));

        // mdf
        String pp1 = getString(0x08BA, 2);
        String pp2 = getString(0x08B8, 2);
        DataSource.setEngine1OilPressure(getShort(0x08BA));
        DataSource.setEngine1OilTemp(getShort(0x08B8));
        DataSource.setEngine1OilQuantity(getShort(0x08D0));
        DataSource.setEngine1Vibration(getLong(0x08D4));
        DataSource.setEngine1HydraulicPressure(getLong(0x08D8));
        DataSource.setEngine1HydraulicQuantity(getLong(0x08DC));

        //-------------------------Engine 2----------------------------
        DataSource.setEngine2N1(getDouble(0x2110));
        DataSource.setEngine2N2(getDouble(0x2118));
        DataSource.setEngine2FFPPH(getDouble(0x2120));
        DataSource.setEngine2EPR(getDouble(0x2130));
        DataSource.setEngine2EGT(getShort(0x8be));

        // mdf
        String pp3 = getString(0x0952, 2);
        String pp4 = getString(0x0950, 2);
        DataSource.setEngine2OilPressure(getShort(0x0952));
        DataSource.setEngine2OilTemp(getShort(0x0950));
        DataSource.setEngine2OilQuantity(getShort(0x0968));
        DataSource.setEngine2Vibration(getLong(0x096C));
        DataSource.setEngine2HydraulicPressure(getLong(0x0970));
        DataSource.setEngine2HydraulicQuantity(getLong(0x0974));
        DataSource.setAutoPilotHeading(getShort(0x7CC));
        DataSource.setAdf1Heading(getShort(0x0C6A));

        // Grab the requested data from the sim
        // this->Process();

        //----------------Convert the data to a meaningful form--------------

        //-------------Airframe data--------------
        double Roll = -360.0 * (DataSource.getRoll()) / (65536.0 * 65536.0);
        DataSource.setRoll(Roll);

        double Pitch = -360.0 * (DataSource.getPitch()) / (65536.0 * 65536.0);
        DataSource.setPitch(Pitch);

        double True_Heading = 360.0 * (DataSource.getTrueHeading()) / (65536.0 * 65536.0);
        if (True_Heading < 0)
            True_Heading += 360;
        DataSource.setTrueHeading(True_Heading);

        double Mag_Variation = 360.0 / 65536.0 * DataSource.getMagneticVariation();
        DataSource.setMagneticVariation(Mag_Variation);

        double Mag_Heading = True_Heading - Mag_Variation;
        Mag_Heading = WrapHeading(Mag_Heading);
        DataSource.setMagneticHeading(Mag_Heading);

        double _fLatitudeAircraft = DataSource.getLatitudeAircraftHigh() * 90. / 10001750.0;
        _fLatitudeAircraft += (DataSource.getLatitudeAircraftLow() * 90. / 10001750) / (65536. * 65536.);
        DataSource.setLatitude(_fLatitudeAircraft);

        double _fLongitudeAircraft = DataSource.getLongitudeAircraftHigh() * 360. / (65536. * 65536.);
        _fLongitudeAircraft += (DataSource.getLongitudeAircraftLow() * 360 / (65536. * 65536.)) / (65536. * 65536.);
        DataSource.setLongitude(_fLongitudeAircraft);

        DataSource.setIas(DataSource.getIas() / 128);

        DataSource.setTas(DataSource.getTas() / 128);
        DataSource.setMachSpeed(DataSource.getMachSpeed() / 20480.0);

        DataSource.setGroundSpeedK(DataSource.getGroundSpeedK() / 65536 * 1.943844);

        // Compute altitude
        // Note that we use the meters and fractional meters data, rather than
        // the barometric altitude in feet (which seems to update rather slowly)
        // 4294967296 is 2^32, because the fractional unit is scaled to maximum
        long bob = 4294967 * 100 + 296;

        // possible magnitude of an unsigned long int
        double Pressure_Alt_Metres = DataSource.getUnitMeters() + DataSource.getFracMeters() / bob;
        DataSource.setAltitudeMeters(Pressure_Alt_Metres);
        DataSource.setAltitude(Pressure_Alt_Metres * 3.28084);

        DataSource.setAltimeterPressure(DataSource.getAltimeterPressure() / 16.0);

        DataSource.setVerticalSpeedMS(DataSource.getVerticalSpeedMS() / 256);

        DataSource.setVertSpeedFPM(DataSource.getVerticalSpeedMS() * 60 * 3.28084 / 256);
    }

    double WrapHeading(double rawHead) {
        if (rawHead < 0)
            return 360 - rawHead;

        if (rawHead >= 360)
            return rawHead - 360;

        return rawHead;
    }

    public void start() {
        try {
            int ret = fsuipc_wrapper.Open(FsuipcWrapper.SIM_ANY);
            int counter = 0;

            while (true && counter <= 50) {
                if (ret == 0) {
                    System.out.println("Flight sim not found");
                    ret = fsuipc_wrapper.Open(FsuipcWrapper.SIM_ANY);
                } else {
                    load();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter ++;
                System.out.println(counter);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}