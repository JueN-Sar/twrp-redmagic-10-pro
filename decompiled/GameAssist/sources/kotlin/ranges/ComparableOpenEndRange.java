package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.OpenEndRange;

@Metadata
/* loaded from: classes2.dex */
class ComparableOpenEndRange<T extends Comparable<? super T>> implements OpenEndRange<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Comparable f18605c;

    /* renamed from: h, reason: collision with root package name */
    private final Comparable f18606h;

    public boolean a() {
        return OpenEndRange.DefaultImpls.a(this);
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable b() {
        return this.f18605c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ComparableOpenEndRange) {
            if (!a() || !((ComparableOpenEndRange) obj).a()) {
                ComparableOpenEndRange comparableOpenEndRange = (ComparableOpenEndRange) obj;
                if (!Intrinsics.a(b(), comparableOpenEndRange.b()) || !Intrinsics.a(f(), comparableOpenEndRange.f())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.OpenEndRange
    public Comparable f() {
        return this.f18606h;
    }

    public int hashCode() {
        if (a()) {
            return -1;
        }
        return f().hashCode() + (b().hashCode() * 31);
    }

    public String toString() {
        return b() + "..<" + f();
    }
}
