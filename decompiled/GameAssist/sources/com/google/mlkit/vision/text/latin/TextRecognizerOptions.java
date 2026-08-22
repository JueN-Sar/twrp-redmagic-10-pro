package com.google.mlkit.vision.text.latin;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.text.latin.ModuleDescriptor;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class TextRecognizerOptions implements TextRecognizerOptionsInterface {

    /* renamed from: c, reason: collision with root package name */
    public static final TextRecognizerOptions f16143c = new Builder().a();

    /* renamed from: a, reason: collision with root package name */
    private final Executor f16144a;

    @VisibleForTesting
    final AtomicReference zza = new AtomicReference();

    /* renamed from: b, reason: collision with root package name */
    private final String f16145b = "taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile";

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Executor f16146a;

        public TextRecognizerOptions a() {
            return new TextRecognizerOptions(this.f16146a, "taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile");
        }
    }

    public TextRecognizerOptions(Executor executor, String str) {
        this.f16144a = executor;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String a() {
        return true != c() ? "play-services-mlkit-text-recognition" : "text-recognition";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String b() {
        return this.f16145b;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean c() {
        return TextRecognizerOptionsUtils.a(this.zza, ModuleDescriptor.MODULE_ID);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int d() {
        return c() ? 24317 : 24306;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String e() {
        return true != c() ? "com.google.android.gms.vision.ocr" : ModuleDescriptor.MODULE_ID;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextRecognizerOptions) {
            return Objects.a(this.f16144a, ((TextRecognizerOptions) obj).f16144a);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String f() {
        return "en";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor g() {
        return this.f16144a;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int h() {
        return 1;
    }

    public int hashCode() {
        return Objects.b(this.f16144a);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String i() {
        return "optional-module-text-latin";
    }
}
