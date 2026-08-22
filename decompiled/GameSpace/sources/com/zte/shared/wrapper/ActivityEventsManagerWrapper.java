package com.zte.shared.wrapper;

import android.os.Bundle;
import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
import com.zte.activityevent.IActivityEventsCallback;
import com.zte.activityevent.IActivityEventsServer;
import com.zte.activityevent.IActivityInnerListenerCallback;
import com.zte.utils.WindowUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ActivityEventsManagerWrapper {
    public static final IActivityEventsServer mActivityEventsServer;
    public static final boolean mServiceIsLive;

    public static abstract class Callback {
        private final IBinder mStub = new IActivityEventsCallback.Stub() { // from class: com.zte.shared.wrapper.ActivityEventsManagerWrapper.Callback.1
            @Override // com.zte.activityevent.IActivityEventsCallback
            public void notifiyActivityEvent(int i, String str, String str2, String str3) {
                Callback.this.onActivityEvent(i, str, str2, str3);
            }
        };

        public abstract void onActivityEvent(int i, String str, String str2, String str3);
    }

    public static abstract class InnerCallback {
        private final IBinder mStub = new IActivityInnerListenerCallback.Stub() { // from class: com.zte.shared.wrapper.ActivityEventsManagerWrapper.InnerCallback.1
            @Override // com.zte.activityevent.IActivityInnerListenerCallback
            public void onNotifyActivityEvent(int i, String str, String str2, String str3, int i2, int i3, int i4) {
                InnerCallback.this.onActivityEvent(i, str, str2, str3, i2, i3, i4);
            }
        };

        public abstract void onActivityEvent(int i, String str, String str2, String str3, int i2, int i3, int i4);
    }

    static {
        IBinder service = ServiceManager.getService("activityevent");
        if (service != null) {
            mActivityEventsServer = IActivityEventsServer.Stub.asInterface(service);
        } else {
            mActivityEventsServer = null;
            Log.w("", "not have activityevent service");
        }
        mServiceIsLive = mActivityEventsServer != null;
    }

    public static boolean addCallBack(String str, Callback callback, int i) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.addCallBack(str, callback.mStub, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean addInnerCallBack(String str, InnerCallback innerCallback, int i) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.addInnerCallBack(str, innerCallback.mStub, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void collectActivityEventDetail(int i, String str, String str2, String str3, int i2, int i3, int i4, String str4) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                iActivityEventsServer.collectActivityEventDetail(i, str, str2, str3, i2, i3, i4, str4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delCallBack(String str) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.delCallBack(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean delInnerCallBack(String str) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.delInnerCallBack(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static long getFirstInstallTime(String str) {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer == null) {
            return -1L;
        }
        try {
            return iActivityEventsServer.getFirstInstallTime(str);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static List<WindowUtils.VisibleActivity> getVisibleActivity() {
        return WindowUtils.getVisibleActivity();
    }

    public static List<Bundle> getVisiblePackageDates() {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.getVisiblePackageDates();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    public static boolean hasGameAppForeground() {
        IActivityEventsServer iActivityEventsServer = mActivityEventsServer;
        if (iActivityEventsServer != null) {
            try {
                return iActivityEventsServer.hasGameAppForeground();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
