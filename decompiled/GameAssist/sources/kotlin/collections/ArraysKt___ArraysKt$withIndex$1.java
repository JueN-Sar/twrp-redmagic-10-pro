package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ArraysKt___ArraysKt$withIndex$1 extends Lambda implements Function0<Iterator<Object>> {
    final /* synthetic */ Object[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$1(Object[] objArr) {
        super(0);
        this.$this_withIndex = objArr;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Iterator a() {
        return ArrayIteratorKt.a(this.$this_withIndex);
    }
}
