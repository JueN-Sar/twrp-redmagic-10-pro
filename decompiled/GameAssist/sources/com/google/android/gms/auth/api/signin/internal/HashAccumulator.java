package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class HashAccumulator {

    /* renamed from: a, reason: collision with root package name */
    private int f10477a = 1;

    public HashAccumulator a(Object obj) {
        this.f10477a = (this.f10477a * 31) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    public int b() {
        return this.f10477a;
    }

    public final HashAccumulator c(boolean z) {
        this.f10477a = (this.f10477a * 31) + (z ? 1 : 0);
        return this;
    }
}
