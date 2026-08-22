package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public final class DeviceProperties {

    /* renamed from: a, reason: collision with root package name */
    private static Boolean f11256a;

    /* renamed from: b, reason: collision with root package name */
    private static Boolean f11257b;

    /* renamed from: c, reason: collision with root package name */
    private static Boolean f11258c;

    /* renamed from: d, reason: collision with root package name */
    private static Boolean f11259d;

    public static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (f11259d == null) {
            boolean z = false;
            if (PlatformVersion.f() && packageManager.hasSystemFeature("android.hardware.type.automotive")) {
                z = true;
            }
            f11259d = Boolean.valueOf(z);
        }
        return f11259d.booleanValue();
    }

    public static boolean b(Context context) {
        return f(context.getPackageManager());
    }

    public static boolean c(Context context) {
        if (b(context) && !PlatformVersion.e()) {
            return true;
        }
        if (d(context)) {
            return !PlatformVersion.f() || PlatformVersion.i();
        }
        return false;
    }

    public static boolean d(Context context) {
        if (f11257b == null) {
            boolean z = false;
            if (PlatformVersion.d() && context.getPackageManager().hasSystemFeature("cn.google")) {
                z = true;
            }
            f11257b = Boolean.valueOf(z);
        }
        return f11257b.booleanValue();
    }

    public static boolean e(Context context) {
        if (f11258c == null) {
            boolean z = true;
            if (!context.getPackageManager().hasSystemFeature("android.hardware.type.iot") && !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z = false;
            }
            f11258c = Boolean.valueOf(z);
        }
        return f11258c.booleanValue();
    }

    public static boolean f(PackageManager packageManager) {
        if (f11256a == null) {
            boolean z = false;
            if (PlatformVersion.c() && packageManager.hasSystemFeature("android.hardware.type.watch")) {
                z = true;
            }
            f11256a = Boolean.valueOf(z);
        }
        return f11256a.booleanValue();
    }
}
