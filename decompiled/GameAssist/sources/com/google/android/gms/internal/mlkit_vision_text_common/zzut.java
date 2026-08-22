package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes.dex */
public abstract class zzut extends zzb implements zzuu {
    public static zzuu zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator");
        return queryLocalInterface instanceof zzuu ? (zzuu) queryLocalInterface : new zzus(iBinder);
    }
}
