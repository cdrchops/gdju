# Microsoft Developer Studio Generated NMAKE File, Based on fsuipc_java.dsp
!IF "$(CFG)" == ""
CFG=fsuipc_java - Win32 Debug
!MESSAGE No configuration specified. Defaulting to fsuipc_java - Win32 Debug.
!ENDIF 

!IF "$(CFG)" != "fsuipc_java - Win32 Release" && "$(CFG)" != "fsuipc_java - Win32 Debug"
!MESSAGE Invalid configuration "$(CFG)" specified.
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "fsuipc_java.mak" CFG="fsuipc_java - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "fsuipc_java - Win32 Release" (based on "Win32 (x86) Dynamic-Link Library")
!MESSAGE "fsuipc_java - Win32 Debug" (based on "Win32 (x86) Dynamic-Link Library")
!MESSAGE 
!ERROR An invalid configuration is specified.
!ENDIF 

!IF "$(OS)" == "Windows_NT"
NULL=
!ELSE 
NULL=nul
!ENDIF 

!IF  "$(CFG)" == "fsuipc_java - Win32 Release"

OUTDIR=.\Release
INTDIR=.\Release
# Begin Custom Macros
OutDir=.\Release
# End Custom Macros

ALL : "$(OUTDIR)\fsuipc_java.dll"


CLEAN :
	-@erase "$(INTDIR)\fsuipc_java.obj"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(OUTDIR)\fsuipc_java.dll"
	-@erase "$(OUTDIR)\fsuipc_java.exp"
	-@erase "$(OUTDIR)\fsuipc_java.lib"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

CPP=cl.exe
CPP_PROJ=/nologo /MT /W3 /GX /O2 /I "..\bin" /I "..\..\simpit\inc" /I "D:\j2sdk1.4.0_02\include" /I "D:\j2sdk1.4.0_02\include\win32" /I "..\c-lib" /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "FSUIPC_JAVA_EXPORTS" /Fp"$(INTDIR)\fsuipc_java.pch" /Yu"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /c 

.c{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.c{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

MTL=midl.exe
MTL_PROJ=/nologo /D "NDEBUG" /mktyplib203 /win32 
RSC=rc.exe
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\fsuipc_java.bsc" 
BSC32_SBRS= \
	
LINK32=link.exe
LINK32_FLAGS=kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /incremental:no /pdb:"$(OUTDIR)\fsuipc_java.pdb" /machine:I386 /out:"$(OUTDIR)\fsuipc_java.dll" /implib:"$(OUTDIR)\fsuipc_java.lib" 
LINK32_OBJS= \
	"$(INTDIR)\fsuipc_java.obj" \
	"..\c-lib\FSUIPC_User.lib"

"$(OUTDIR)\fsuipc_java.dll" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ELSEIF  "$(CFG)" == "fsuipc_java - Win32 Debug"

OUTDIR=.\..\bin
INTDIR=.\Debug
# Begin Custom Macros
OutDir=.\..\bin
# End Custom Macros

ALL : "$(OUTDIR)\fsuipc_java.dll" "$(OUTDIR)\fsuipc_java.bsc"


CLEAN :
	-@erase "$(INTDIR)\fsuipc_java.obj"
	-@erase "$(INTDIR)\fsuipc_java.sbr"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(INTDIR)\vc60.pdb"
	-@erase "$(OUTDIR)\fsuipc_java.bsc"
	-@erase "$(OUTDIR)\fsuipc_java.dll"
	-@erase "$(OUTDIR)\fsuipc_java.exp"
	-@erase "$(OUTDIR)\fsuipc_java.ilk"
	-@erase "$(OUTDIR)\fsuipc_java.lib"
	-@erase "$(OUTDIR)\fsuipc_java.pdb"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

"$(INTDIR)" :
    if not exist "$(INTDIR)/$(NULL)" mkdir "$(INTDIR)"

CPP=cl.exe
CPP_PROJ=/nologo /MTd /W3 /Gm /GX /ZI /Od /I "..\inc" /I "..\bin" /I "..\..\simpit\hwdriver\inc" /I "D:\j2sdk1.4.0_02\include" /I "D:\j2sdk1.4.0_02\include\win32" /I "..\c-lib" /I "..\..\..\vxcl\threading" /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "FSUIPC_JAVA_EXPORTS" /FR"$(INTDIR)\\" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /GZ /c 

.c{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.c{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

MTL=midl.exe
MTL_PROJ=/nologo /D "_DEBUG" /mktyplib203 /win32 
RSC=rc.exe
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\fsuipc_java.bsc" 
BSC32_SBRS= \
	"$(INTDIR)\fsuipc_java.sbr"

"$(OUTDIR)\fsuipc_java.bsc" : "$(OUTDIR)" $(BSC32_SBRS)
    $(BSC32) @<<
  $(BSC32_FLAGS) $(BSC32_SBRS)
<<

LINK32=link.exe
LINK32_FLAGS=kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /incremental:yes /pdb:"$(OUTDIR)\fsuipc_java.pdb" /debug /machine:I386 /out:"$(OUTDIR)\fsuipc_java.dll" /implib:"$(OUTDIR)\fsuipc_java.lib" /pdbtype:sept /libpath:"..\..\SimPit\HWDriver\vc6\debug" 
LINK32_OBJS= \
	"$(INTDIR)\fsuipc_java.obj" \
	"..\c-lib\FSUIPC_User.lib"

"$(OUTDIR)\fsuipc_java.dll" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ENDIF 


!IF "$(NO_EXTERNAL_DEPS)" != "1"
!IF EXISTS("fsuipc_java.dep")
!INCLUDE "fsuipc_java.dep"
!ELSE 
!MESSAGE Warning: cannot find "fsuipc_java.dep"
!ENDIF 
!ENDIF 


!IF "$(CFG)" == "fsuipc_java - Win32 Release" || "$(CFG)" == "fsuipc_java - Win32 Debug"
SOURCE=..\src\fsuipc_java.cpp

!IF  "$(CFG)" == "fsuipc_java - Win32 Release"


"$(INTDIR)\fsuipc_java.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\fsuipc_java.pch"
	$(CPP) $(CPP_PROJ) $(SOURCE)


!ELSEIF  "$(CFG)" == "fsuipc_java - Win32 Debug"


"$(INTDIR)\fsuipc_java.obj"	"$(INTDIR)\fsuipc_java.sbr" : $(SOURCE) "$(INTDIR)"
	$(CPP) $(CPP_PROJ) $(SOURCE)


!ENDIF 


!ENDIF 

