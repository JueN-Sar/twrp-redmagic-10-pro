package com.google.android.gms.common.wrappers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class PackageManagerWrapper {

    /* renamed from: a, reason: collision with root package name */
    protected final Context f11288a;

    public PackageManagerWrapper(Context context) {
        this.f11288a = context;
    }

    public ApplicationInfo a(String str, int i2) {
        return this.f11288a.getPackageManager().getApplicationInfo(str, i2);
    }

    public CharSequence b(String str) {
        Context context = this.f11288a;
        return context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0));
    }

    public PackageInfo c(String str, int i2) {
        return this.f11288a.getPackageManager().getPackageInfo(str, i2);
    }
}
