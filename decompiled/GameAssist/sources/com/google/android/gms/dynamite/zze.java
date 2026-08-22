package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zze implements DynamiteModule.VersionPolicy.IVersions {
    zze() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int a(Context context, String str, boolean z) {
        return DynamiteModule.f(context, str, z);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int b(Context context, String str) {
        return DynamiteModule.a(context, str);
    }
}
