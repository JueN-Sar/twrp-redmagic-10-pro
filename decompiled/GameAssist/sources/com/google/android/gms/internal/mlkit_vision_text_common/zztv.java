package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class zztv implements zzts {

    @VisibleForTesting
    final List zza;

    public zztv(Context context, zztu zztuVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zztuVar.c()) {
            arrayList.add(new zzuk(context, zztuVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzts
    public final void a(zztr zztrVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzts) it.next()).a(zztrVar);
        }
    }
}
