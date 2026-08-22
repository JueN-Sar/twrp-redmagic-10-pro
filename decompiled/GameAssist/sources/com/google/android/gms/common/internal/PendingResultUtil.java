package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

@KeepForSdk
/* loaded from: classes.dex */
public class PendingResultUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final zas f11022a = new zao();

    @KeepForSdk
    public interface ResultConverter<R extends Result, T> {
        Object a(Result result);
    }
}
