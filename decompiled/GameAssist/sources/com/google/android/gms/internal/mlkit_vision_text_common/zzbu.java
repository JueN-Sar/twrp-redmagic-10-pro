package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public final class zzbu {
    public static List a(List list, zzu zzuVar) {
        return list instanceof RandomAccess ? new zzbr(list, zzuVar) : new zzbt(list, zzuVar);
    }
}
