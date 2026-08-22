package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class SelectKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f19488a = new Symbol("NOT_SELECTED");

    /* renamed from: b, reason: collision with root package name */
    private static final Object f19489b = new Symbol("ALREADY_SELECTED");

    /* renamed from: c, reason: collision with root package name */
    private static final Object f19490c = new Symbol("UNDECIDED");

    /* renamed from: d, reason: collision with root package name */
    private static final Object f19491d = new Symbol("RESUMED");

    /* renamed from: e, reason: collision with root package name */
    private static final SeqNumber f19492e = new SeqNumber();

    public static final Object d() {
        return f19489b;
    }

    public static final Object e() {
        return f19488a;
    }
}
