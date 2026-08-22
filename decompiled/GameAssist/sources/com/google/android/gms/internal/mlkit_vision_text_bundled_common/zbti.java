package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zbti extends IOException {
    zbti() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zbti(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    zbti(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
