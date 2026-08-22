package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.text.Text;

/* loaded from: classes.dex */
final class zze implements zzm {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16121a;

    /* renamed from: b, reason: collision with root package name */
    private final com.google.android.gms.internal.mlkit_vision_text_common.zzp f16122b = new com.google.android.gms.internal.mlkit_vision_text_common.zzp(null);

    /* renamed from: c, reason: collision with root package name */
    private boolean f16123c;

    /* renamed from: d, reason: collision with root package name */
    private com.google.android.gms.internal.mlkit_vision_text_common.zzh f16124d;

    zze(Context context) {
        this.f16121a = context;
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final Text a(InputImage inputImage) {
        Bitmap d2;
        int i2;
        if (this.f16124d == null) {
            zzb();
        }
        if (this.f16124d == null) {
            throw new MlKitException("Waiting for the text recognition module to be downloaded. Please wait.", 14);
        }
        if (inputImage.g() == -1) {
            d2 = inputImage.d();
            i2 = CommonConvertUtils.b(inputImage.k());
        } else {
            d2 = ImageConvertUtils.e().d(inputImage);
            i2 = 0;
        }
        int i3 = i2;
        try {
            return zzk.a(((com.google.android.gms.internal.mlkit_vision_text_common.zzh) Preconditions.i(this.f16124d)).zze(ObjectWrapper.wrap(d2), new com.google.android.gms.internal.mlkit_vision_text_common.zzd(inputImage.l(), inputImage.h(), 0, 0L, i3)), inputImage.f());
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to run legacy text recognizer.", 13, e2);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final void zzb() {
        if (this.f16124d != null) {
            return;
        }
        try {
            com.google.android.gms.internal.mlkit_vision_text_common.zzh zzd = com.google.android.gms.internal.mlkit_vision_text_common.zzj.zza(DynamiteModule.e(this.f16121a, DynamiteModule.f11340b, "com.google.android.gms.vision.dynamite").d("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")).zzd(ObjectWrapper.wrap(this.f16121a), this.f16122b);
            this.f16124d = zzd;
            if (zzd != null || this.f16123c) {
                return;
            }
            Log.d("LegacyTextDelegate", "Request OCR optional module download.");
            OptionalModuleUtils.a(this.f16121a, "ocr");
            this.f16123c = true;
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to create legacy text recognizer.", 13, e2);
        } catch (DynamiteModule.LoadingException e3) {
            throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e3);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final void zzc() {
        com.google.android.gms.internal.mlkit_vision_text_common.zzh zzhVar = this.f16124d;
        if (zzhVar != null) {
            try {
                zzhVar.zzd();
            } catch (RemoteException e2) {
                Log.e("LegacyTextDelegate", "Failed to release legacy text recognizer.", e2);
            }
            this.f16124d = null;
        }
    }
}
