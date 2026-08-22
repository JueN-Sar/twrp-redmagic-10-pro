package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
/* loaded from: classes.dex */
public final class TextRecognizerOptionsUtils {
    public static boolean a(AtomicReference atomicReference, String str) {
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean z = DynamiteModule.a(MlKitContext.c().b(), str) > 0;
        atomicReference.set(Boolean.valueOf(z));
        return z;
    }
}
