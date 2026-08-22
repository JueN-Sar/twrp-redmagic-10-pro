package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_text_common.zzot;
import com.google.android.gms.internal.mlkit_vision_text_common.zzov;
import com.google.android.gms.internal.mlkit_vision_text_common.zzow;
import com.google.android.gms.internal.mlkit_vision_text_common.zzrx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzsa;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuf;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class zzn extends MobileVisionBase implements TextRecognizer {

    /* renamed from: n, reason: collision with root package name */
    private final TextRecognizerOptionsInterface f16130n;

    zzn(TextRecognizerTaskWithResource textRecognizerTaskWithResource, Executor executor, zzuc zzucVar, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(textRecognizerTaskWithResource, executor);
        this.f16130n = textRecognizerOptionsInterface;
        zzow zzowVar = new zzow();
        zzowVar.e(textRecognizerOptionsInterface.c() ? zzot.TYPE_THICK : zzot.TYPE_THIN);
        zzrx zzrxVar = new zzrx();
        zzsa zzsaVar = new zzsa();
        zzsaVar.a(LoggingUtils.a(textRecognizerOptionsInterface.h()));
        zzrxVar.e(zzsaVar.c());
        zzowVar.h(zzrxVar.f());
        zzucVar.c(zzuf.e(zzowVar, 1), zzov.ON_DEVICE_TEXT_CREATE);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    public final Task X(InputImage inputImage) {
        return super.p(inputImage);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] e() {
        return TextOptionalModuleUtils.a(this.f16130n);
    }
}
