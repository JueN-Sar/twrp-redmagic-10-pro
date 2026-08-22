package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class zzus extends zza implements zzuu {
    zzus(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ICommonTextRecognizerCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzuu
    public final zzuv zzd(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, zzvh zzvhVar) {
        Parcel zza = zza();
        zzc.b(zza, iObjectWrapper);
        zzuv zzuvVar = null;
        zzc.b(zza, null);
        zzc.a(zza, zzvhVar);
        Parcel zzb = zzb(1, zza);
        IBinder readStrongBinder = zzb.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizer");
            zzuvVar = queryLocalInterface instanceof zzuv ? (zzuv) queryLocalInterface : new zzuv(readStrongBinder);
        }
        zzb.recycle();
        return zzuvVar;
    }
}
