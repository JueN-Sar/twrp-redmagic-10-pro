package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;

@Metadata
/* loaded from: classes2.dex */
final class DelimitedRangesSequence implements Sequence<IntRange> {

    /* renamed from: a, reason: collision with root package name */
    private final CharSequence f18767a;

    /* renamed from: b, reason: collision with root package name */
    private final int f18768b;

    /* renamed from: c, reason: collision with root package name */
    private final int f18769c;

    /* renamed from: d, reason: collision with root package name */
    private final Function2 f18770d;

    public DelimitedRangesSequence(CharSequence input, int i2, int i3, Function2 getNextMatch) {
        Intrinsics.e(input, "input");
        Intrinsics.e(getNextMatch, "getNextMatch");
        this.f18767a = input;
        this.f18768b = i2;
        this.f18769c = i3;
        this.f18770d = getNextMatch;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new DelimitedRangesSequence$iterator$1(this);
    }
}
