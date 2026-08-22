package androidx.core.util;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4 extends LruCache<Object, Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Function2 f3267a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Function1 f3268b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function4 f3269c;

    @Override // android.util.LruCache
    protected Object create(Object obj) {
        return this.f3268b.c(obj);
    }

    @Override // android.util.LruCache
    protected void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        this.f3269c.j(Boolean.valueOf(z), obj, obj2, obj3);
    }

    @Override // android.util.LruCache
    protected int sizeOf(Object obj, Object obj2) {
        return ((Number) this.f3267a.y(obj, obj2)).intValue();
    }
}
