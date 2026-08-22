package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzun;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* loaded from: classes.dex */
public final class zzo {

    /* renamed from: a, reason: collision with root package name */
    private final zzp f16131a;

    /* renamed from: b, reason: collision with root package name */
    private final ExecutorSelector f16132b;

    zzo(zzp zzpVar, ExecutorSelector executorSelector) {
        this.f16131a = zzpVar;
        this.f16132b = executorSelector;
    }

    public final TextRecognizer a(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        return new zzn((TextRecognizerTaskWithResource) this.f16131a.b(textRecognizerOptionsInterface), this.f16132b.a(textRecognizerOptionsInterface.g()), zzun.b(textRecognizerOptionsInterface.a()), textRecognizerOptionsInterface);
    }
}
