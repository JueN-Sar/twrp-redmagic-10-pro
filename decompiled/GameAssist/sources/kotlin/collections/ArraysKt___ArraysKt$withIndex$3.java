package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ArraysKt___ArraysKt$withIndex$3 extends Lambda implements Function0<Iterator<? extends Short>> {
    final /* synthetic */ short[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$3(short[] sArr) {
        super(0);
        this.$this_withIndex = sArr;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Iterator a() {
        return ArrayIteratorsKt.h(this.$this_withIndex);
    }
}
