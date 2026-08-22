package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzl implements DynamiteModule.VersionPolicy {
    zzl() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult a(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        int a2;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int b2 = iVersions.b(context, str);
        selectionResult.f11358a = b2;
        int i2 = 1;
        int i3 = 0;
        if (b2 != 0) {
            a2 = iVersions.a(context, str, false);
            selectionResult.f11359b = a2;
        } else {
            a2 = iVersions.a(context, str, true);
            selectionResult.f11359b = a2;
        }
        int i4 = selectionResult.f11358a;
        if (i4 != 0) {
            i3 = i4;
        } else if (a2 == 0) {
            i2 = 0;
            selectionResult.f11360c = i2;
            return selectionResult;
        }
        if (a2 < i3) {
            i2 = -1;
        }
        selectionResult.f11360c = i2;
        return selectionResult;
    }
}
