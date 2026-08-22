package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbwk extends RuntimeException {
    public zbwk(zbvm zbvmVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zbuq a() {
        return new zbuq(getMessage());
    }
}
