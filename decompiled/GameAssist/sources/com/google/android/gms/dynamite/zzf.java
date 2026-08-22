package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult a(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int a2 = iVersions.a(context, str, true);
        selectionResult.f11359b = a2;
        if (a2 != 0) {
            selectionResult.f11360c = 1;
        } else {
            int b2 = iVersions.b(context, str);
            selectionResult.f11358a = b2;
            if (b2 != 0) {
                selectionResult.f11360c = -1;
            }
        }
        return selectionResult;
    }
}
