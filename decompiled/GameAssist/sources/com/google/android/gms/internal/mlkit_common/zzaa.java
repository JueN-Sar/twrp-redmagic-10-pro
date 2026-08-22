package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public class zzaa {
    zzaa() {
    }

    static int a(int i2, int i3) {
        if (i3 < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }
        int i4 = i2 + (i2 >> 1) + 1;
        if (i4 < i3) {
            int highestOneBit = Integer.highestOneBit(i3 - 1);
            i4 = highestOneBit + highestOneBit;
        }
        return i4 < 0 ? Api.BaseClientBuilder.API_PRIORITY_OTHER : i4;
    }
}
