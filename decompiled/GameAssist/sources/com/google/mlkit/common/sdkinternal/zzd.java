package com.google.mlkit.common.sdkinternal;

import com.google.mlkit.common.sdkinternal.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* loaded from: classes.dex */
final class zzd extends PhantomReference implements Cleaner.Cleanable {

    /* renamed from: c, reason: collision with root package name */
    private final Set f16021c;

    /* renamed from: h, reason: collision with root package name */
    private final Runnable f16022h;

    /* synthetic */ zzd(Object obj, ReferenceQueue referenceQueue, Set set, Runnable runnable, zzc zzcVar) {
        super(obj, referenceQueue);
        this.f16021c = set;
        this.f16022h = runnable;
    }

    @Override // com.google.mlkit.common.sdkinternal.Cleaner.Cleanable
    public final void clean() {
        if (this.f16021c.remove(this)) {
            clear();
            this.f16022h.run();
        }
    }
}
