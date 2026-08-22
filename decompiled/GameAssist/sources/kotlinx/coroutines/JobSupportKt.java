package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class JobSupportKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f18911a = new Symbol("COMPLETING_ALREADY");

    /* renamed from: b, reason: collision with root package name */
    public static final Symbol f18912b = new Symbol("COMPLETING_WAITING_CHILDREN");

    /* renamed from: c, reason: collision with root package name */
    private static final Symbol f18913c = new Symbol("COMPLETING_RETRY");

    /* renamed from: d, reason: collision with root package name */
    private static final Symbol f18914d = new Symbol("TOO_LATE_TO_CANCEL");

    /* renamed from: e, reason: collision with root package name */
    private static final Symbol f18915e = new Symbol("SEALED");

    /* renamed from: f, reason: collision with root package name */
    private static final Empty f18916f = new Empty(false);

    /* renamed from: g, reason: collision with root package name */
    private static final Empty f18917g = new Empty(true);

    public static final Object g(Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    public static final Object h(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.f18893a) == null) ? obj : incomplete;
    }
}
