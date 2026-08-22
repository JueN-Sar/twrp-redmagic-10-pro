package com.android.systemui.shared.system;

import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.List;

/* loaded from: classes2.dex */
public class PackageManagerWrapper {
    public static final String ACTION_PREFERRED_ACTIVITY_CHANGED = "android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED";
    private static final PackageManagerWrapper sInstance = new PackageManagerWrapper();
    private static final IPackageManager mIPackageManager = AppGlobals.getPackageManager();

    private PackageManagerWrapper() {
    }

    public static PackageManagerWrapper getInstance() {
        return sInstance;
    }

    public static boolean isPackageInstalled(Context context, String str, int i) {
        boolean z = false;
        try {
            if (context.getPackageManager().getPackageInfoAsUser(str, 0, i) != null) {
                z = true;
            }
        } catch (Exception unused) {
        }
        Log.i("PackageManagerWrapper", "isPackageInstalled: pkgName= " + str);
        return z;
    }

    public ActivityInfo getActivityInfo(ComponentName componentName, int i) {
        try {
            return mIPackageManager.getActivityInfo(componentName, 128, i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ComponentName getHomeActivities(List<ResolveInfo> list) {
        try {
            return mIPackageManager.getHomeActivities(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResolveInfo resolveActivity(Intent intent, int i) {
        try {
            return mIPackageManager.resolveIntent(intent, intent.resolveTypeIfNeeded(AppGlobals.getInitialApplication().getContentResolver()), i, UserHandle.getCallingUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
