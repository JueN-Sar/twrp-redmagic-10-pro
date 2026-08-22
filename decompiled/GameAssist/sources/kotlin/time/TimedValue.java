package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
@ExperimentalTime
/* loaded from: classes2.dex */
public final class TimedValue<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f18815a;

    /* renamed from: b, reason: collision with root package name */
    private final long f18816b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimedValue)) {
            return false;
        }
        TimedValue timedValue = (TimedValue) obj;
        return Intrinsics.a(this.f18815a, timedValue.f18815a) && Duration.n(this.f18816b, timedValue.f18816b);
    }

    public int hashCode() {
        Object obj = this.f18815a;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + Duration.B(this.f18816b);
    }

    public String toString() {
        return "TimedValue(value=" + this.f18815a + ", duration=" + ((Object) Duration.K(this.f18816b)) + ')';
    }
}
