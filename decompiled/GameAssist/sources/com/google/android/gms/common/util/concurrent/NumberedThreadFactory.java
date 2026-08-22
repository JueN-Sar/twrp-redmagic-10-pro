package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
/* loaded from: classes.dex */
public class NumberedThreadFactory implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final String f11281a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicInteger f11282b = new AtomicInteger();

    /* renamed from: c, reason: collision with root package name */
    private final ThreadFactory f11283c = Executors.defaultThreadFactory();

    public NumberedThreadFactory(String str) {
        Preconditions.j(str, "Name must not be null");
        this.f11281a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.f11283c.newThread(new zza(runnable, 0));
        newThread.setName(this.f11281a + "[" + this.f11282b.getAndIncrement() + "]");
        return newThread;
    }
}
