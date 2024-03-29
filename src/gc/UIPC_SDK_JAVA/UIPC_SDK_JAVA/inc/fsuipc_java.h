
// The following ifdef block is the standard way of creating macros which make exporting 
// from a DLL simpler. All files within this DLL are compiled with the FSUIPC_JAVA_EXPORTS
// symbol defined on the command line. this symbol should not be defined on any project
// that uses this DLL. This way any other project whose source files include this file see 
// FSUIPC_JAVA_API functions as being imported from a DLL, wheras this DLL sees symbols
// defined with this macro as being exported.
#ifdef FSUIPC_JAVA_EXPORTS
#define FSUIPC_JAVA_API __declspec(dllexport)
#else
#define FSUIPC_JAVA_API __declspec(dllimport)
#endif

FSUIPC_JAVA_API int fnFsuipc_java(void);

