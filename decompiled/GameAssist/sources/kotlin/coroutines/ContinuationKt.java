package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class ContinuationKt {
    public static final void a(Function2 function2, Object obj, Continuation completion) {
        Continuation b2;
        Continuation c2;
        Intrinsics.e(function2, "<this>");
        Intrinsics.e(completion, "completion");
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(function2, obj, completion);
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(b2);
        Result.Companion companion = Result.Companion;
        c2.g(Result.b(Unit.f18288a));
    }
}
