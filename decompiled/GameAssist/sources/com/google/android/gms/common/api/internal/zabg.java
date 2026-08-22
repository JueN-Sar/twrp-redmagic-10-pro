package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
abstract class zabg {

    /* renamed from: a, reason: collision with root package name */
    private final zabf f10738a;

    protected zabg(zabf zabfVar) {
        this.f10738a = zabfVar;
    }

    protected abstract void a();

    public final void b(zabi zabiVar) {
        Lock lock;
        Lock lock2;
        zabf zabfVar;
        lock = zabiVar.f10740a;
        lock.lock();
        try {
            zabfVar = zabiVar.f10750k;
            if (zabfVar == this.f10738a) {
                a();
            }
        } finally {
            lock2 = zabiVar.f10740a;
            lock2.unlock();
        }
    }
}
