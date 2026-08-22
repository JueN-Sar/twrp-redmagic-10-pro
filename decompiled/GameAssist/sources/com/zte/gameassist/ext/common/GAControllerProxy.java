package com.zte.gameassist.ext.common;

import android.app.IActivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Singleton;
import com.zte.gameassist.GameAssistWrapper;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssistController;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GAControllerProxy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile GAControllerProxy f16662a;

    /* renamed from: b, reason: collision with root package name */
    private static final Singleton f16663b = new Singleton<IGameAssistController>() { // from class: com.zte.gameassist.ext.common.GAControllerProxy.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IGameAssistController create() {
            try {
                IGameAssistController b2 = GAControllerProxy.b();
                if (b2 != null) {
                    return b2;
                }
            } catch (Exception e2) {
                Log.e("GameAssistController.Proxy", "Error creating IGameAssistController", e2);
            }
            Log.w("GameAssistController.Proxy", "Failed to find IGameAssistController, returning default implementation");
            return new IGameAssistController.Default();
        }
    };

    private GAControllerProxy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IGameAssistController b() {
        IGameAssistController gameAssistController = GameAssistWrapper.getGameAssistController();
        if (gameAssistController != null) {
            return gameAssistController;
        }
        IBinder service = ServiceManager.getService("activity");
        if (service == null) {
            throw new IllegalStateException("Service 'activity' not found");
        }
        IActivityManager asInterface = IActivityManager.Stub.asInterface(service);
        if (asInterface == null) {
            throw new IllegalStateException("IActivityManager not found");
        }
        for (Method method : asInterface.getClass().getMethods()) {
            if (method.getName().startsWith("getGameAssist")) {
                Object invoke = method.invoke(asInterface, null);
                if (invoke instanceof IGameAssistController) {
                    return (IGameAssistController) invoke;
                }
                IBinder iBinder = (IBinder) method.invoke(asInterface, null);
                if (iBinder != null) {
                    return IGameAssistController.Stub.asInterface(iBinder);
                }
            }
        }
        return null;
    }

    public static GAControllerProxy c() {
        if (f16662a == null) {
            synchronized (GAControllerProxy.class) {
                try {
                    if (f16662a == null) {
                        f16662a = new GAControllerProxy();
                    }
                } finally {
                }
            }
        }
        return f16662a;
    }

    public List d(String str) {
        try {
            return ((IGameAssistController) f16663b.get()).getMutexTags(str);
        } catch (RemoteException e2) {
            Log.w("GameAssistController.Proxy", "mutexTag " + e2.getMessage());
            return new ArrayList();
        }
    }

    public void e(String str, Bundle bundle, ICallback iCallback) {
        try {
            ((IGameAssistController) f16663b.get()).invake(str, bundle, iCallback);
        } catch (RemoteException e2) {
            Log.w("GameAssistController.Proxy", "invake " + e2.getMessage());
        }
    }

    public void f(boolean z, String str, ICallback iCallback) {
        try {
            ((IGameAssistController) f16663b.get()).monitorMutexTag(z, str, iCallback);
        } catch (RemoteException e2) {
            Log.w("GameAssistController.Proxy", "monitorMutexTag " + e2.getMessage());
        }
    }

    public void g(boolean z, String str, String str2, IBinder iBinder) {
        try {
            ((IGameAssistController) f16663b.get()).mutexTag(z, str, str2, iBinder);
        } catch (RemoteException e2) {
            Log.w("GameAssistController.Proxy", "mutexTag " + e2.getMessage());
        }
    }
}
