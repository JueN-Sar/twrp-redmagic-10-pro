package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class zaco {

    /* renamed from: a, reason: collision with root package name */
    private static final ExecutorService f10813a = com.google.android.gms.internal.base.zat.a().b(new NumberedThreadFactory("GAC_Transform"), 1);

    public static ExecutorService a() {
        return f10813a;
    }
}
