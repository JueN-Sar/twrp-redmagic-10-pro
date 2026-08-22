package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzo implements DynamiteModule.VersionPolicy.IVersions {

    /* renamed from: a, reason: collision with root package name */
    private final int f11364a;

    public zzo(int i2, int i3) {
        this.f11364a = i2;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int a(Context context, String str, boolean z) {
        return 0;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int b(Context context, String str) {
        return this.f11364a;
    }
}
