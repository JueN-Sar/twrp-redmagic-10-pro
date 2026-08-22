package com.google.mlkit.vision.text.pipeline;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class VkpTextRecognizerOptions {

    @KeepForSdk
    public static abstract class Builder {
        public abstract VkpTextRecognizerOptions a();

        public abstract Builder b(boolean z);

        public abstract Builder c(String str);

        public abstract Builder d(String str);
    }

    public static Builder a(String str, String str2, String str3) {
        zbc zbcVar = new zbc();
        zbcVar.e(str);
        if (str2 == null) {
            str2 = "mlkit-google-ocr-models";
        }
        zbcVar.d(str2);
        zbcVar.c(str3);
        zbcVar.b(false);
        return zbcVar;
    }

    abstract String b();

    abstract String c();

    abstract String d();

    abstract boolean e();
}
