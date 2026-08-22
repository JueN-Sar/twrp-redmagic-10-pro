package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class ThreadLocalKey implements CoroutineContext.Key<ThreadLocalElement<?>> {

    /* renamed from: c, reason: collision with root package name */
    private final ThreadLocal f19414c;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ThreadLocalKey) && Intrinsics.a(this.f19414c, ((ThreadLocalKey) obj).f19414c);
    }

    public int hashCode() {
        return this.f19414c.hashCode();
    }

    public String toString() {
        return "ThreadLocalKey(threadLocal=" + this.f19414c + ')';
    }
}
