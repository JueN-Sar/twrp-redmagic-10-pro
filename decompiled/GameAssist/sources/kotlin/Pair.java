package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class Pair<A, B> implements Serializable {
    private final A first;
    private final B second;

    public Pair(A a2, B b2) {
        this.first = a2;
        this.second = b2;
    }

    public final Object a() {
        return this.first;
    }

    public final Object b() {
        return this.second;
    }

    public final Object c() {
        return this.first;
    }

    public final Object d() {
        return this.second;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return Intrinsics.a(this.first, pair.first) && Intrinsics.a(this.second, pair.second);
    }

    public int hashCode() {
        A a2 = this.first;
        int hashCode = (a2 == null ? 0 : a2.hashCode()) * 31;
        B b2 = this.second;
        return hashCode + (b2 != null ? b2.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.first + ", " + this.second + ')';
    }
}
