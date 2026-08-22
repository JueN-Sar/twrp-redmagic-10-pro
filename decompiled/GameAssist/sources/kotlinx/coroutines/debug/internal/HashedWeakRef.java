package kotlinx.coroutines.debug.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class HashedWeakRef<T> extends WeakReference<T> {

    /* renamed from: a, reason: collision with root package name */
    public final int f19083a;

    public HashedWeakRef(Object obj, ReferenceQueue referenceQueue) {
        super(obj, referenceQueue);
        this.f19083a = obj != null ? obj.hashCode() : 0;
    }
}
