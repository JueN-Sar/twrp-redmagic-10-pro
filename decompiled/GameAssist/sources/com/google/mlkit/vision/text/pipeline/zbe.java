package com.google.mlkit.vision.text.pipeline;

/* loaded from: classes.dex */
final class zbe extends VkpTextRecognizerOptions {

    /* renamed from: a, reason: collision with root package name */
    private final String f16158a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16159b;

    /* renamed from: c, reason: collision with root package name */
    private final String f16160c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f16161d;

    /* synthetic */ zbe(String str, String str2, String str3, boolean z, zbd zbdVar) {
        this.f16158a = str;
        this.f16159b = str2;
        this.f16160c = str3;
        this.f16161d = z;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions
    final String b() {
        return this.f16158a;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions
    final String c() {
        return this.f16160c;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions
    final String d() {
        return this.f16159b;
    }

    @Override // com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions
    final boolean e() {
        return this.f16161d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VkpTextRecognizerOptions) {
            VkpTextRecognizerOptions vkpTextRecognizerOptions = (VkpTextRecognizerOptions) obj;
            if (this.f16158a.equals(vkpTextRecognizerOptions.b()) && this.f16159b.equals(vkpTextRecognizerOptions.d()) && this.f16160c.equals(vkpTextRecognizerOptions.c()) && this.f16161d == vkpTextRecognizerOptions.e()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (true != this.f16161d ? 1237 : 1231) ^ ((((((this.f16158a.hashCode() ^ 1000003) * 1000003) ^ this.f16159b.hashCode()) * 1000003) ^ this.f16160c.hashCode()) * 1000003);
    }

    public final String toString() {
        return "VkpTextRecognizerOptions{configLabel=" + this.f16158a + ", modelDir=" + this.f16159b + ", languageHint=" + this.f16160c + ", enableLowLatencyInBackground=" + this.f16161d + "}";
    }
}
