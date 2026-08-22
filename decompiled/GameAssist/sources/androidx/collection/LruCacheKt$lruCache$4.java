package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4 extends LruCache<Object, Object> {

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ Function2 f1311j;

    /* renamed from: k, reason: collision with root package name */
    final /* synthetic */ Function1 f1312k;

    /* renamed from: l, reason: collision with root package name */
    final /* synthetic */ Function4 f1313l;

    @Override // androidx.collection.LruCache
    protected Object a(Object key) {
        Intrinsics.e(key, "key");
        return this.f1312k.c(key);
    }

    @Override // androidx.collection.LruCache
    protected void b(boolean z, Object key, Object oldValue, Object obj) {
        Intrinsics.e(key, "key");
        Intrinsics.e(oldValue, "oldValue");
        this.f1313l.j(Boolean.valueOf(z), key, oldValue, obj);
    }

    @Override // androidx.collection.LruCache
    protected int g(Object key, Object value) {
        Intrinsics.e(key, "key");
        Intrinsics.e(value, "value");
        return ((Number) this.f1311j.y(key, value)).intValue();
    }
}
