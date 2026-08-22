package androidx.collection;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$3 extends Lambda implements Function4 {
    public static final LruCacheKt$lruCache$3 INSTANCE = new LruCacheKt$lruCache$3();

    public LruCacheKt$lruCache$3() {
        super(4);
    }

    public final void d(boolean z, Object obj, Object obj2, Object obj3) {
        Intrinsics.e(obj, "<anonymous parameter 1>");
        Intrinsics.e(obj2, "<anonymous parameter 2>");
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Object j(Object obj, Object obj2, Object obj3, Object obj4) {
        d(((Boolean) obj).booleanValue(), obj2, obj3, obj4);
        return Unit.f18288a;
    }
}
