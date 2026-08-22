package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher n0();

    protected final String o0() {
        MainCoroutineDispatcher mainCoroutineDispatcher;
        MainCoroutineDispatcher c2 = Dispatchers.c();
        if (this == c2) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher = c2.n0();
        } catch (UnsupportedOperationException unused) {
            mainCoroutineDispatcher = null;
        }
        if (this == mainCoroutineDispatcher) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String o0 = o0();
        if (o0 != null) {
            return o0;
        }
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this);
    }
}
