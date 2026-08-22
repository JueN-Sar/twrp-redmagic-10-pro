package com.google.mlkit.vision.text.japanese;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.text.japanese.ModuleDescriptor;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class JapaneseTextRecognizerOptions implements TextRecognizerOptionsInterface {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f16139a;

    @VisibleForTesting
    final AtomicReference zza = new AtomicReference();

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Executor f16140a;

        public JapaneseTextRecognizerOptions a() {
            return new JapaneseTextRecognizerOptions(this.f16140a, null);
        }
    }

    /* synthetic */ JapaneseTextRecognizerOptions(Executor executor, zza zzaVar) {
        this.f16139a = executor;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String a() {
        return true != c() ? "play-services-mlkit-text-recognition-japanese" : "text-recognition-japanese";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String b() {
        return "taser_tflite_gocrjapanese_and_latin_mbv2_aksara_layout_gcn_mobile";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean c() {
        return TextRecognizerOptionsUtils.a(this.zza, ModuleDescriptor.MODULE_ID);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int d() {
        return c() ? 24318 : 24332;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String e() {
        return true != c() ? "com.google.android.gms.mlkit_ocr_japanese" : ModuleDescriptor.MODULE_ID;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JapaneseTextRecognizerOptions) {
            return Objects.a(this.f16139a, ((JapaneseTextRecognizerOptions) obj).f16139a);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String f() {
        return "ja";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor g() {
        return this.f16139a;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int h() {
        return 4;
    }

    public int hashCode() {
        return Objects.b(this.f16139a);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String i() {
        return "optional-module-text-japanese";
    }
}
