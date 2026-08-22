package com.google.android.odml.image;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public final class BitmapExtractor {
    public static Bitmap a(MlImage mlImage) {
        zzg h2 = mlImage.h();
        if (h2.zzb().b() == 1) {
            return ((zze) h2).a();
        }
        throw new IllegalArgumentException("Extracting Bitmap from an MlImage created by objects other than Bitmap is not supported");
    }
}
