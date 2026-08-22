package com.google.android.gms.tasks;

import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class zzv extends LifecycleCallback {

    /* renamed from: c, reason: collision with root package name */
    private final List f13725c;

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        synchronized (this.f13725c) {
            try {
                Iterator it = this.f13725c.iterator();
                while (it.hasNext()) {
                    zzq zzqVar = (zzq) ((WeakReference) it.next()).get();
                    if (zzqVar != null) {
                        zzqVar.zzc();
                    }
                }
                this.f13725c.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
