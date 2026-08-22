package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata
/* loaded from: classes2.dex */
public final class MatchGroup {

    /* renamed from: a, reason: collision with root package name */
    private final String f18777a;

    /* renamed from: b, reason: collision with root package name */
    private final IntRange f18778b;

    public MatchGroup(String value, IntRange range) {
        Intrinsics.e(value, "value");
        Intrinsics.e(range, "range");
        this.f18777a = value;
        this.f18778b = range;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MatchGroup)) {
            return false;
        }
        MatchGroup matchGroup = (MatchGroup) obj;
        return Intrinsics.a(this.f18777a, matchGroup.f18777a) && Intrinsics.a(this.f18778b, matchGroup.f18778b);
    }

    public int hashCode() {
        return (this.f18777a.hashCode() * 31) + this.f18778b.hashCode();
    }

    public String toString() {
        return "MatchGroup(value=" + this.f18777a + ", range=" + this.f18778b + ')';
    }
}
