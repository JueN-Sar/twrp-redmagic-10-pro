package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes.dex */
public abstract class zzux extends zzb implements zzuy {
    public static zzuy zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
        return queryLocalInterface instanceof zzuy ? (zzuy) queryLocalInterface : new zzuw(iBinder);
    }
}
