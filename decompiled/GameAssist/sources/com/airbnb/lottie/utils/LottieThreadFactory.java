package com.airbnb.lottie.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class LottieThreadFactory implements ThreadFactory {

    /* renamed from: d, reason: collision with root package name */
    private static final AtomicInteger f9914d = new AtomicInteger(1);

    /* renamed from: a, reason: collision with root package name */
    private final ThreadGroup f9915a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicInteger f9916b = new AtomicInteger(1);

    /* renamed from: c, reason: collision with root package name */
    private final String f9917c;

    public LottieThreadFactory() {
        SecurityManager securityManager = System.getSecurityManager();
        this.f9915a = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
        this.f9917c = "lottie-" + f9914d.getAndIncrement() + "-thread-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f9915a, runnable, this.f9917c + this.f9916b.getAndIncrement(), 0L);
        thread.setDaemon(false);
        thread.setPriority(10);
        return thread;
    }
}
