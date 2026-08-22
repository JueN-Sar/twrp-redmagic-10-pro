package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ArraysKt___ArraysKt$withIndex$6 extends Lambda implements Function0<Iterator<? extends Float>> {
    final /* synthetic */ float[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$6(float[] fArr) {
        super(0);
        this.$this_withIndex = fArr;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Iterator a() {
        return ArrayIteratorsKt.e(this.$this_withIndex);
    }
}
