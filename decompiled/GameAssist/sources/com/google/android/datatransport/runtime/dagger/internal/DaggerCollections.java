package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.gms.common.api.Api;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class DaggerCollections {
    private static int a(int i2) {
        return i2 < 3 ? i2 + 1 : i2 < 1073741824 ? (int) ((i2 / 0.75f) + 1.0f) : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    static HashSet b(int i2) {
        return new HashSet(a(i2));
    }

    public static LinkedHashMap c(int i2) {
        return new LinkedHashMap(a(i2));
    }
}
