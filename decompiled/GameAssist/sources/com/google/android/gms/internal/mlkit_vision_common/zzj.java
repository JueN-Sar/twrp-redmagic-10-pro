package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.api.Api;
import java.util.Arrays;

/* loaded from: classes.dex */
class zzj extends zzk {

    /* renamed from: a, reason: collision with root package name */
    Object[] f12567a = new Object[4];

    /* renamed from: b, reason: collision with root package name */
    int f12568b = 0;

    /* renamed from: c, reason: collision with root package name */
    boolean f12569c;

    zzj(int i2) {
    }

    private final void b(int i2) {
        Object[] objArr = this.f12567a;
        int length = objArr.length;
        if (length >= i2) {
            if (this.f12569c) {
                this.f12567a = (Object[]) objArr.clone();
                this.f12569c = false;
                return;
            }
            return;
        }
        int i3 = length + (length >> 1) + 1;
        if (i3 < i2) {
            int highestOneBit = Integer.highestOneBit(i2 - 1);
            i3 = highestOneBit + highestOneBit;
        }
        if (i3 < 0) {
            i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        }
        this.f12567a = Arrays.copyOf(objArr, i3);
        this.f12569c = false;
    }

    public final zzj a(Object obj) {
        obj.getClass();
        b(this.f12568b + 1);
        Object[] objArr = this.f12567a;
        int i2 = this.f12568b;
        this.f12568b = i2 + 1;
        objArr[i2] = obj;
        return this;
    }
}
