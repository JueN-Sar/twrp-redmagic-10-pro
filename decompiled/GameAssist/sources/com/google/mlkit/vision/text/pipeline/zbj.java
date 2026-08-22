package com.google.mlkit.vision.text.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcr;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbnx;
import com.google.android.libraries.vision.visionkit.pipeline.zbbd;
import com.google.android.libraries.vision.visionkit.pipeline.zbbe;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
final class zbj {
    static zbbe a(ByteBuffer byteBuffer, zbnx zbnxVar) {
        zbbd zbbdVar = new zbbd();
        zbbdVar.a(byteBuffer.array());
        zbbdVar.f(b(zbnxVar.R()));
        zbbdVar.b(new zbcr(zbnxVar.T(), zbnxVar.G()));
        zbbdVar.c(zbnxVar.W() * 1000);
        zbbdVar.e(2);
        return zbbdVar.d();
    }

    static int b(int i2) {
        if (i2 == 1) {
            return 4;
        }
        if (i2 != 2) {
            return i2 != 3 ? 1 : 2;
        }
        return 3;
    }
}
