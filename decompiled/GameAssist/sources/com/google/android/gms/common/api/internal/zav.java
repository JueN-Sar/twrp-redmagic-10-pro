package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zav implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zaaa f10867c;

    @Override // java.lang.Runnable
    public final void run() {
        Lock lock;
        Lock lock2;
        lock = this.f10867c.f10656m;
        lock.lock();
        try {
            zaaa.v(this.f10867c);
        } finally {
            lock2 = this.f10867c.f10656m;
            lock2.unlock();
        }
    }
}
