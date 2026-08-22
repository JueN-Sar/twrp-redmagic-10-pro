package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

@Metadata
/* loaded from: classes2.dex */
public final class FastServiceLoaderKt {
    static {
        Object b2;
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        Result.g(b2);
    }

    public static final boolean a() {
        return true;
    }
}
