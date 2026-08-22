package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzot;
import com.google.android.gms.internal.mlkit_vision_text_common.zzou;
import com.google.android.gms.internal.mlkit_vision_text_common.zzov;
import com.google.android.gms.internal.mlkit_vision_text_common.zzow;
import com.google.android.gms.internal.mlkit_vision_text_common.zzru;
import com.google.android.gms.internal.mlkit_vision_text_common.zzsb;
import com.google.android.gms.internal.mlkit_vision_text_common.zztr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzub;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuf;

@KeepForSdk
/* loaded from: classes.dex */
public final class LoggingUtils {
    static zzsb a(int i2) {
        switch (i2) {
            case 1:
                return zzsb.LATIN;
            case 2:
                return zzsb.LATIN_AND_CHINESE;
            case 3:
                return zzsb.LATIN_AND_DEVANAGARI;
            case 4:
                return zzsb.LATIN_AND_JAPANESE;
            case 5:
                return zzsb.LATIN_AND_KOREAN;
            case 6:
                return zzsb.CREDIT_CARD;
            case 7:
                return zzsb.DOCUMENT;
            case 8:
                return zzsb.PIXEL_AI;
            default:
                return zzsb.TYPE_UNKNOWN;
        }
    }

    static void b(zzuc zzucVar, final boolean z, final zzou zzouVar) {
        zzucVar.e(new zzub() { // from class: com.google.mlkit.vision.text.internal.zzl
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzub
            public final zztr zza() {
                zzow zzowVar = new zzow();
                zzot zzotVar = z ? zzot.TYPE_THICK : zzot.TYPE_THIN;
                zzou zzouVar2 = zzouVar;
                zzowVar.e(zzotVar);
                zzru zzruVar = new zzru();
                zzruVar.b(zzouVar2);
                zzowVar.g(zzruVar.c());
                return zzuf.d(zzowVar);
            }
        }, zzov.ON_DEVICE_TEXT_LOAD);
    }
}
