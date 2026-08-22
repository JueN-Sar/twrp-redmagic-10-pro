package com.zte.shared.wrapper;

import android.os.IBinder;
import android.os.ServiceManager;

/* loaded from: classes2.dex */
public class ServiceManagerWrapper {
    public static IBinder getService(String str) {
        return ServiceManager.getService(str);
    }

    public static <T> T invake(String str, String str2, String str3, Class[] clsArr, Object[] objArr) {
        IBinder service = getService(str);
        if (service != null) {
            try {
                return (T) Class.forName(str2).getMethod(str3, clsArr).invoke(Class.forName(str2 + "$Stub").getMethod("asInterface", IBinder.class).invoke(null, service), objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
