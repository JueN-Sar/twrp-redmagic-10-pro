package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class zzmf implements zzmc {

    @VisibleForTesting
    final List zza;

    public zzmf(Context context, zzme zzmeVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzmeVar.c()) {
            arrayList.add(new zzmp(context, zzmeVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmc
    public final void a(zzmb zzmbVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzmc) it.next()).a(zzmbVar);
        }
    }
}
