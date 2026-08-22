package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuation;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ContextAwareKt$withContextAvailable$2$listener$1 implements OnContextAvailableListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ CancellableContinuation f88a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Function1 f89b;

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public void a(Context context) {
        Object b2;
        Intrinsics.e(context, "context");
        CancellableContinuation cancellableContinuation = this.f88a;
        Function1 function1 = this.f89b;
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(function1.c(context));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        cancellableContinuation.g(b2);
    }
}
