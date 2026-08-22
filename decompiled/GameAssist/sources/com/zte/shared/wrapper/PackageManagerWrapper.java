package com.zte.shared.wrapper;

import android.content.Context;
import android.util.Log;

/* loaded from: classes2.dex */
public class PackageManagerWrapper {
    public static boolean isPackageInstalled(Context context, String str, int i2) {
        boolean z = false;
        try {
            if (context.getPackageManager().getPackageInfoAsUser(str, 0, i2) != null) {
                z = true;
            }
        } catch (Exception unused) {
        }
        Log.i("PackageManagerWrapper", "isPackageInstalled: pkgName= " + str);
        return z;
    }
}
