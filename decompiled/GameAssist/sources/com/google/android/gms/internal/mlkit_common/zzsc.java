package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class zzsc implements zzrz {

    @VisibleForTesting
    final List zza;

    public zzsc(Context context, zzsb zzsbVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzsbVar.c()) {
            arrayList.add(new zzsp(context, zzsbVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzrz
    public final void a(zzry zzryVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzrz) it.next()).a(zzryVar);
        }
    }
}
