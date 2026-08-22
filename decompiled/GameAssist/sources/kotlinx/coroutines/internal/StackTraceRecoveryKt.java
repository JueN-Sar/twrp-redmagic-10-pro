package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;

@Metadata
/* loaded from: classes2.dex */
public final class StackTraceRecoveryKt {

    /* renamed from: a, reason: collision with root package name */
    private static final String f19403a;

    /* renamed from: b, reason: collision with root package name */
    private static final String f19404b;

    static {
        Object b2;
        Object b3;
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(BaseContinuationImpl.class.getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        if (Result.d(b2) != null) {
            b2 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        f19403a = (String) b2;
        try {
            b3 = Result.b(StackTraceRecoveryKt.class.getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            b3 = Result.b(ResultKt.a(th2));
        }
        if (Result.d(b3) != null) {
            b3 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        f19404b = (String) b3;
    }

    public static final Throwable a(Throwable th) {
        return th;
    }
}
