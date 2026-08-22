package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_text_common.zzou;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuq;
import com.google.android.gms.internal.mlkit_vision_text_common.zzut;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuv;
import com.google.android.gms.internal.mlkit_vision_text_common.zzux;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuy;
import com.google.android.gms.internal.mlkit_vision_text_common.zzvh;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* loaded from: classes.dex */
final class zzd implements zzm {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16115a;

    /* renamed from: b, reason: collision with root package name */
    private final TextRecognizerOptionsInterface f16116b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f16117c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f16118d;

    /* renamed from: e, reason: collision with root package name */
    private final zzuc f16119e;

    /* renamed from: f, reason: collision with root package name */
    private zzuv f16120f;

    zzd(Context context, TextRecognizerOptionsInterface textRecognizerOptionsInterface, zzuc zzucVar) {
        this.f16115a = context;
        this.f16116b = textRecognizerOptionsInterface;
        this.f16119e = zzucVar;
    }

    private static zzvh b(TextRecognizerOptionsInterface textRecognizerOptionsInterface, String str) {
        int i2 = 1;
        boolean z = (textRecognizerOptionsInterface instanceof zzc) && ((zzc) textRecognizerOptionsInterface).zza();
        String b2 = textRecognizerOptionsInterface.b();
        String i3 = textRecognizerOptionsInterface.i();
        switch (textRecognizerOptionsInterface.h()) {
            case 1:
                i2 = 2;
                break;
            case 2:
                i2 = 3;
                break;
            case 3:
                i2 = 4;
                break;
            case 4:
                i2 = 5;
                break;
            case 5:
                i2 = 6;
                break;
            case 6:
                i2 = 7;
                break;
            case 7:
                i2 = 8;
                break;
            case 8:
                i2 = 9;
                break;
        }
        return new zzvh(b2, i3, str, true, i2 - 1, textRecognizerOptionsInterface.f(), z);
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final Text a(InputImage inputImage) {
        if (this.f16120f == null) {
            zzb();
        }
        zzuv zzuvVar = (zzuv) Preconditions.i(this.f16120f);
        if (!this.f16117c) {
            try {
                zzuvVar.zze();
                this.f16117c = true;
            } catch (RemoteException e2) {
                throw new MlKitException("Failed to init text recognizer ".concat(String.valueOf(this.f16116b.a())), 13, e2);
            }
        }
        try {
            return new Text(zzuvVar.zzd(ImageUtils.b().a(inputImage), new zzuq(inputImage.g(), inputImage.l(), inputImage.h(), CommonConvertUtils.b(inputImage.k()), SystemClock.elapsedRealtime())), inputImage.f());
        } catch (RemoteException e3) {
            throw new MlKitException("Failed to run text recognizer ".concat(String.valueOf(this.f16116b.a())), 13, e3);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final void zzb() {
        zzuv zzd;
        if (this.f16120f != null) {
            return;
        }
        try {
            TextRecognizerOptionsInterface textRecognizerOptionsInterface = this.f16116b;
            boolean z = textRecognizerOptionsInterface instanceof zzb;
            String zza = z ? ((zzb) textRecognizerOptionsInterface).zza() : null;
            if (this.f16116b.c()) {
                Log.d("DecoupledTextDelegate", "Start loading thick OCR module.");
                zzd = zzux.zza(DynamiteModule.e(this.f16115a, DynamiteModule.f11341c, this.f16116b.e()).d("com.google.mlkit.vision.text.bundled.common.BundledTextRecognizerCreator")).zze(ObjectWrapper.wrap(this.f16115a), b(this.f16116b, zza));
            } else if (z) {
                Log.d("DecoupledTextDelegate", "Start loading custom OCR module.");
                zzd = zzut.zza(DynamiteModule.e(this.f16115a, DynamiteModule.f11340b, this.f16116b.e()).d("com.google.android.gms.vision.text.mlkit.CommonTextRecognizerCreator")).zzd(ObjectWrapper.wrap(this.f16115a), null, b(this.f16116b, zza));
            } else {
                Log.d("DecoupledTextDelegate", "Start loading thin OCR module.");
                zzuy zza2 = zzux.zza(DynamiteModule.e(this.f16115a, DynamiteModule.f11340b, this.f16116b.e()).d("com.google.android.gms.vision.text.mlkit.TextRecognizerCreator"));
                zzd = this.f16116b.h() == 1 ? zza2.zzd(ObjectWrapper.wrap(this.f16115a)) : zza2.zze(ObjectWrapper.wrap(this.f16115a), b(this.f16116b, zza));
            }
            this.f16120f = zzd;
            LoggingUtils.b(this.f16119e, this.f16116b.c(), zzou.NO_ERROR);
        } catch (RemoteException e2) {
            LoggingUtils.b(this.f16119e, this.f16116b.c(), zzou.OPTIONAL_MODULE_INIT_ERROR);
            throw new MlKitException("Failed to create text recognizer ".concat(String.valueOf(this.f16116b.a())), 13, e2);
        } catch (DynamiteModule.LoadingException e3) {
            LoggingUtils.b(this.f16119e, this.f16116b.c(), zzou.OPTIONAL_MODULE_NOT_AVAILABLE);
            if (this.f16116b.c()) {
                throw new MlKitException(String.format("Failed to load text module %s. %s", this.f16116b.a(), e3.getMessage()), 13, e3);
            }
            if (!this.f16118d) {
                OptionalModuleUtils.c(this.f16115a, TextOptionalModuleUtils.a(this.f16116b));
                this.f16118d = true;
            }
            throw new MlKitException("Waiting for the text optional module to be downloaded. Please wait.", 14);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzm
    public final void zzc() {
        zzuv zzuvVar = this.f16120f;
        if (zzuvVar != null) {
            try {
                zzuvVar.zzf();
            } catch (RemoteException e2) {
                Log.e("DecoupledTextDelegate", "Failed to release text recognizer ".concat(String.valueOf(this.f16116b.a())), e2);
            }
            this.f16120f = null;
        }
        this.f16117c = false;
    }
}
