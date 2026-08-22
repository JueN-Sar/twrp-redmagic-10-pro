package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes.dex */
final class zzsr extends LazyInstanceMap {
    /* synthetic */ zzsr(zzsq zzsqVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zzsb zzsbVar = (zzsb) obj;
        MlKitContext c2 = MlKitContext.c();
        return new zzsh(c2.b(), (SharedPrefManager) c2.a(SharedPrefManager.class), new zzsc(MlKitContext.c().b(), zzsbVar), zzsbVar.b());
    }
}
