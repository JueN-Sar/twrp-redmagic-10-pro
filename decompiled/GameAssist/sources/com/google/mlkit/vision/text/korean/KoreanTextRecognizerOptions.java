package com.google.mlkit.vision.text.korean;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.text.korean.ModuleDescriptor;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class KoreanTextRecognizerOptions implements TextRecognizerOptionsInterface {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f16141a;

    @VisibleForTesting
    final AtomicReference zza = new AtomicReference();

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Executor f16142a;

        public KoreanTextRecognizerOptions a() {
            return new KoreanTextRecognizerOptions(this.f16142a, null);
        }
    }

    /* synthetic */ KoreanTextRecognizerOptions(Executor executor, zza zzaVar) {
        this.f16141a = executor;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String a() {
        return true != c() ? "play-services-mlkit-text-recognition-korean" : "text-recognition-korean";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String b() {
        return "taser_tflite_gocrkorean_and_latin_mbv2_aksara_layout_gcn_mobile";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean c() {
        return TextRecognizerOptionsUtils.a(this.zza, ModuleDescriptor.MODULE_ID);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int d() {
        return c() ? 24319 : 24333;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String e() {
        return true != c() ? "com.google.android.gms.mlkit_ocr_korean" : ModuleDescriptor.MODULE_ID;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof KoreanTextRecognizerOptions) {
            return Objects.a(this.f16141a, ((KoreanTextRecognizerOptions) obj).f16141a);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String f() {
        return "ko";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor g() {
        return this.f16141a;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int h() {
        return 5;
    }

    public int hashCode() {
        return Objects.b(this.f16141a);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String i() {
        return "optional-module-text-korean";
    }
}
