package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class CompletedWithCancellation {

    /* renamed from: a, reason: collision with root package name */
    public final Object f18846a;

    /* renamed from: b, reason: collision with root package name */
    public final Function1 f18847b;

    public CompletedWithCancellation(Object obj, Function1 function1) {
        this.f18846a = obj;
        this.f18847b = function1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedWithCancellation)) {
            return false;
        }
        CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
        return Intrinsics.a(this.f18846a, completedWithCancellation.f18846a) && Intrinsics.a(this.f18847b, completedWithCancellation.f18847b);
    }

    public int hashCode() {
        Object obj = this.f18846a;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.f18847b.hashCode();
    }

    public String toString() {
        return "CompletedWithCancellation(result=" + this.f18846a + ", onCancellation=" + this.f18847b + ')';
    }
}
