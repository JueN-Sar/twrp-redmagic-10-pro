package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    public static final Companion f18306c = new Companion(null);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final boolean a(Set c2, Set other) {
            Intrinsics.e(c2, "c");
            Intrinsics.e(other, "other");
            if (c2.size() != other.size()) {
                return false;
            }
            return c2.containsAll(other);
        }

        public final int b(Collection c2) {
            Intrinsics.e(c2, "c");
            Iterator<E> it = c2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                E next = it.next();
                i2 += next != null ? next.hashCode() : 0;
            }
            return i2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    protected AbstractSet() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            return f18306c.a(this, (Set) obj);
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return f18306c.b(this);
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
