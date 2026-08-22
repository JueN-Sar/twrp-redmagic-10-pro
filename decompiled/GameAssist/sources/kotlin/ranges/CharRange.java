package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class CharRange extends CharProgression implements ClosedRange<Character>, OpenEndRange<Character> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18599k = new Companion(null);

    /* renamed from: l, reason: collision with root package name */
    private static final CharRange f18600l = new CharRange(1, 0);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CharRange(char c2, char c3) {
        super(c2, c3, 1);
    }

    @Override // kotlin.ranges.CharProgression
    public boolean equals(Object obj) {
        if (obj instanceof CharRange) {
            if (!isEmpty() || !((CharRange) obj).isEmpty()) {
                CharRange charRange = (CharRange) obj;
                if (g() != charRange.g() || h() != charRange.h()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.CharProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return h() + (g() * 31);
    }

    @Override // kotlin.ranges.CharProgression
    public boolean isEmpty() {
        return Intrinsics.f(g(), h()) > 0;
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public Character f() {
        if (h() != 65535) {
            return Character.valueOf((char) (h() + 1));
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Character d() {
        return Character.valueOf(h());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public Character b() {
        return Character.valueOf(g());
    }

    @Override // kotlin.ranges.CharProgression
    public String toString() {
        return g() + ".." + h();
    }
}
