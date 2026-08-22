package com.google.mlkit.vision.text.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaaw;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class zbg {
    static String a(List list) {
        Iterator it = list.iterator();
        float f2 = 0.0f;
        String str = "und";
        while (it.hasNext()) {
            zbaaw zbaawVar = (zbaaw) it.next();
            if (f2 < zbaawVar.E()) {
                f2 = zbaawVar.E();
                str = zbaawVar.H();
            }
        }
        return str;
    }
}
