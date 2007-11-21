// fsuipc_java.cpp : Defines the entry point for the DLL application.
//

#include <windows.h>
#include <iostream.h>
#include "fsuipc_java.h"
#include "com_flightsim_fsuipc_fsuipc_0005fwrapper.h"
#include "fsuipc_user.h"

BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
    switch (ul_reason_for_call)
	{
		case DLL_PROCESS_ATTACH:
		case DLL_THREAD_ATTACH:
		case DLL_THREAD_DETACH:
		case DLL_PROCESS_DETACH:
			break;
    }
    return TRUE;
}

static DWORD iResult;

JNIEXPORT jint JNICALL Java_com_flightsim_fsuipc_fsuipc_1wrapper_Open(JNIEnv *, jclass, jint aFlightSim)
    {
    return FSUIPC_Open(aFlightSim,&iResult);
    }

JNIEXPORT void JNICALL Java_com_flightsim_fsuipc_fsuipc_1wrapper_Close(JNIEnv *, jclass)
    {
    FSUIPC_Close();
    }


JNIEXPORT void JNICALL Java_com_flightsim_fsuipc_fsuipc_1wrapper_ReadData(JNIEnv * env, jclass, jint aOffset, jint aCount, jbyteArray aData)
	{
	jbyte* data = env->GetByteArrayElements(aData, 0);
	FSUIPC_Read(aOffset,aCount,(void*)data,&iResult);
	FSUIPC_Process(&iResult);
	env->ReleaseByteArrayElements(aData, data, 0);

    }

JNIEXPORT void JNICALL Java_com_flightsim_fsuipc_fsuipc_1wrapper_WriteData(JNIEnv * env, jclass, jint aOffset, jint aCount, jbyteArray aData)
	{
	jbyte* data = env->GetByteArrayElements(aData, 0);
	FSUIPC_Write(aOffset,aCount,(void*)data,&iResult);
    FSUIPC_Process(&iResult);
    env->ReleaseByteArrayElements(aData, data, 0);
	}


JNIEXPORT void JNICALL Java_com_flightsim_fsuipc_fsuipc_1wrapper_Process(JNIEnv *, jclass)
	{
	FSUIPC_Process(&iResult);
    }
