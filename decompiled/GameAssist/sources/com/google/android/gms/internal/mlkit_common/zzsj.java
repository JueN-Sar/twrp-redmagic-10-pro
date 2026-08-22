package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* loaded from: classes.dex */
public abstract class zzsj {
    public static zzsi h() {
        zzrv zzrvVar = new zzrv();
        zzrvVar.h("NA");
        zzrvVar.f(false);
        zzrvVar.e(false);
        zzrvVar.d(ModelType.UNKNOWN);
        zzrvVar.b(zzmu.NO_ERROR);
        zzrvVar.a(zzna.UNKNOWN_STATUS);
        zzrvVar.c(0);
        return zzrvVar;
    }

    public abstract int a();

    public abstract ModelType b();

    public abstract zzmu c();

    public abstract zzna d();

    public abstract String e();

    public abstract boolean f();

    public abstract boolean g();
}
