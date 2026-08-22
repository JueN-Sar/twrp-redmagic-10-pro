package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class ExecutorSelector {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f15934a;

    public ExecutorSelector(Provider provider) {
        this.f15934a = provider;
    }

    public Executor a(Executor executor) {
        return executor != null ? executor : (Executor) this.f15934a.get();
    }
}
