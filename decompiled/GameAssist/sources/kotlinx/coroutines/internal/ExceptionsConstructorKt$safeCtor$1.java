package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
public final class ExceptionsConstructorKt$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Function1<Throwable, Throwable> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ExceptionsConstructorKt$safeCtor$1(Function1<? super Throwable, ? extends Throwable> function1) {
        super(1);
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Throwable c(Throwable th) {
        Object b2;
        Function1<Throwable, Throwable> function1 = this.$block;
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b((Throwable) function1.c(th));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th2));
        }
        if (Result.f(b2)) {
            b2 = null;
        }
        return (Throwable) b2;
    }
}
