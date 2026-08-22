package com.zte.timeutil.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class ThreadFactoryWithName implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final String f18116a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicInteger f18117b;

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f18116a + "#" + this.f18117b.getAndIncrement());
    }
}
