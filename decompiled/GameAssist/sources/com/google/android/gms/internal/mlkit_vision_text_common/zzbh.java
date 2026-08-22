package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.api.Api;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzbh extends zzbd {
    public zzbh() {
        super(4);
    }

    public final zzbh a(Object obj) {
        obj.getClass();
        int i2 = this.f13123b;
        int i3 = i2 + 1;
        Object[] objArr = this.f13122a;
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
            this.f13122a = Arrays.copyOf(objArr, i4);
            this.f13124c = false;
        } else if (this.f13124c) {
            this.f13122a = (Object[]) objArr.clone();
            this.f13124c = false;
        }
        Object[] objArr2 = this.f13122a;
        int i5 = this.f13123b;
        this.f13123b = i5 + 1;
        objArr2[i5] = obj;
        return this;
    }

    public final zzbk b() {
        this.f13124c = true;
        return zzbk.j(this.f13122a, this.f13123b);
    }
}
