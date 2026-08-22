package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
/* loaded from: classes.dex */
public class InstantApps {

    /* renamed from: a, reason: collision with root package name */
    private static Context f11286a;

    /* renamed from: b, reason: collision with root package name */
    private static Boolean f11287b;

    public static synchronized boolean a(Context context) {
        Boolean bool;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = f11286a;
            if (context2 != null && (bool = f11287b) != null && context2 == applicationContext) {
                return bool.booleanValue();
            }
            f11287b = null;
            if (PlatformVersion.f()) {
                f11287b = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    f11287b = Boolean.TRUE;
                } catch (ClassNotFoundException unused) {
                    f11287b = Boolean.FALSE;
                }
            }
            f11286a = applicationContext;
            return f11287b.booleanValue();
        }
    }
}
