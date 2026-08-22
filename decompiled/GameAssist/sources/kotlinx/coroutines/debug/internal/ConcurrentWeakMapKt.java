package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class ConcurrentWeakMapKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f19053a = new Symbol("REHASH");

    /* renamed from: b, reason: collision with root package name */
    private static final Marked f19054b = new Marked(null);

    /* renamed from: c, reason: collision with root package name */
    private static final Marked f19055c = new Marked(Boolean.TRUE);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Marked d(Object obj) {
        return obj == null ? f19054b : Intrinsics.a(obj, Boolean.TRUE) ? f19055c : new Marked(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void e() {
        throw new UnsupportedOperationException("not implemented");
    }
}
