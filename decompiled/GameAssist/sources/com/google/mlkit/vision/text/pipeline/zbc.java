package com.google.mlkit.vision.text.pipeline;

import com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions;

/* loaded from: classes.dex */
final class zbc extends VkpTextRecognizerOptions.Builder {

    /* renamed from: a, reason: collision with root package name */
    private String f16153a;

    /* renamed from: b, reason: collision with root package name */
    private String f16154b;

    /* renamed from: c, reason: collision with root package name */
    private String f16155c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f16156d;

    /* renamed from: e, reason: collision with root package name */
    private byte f16157e;

    zbc() {
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions.Builder
    public final VkpTextRecognizerOptions a() {
        String str;
        String str2;
        String str3;
        if (this.f16157e == 1 && (str = this.f16153a) != null && (str2 = this.f16154b) != null && (str3 = this.f16155c) != null) {
            return new zbe(str, str2, str3, this.f16156d, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f16153a == null) {
            sb.append(" configLabel");
        }
        if (this.f16154b == null) {
            sb.append(" modelDir");
        }
        if (this.f16155c == null) {
            sb.append(" languageHint");
        }
        if (this.f16157e == 0) {
            sb.append(" enableLowLatencyInBackground");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions.Builder
    public final VkpTextRecognizerOptions.Builder b(boolean z) {
        this.f16156d = z;
        this.f16157e = (byte) 1;
        return this;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions.Builder
    public final VkpTextRecognizerOptions.Builder c(String str) {
        if (str == null) {
            throw new NullPointerException("Null languageHint");
        }
        this.f16155c = str;
        return this;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions.Builder
    public final VkpTextRecognizerOptions.Builder d(String str) {
        if (str == null) {
            throw new NullPointerException("Null modelDir");
        }
        this.f16154b = str;
        return this;
    }

    final VkpTextRecognizerOptions.Builder e(String str) {
        if (str == null) {
            throw new NullPointerException("Null configLabel");
        }
        this.f16153a = str;
        return this;
    }
}
