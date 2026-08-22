package com.google.android.odml.image;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ByteBufferExtractor {
    public static ByteBuffer a(MlImage mlImage) {
        zzg h2 = mlImage.h();
        if (h2.zzb().b() == 2) {
            return ((zzf) h2).a().asReadOnlyBuffer();
        }
        throw new IllegalArgumentException("Extract ByteBuffer from an MlImage created by objects other than Bytebuffer is not supported");
    }
}
