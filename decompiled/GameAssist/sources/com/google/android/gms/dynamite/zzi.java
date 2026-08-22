package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzi implements DynamiteModule.VersionPolicy {
    zzi() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult a(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.f11358a = iVersions.b(context, str);
        int i2 = 1;
        int a2 = iVersions.a(context, str, true);
        selectionResult.f11359b = a2;
        int i3 = selectionResult.f11358a;
        if (i3 == 0) {
            i3 = 0;
            if (a2 == 0) {
                i2 = 0;
                selectionResult.f11360c = i2;
                return selectionResult;
            }
        }
        if (i3 >= a2) {
            i2 = -1;
        }
        selectionResult.f11360c = i2;
        return selectionResult;
    }
}
