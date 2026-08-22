package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
/* loaded from: classes.dex */
public class NamedThreadFactory implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final String f11279a;

    /* renamed from: b, reason: collision with root package name */
    private final ThreadFactory f11280b = Executors.defaultThreadFactory();

    public NamedThreadFactory(String str) {
        Preconditions.j(str, "Name must not be null");
        this.f11279a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.f11280b.newThread(new zza(runnable, 0));
        newThread.setName(this.f11279a);
        return newThread;
    }
}
