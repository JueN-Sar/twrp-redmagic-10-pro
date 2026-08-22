package com.google.android.gms.common.util.concurrent;

import android.os.Process;

/* loaded from: classes.dex */
final class zza implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final Runnable f11284c;

    public zza(Runnable runnable, int i2) {
        this.f11284c = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(0);
        this.f11284c.run();
    }
}
