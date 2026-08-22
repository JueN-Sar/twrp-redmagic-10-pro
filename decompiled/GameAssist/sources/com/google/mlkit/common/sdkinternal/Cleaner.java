package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class Cleaner {

    /* renamed from: a, reason: collision with root package name */
    private final ReferenceQueue f15927a = new ReferenceQueue();

    /* renamed from: b, reason: collision with root package name */
    private final Set f15928b = Collections.synchronizedSet(new HashSet());

    public interface Cleanable {
        void clean();
    }

    private Cleaner() {
    }

    public static Cleaner a() {
        Cleaner cleaner = new Cleaner();
        cleaner.b(cleaner, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zza
            @Override // java.lang.Runnable
            public final void run() {
            }
        });
        final ReferenceQueue referenceQueue = cleaner.f15927a;
        final Set set = cleaner.f15928b;
        Thread thread = new Thread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzb
            @Override // java.lang.Runnable
            public final void run() {
                ReferenceQueue referenceQueue2 = referenceQueue;
                while (!set.isEmpty()) {
                    try {
                        ((zzd) referenceQueue2.remove()).clean();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return cleaner;
    }

    public Cleanable b(Object obj, Runnable runnable) {
        zzd zzdVar = new zzd(obj, this.f15927a, this.f15928b, runnable, null);
        this.f15928b.add(zzdVar);
        return zzdVar;
    }
}
