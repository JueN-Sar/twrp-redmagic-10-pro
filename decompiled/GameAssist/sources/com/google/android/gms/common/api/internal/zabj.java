package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class zabj {

    /* renamed from: a, reason: collision with root package name */
    private static final ExecutorService f10755a = com.google.android.gms.internal.base.zat.a().a(2, new NumberedThreadFactory("GAC_Executor"), 2);

    public static ExecutorService a() {
        return f10755a;
    }
}
