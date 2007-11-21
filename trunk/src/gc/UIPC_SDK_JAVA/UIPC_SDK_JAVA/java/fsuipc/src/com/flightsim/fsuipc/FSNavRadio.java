/*
	Copyright 2003 Mark Burton
*/

package com.flightsim.fsuipc;

public abstract class FSNavRadio extends FSUIPC
{
	protected int iStandbyFreq;
	protected int iFreq;
	protected int iID;
	protected int iName;
	protected int iSwap;
	protected int iLocNeedle;
	protected int iGlideSlope;
	public FSNavRadio()
	{
	super();
	}
	public short StandByFreq()
	{
	return getShort(iStandbyFreq);
	}
	
	public String StandByFreqAsString()
	{
		int freq = StandByFreq();
		int dig1 = (freq>>12 & 0x0f);
		int	dig2 = (freq>>8 & 0x0f);
		int dig3 = (freq>>4 & 0x0f);
		int dig4 = (freq& 0x0f);
		String ret = new String("1"+new Integer(dig1).toString()+new Integer(dig2).toString()+"."+new Integer(dig3).toString()+new Integer(dig4).toString());
		return ret;
	}
	
	public short Freq()
	{
	return getShort(iFreq);
	}
	public String FreqAsString()
		{
		int freq = Freq();
		int dig1 = (freq>>12 & 0x0f);
		int	dig2 = (freq>>8 & 0x0f);
		int dig3 = (freq>>4 & 0x0f);
		int dig4 = (freq& 0x0f);
		String ret = new String("1"+new Integer(dig1).toString()+new Integer(dig2).toString()+"."+new Integer(dig3).toString()+new Integer(dig4).toString());
		return ret;
		}

	public String Identity()
	{
	return getString(iID,6);
	}
	public String Name()
	{
	return getString(iName,25);
	}
	public void SwapFrequencies()
	{
	byte[] data = new byte[1];
	data[0]=2;
	fsuipc_wrapper.WriteData(iSwap,1,data);
	}	
	public byte LocaliserNeedle()
	{
	return getByte(iLocNeedle);
	}
	public byte GlideSlope()
	{
	return getByte(iGlideSlope);
	}
}