package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;

/* loaded from: classes.dex */
public final class zzah {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11092a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static boolean f11093b;

    /* renamed from: c, reason: collision with root package name */
    private static String f11094c;

    /* renamed from: d, reason: collision with root package name */
    private static int f11095d;

    public static int a(Context context) {
        c(context);
        return f11095d;
    }

    public static String b(Context context) {
        c(context);
        return f11094c;
    }

    private static void c(Context context) {
        Bundle bundle;
        synchronized (f11092a) {
            try {
                if (f11093b) {
                    return;
                }
                f11093b = true;
                try {
                    bundle = Wrappers.a(context).a(context.getPackageName(), 128).metaData;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.wtf("MetadataValueReader", "This should never happen.", e2);
                }
                if (bundle == null) {
                    return;
                }
                f11094c = bundle.getString("com.google.app.id");
                f11095d = bundle.getInt("com.google.android.gms.version");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
