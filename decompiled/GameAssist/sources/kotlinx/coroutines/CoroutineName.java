package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineName extends AbstractCoroutineContextElement {

    /* renamed from: i, reason: collision with root package name */
    public static final Key f18855i = new Key(null);

    /* renamed from: h, reason: collision with root package name */
    private final String f18856h;

    @Metadata
    public static final class Key implements CoroutineContext.Key<CoroutineName> {
        private Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoroutineName) && Intrinsics.a(this.f18856h, ((CoroutineName) obj).f18856h);
    }

    public int hashCode() {
        return this.f18856h.hashCode();
    }

    public final String j0() {
        return this.f18856h;
    }

    public String toString() {
        return "CoroutineName(" + this.f18856h + ')';
    }
}
