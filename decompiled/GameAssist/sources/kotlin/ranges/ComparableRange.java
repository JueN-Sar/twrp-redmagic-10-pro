package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;

@Metadata
/* loaded from: classes2.dex */
class ComparableRange<T extends Comparable<? super T>> implements ClosedRange<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Comparable f18607c;

    /* renamed from: h, reason: collision with root package name */
    private final Comparable f18608h;

    public boolean a() {
        return ClosedRange.DefaultImpls.a(this);
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable b() {
        return this.f18607c;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable d() {
        return this.f18608h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ComparableRange) {
            if (!a() || !((ComparableRange) obj).a()) {
                ComparableRange comparableRange = (ComparableRange) obj;
                if (!Intrinsics.a(b(), comparableRange.b()) || !Intrinsics.a(d(), comparableRange.d())) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (a()) {
            return -1;
        }
        return d().hashCode() + (b().hashCode() * 31);
    }

    public String toString() {
        return b() + ".." + d();
    }
}
