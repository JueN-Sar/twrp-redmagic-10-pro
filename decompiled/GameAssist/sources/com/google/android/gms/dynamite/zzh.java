package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzh implements DynamiteModule.VersionPolicy {
    zzh() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult a(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int a2 = iVersions.a(context, str, false);
        selectionResult.f11359b = a2;
        selectionResult.f11360c = a2 != 0 ? 1 : 0;
        return selectionResult;
    }
}
