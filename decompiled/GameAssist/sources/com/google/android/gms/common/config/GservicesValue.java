package com.google.android.gms.common.config;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class GservicesValue<T> {

    /* renamed from: b, reason: collision with root package name */
    private static final Object f10884b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Object f10885a;

    @KeepForSdk
    @VisibleForTesting
    public void override(@NonNull T t) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.f10885a = t;
        Object obj = f10884b;
        synchronized (obj) {
            synchronized (obj) {
            }
        }
    }

    @KeepForSdk
    @VisibleForTesting
    public void resetOverride() {
        this.f10885a = null;
    }
}
