package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zbwa extends zbwh {
    zbwa() {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwh
    public final void a() {
        if (!j()) {
            for (int i2 = 0; i2 < c(); i2++) {
                ((zbtt) ((zbwb) g(i2)).c()).k();
            }
            Iterator it = d().iterator();
            while (it.hasNext()) {
                ((zbtt) ((Map.Entry) it.next()).getKey()).k();
            }
        }
        super.a();
    }
}
