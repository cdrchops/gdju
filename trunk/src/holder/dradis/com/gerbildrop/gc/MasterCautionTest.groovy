package com.gerbildrop.gc;

left = new FuelTank(fuelLevel:190);
right = new FuelTank(fuelLevel:190);

numberOneFuelLight = new MasterCautionLight(displayText:'#1 FUEL LOW', displayColor:MasterCautionUtility.GREEN)
numberTwoFuelLight = new MasterCautionLight(displayText:'#2 FUEL LOW', displayColor:MasterCautionUtility.GREEN)

masterCaution = new MasterCaution(numberOneFuelTank:left,
                                  numberOneFuelLow:numberOneFuelLight,
                                  numberTwoFuelTank: right,
                                  numberTwoFuelLow:numberTwoFuelLight);

def oneFuelLow = masterCaution.numberOneFuelLow;
def oneFuelTank = masterCaution.numberOneFuelTank;
def twoFuelLow = masterCaution.numberTwoFuelLow;
def twoFuelTank = masterCaution.numberTwoFuelTank;

println oneFuelLow.displayText;
println oneFuelLow.displayColor;
println oneFuelTank.fuelLevel;

println twoFuelLow.displayText;
println twoFuelLow.displayColor;
println twoFuelTank.fuelLevel;

def checkFuelLevel = MasterCautionUtility.checkFuelLevel;

checkFuelLevel(oneFuelTank, oneFuelLow);

println oneFuelLow.displayColor;

oneFuelTank.fuelLevel = 171;

checkFuelLevel(oneFuelTank, oneFuelLow);

println oneFuelLow.displayColor;

checkFuelLevel(twoFuelTank, twoFuelLow);
println twoFuelLow.displayColor;

/*
interface with MSFS
get values from MSFS

create EFIS, AFCS and other instruments via text

display instruments as gui

#1 FUEL LOW Flashes when left fuel tank level is about 172 pounds.
#1 FUEL PRESS Left engine fuel pressure between engine-driven low-pressure fuel pump and highpressure
fuel pump is low.
#1 ENGINE OIL PRESS Left engine oil pressure is too low for continued operation.
#1 ENGINE OIL TEMP Left engine oil temperature is above 150°C.
CHIP #1 ENGINE Left engine chip detector in scavenge oil system has metal chip or particle buildup.
#1 FUEL FLTR BYPASS Left engine fuel filter has excessive pressure differential across filter.
#1 ENGINE STARTER Left engine start circuit is actuated.
#1 PRI SERVO PRESS First stage pressure is shut off, or has dropped below minimum, or servo pilot valve is
jammed.
TAIL ROTOR QUADRANT Appears when a tail rotor cable is broken or disconnected.
MAIN XMSN OIL TEMP Main transmission oil temperature is above 120°C.
BOOST SERVO OFF Indicates loss of second stage hydraulic pressure to the boost servo, or a boost servo
jam.
LFT PITOT HEAT Indicates left pitot heater element is not receiving power with PITOT HEAT switch
ON.
CHIP INPUT MDL-LH Indicates a metal particle has been detected by the chip detector.
CHIP ACCESS MDL-LH Indicates a metal particle has been detected by the chip detector.
MR DE-ICE FAIL Indicates a short or open in the main rotor deice system, which will disable the system.
MAIN XMSN OIL PRESS Main transmission oil pressure is below about 14 psi.
*/