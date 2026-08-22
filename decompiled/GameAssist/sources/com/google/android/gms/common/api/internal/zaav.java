package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
abstract class zaav implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zaaw f10687c;

    protected abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        Lock lock;
        Lock lock2;
        zabi zabiVar;
        lock = this.f10687c.f10689b;
        lock.lock();
        try {
            try {
                if (!Thread.interrupted()) {
                    a();
                }
            } catch (RuntimeException e2) {
                zabiVar = this.f10687c.f10688a;
                zabiVar.n(e2);
            }
        } finally {
            lock2 = this.f10687c.f10689b;
            lock2.unlock();
        }
    }
}
