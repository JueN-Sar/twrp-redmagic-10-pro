package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes.dex */
final class zzmr extends LazyInstanceMap {
    /* synthetic */ zzmr(zzmq zzmqVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zzme zzmeVar = (zzme) obj;
        MlKitContext c2 = MlKitContext.c();
        return new zzmj(c2.b(), (SharedPrefManager) c2.a(SharedPrefManager.class), new zzmf(MlKitContext.c().b(), zzmeVar), zzmeVar.b());
    }
}
