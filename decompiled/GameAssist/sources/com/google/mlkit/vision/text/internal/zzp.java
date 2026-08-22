package com.google.mlkit.vision.text.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzun;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* loaded from: classes.dex */
public final class zzp extends LazyInstanceMap {

    /* renamed from: b, reason: collision with root package name */
    private final MlKitContext f16133b;

    public zzp(MlKitContext mlKitContext) {
        this.f16133b = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        TextRecognizerOptionsInterface textRecognizerOptionsInterface = (TextRecognizerOptionsInterface) obj;
        zzuc b2 = zzun.b(textRecognizerOptionsInterface.a());
        Context b3 = this.f16133b.b();
        return new TextRecognizerTaskWithResource(b2, (GoogleApiAvailabilityLight.h().b(b3) >= 204700000 || textRecognizerOptionsInterface.c()) ? new zzd(b3, textRecognizerOptionsInterface, b2) : new zze(b3), textRecognizerOptionsInterface);
    }
}
