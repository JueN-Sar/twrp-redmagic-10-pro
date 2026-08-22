package com.zte.gameassist.ext.common;

import android.app.IActivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Singleton;
import cn.nubia.gamelauncher.service.GameFeatureService;
import com.zte.gameassist.GameAssistWrapper;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssistController;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GAControllerProxy {
    public static final String BUNDLE_KEY_CODE = "bundle_key_code";
    public static final String BUNDLE_KEY_NAME = "bundle_key_name";
    public static final String BUNDLE_KEY_REASON = "bundle_key_reason";
    public static final String BUNDLE_KEY_RETURN = "bundle_key_return";
    public static final String BUNDLE_KEY_TO_GAMEASSIST_SERVICES_ACTION = "action_name";
    public static final String BUNDLE_KEY_TYPE = "bundle_key_type";
    public static final String BUNDLE_KEY_VALUE = "bundle_key_value";
    private static final Singleton<IGameAssistController> IGameAssistControllerSingleton = new Singleton<IGameAssistController>() { // from class: com.zte.gameassist.ext.common.GAControllerProxy.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: create, reason: merged with bridge method [inline-methods] */
        public IGameAssistController m449create() {
            try {
                IGameAssistController gameAssistController = GAControllerProxy.getGameAssistController();
                if (gameAssistController != null) {
                    return gameAssistController;
                }
            } catch (Exception e) {
                Log.e(GAControllerProxy.TAG, "Error creating IGameAssistController", e);
            }
            Log.w(GAControllerProxy.TAG, "Failed to find IGameAssistController, returning default implementation");
            return new IGameAssistController.Default();
        }
    };
    public static final String INVAKE_EXISTS_FILE = "exists_file";
    public static final String INVAKE_READ_FILE = "read_file";
    public static final String INVAKE_REGISTER_APP_CALLBACK = "register_app_callback";
    public static final String INVAKE_RESTART_PACKAGE = "restart_package";
    public static final String INVAKE_SEND_TO_GAMEASSIST_SERVICES = "send_to_gameassist_services";
    public static final String INVAKE_SET_PROP = "set_prop";
    public static final String INVAKE_SET_SETTINGS = "set_settings";
    public static final String INVAKE_SET_SURFACEFLINGER = "set_surfaceflinger";
    public static final String INVAKE_START_HOME = "start_home";
    public static final String INVAKE_UNREGISTER_APP_CALLBACK = "unregister_app_callback";
    public static final String INVAKE_UPDATE_APP_BOUNDS = "update_app_bounds";
    public static final String INVAKE_WRITE_FILE = "write_file";
    public static final String TAG = "GameAssistController.Proxy";
    private static volatile GAControllerProxy mController;

    private GAControllerProxy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IGameAssistController getGameAssistController() throws Exception {
        IGameAssistController gameAssistController = GameAssistWrapper.getGameAssistController();
        if (gameAssistController != null) {
            return gameAssistController;
        }
        IBinder service = ServiceManager.getService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
        if (service == null) {
            throw new IllegalStateException("Service 'activity' not found");
        }
        IActivityManager asInterface = IActivityManager.Stub.asInterface(service);
        if (asInterface == null) {
            throw new IllegalStateException("IActivityManager not found");
        }
        for (Method method : asInterface.getClass().getMethods()) {
            if (method.getName().startsWith("getGameAssist")) {
                Object invoke = method.invoke(asInterface, new Object[0]);
                if (invoke instanceof IGameAssistController) {
                    return (IGameAssistController) invoke;
                }
                IBinder iBinder = (IBinder) method.invoke(asInterface, new Object[0]);
                if (iBinder != null) {
                    return IGameAssistController.Stub.asInterface(iBinder);
                }
            }
        }
        return null;
    }

    public static GAControllerProxy getInstance() {
        if (mController == null) {
            synchronized (GAControllerProxy.class) {
                if (mController == null) {
                    mController = new GAControllerProxy();
                }
            }
        }
        return mController;
    }

    public IGameAssistController getBase() {
        return (IGameAssistController) IGameAssistControllerSingleton.get();
    }

    public List<String> getGameLauncherAppNameList() {
        try {
            return ((IGameAssistController) IGameAssistControllerSingleton.get()).getGameLauncherAppNameList();
        } catch (RemoteException e) {
            Log.w(TAG, "getGameLauncherAppNameList " + e.getMessage());
            return new ArrayList();
        }
    }

    public List<String> getMutexTags(String str) {
        try {
            return ((IGameAssistController) IGameAssistControllerSingleton.get()).getMutexTags(str);
        } catch (RemoteException e) {
            Log.w(TAG, "mutexTag " + e.getMessage());
            return new ArrayList();
        }
    }

    public void invake(String str, Bundle bundle, ICallback iCallback) {
        try {
            ((IGameAssistController) IGameAssistControllerSingleton.get()).invake(str, bundle, iCallback);
        } catch (RemoteException e) {
            Log.w(TAG, "invake " + e.getMessage());
        }
    }

    public void monitorMutexTag(boolean z, String str, ICallback iCallback) {
        try {
            ((IGameAssistController) IGameAssistControllerSingleton.get()).monitorMutexTag(z, str, iCallback);
        } catch (RemoteException e) {
            Log.w(TAG, "monitorMutexTag " + e.getMessage());
        }
    }

    public void mutexTag(boolean z, String str, String str2, IBinder iBinder) {
        try {
            ((IGameAssistController) IGameAssistControllerSingleton.get()).mutexTag(z, str, str2, iBinder);
        } catch (RemoteException e) {
            Log.w(TAG, "mutexTag " + e.getMessage());
        }
    }
}
