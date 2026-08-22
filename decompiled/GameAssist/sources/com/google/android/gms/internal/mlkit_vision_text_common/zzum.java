package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes.dex */
final class zzum extends LazyInstanceMap {
    /* synthetic */ zzum(zzul zzulVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zztu zztuVar = (zztu) obj;
        MlKitContext c2 = MlKitContext.c();
        return new zzuc(c2.b(), (SharedPrefManager) c2.a(SharedPrefManager.class), new zztv(MlKitContext.c().b(), zztuVar), zztuVar.b());
    }
}
