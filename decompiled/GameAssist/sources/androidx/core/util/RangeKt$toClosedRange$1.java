package androidx.core.util;

import android.util.Range;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;

@Metadata
/* loaded from: classes.dex */
public final class RangeKt$toClosedRange$1 implements ClosedRange<Comparable<Object>> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Range f3285c;

    @Override // kotlin.ranges.ClosedRange
    public Comparable b() {
        return this.f3285c.getLower();
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable d() {
        return this.f3285c.getUpper();
    }
}
