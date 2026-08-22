package androidx.collection;

import kotlin.Metadata;

@Metadata
/* loaded from: classes.dex */
public final class LongLongPair {

    /* renamed from: a, reason: collision with root package name */
    private final long f1279a;

    /* renamed from: b, reason: collision with root package name */
    private final long f1280b;

    public boolean equals(Object obj) {
        if (!(obj instanceof LongLongPair)) {
            return false;
        }
        LongLongPair longLongPair = (LongLongPair) obj;
        return longLongPair.f1279a == this.f1279a && longLongPair.f1280b == this.f1280b;
    }

    public int hashCode() {
        return Long.hashCode(this.f1280b) ^ Long.hashCode(this.f1279a);
    }

    public String toString() {
        return '(' + this.f1279a + ", " + this.f1280b + ')';
    }
}
