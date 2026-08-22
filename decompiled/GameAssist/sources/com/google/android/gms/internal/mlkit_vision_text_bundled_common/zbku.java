package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zbku extends zbkr {
    public zbku() {
        super(4);
    }

    public final zbku a(Object obj) {
        int i2 = this.f12852b;
        int i3 = i2 + 1;
        Object[] objArr = this.f12851a;
        int length = objArr.length;
        if (length < i3) {
            int i4 = length + (length >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i2);
                i4 = highestOneBit + highestOneBit;
            }
            if (i4 < 0) {
                i4 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            }
            this.f12851a = Arrays.copyOf(objArr, i4);
            this.f12853c = false;
        } else if (this.f12853c) {
            this.f12851a = (Object[]) objArr.clone();
            this.f12853c = false;
        }
        Object[] objArr2 = this.f12851a;
        int i5 = this.f12852b;
        this.f12852b = i5 + 1;
        objArr2[i5] = obj;
        return this;
    }

    public final zbkx b() {
        this.f12853c = true;
        return zbkx.j(this.f12851a, this.f12852b);
    }
}
