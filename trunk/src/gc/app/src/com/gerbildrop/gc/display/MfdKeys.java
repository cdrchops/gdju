package com.gerbildrop.gc.display;

import com.gerbildrop.gc.data.DataSource;
import com.gerbildrop.gc.window.WindowUtil;

import java.awt.event.KeyEvent;

public class MfdKeys {
    static int engineNumber = DataSource.getEngineNumber();

    public static void checkKeys(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'f':
                DataSource.setVertSpeedFPM(DataSource.getVertSpeedFPM() + 10);
                break;
            case 'F':
                DataSource.setVertSpeedFPM(DataSource.getVertSpeedFPM() - 10);
                break;
            case 'g':
                DataSource.setMagneticHeading(DataSource.getMagneticHeading() + 10);
                DataSource.setTrueHeading(DataSource.getTrueHeading() + 10);
                break;
            case 'G':
                DataSource.setMagneticHeading(DataSource.getMagneticHeading() - 10);
                DataSource.setTrueHeading(DataSource.getTrueHeading() - 10);
                break;
            case 'a':
                DataSource.setPitch(DataSource.getPitch() + 10);
                break;
            case 'A':
                DataSource.setPitch(DataSource.getPitch() - 10);
                break;
            case 'x':
                DataSource.setRoll(DataSource.getRoll() + 10);
                break;
            case 'X':
                DataSource.setRoll(DataSource.getRoll() - 10);
                break;
            case 's':
                DataSource.setIas(DataSource.getIas() + 10);
                break;
            case 'S':
                DataSource.setIas(DataSource.getIas() - 10);
                break;
            case 'w':
                DataSource.setAltitude(DataSource.getAltitude() + 10);
                break;
            case 'W':
                DataSource.setAltitude(DataSource.getAltitude() - 10);
                break;
            case 'j':
                DataSource.setLcabinDoorClosed(!DataSource.isLcabinDoorClosed());
                break;
            case 'J':
                DataSource.setLcabinDoor2Closed(!DataSource.isLcabinDoor2Closed());
                break;
            case 'k':
                DataSource.setLcabinDoor3Closed(!DataSource.isLcabinDoor3Closed());
                break;
            case 'K':
                DataSource.setLcabinDoor4Closed(!DataSource.isLcabinDoor4Closed());
                break;
            case 'n':
                DataSource.setRcabinDoorClosed(!DataSource.isRcabinDoorClosed());
                break;
            case 'N':
                DataSource.setRcabinDoor2Closed(!DataSource.isRcabinDoor2Closed());
                break;
            case 'm':
                DataSource.setRcabinDoor3Closed(!DataSource.isRcabinDoor3Closed());
                break;
            case 'M':
                DataSource.setRcabinDoor4Closed(!DataSource.isRcabinDoor4Closed());
                break;
            case 'b':
                DataSource.setAvionicDoorClosed(!DataSource.isAvionicDoorClosed());
                break;
            case 'B':
                DataSource.setBulkDoorClosed(!DataSource.isBulkDoorClosed());
                break;
            case 'l':
                DataSource.setCargoDoor1Closed(!DataSource.isCargoDoor1Closed());
                break;
            case 'L':
                DataSource.setCargoDoor2Closed(!DataSource.isCargoDoor2Closed());
                break;
            case '1':
                DataSource.setEngineNumber(1);
                break;
            case '2':
                DataSource.setEngineNumber(2);
                break;
            case '3':
                DataSource.setEngineNumber(3);
                break;
            case '4':
                DataSource.setEngineNumber(4);
                break;
            case '5':
                DataSource.setDisplay(5);
                break;
            case '8':
                DataSource.setDisplay(8);
                break;
            case '9':
                DataSource.setDisplay(9);
                break;
            case '0':
                DataSource.setDisplay(0);
                break;
            case 'e':
                if (engineNumber == 1) {
                    DataSource.setResevoir1LowAirPress(!DataSource.isResevoir1LowAirPress());
                } else if (engineNumber == 2) {
                    DataSource.setResevoir2LowAirPress(!DataSource.isResevoir2LowAirPress());
                } else if (engineNumber == 3) {
                    DataSource.setResevoir3LowAirPress(!DataSource.isResevoir3LowAirPress());
                } else if (engineNumber == 4) {
                }
                break;
            case 'E':
                if (engineNumber == 1) {
                    DataSource.setResevoir1Overheat(!DataSource.isResevoir1Overheat());
                } else if (engineNumber == 2) {
                    DataSource.setResevoir2Overheat(!DataSource.isResevoir2Overheat());
                } else if (engineNumber == 3) {
                    DataSource.setResevoir3Overheat(!DataSource.isResevoir3Overheat());
                } else if (engineNumber == 4) {
                }
                break;
            case 'r':
                if (engineNumber == 1) {
                    DataSource.setResevoir1ValveOpen(!DataSource.isResevoir1ValveOpen());
                } else if (engineNumber == 2) {
                    DataSource.setResevoir2ValveOpen(!DataSource.isResevoir2ValveOpen());
                } else if (engineNumber == 3) {
                    DataSource.setResevoir3ValveOpen(!DataSource.isResevoir3ValveOpen());
                } else if (engineNumber == 4) {
                    DataSource.setResevoir4ValveOpen(!DataSource.isResevoir4ValveOpen());
                }
                break;
            case 'R':
                if (engineNumber == 1) {
                    DataSource.setEnginePump1On(!DataSource.isEnginePump1On());
                } else if (engineNumber == 2) {
                    DataSource.setEnginePump2On(!DataSource.isEnginePump2On());
                } else if (engineNumber == 3) {
                    DataSource.setEnginePump3On(!DataSource.isEnginePump3On());
                } else if (engineNumber == 4) {
                    DataSource.setEnginePump4On(!DataSource.isEnginePump4On());
                }
                break;
            case 't':
                if (engineNumber == 1) {
                    DataSource.setEnginePump1LowPress(!DataSource.isEnginePump1LowPress());
                } else if (engineNumber == 2) {
                    DataSource.setEnginePump2LowPress(!DataSource.isEnginePump2LowPress());
                } else if (engineNumber == 3) {
                    DataSource.setEnginePump3LowPress(!DataSource.isEnginePump3LowPress());
                } else if (engineNumber == 4) {
                    DataSource.setEnginePump4LowPress(!DataSource.isEnginePump4LowPress());
                }
                break;
            case 'T':
                if (engineNumber == 1) {
                    DataSource.setElectPump1commanded(!DataSource.isElectPump1commanded());
                } else if (engineNumber == 2) {
                    DataSource.setElectPump2commanded(!DataSource.isElectPump2commanded());
                } else if (engineNumber == 3) {
                    DataSource.setElectPump3commanded(!DataSource.isElectPump3commanded());
                } else if (engineNumber == 4) {
                    DataSource.setRamAirTurbineStowed(!DataSource.isRamAirTurbineStowed());
                }
                break;
            case 'y':
                if (engineNumber == 1) {
                    DataSource.setElectPump1On(!DataSource.isElectPump1On());
                } else if (engineNumber == 2) {
                    DataSource.setElectPump2On(!DataSource.isElectPump2On());
                } else if (engineNumber == 3) {
                    DataSource.setElectPump3On(!DataSource.isElectPump3On());
                } else if (engineNumber == 4) {
                    DataSource.setRamAirTurbineStowing(!DataSource.isRamAirTurbineStowing());
                }
                break;
            case 'Y':
                if (engineNumber == 1) {
                    DataSource.setElectPump1LowPress(!DataSource.isElectPump1LowPress());
                } else if (engineNumber == 2) {
                    DataSource.setElectPump2LowPress(!DataSource.isElectPump2LowPress());
                } else if (engineNumber == 3) {
                    DataSource.setElectPump3LowPress(!DataSource.isElectPump3LowPress());
                } else if (engineNumber == 4) {
                    DataSource.setRamAirTurbineOverspeed(!DataSource.isRamAirTurbineOverspeed());
                }
                break;

            case KeyEvent.VK_ESCAPE:
                WindowUtil.lazilyExit();
                break;
        }
    }
}
