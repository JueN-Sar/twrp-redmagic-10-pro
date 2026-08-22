package com.google.android.odml.image;

import android.media.Image;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
public class MediaImageExtractor {
    public static Image a(MlImage mlImage) {
        zzg h2 = mlImage.h();
        if (h2.zzb().b() == 3) {
            return ((zzi) h2).a();
        }
        throw new IllegalArgumentException("Extract Media Image from an MlImage created by objects other than Media Image is not supported");
    }
}
