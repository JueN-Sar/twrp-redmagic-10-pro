package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class LockFreeLinkedListKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f19365a = new Symbol("CONDITION_FALSE");

    /* renamed from: b, reason: collision with root package name */
    private static final Object f19366b = new Symbol("LIST_EMPTY");

    public static final Object a() {
        return f19365a;
    }

    public static final Object b() {
        return f19366b;
    }

    public static final LockFreeLinkedListNode c(Object obj) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed = obj instanceof Removed ? (Removed) obj : null;
        return (removed == null || (lockFreeLinkedListNode = removed.f19398a) == null) ? (LockFreeLinkedListNode) obj : lockFreeLinkedListNode;
    }
}
