#include <windows.h>
#include "fsuipc_java.h"
#include "com_mark_FS_panel_0005fwrapper.h"
#include "fsuipc_user.h"
#include "Threaded.h"

#ifdef _DEBUG
#include <stdio.h>
#define LOG(A) {printf(A);printf("\n");}
#define LOG2(A,B) {printf(A,B);printf("\n");}
#else
#define LOG(A)
#endif

class CPanelDriver : public MControllerObserver
    {
    public:
        CPanelDriver(JNIEnv * aJavaEnv):iJavaEnv(aJavaEnv)
            {
            LOG("CPanelDriver::CPanelDriver");
            iVm=NULL;
            iDriver = new CControllerThread(this);
            }
        virtual ~CPanelDriver()
            {
            LOG("CPanelDriver::~CPanelDriver");
            //not sure why, but this causes a panic
            //delete iDriver;
            //iDriver=NULL;
            }
        int Close()
            {
            LOG("CPanelDriver::Close()");

            return iDriver->End();
            }
        void Open()
            {
            LOG("CPanelDriver::Open()");
            iDriver->Begin();
            }
        void Finalise()
            {
            LOG("CPanelDriver::Finalise()");
            if(iVm)
                {
                iVm->DetachCurrentThread();
                iVm=NULL;
                }
            }
        void AddRead(BYTE aAdd,jobject aJavaObserver)
            {
            LOG("CPanelDriver::AddRead()");
            jobject obj = iJavaEnv->NewGlobalRef(aJavaObserver);
            iDriver->AppendEntry(TPanelEntry(aAdd,1,this,obj));
            }
        void AddWrite(BYTE aAdd,BYTE aData)
            {
            LOG("CPanelDriver::AddWrite()");
            iDriver->AppendOutput(TPanelOutput(aAdd,aData));
            }
        //
        void Update(TPanelEntry& aEntry)
            {
            LOG("CPanelDriver::Update()");
            if(!iVm)
                {
                LOG("Attaching thread");
                iJavaEnv->GetJavaVM(&iVm);
                jint ret = iVm->AttachCurrentThread((void**)&iNewEnv,NULL);
                LOG2("new Env 0x%08x ",iNewEnv);
                }
            // aPtr is the java object to call
            jobject obj = static_cast<jobject>(aEntry.iPtr);
            jclass cls = iNewEnv->FindClass("com/mark/FS/IPanelObserver");
            jmethodID mid = iNewEnv->GetMethodID(cls, "Update", "(II)V");
            iNewEnv->CallVoidMethod(obj,mid,aEntry.iAddress,aEntry.iData[0]);
            }
        void Cleanup(TPanelEntry& aEntry)
            {
            jobject obj = static_cast<jobject>(aEntry.iPtr);
            LOG2("Cleanup 0x%08x ",obj);
            iJavaEnv->DeleteGlobalRef(obj);
            }
    private:
        CControllerThread* iDriver;
        JNIEnv * iJavaEnv;
        JNIEnv * iNewEnv;
        JavaVM * iVm;
        
    };

static CPanelDriver* gDriver=0;

JNIEXPORT jint JNICALL Java_com_mark_FS_panel_1wrapper_Open(JNIEnv * aJavaEnv, jobject obj)
    {
    gDriver = new CPanelDriver(aJavaEnv);
    gDriver->Open();
    return 0;
    }

JNIEXPORT void JNICALL Java_com_mark_FS_panel_1wrapper_Close(JNIEnv *, jobject)
    {
    LOG("->wrapper_Close()");
    if(gDriver->Close())
        {
        delete gDriver;
        gDriver=NULL;
        }
    LOG("<-wrapper_Close()");
    }

JNIEXPORT void JNICALL Java_com_mark_FS_panel_1wrapper_AddRead(JNIEnv *, jobject aJavaParent, jint aAddress, jobject aJavaObserver)
    {
    LOG2("\t\taJavaObserver 0x%08x ",aJavaObserver);
            
    gDriver->AddRead(aAddress,aJavaObserver);
    }

JNIEXPORT void JNICALL Java_com_mark_FS_panel_1wrapper_AddWrite(JNIEnv *, jobject, jint aAdd, jint aData)
    {
    gDriver->AddWrite(aAdd,aData);
    //gDriver->Write(aAddress,aData);
    }

JNIEXPORT void JNICALL Java_com_mark_FS_panel_1wrapper_Delete(JNIEnv *, jobject)
    {
    delete gDriver;
    gDriver=NULL;
    }

