package com.google.mlkit.vision.text.devanagari;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.text.devanagari.ModuleDescriptor;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class DevanagariTextRecognizerOptions implements TextRecognizerOptionsInterface {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f16107a;

    @VisibleForTesting
    final AtomicReference zza = new AtomicReference();

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Executor f16108a;

        public DevanagariTextRecognizerOptions a() {
            return new DevanagariTextRecognizerOptions(this.f16108a, null);
        }
    }

    /* synthetic */ DevanagariTextRecognizerOptions(Executor executor, zza zzaVar) {
        this.f16107a = executor;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String a() {
        return true != c() ? "play-services-mlkit-text-recognition-devanagari" : "text-recognition-devanagari";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String b() {
        return "taser_tflite_gocrdevanagari_and_latin_mbv2_aksara_layout_gcn_mobile";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean c() {
        return TextRecognizerOptionsUtils.a(this.zza, ModuleDescriptor.MODULE_ID);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int d() {
        return c() ? 24320 : 24331;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String e() {
        return true != c() ? "com.google.android.gms.mlkit_ocr_devanagari" : ModuleDescriptor.MODULE_ID;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DevanagariTextRecognizerOptions) {
            return Objects.a(this.f16107a, ((DevanagariTextRecognizerOptions) obj).f16107a);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String f() {
        return "hi";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor g() {
        return this.f16107a;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int h() {
        return 3;
    }

    public int hashCode() {
        return Objects.b(this.f16107a);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String i() {
        return "optional-module-text-devanagari";
    }
}
