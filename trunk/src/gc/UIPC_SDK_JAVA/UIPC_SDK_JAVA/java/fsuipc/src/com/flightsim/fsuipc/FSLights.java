package com.flightsim.fsuipc;

/*
Copyright 2002 Mark Burton
*/



public class FSLights
	{
	protected byte[] iData;
	public FSLights()
		{
		iData = new byte[2];
		}

	public int NavLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x01;
		}
	
	public void SetNavLight(boolean aOn)
		{
		int current = NavLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x01);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xfe);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}

	public int BeaconLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x02;
		}

	public void SetBeaconLight(boolean aOn)
		{
		int current = BeaconLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x02);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xfd);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}

	public int LandingLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x04;
		}
	public void SetLandingLight(boolean aOn)
		{
		int current = LandingLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x04);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xfb);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	
	public int TaxiLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x08;
		}
	
	public void SetTaxiLight(boolean aOn)
		{
		int current = TaxiLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x08);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xf7);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	
	public int StrobeLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x10;
		}

	public void SetStrobeLight(boolean aOn)
		{
		int current = StrobeLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x10);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xef);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	
	public int InstrumentLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x20;
		}

	public void SetInstrumentLight(boolean aOn)
		{
		int current = InstrumentLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x20);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xdf);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	
	public int RecognitionLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x40;
		}
	
	public void SetRecognitionLight(boolean aOn)
		{
		int current = RecognitionLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x40);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0xbf);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	

	public int WingLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return iData[0]  & 0x80;
		}

	public void SetWingLight(boolean aOn)
		{
		int current = WingLightState();
		if(aOn == true)
			{
			iData[0] = (byte) (current| 0x80);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		else
			{
			iData[0] = (byte) (current & 0x7f);
			iData[1]=0;
			fsuipc_wrapper.WriteData(0x0d0c,2,iData);
			}
		}
	
	public int LogoLightState()
		{
		fsuipc_wrapper.ReadData(0x0d0c,2,iData);
		return (iData[0] + iData[1]<<8)& 0x100;
		}
	
	
	
	
	
	
	}
