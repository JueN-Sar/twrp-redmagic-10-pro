package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
final class zzr {

    /* renamed from: a, reason: collision with root package name */
    private final Object f13720a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private Queue f13721b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f13722c;

    zzr() {
    }

    public final void a(zzq zzqVar) {
        synchronized (this.f13720a) {
            try {
                if (this.f13721b == null) {
                    this.f13721b = new ArrayDeque();
                }
                this.f13721b.add(zzqVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(Task task) {
        zzq zzqVar;
        synchronized (this.f13720a) {
            if (this.f13721b != null && !this.f13722c) {
                this.f13722c = true;
                while (true) {
                    synchronized (this.f13720a) {
                        try {
                            zzqVar = (zzq) this.f13721b.poll();
                            if (zzqVar == null) {
                                this.f13722c = false;
                                return;
                            }
                        } finally {
                        }
                    }
                    zzqVar.c(task);
                }
            }
        }
    }
}
