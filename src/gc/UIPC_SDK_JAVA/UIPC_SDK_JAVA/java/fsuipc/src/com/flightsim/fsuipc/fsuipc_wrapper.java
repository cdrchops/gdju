/*
*/ 
package com.flightsim.fsuipc;


public class fsuipc_wrapper
	{ 
	static 
		{ 
		// load library
		System.loadLibrary("fsuipc_java" ); 
		} 
	public static synchronized native int Open();
	public static synchronized native void Close();
	
    public static synchronized native void ReadData(int aOffset,int aCount,byte[] aData);	
	public static synchronized native void WriteData(int aOffset,int aCount,byte[] aData);	
	public static synchronized native void Process();
    }
