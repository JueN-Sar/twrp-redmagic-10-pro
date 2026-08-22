package com.airbnb.lottie.network;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public enum FileExtension {
    JSON(".json"),
    ZIP(".zip"),
    GZIP(".gz");

    public final String extension;

    FileExtension(String str) {
        this.extension = str;
    }

    public String d() {
        return ".temp" + this.extension;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.extension;
    }
}
