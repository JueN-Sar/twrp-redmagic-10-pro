package com.google.mlkit.vision.text.chinese;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.text.chinese.ModuleDescriptor;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ChineseTextRecognizerOptions implements TextRecognizerOptionsInterface {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f16105a;

    @VisibleForTesting
    final AtomicReference zza = new AtomicReference();

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Executor f16106a;

        public ChineseTextRecognizerOptions a() {
            return new ChineseTextRecognizerOptions(this.f16106a, null);
        }
    }

    /* synthetic */ ChineseTextRecognizerOptions(Executor executor, zza zzaVar) {
        this.f16105a = executor;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String a() {
        return true != c() ? "play-services-mlkit-text-recognition-chinese" : "text-recognition-chinese";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String b() {
        return "taser_tflite_gocrchinese_and_latin_mbv2_aksara_layout_gcn_mobile";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean c() {
        return TextRecognizerOptionsUtils.a(this.zza, ModuleDescriptor.MODULE_ID);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int d() {
        return c() ? 24316 : 24330;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String e() {
        return true != c() ? "com.google.android.gms.mlkit_ocr_chinese" : ModuleDescriptor.MODULE_ID;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ChineseTextRecognizerOptions) {
            return Objects.a(this.f16105a, ((ChineseTextRecognizerOptions) obj).f16105a);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String f() {
        return "zh";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor g() {
        return this.f16105a;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int h() {
        return 2;
    }

    public int hashCode() {
        return Objects.b(this.f16105a);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String i() {
        return "optional-module-text-chinese";
    }
}
