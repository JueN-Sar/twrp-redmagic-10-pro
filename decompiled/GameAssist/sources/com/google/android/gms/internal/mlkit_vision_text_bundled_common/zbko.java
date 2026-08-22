package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class zbko {

    /* renamed from: a, reason: collision with root package name */
    private final zbkm f12848a;

    private zbko(zbkm zbkmVar) {
        zbkd zbkdVar = zbkc.f12841b;
        this.f12848a = zbkmVar;
    }

    public static zbko a(String str) {
        return new zbko(new zbkm("#vk "));
    }

    public final List b(CharSequence charSequence) {
        charSequence.getClass();
        zbkl zbklVar = new zbkl(this.f12848a, this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zbklVar.hasNext()) {
            arrayList.add((String) zbklVar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
