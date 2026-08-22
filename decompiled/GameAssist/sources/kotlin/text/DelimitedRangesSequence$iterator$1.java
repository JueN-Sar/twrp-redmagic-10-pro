package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
/* loaded from: classes2.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f18771c = -1;

    /* renamed from: h, reason: collision with root package name */
    private int f18772h;

    /* renamed from: i, reason: collision with root package name */
    private int f18773i;

    /* renamed from: j, reason: collision with root package name */
    private IntRange f18774j;

    /* renamed from: k, reason: collision with root package name */
    private int f18775k;

    /* renamed from: l, reason: collision with root package name */
    final /* synthetic */ DelimitedRangesSequence f18776l;

    DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        int i2;
        CharSequence charSequence;
        int e2;
        this.f18776l = delimitedRangesSequence;
        i2 = delimitedRangesSequence.f18768b;
        charSequence = delimitedRangesSequence.f18767a;
        e2 = RangesKt___RangesKt.e(i2, 0, charSequence.length());
        this.f18772h = e2;
        this.f18773i = e2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
    
        if (r0 < r4) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void b() {
        /*
            r6 = this;
            int r0 = r6.f18773i
            r1 = 0
            if (r0 >= 0) goto Lc
            r6.f18771c = r1
            r0 = 0
            r6.f18774j = r0
            goto L9e
        Lc:
            kotlin.text.DelimitedRangesSequence r0 = r6.f18776l
            int r0 = kotlin.text.DelimitedRangesSequence.c(r0)
            r2 = -1
            r3 = 1
            if (r0 <= 0) goto L23
            int r0 = r6.f18775k
            int r0 = r0 + r3
            r6.f18775k = r0
            kotlin.text.DelimitedRangesSequence r4 = r6.f18776l
            int r4 = kotlin.text.DelimitedRangesSequence.c(r4)
            if (r0 >= r4) goto L31
        L23:
            int r0 = r6.f18773i
            kotlin.text.DelimitedRangesSequence r4 = r6.f18776l
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = r4.length()
            if (r0 <= r4) goto L47
        L31:
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r6.f18772h
            kotlin.text.DelimitedRangesSequence r4 = r6.f18776l
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = kotlin.text.StringsKt__StringsKt.r(r4)
            r0.<init>(r1, r4)
            r6.f18774j = r0
            r6.f18773i = r2
            goto L9c
        L47:
            kotlin.text.DelimitedRangesSequence r0 = r6.f18776l
            kotlin.jvm.functions.Function2 r0 = kotlin.text.DelimitedRangesSequence.a(r0)
            kotlin.text.DelimitedRangesSequence r4 = r6.f18776l
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r5 = r6.f18773i
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.y(r4, r5)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L77
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r6.f18772h
            kotlin.text.DelimitedRangesSequence r4 = r6.f18776l
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = kotlin.text.StringsKt__StringsKt.r(r4)
            r0.<init>(r1, r4)
            r6.f18774j = r0
            r6.f18773i = r2
            goto L9c
        L77:
            java.lang.Object r2 = r0.a()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.b()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r4 = r6.f18772h
            kotlin.ranges.IntRange r4 = kotlin.ranges.RangesKt.h(r4, r2)
            r6.f18774j = r4
            int r2 = r2 + r0
            r6.f18772h = r2
            if (r0 != 0) goto L99
            r1 = r3
        L99:
            int r2 = r2 + r1
            r6.f18773i = r2
        L9c:
            r6.f18771c = r3
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.b():void");
    }

    @Override // java.util.Iterator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public IntRange next() {
        if (this.f18771c == -1) {
            b();
        }
        if (this.f18771c == 0) {
            throw new NoSuchElementException();
        }
        IntRange intRange = this.f18774j;
        Intrinsics.c(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.f18774j = null;
        this.f18771c = -1;
        return intRange;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f18771c == -1) {
            b();
        }
        return this.f18771c == 1;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
